package com.example.addressbook.repository;

import com.example.addressbook.domain.Address;
import com.example.addressbook.domain.Communication;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationRepository extends CrudRepository<Communication, Long> {
}
