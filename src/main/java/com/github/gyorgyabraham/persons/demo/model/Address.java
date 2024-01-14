package com.github.gyorgyabraham.persons.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country", nullable = false)
    @Min(2)
    @Max(30)
    @NotNull
    private String country;

    @Column(name = "city", nullable = false)
    @Min(2)
    @Max(30)
    @NotNull
    private String city;

    @Column(name = "street", nullable = false)
    @Min(2)
    @Max(30)
    @NotNull
    private String street;

    @Column(name = "houseNr", nullable = false)
    @Min(2)
    @Max(30)
    @NotNull
    private String houseNr;

    @Column(name = "zipcode", nullable = false)
    @Min(2)
    @Max(30)
    @NotNull
    private String zipcode;

    @Null
    @OneToOne
    private Person owningPerson;

    public Address() {

    }

    public Address(long id, String country, String city, String street, String houseNr, String zipcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNr = houseNr;
        this.zipcode = zipcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Person getOwningPerson() {
        return owningPerson;
    }

    public void setOwningPerson(Person owningPerson) {
        this.owningPerson = owningPerson;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNr='" + houseNr + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
