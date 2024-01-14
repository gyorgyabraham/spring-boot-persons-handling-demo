package com.github.gyorgyabraham.persons.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contactType", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailContact.class, name = "emailContact"),
        @JsonSubTypes.Type(value = PhoneContact.class, name = "phoneContact")
})
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person owningPerson;

    public Person getOwningPerson() {
        return owningPerson;
    }

    public void setOwningPerson(Person owningPerson) {
        this.owningPerson = owningPerson;
    }
}
