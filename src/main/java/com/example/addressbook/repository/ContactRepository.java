package com.example.addressbook.repository;

import com.example.addressbook.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Person, Long> {
//    @Query(value = "SELECT DISTINCT c FROM Person c LEFT JOIN FETCH c.address")
//    Collection<Person> fetchAll();
//
//    @Query(value = "SELECT DISTINCT c FROM Person c LEFT JOIN FETCH c.address WHERE c.id = :id")
//    Person fetchByID(@Param("id") Long id);
}
