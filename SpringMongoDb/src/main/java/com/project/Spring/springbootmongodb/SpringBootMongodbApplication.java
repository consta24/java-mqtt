package com.project.Spring.springbootmongodb;

import com.project.Spring.springbootmongodb.Models.Person;
import com.project.Spring.springbootmongodb.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringBootMongodbApplication implements CommandLineRunner {

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) {
        personService.addPerson(new Person("0", "Consta", "21", "Acasa"));
        personService.addPerson(new Person("1", "Mihai", "20", "Afara"));
        for (Person person : personService.getAllPersons()) {
            System.out.println(person);
        }
        personService.deletePerson("1");
        personService.updatePerson(new Person("0", "Consta", "21", "Afara"));
        for (Person person : personService.getAllPersons()) {
            System.out.println(person);
        }
        System.out.println(personService.getPersonByName("Consta"));
    }
}
