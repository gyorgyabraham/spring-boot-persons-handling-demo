package com.github.gyorgyabraham.persons.demo.repository;

import com.github.gyorgyabraham.persons.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
