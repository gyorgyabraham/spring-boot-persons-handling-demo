package com.github.gyorgyabraham.persons.demo.repository;

import com.github.gyorgyabraham.persons.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
