package com.woliveira1728.LiterAlura.repository;

import com.woliveira1728.LiterAlura.model.Author;
import com.woliveira1728.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> findAllAuthors();

    @Query("SELECT b.authors FROM Book b WHERE b.authors.name = :name")
    Author findAuthorsByName(String name);

    List<Book> findByLanguages(String lang);
}
