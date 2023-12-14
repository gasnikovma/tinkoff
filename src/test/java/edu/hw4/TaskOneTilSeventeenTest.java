package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskOneTilSeventeenTest {
    private static final int SPIDER_AGE = 14;
    private static final int SPIDER_HEIGHT = 5;
    private static final int SPIDER_WEIGHT = 3;
    private static final int DOG_AGE = 7;
    private static final int FIRST_K = 3;

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
    private static final int NUMBER_CATS = 2;
    private static final int NUMBER = 1;

    //1
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task1")
    void sortByHeightTest(List<Animal> initial) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.sortByHeight(animals);
        for (int i = 0; i < stream.size() - 1; i++) {
            assertThat(stream.get(i).height()).isLessThanOrEqualTo(stream.get(i + 1).height());
        }
    }

    //2
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task2")
    void sortByWeightTest(List<Animal> initial) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.sortByWeight(animals, FIRST_K);
        assertThat(stream.size()).isEqualTo(FIRST_K);
        for (int i = 0; i < stream.size() - 1; i++) {
            assertThat(stream.get(i).weight()).isGreaterThanOrEqualTo(stream.get(i + 1).weight());
        }
    }

    //3
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task3")
    void getNumberOfEachTypeOfAnimalsTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Map<Animal.Type, Integer> stream = AnimalStreamRequests.getNumberOfEachTypeOfAnimals(animals);
        assertThat(stream.get(Animal.Type.CAT)).isEqualTo(NUMBER_CATS);
        assertThat(stream.get(Animal.Type.DOG)).isEqualTo(NUMBER_CATS);
        assertThat(stream.get(Animal.Type.FISH)).isEqualTo(NUMBER);
        assertThat(stream.get(Animal.Type.BIRD)).isEqualTo(NUMBER);
        assertThat(stream.get(Animal.Type.SPIDER)).isEqualTo(NUMBER);

    }

    //4
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task4")
    void getAnimalWithLongestNameTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Animal stream = AnimalStreamRequests.getAnimalWithLongestName(animals);
        assertThat(stream.name()).isEqualTo("Рыба в поисках немо");

    }

    //5
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task5")
    void getMostPopularSexTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Animal.Sex stream = AnimalStreamRequests.getMostPopularSex(animals);
        assertThat(stream).isEqualTo(Animal.Sex.F);

    }

    //6
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task6")
    void getHeaviestOfAnyTypeTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Map<Animal.Type, Animal> stream = AnimalStreamRequests.getHeaviestOfAnyType(animals);
        assertThat(stream.get(Animal.Type.CAT).name()).isEqualTo("Кот2");
        assertThat(stream.get(Animal.Type.DOG).name()).isEqualTo("Собака");
        assertThat(stream.get(Animal.Type.FISH).name()).isEqualTo("Рыба в поисках немо");
        assertThat(stream.get(Animal.Type.BIRD).name()).isEqualTo("Птица");
        assertThat(stream.get(Animal.Type.SPIDER).name()).isEqualTo("Паук");

    }

    //7
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task7")
    void getKOldestAnimalTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Animal stream = AnimalStreamRequests.getKOldestAnimal(animals, NUMBER_CATS);
        assertThat(stream.name()).isEqualTo("Паук");

    }

    //8
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task8")
    void getHeaviestAmongLessThanKcmTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Optional<Animal> stream = AnimalStreamRequests.getHeaviestAmongLessThanKcm(animals, NUMBER_CATS + 6);
        assertThat(stream.get().name()).isEqualTo("Кот2");

    }

    //9
    @ParameterizedTest @MethodSource("providerAnimals") @DisplayName("task9") void sumOfPawsTest(List<Animal> initial) {
        List<Animal> animals = initial;
        Integer stream = AnimalStreamRequests.sumOfPaws(animals);
        assertThat(stream).isEqualTo(26);
    }

    //10
    @ParameterizedTest
    @MethodSource("providerAnimals")
    @DisplayName("task10")
    void getAnimalsWithAgeNotMatchPawsTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.getAnimalsWithAgeNotMatchPaws(animals);
        List<Animal> expected = getAnimalsTestTen(animals);
        for (int i = 0; i < expected.size(); ++i) {
            assertThat(stream.get(i)).isEqualTo(expected.get(i));
        }
    }

    //11
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task11") void getAnimalsThatNotBiteAndHeightIsHigherThanHundredTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.getAnimalsThatNotBiteAndHeightIsHigherThanHundred(animals);
        List<Animal> expected = getAnimalsTestEleven(animals);
        for (int i = 0; i < expected.size(); ++i) {
            assertThat(stream.get(i)).isEqualTo(expected.get(i));
        }

    }

    //12
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task12") void getNumberOfAnimalsWhoseHeightExceedsWeightTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        int stream = AnimalStreamRequests.getNumberOfAnimalsWhoseHeightExceedsWeight(animals);
        assertThat(stream).isEqualTo(1);

    }

    //13
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task13") void getAnimalsWhoseNameConsistsOfMoreThanTwoWordTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.getAnimalsWhoseNameConsistsOfMoreThanTwoWords(animals);
        assertThat(stream.size()).isEqualTo(1);
        assertThat(stream.get(0).name()).isEqualTo("Рыба в поисках немо");

    }

    //14
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task14") void isDogWithHeightMoreThanKcmTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        Boolean stream = AnimalStreamRequests.isDogWithHeightMoreThanKcm(animals, 110);
        assertTrue(stream);

    }

    //15
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task15") void findWeightOfAnimalsWithAgeFromKToLTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        Integer stream = AnimalStreamRequests.findWeightOfAnimalsWithAgeFromKToL(animals, 5, 15);
        Integer expected = getAnimalsTestFifteen(animals, 5, 15);
        assertThat(stream).isEqualTo(expected);

    }

    //16
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task16") void getAnimalsThatSortedByTypeThenBySexThenByNameTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        List<Animal> stream = AnimalStreamRequests.getAnimalsThatSortedByTypeThenBySexThenByName(animals);
        List<Animal> expected = getAnimalsTestSixteen(animals);
        for (int i = 0; i < stream.size(); i++) {
            assertThat(stream.get(i)).isEqualTo(expected.get(i));
        }

    }

    //17
    @ParameterizedTest
    @MethodSource("providerAnimals") @DisplayName("task17") void spidersBiteMoreThanDogsTest(
        List<Animal> initial
    ) {
        List<Animal> animals = initial;
        boolean stream = AnimalStreamRequests.spidersBiteMoreThanDogs(animals);
        boolean expected = getAnimalsTestSeventeen(animals);
        assertThat(stream).isEqualTo(expected);

    }

    private List<Animal> getAnimalsTestTen(List<Animal> animals) {
        List<Animal> result = new ArrayList<>();
        for (var animal : animals) {
            if (animal.age() != animal.paws()) {
                result.add(animal);
            }
        }
        return result;
    }

    private List<Animal> getAnimalsTestEleven(List<Animal> animals) {
        List<Animal> result = new ArrayList<>();
        for (var animal : animals) {
            if (animal.bites() && animal.height() > 100) {
                result.add(animal);
            }
        }
        return result;
    }

    private int getAnimalsTestFifteen(List<Animal> animals, int k, int l) {
        int total = 0;
        List<Animal> result = new ArrayList<>();
        for (var animal : animals) {
            if (animal.age() > k && animal.age() < l) {
                total += animal.weight();
            }
        }
        return total;
    }

    private boolean getAnimalsTestSeventeen(List<Animal> animals) {
        double count_ofDogs = 0;
        double count_ofSpiders = 0;
        double bitDogs = 0;
        double bitSpiders = 0;
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).type() == Animal.Type.DOG) {
                count_ofDogs += 1;
                if (animals.get(i).bites()) {
                    bitDogs += 1;
                }
            } else if (animals.get(i).type() == Animal.Type.SPIDER) {
                count_ofSpiders += 1;
                if (animals.get(i).bites()) {
                    bitSpiders += 1;
                }
            }
        }
        return (bitSpiders / count_ofSpiders) > (bitDogs / count_ofDogs);
    }

    private List<Animal> getAnimalsTestSixteen(List<Animal> animals) {
        List<Animal> result = new ArrayList<>(animals);
        result.sort(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name));

        return result;
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
            new Animal("Рыба в поисках немо", Animal.Type.FISH, Animal.Sex.F, FISH_AGE, FISH_HEIGHT, FISH_WEIGHT, true),
            new Animal("Кот2", Animal.Type.CAT, Animal.Sex.F, CAT_TWO_AGE, CAT_TWO_HEIGHT, CAT_TWO_WEIGHT, false),
            new Animal("Собака2", Animal.Type.DOG, Animal.Sex.F, BIRD_AGE, CAT_HEIGHT, FISH_WEIGHT, true)
        )));

    }
}
