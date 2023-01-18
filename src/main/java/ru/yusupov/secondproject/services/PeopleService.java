package ru.yusupov.secondproject.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yusupov.secondproject.models.Book;
import ru.yusupov.secondproject.models.Person;
import ru.yusupov.secondproject.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        return peopleRepository.findById(id).get();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person upPerson) {
        upPerson.setId(id);
        peopleRepository.save(upPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> bookList(int id) {
        List<Book> bookList = peopleRepository.findById(id).get().getBookList();
        bookList.forEach(book -> book.setOutOfDate(book.getTakenFrom().getTime() + 864000000 < new Date().getTime()));
        return bookList;
    }

    public Optional<Person> getPerson(String fullName) {
        return Optional.ofNullable(peopleRepository.findByFullName(fullName));
    }
}
