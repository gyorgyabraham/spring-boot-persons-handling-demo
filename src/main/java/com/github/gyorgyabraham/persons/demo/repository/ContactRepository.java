package com.github.gyorgyabraham.persons.demo.repository;

import com.github.gyorgyabraham.persons.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
