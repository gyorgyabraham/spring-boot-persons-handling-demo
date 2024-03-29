package com.github.gyorgyabraham.persons.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country", nullable = false)
    @NotNull
    private String country;

    @Column(name = "city", nullable = false)
    @NotNull
    private String city;

    @Column(name = "street", nullable = false)
    @NotNull
    private String street;

    @Column(name = "houseNr", nullable = false)
    @NotNull
    private String houseNr;

    @Column(name = "zipcode", nullable = false)
    @NotNull
    private String zipcode;

    @OneToOne
    @JsonIgnore
    private Person owningPerson;

    public Address() {

    }

    public Address(String country, String city, String street, String houseNr, String zipcode) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(houseNr, address.houseNr) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, houseNr, zipcode);
    }
}
