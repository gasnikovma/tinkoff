package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Откуда произошел вызов функции")
    void CallInfo_ShouldReturnInformation() {
        CallingInfo callingInfo = Utils.callingInfo();
        System.out.println(callingInfo.className());
        System.out.println(callingInfo.methodName());
        assertThat(callingInfo.className().equals("edu.hw2.Task4Test") &&
            callingInfo.methodName().equals("CallInfo_ShouldReturnInformation")).isEqualTo(true);
    }

}
