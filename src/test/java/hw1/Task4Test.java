package hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @ParameterizedTest
    @CsvSource({
        "123456,  214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde"
    })
    void fixString_ShouldReturnValue(String input, String res) {
        assertThat(Task4.fixString(input)).isEqualTo(res);
    }

}
