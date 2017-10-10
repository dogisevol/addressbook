package com.example.addressbook.repository;

import com.example.addressbook.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
