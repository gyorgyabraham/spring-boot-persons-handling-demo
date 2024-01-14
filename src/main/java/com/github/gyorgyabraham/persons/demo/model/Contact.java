package com.github.gyorgyabraham.persons.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contactType", discriminatorType = DiscriminatorType.STRING)
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Null
    @ManyToOne
    @JoinColumn(name = "personId")
    private Person owningPerson;

    public Person getOwningPerson() {
        return owningPerson;
    }

    public void setOwningPerson(Person owningPerson) {
        this.owningPerson = owningPerson;
    }
}
