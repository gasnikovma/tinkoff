package edu.hw8.task1;

import java.util.Map;

public class Storage {

    private Storage() {

    }

    private static Map<String, String> dictionary = Map.of(
        "личности",
        "Не переходи на личности там, где их нет.\n",
        "оскорбления",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.\n",
        "глупый",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.\n",
        "интеллект",
        "Чем ниже интеллект, тем громче оскорбления.\n"
    );

    public static String getReply(String request) {
        if (dictionary.containsKey(request)) {
            return dictionary.get(request);
        }
        return "Кто как обзывется, тот сам так назывется!\n";
    }

}
