package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskEightteenTest {
    private static final int SPIDER_AGE = 14;
    private static final int SPIDER_HEIGHT = 5;
    private static final int SPIDER_WEIGHT = 3;
    private static final int DOG_AGE = 7;
    private static final int DOG_HEIGHT = 100;
    private static final int DOG_WEIGHT = 40;
    private static final int BIRD_AGE = 3;
    private static final int BIRD_HEIGHT = 17;
    private static final int BIRD_WEIGHT = 10;
    private static final int CAT_AGE = 4;
    private static final int CAT_HEIGHT = 130;
    private static final int CAT_WEIGHT = 10;
    private static final int FISH_AGE = 13;
    private static final int FISH_HEIGHT = 8;
    private static final int FISH_WEIGHT = 4;
    private static final int CAT_TWO_AGE = 18;
    private static final int CAT_TWO_HEIGHT = 6;
    private static final int CAT_TWO_WEIGHT = 15;

    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task18") void getHeaviestFishTest(
        List<List<Animal>> initial
    ) {
        List<List<Animal>> animals = initial;
        Animal stream = AnimalStreamRequests.getHeaviestFish(animals);
        Animal expected = getAnimalsTestEightteen(animals);
        System.out.println(stream.name());
        System.out.println(expected.name());
        assertThat(stream).isEqualTo(expected);

    }

    private Animal getAnimalsTestEightteen(List<List<Animal>> animals) {
        Animal heaviest = new Animal("", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, false);
        for (List<Animal> animal : animals) {
            for (int i = 0; i < animal.size(); i++) {
                if (animal.get(i).type() == Animal.Type.FISH && animal.get(i).weight() > heaviest.weight()) {
                    heaviest = animal.get(i);
                }
            }

        }
        return heaviest;
    }

    private static Stream<Arguments> providerAnimals() {

        return Stream.of(Arguments.of(Arrays.asList(
            Arrays.asList(
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
                new Animal(
                    "Рыба в поисках немо",
                    Animal.Type.FISH,
                    Animal.Sex.F,
                    FISH_AGE,
                    FISH_HEIGHT,
                    FISH_WEIGHT,
                    true
                ),
                new Animal("Кот2", Animal.Type.CAT, Animal.Sex.F, CAT_TWO_AGE, CAT_TWO_HEIGHT, CAT_TWO_WEIGHT, false),
                new Animal("Собака2", Animal.Type.DOG, Animal.Sex.F, BIRD_AGE, CAT_HEIGHT, FISH_WEIGHT, true)
            ),
            Arrays.asList(
                new Animal(
                    "Рыба2",
                    Animal.Type.SPIDER,
                    Animal.Sex.M,
                    SPIDER_AGE,
                    SPIDER_HEIGHT,
                    SPIDER_WEIGHT,
                    true
                ),
                new Animal("Собака3", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false),
                new Animal("Птица2", Animal.Type.BIRD, Animal.Sex.M, BIRD_AGE, BIRD_HEIGHT, BIRD_WEIGHT, false),
                new Animal("Кот4", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, false),
                new Animal("Рыба4", Animal.Type.FISH, Animal.Sex.F, FISH_AGE, FISH_HEIGHT, FISH_WEIGHT, true),
                new Animal("Кот3", Animal.Type.CAT, Animal.Sex.F, CAT_TWO_AGE, CAT_TWO_HEIGHT, CAT_TWO_WEIGHT, false),
                new Animal("Рыба Немо", Animal.Type.FISH, Animal.Sex.F, BIRD_AGE, CAT_HEIGHT, DOG_WEIGHT, true)
            )
        )));

    }
}
