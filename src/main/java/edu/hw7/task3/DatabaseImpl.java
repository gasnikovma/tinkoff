package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DatabaseImpl implements PersonDatabase {
    public final List<Person> personList = new ArrayList<>();

    @Override
    public synchronized void add(Person person) {
        personList.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        personList.removeIf(p -> p.id() == id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return personList.stream().filter(p -> !isInvalid(p) && p.name().equals(name)).toList();
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return personList.stream().filter(p -> !isInvalid(p) && p.address().equals(address)).toList();
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return personList.stream().filter(p -> !isInvalid(p) && p.phoneNumber().equals(phone)).toList();
    }

    boolean isInvalid(Person person) {
        return Stream.of(person.address(), person.name(), person.phoneNumber()).anyMatch(Objects::isNull);
    }
}
