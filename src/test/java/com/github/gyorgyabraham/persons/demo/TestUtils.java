package com.github.gyorgyabraham.persons.demo;

import com.github.gyorgyabraham.persons.demo.model.Address;
import com.github.gyorgyabraham.persons.demo.model.EmailContact;
import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.model.PhoneContact;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static final List<Person> DEFAULT_TEST_PERSONS = new ArrayList<>();

    static {
        Person peter = new Person("Peter", "Moore");
        DEFAULT_TEST_PERSONS.add(peter);

        Address peterPermanentAddress = new Address("Hungary", "Budapest", "Puli setany", "1", "1213");
        Address peterTemporaryAddress = new Address("Hungary", "Budapest", "Cirmos setany", "3", "1213");

        peter.setPermanentAddress(peterPermanentAddress);
        peter.setTemporaryAddress(peterTemporaryAddress);

        EmailContact peterEmailContact = new EmailContact("peter@puli.com");
        PhoneContact peterPhoneContact = new PhoneContact("06405004321");

        peter.getContacts().add(peterEmailContact);
        peter.getContacts().add(peterPhoneContact);

        Person jacob = new Person("Jacob", "Smith");
        DEFAULT_TEST_PERSONS.add(jacob);

        Person jakab = new Person("Gipsz", "Jakab");
        DEFAULT_TEST_PERSONS.add(jakab);
    }
}
