package com.example.addressbook.repository;

import com.example.addressbook.domain.Communication;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Communication, Long> {
//    @Query(value = "SELECT DISTINCT c FROM BusinessContact c LEFT JOIN FETCH c.address")
//    Collection<BusinessContact> fetchAll();
//
//    @Query(value = "SELECT DISTINCT c FROM BusinessContact c LEFT JOIN FETCH c.address WHERE c.id = :id")
//    BusinessContact fetchByID(@Param("id") Long id);
}
