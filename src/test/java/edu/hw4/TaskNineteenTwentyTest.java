package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskNineteenTwentyTest {
    private static final int SPIDER_AGE = 14;
    private static final int SPIDER_HEIGHT = 0;
    private static final int SPIDER_WEIGHT = -3;
    private static final int DOG_AGE = 7;
    private static final int DOG_HEIGHT = 100;
    private static final int DOG_WEIGHT = 40;
    private static final int BIRD_AGE = -3;
    private static final int BIRD_HEIGHT = 17;
    private static final int BIRD_WEIGHT = 10;
    private static final int CAT_AGE = 4;
    private static final int CAT_HEIGHT = -130;
    private static final int CAT_WEIGHT = 10;
    private static final int FISH_AGE = 13;
    private static final int FISH_HEIGHT = -8;
    private static final int FISH_WEIGHT = -4;
    private static final int CAT_TWO_AGE = 18;
    private static final int CAT_TWO_HEIGHT = 0;
    private static final int CAT_TWO_WEIGHT = 15;

    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task19")
    void getErrorsTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Map<String, Set<ValidationError>> stream = AnimalStreamRequests.getErrors(animals);
        Map<String, Set<ValidationError>> expected = getAnimalsTestNineteen(animals);
        assertTrue(equalMaps(expected, stream));

    }
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task20")
    void getErrorsWithStringTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Map<String, String> stream = AnimalStreamRequests.getErrorsWithString(animals);
        Map<String, String> expected = getAnimalsTestTwenty(animals);
        for(Map.Entry<String,String>s : stream.entrySet()){
            System.out.println(s.getKey() + " "+ s.getValue());
        }
        System.out.println();
        for(Map.Entry<String,String>s : expected.entrySet()){
            System.out.println(s.getKey() + " "+ s.getValue());
        }
        assertTrue(equalMaps(expected, stream));

    }
    <K,V>boolean equalMaps(Map<K,V>m1, Map<K,V>m2) {
        if (m1.size() != m2.size())
            return false;
        for (K key: m1.keySet())
            if (!m1.get(key).equals(m2.get(key)))
                return false;
        return true;
    }

    private Map<String, Set<ValidationError>> getAnimalsTestNineteen(List<Animal> animals) {
        Map<String, Set<ValidationError>> a = new HashMap<>();
        for (int i = 0; i < animals.size(); i++) {
            a.put(animals.get(i).name(), ValidationError.getErrors(animals.get(i)));
        }
        return a;
    }
    private Map<String, String> getAnimalsTestTwenty(List<Animal> animals) {
        Map<String, String> a = new HashMap<>();
        for (int i = 0; i < animals.size(); i++) {
            a.put(animals.get(i).name(), ValidationError.getErrors(animals.get(i)).toString());
        }
        return a;
    }

    private static Stream<Arguments> providerAnimals() {

        return Stream.of(Arguments.of(Arrays.asList(
            new Animal(
                "Паук",
                Animal.Type.SPIDER,
                Animal.Sex.M,
                SPIDER_AGE,
                SPIDER_HEIGHT,
                SPIDER_WEIGHT,
                true
            ),
            new Animal("Собака", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false),
            new Animal("Птица", Animal.Type.BIRD, Animal.Sex.M, BIRD_AGE, BIRD_HEIGHT, BIRD_WEIGHT, false),
            new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, false),
            new Animal("Рыба", Animal.Type.FISH, Animal.Sex.F, FISH_AGE, FISH_HEIGHT, FISH_WEIGHT, true),
            new Animal("Кот2", Animal.Type.CAT, Animal.Sex.F, CAT_TWO_AGE, CAT_TWO_HEIGHT, CAT_TWO_WEIGHT, false),
            new Animal("Собака2", Animal.Type.DOG, Animal.Sex.F, BIRD_AGE, CAT_HEIGHT, FISH_WEIGHT, true)
        )));

    }
}
