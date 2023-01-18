package ru.yusupov.secondproject.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName")
    @Size(min = 2, max = 100, message = "Кол-во символов должно быть от 2 до 100")
    @Pattern(regexp = "[\\u0410-\\u042f][\\u0430-\\u044f]+ [\\u0410-\\u042f][\\u0430-\\u044f]+ [\\u0410-\\u042f][\\u0430-\\u044f]+"
            , message = "Введите правильно ФИО по Формату: Фамилия Имя Отчество")
    private String fullName;

    @Column(name = "yearBirth", nullable = false)
    @Min(value = 1899, message = "Минимальный год рождения 1899")
    @Max(value = 2023, message = "Максимальный год рождения 2023")
    private int yearBirth;

    @OneToMany(mappedBy = "owner")
    List<Book> bookList;

    public Person() {
    }

    public Person(int id, String fullName, int yearBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", yearBirth=" + yearBirth +
                ", bookList=" + bookList +
                '}';
    }
}
