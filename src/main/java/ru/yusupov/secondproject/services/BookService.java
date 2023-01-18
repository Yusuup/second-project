package ru.yusupov.secondproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yusupov.secondproject.models.Book;
import ru.yusupov.secondproject.models.Person;
import ru.yusupov.secondproject.repositories.BookRepository;
import ru.yusupov.secondproject.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> index() {
        return bookRepository.findAll();
    }

    public List<Book> index(boolean sort) {
        return bookRepository.findAll(Sort.by("year"));
    }

    public List<Book> index(int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> index(boolean pagination, boolean sort, int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public Book show(int id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book upBook) {
        upBook.setId(id);
        bookRepository.save(upBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<Person> getOwner(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(Book::getOwner);
    }

    @Transactional
    public void updatePerson(int id, Person person) {
        Book book = bookRepository.findById(id).get();
        book.setOwner(person);
        book.setTakenFrom(new Date());
    }

    @Transactional
    public void deleteUser(int id) {
        Book book = bookRepository.findById(id).get();
        book.setTakenFrom(null);
        book.setOwner(null);
    }

    public List<Book> search(String startingWith) {
        return bookRepository.findBooksByNameStartingWith(startingWith);
    }
}
