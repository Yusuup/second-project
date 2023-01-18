package ru.yusupov.secondproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yusupov.secondproject.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByNameStartingWith(String name);
}
