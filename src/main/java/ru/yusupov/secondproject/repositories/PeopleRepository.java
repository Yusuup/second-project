package ru.yusupov.secondproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yusupov.secondproject.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String fullName);
}
