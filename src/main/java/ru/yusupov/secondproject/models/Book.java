package ru.yusupov.secondproject.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @NotEmpty(message = "Заполните поле")
    @Size(min = 2, max = 100, message = "Кол-во символов названии должно быть от 2 до 100")
    String name;

    @Column(name = "author")
    @NotEmpty(message = "Заполните поле")
    @Size(min = 2, max = 100, message = "Кол-во символов должно быть от 2 до 100")
    String author;

    @Column(name = "year")
    @Min(value = 1800, message = "Введите год, который начинается с 1800")
    @Max(value = 2023, message = "Введите год, который заканчивается 2023")
    int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenFrom;

    @Transient
    private boolean outOfDate;

    public Book() {
    }

    public Book(int id, String name, String author, int year, Person owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(boolean outOfDate) {
        this.outOfDate = outOfDate;
    }

    public Date getTakenFrom() {
        return takenFrom;
    }

    public void setTakenFrom(Date takenFrom) {
        this.takenFrom = takenFrom;
    }
}