package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public class ReadWriteLockImpl implements PersonDatabase {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final List<Person> personList = new ArrayList<>();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            personList.add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            personList.removeIf(p -> p.id() == id);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return personList.stream()
                .filter(p -> !isInvalid(p) && p.name().equals(name)).toList();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    boolean isInvalid(Person person) {
        return Stream.of(person.address(), person.name(), person.phoneNumber()).anyMatch(Objects::isNull);
    }

    @Override public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return personList.stream()
                .filter(p -> !isInvalid(p) && p.address().equals(address)).toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override public List<Person> findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return personList.stream()
                .filter(p -> !isInvalid(p) && p.phoneNumber().equals(phone)).toList();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
