package com.woliveira1728.LiterAlura.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Year birthYear;
    private Year deathYear;

    @OneToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(DataAuthor dataAuthor) {
        this.name = dataAuthor.name();
        this.birthYear = Year.of(dataAuthor.birthYear());
        this.deathYear = Year.of(dataAuthor.deathYear());
    }

    public Author(String name, Year birthYear, Year deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Year getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }

    public Year getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Year deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



    @Override
    public String toString() {
        return
                "{" +
                "\nName: " + name +
                "\nBirth Year: " + birthYear +
                "\nDeath Year: " + deathYear +
                "\n}";
    }

    public String getAuthor() {
        return
                "###############     AUTHOR    ################" +
                "\nName: " + name +
                "\nBirth Year: " + birthYear +
                "\nDeath Year: " + deathYear +
                "\nBooks: " + books +
                "\n##############################################";
    }
}
