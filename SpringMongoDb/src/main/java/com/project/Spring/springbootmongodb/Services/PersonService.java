package com.project.Spring.springbootmongodb.Services;

import com.project.Spring.springbootmongodb.Models.Person;
import com.project.Spring.springbootmongodb.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person) {
        if (personRepository.findById(person.getId()).isPresent()) {
            personRepository.save(person);
        } else {
            personRepository.insert(person);
        }
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    public void updatePerson(Person person) {
        Person savedPerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find person by ID %s", person.getId())));

        savedPerson.setName(person.getName());
        savedPerson.setAge(person.getAge());
        savedPerson.setAddress(person.getAddress());

        personRepository.save(savedPerson);
    }

    public Person getPersonByName(String name) {
        return personRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find person by name: %s", name)));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
