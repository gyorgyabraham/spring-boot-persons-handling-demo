package com.github.gyorgyabraham.persons.demo.model;

public enum ContactType {
    PHONE(Values.PHONE),
    EMAIL(Values.EMAIL);

    private String value;

    ContactType(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String PHONE = "PHONE";
        public static final String EMAIL = "EMAIL";
    }
}
