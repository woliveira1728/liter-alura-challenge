package com.woliveira1728.LiterAlura.repository;

import com.woliveira1728.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;

public interface AuthorRepository extends JpaRepository <Author, Long> {

    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> findAllAuthors();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Author> findAuthorWhereDeathIsAGivenYear(Year year);

}
