package com.example.addressbook.repository;

import com.example.addressbook.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
//    @Query(value = "SELECT DISTINCT c FROM Person c LEFT JOIN FETCH c.address ")
//    Collection<Person> fetchAll();
//
//    @Query(value = "SELECT DISTINCT c FROM Person c LEFT JOIN FETCH c.address")
//    Person fetchByID(@Param("id") Long id);
}
