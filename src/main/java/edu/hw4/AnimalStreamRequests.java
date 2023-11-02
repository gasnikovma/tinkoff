package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalStreamRequests {
    private AnimalStreamRequests() {

    }

    //1
    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).collect(Collectors.toList());

    }

    //2
    public static List<Animal> sortByWeight(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight).reversed()).limit(k)
            .collect(Collectors.toList());
    }

    //3
    public static Map<Animal.Type, Integer> getNumberOfEachTypeOfAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
        ));
    }

    //4
    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(o -> o.name().length())).get();
    }

    //5
    public static Animal.Sex getMostPopularSex(List<Animal> animals) {
        Map<Animal.Sex, Long> groupbySex =
            animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return groupbySex.get(Animal.Sex.F) >= groupbySex.get(Animal.Sex.M) ? Animal.Sex.F : Animal.Sex.M;
    }

    //6
    public static Map<Animal.Type, Animal> getHeaviestOfAnyType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))))
            .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().orElse(null)));
    }

    //7
    public static Animal getKOldestAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::age).reversed()).skip(k - 1).findFirst()
            .orElseThrow();
    }

    //8
    public static Optional<Animal> getHeaviestAmongLessThanKcm(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }

    //9
    public static Integer sumOfPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    //10
    public static List<Animal> getAnimalsWithAgeNotMatchPaws(List<Animal> animals) {
        return animals.stream().filter(a -> a.age() != a.paws()).collect(Collectors.toList());
    }

    //11
    @SuppressWarnings("checkstyle:magicnumber")
    public static List<Animal> getAnimalsThatNotBiteAndHeightIsHigherThanHundred(List<Animal> animals) {
        return animals.stream().filter(a -> a.bites() && a.height() > 100).collect(Collectors.toList());
    }

    //12
    public static Integer getNumberOfAnimalsWhoseHeightExceedsWeight(List<Animal> animals) {
        return (int) animals.stream().filter(a -> a.weight() > a.height()).count();
    }

    //13
    public static List<Animal> getAnimalsWhoseNameConsistsOfMoreThanTwoWords(List<Animal> animals) {
        return animals.stream().filter(a -> a.name().split(" ").length > 2).collect(Collectors.toList());
    }

    //14
    public static Boolean isDogWithHeightMoreThanKcm(List<Animal> animals, int k) {
        return animals.stream().anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    //15
    public static Integer findWeightOfAnimalsWithAgeFromKToL(List<Animal> animals, int k, int l) {
        return animals.stream().filter(a -> a.age() > k && a.age() < l).mapToInt(Animal::weight).sum();
    }

    //16
    public static List<Animal> getAnimalsThatSortedByTypeThenBySexThenByName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    //17
    public static boolean spidersBiteMoreThanDogs(List<Animal> animals) {
        double a = animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER).map(Animal::bites)
            .mapToInt(value -> value ? 1 : 0).average().orElse(-1d);
        double b = animals.stream().filter(animal -> animal.type() == Animal.Type.DOG).map(Animal::bites)
            .mapToInt(value -> value ? 1 : 0).average().orElse(-1d);
        if (a == -1d || b == -1d) {
            return false;
        } else {
            return a > b;
        }
    }

    //18
    public static Animal getHeaviestFish(List<List<Animal>> animals) {
        return animals.stream().flatMap(List::stream).filter(a -> a.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight)).get();
    }

    //19
    public static Map<String, Set<ValidationError>> getErrors(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, ValidationError::getErrors));

    }

    //20
    public static Map<String, String> getErrorsWithString(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, a -> ValidationError.getErrors(a).toString()));
    }
}
