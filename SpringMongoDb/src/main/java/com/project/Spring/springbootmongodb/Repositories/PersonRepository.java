package com.project.Spring.springbootmongodb.Repositories;

import com.project.Spring.springbootmongodb.Models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    @Query("{'name': ?0}")
    Optional<Person> findByName(String name);
}
