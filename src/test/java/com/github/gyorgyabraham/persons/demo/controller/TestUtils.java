package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.model.Person;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static final List<Person> DEFAULT_TEST_PERSONS = new ArrayList<>();

    static {
        Person peter = new Person("Peter", "Moore");
        DEFAULT_TEST_PERSONS.add(peter);

        Person jacob = new Person("Jacob", "Smith");
        DEFAULT_TEST_PERSONS.add(jacob);
    }
}
