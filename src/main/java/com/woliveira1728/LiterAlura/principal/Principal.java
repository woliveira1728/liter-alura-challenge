package com.woliveira1728.LiterAlura.principal;

import com.woliveira1728.LiterAlura.model.*;
import com.woliveira1728.LiterAlura.repository.AuthorRepository;
import com.woliveira1728.LiterAlura.repository.BookRepository;
import com.woliveira1728.LiterAlura.service.ConsumptionApi;
import com.woliveira1728.LiterAlura.service.ConvertsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private Scanner scanner = new Scanner(System.in);
    private ConsumptionApi consumption = new ConsumptionApi();
    private ConvertsData converter = new ConvertsData();
    private final String ADDRESS = "https://gutendex.com/books";

    @Autowired
    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    #########################################
                    Enter the number of your desired option:
                    1 - Search book by title
                    2 - List registered books
                    3 - List registered authors
                    4 - List living authors in a given year
                    5 - List book in a certain language

                    0 - Exit
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    findBookByTitle();
                    break;
                case 2:
                    getBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsInAGivenYear();
                    break;
                case 5:
                    listBookInACertainLanguage();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void findBookByTitle() {
        System.out.println("Enter the name of the title:");
        var title = scanner.nextLine();
        var json = consumption.getData(ADDRESS + "?search=" + title.replace(" ", "%20"));
        var dataBooksList = converter.extractObjectJson(json, "results");
        List<DataBook> dataBooks = converter.getList(dataBooksList, DataBook.class);

        if(dataBooks.size() > 0) {
            Book book = new Book(dataBooks.get(0));


            Author author = bookRepository.findAuthorsByName(book.getAuthors().getName());
            if(author != null) {
                book.setAuthors(null);
                bookRepository.save(book);
                book.setAuthors(author);
            }
            book = bookRepository.save(book);
            System.out.println(book);
        } else {
            System.out.println("Book not found!");
        }
    }

    private void getBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(b -> System.out.println(b.getBook()));
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(a -> System.out.println(a.getAuthor()));
    }

    private void listLivingAuthorsInAGivenYear() {

        System.out.println("Enter the year:");
        Year aGivenYear = Year.of(scanner.nextInt());
        scanner.nextLine();

        List<Author> authors = authorRepository.findAuthorWhereDeathIsAGivenYear(aGivenYear);
        if(authors.size() <= 0) {
            System.out.println("Authors not found in the database for that year");
        } else {
            authors.forEach(a -> System.out.println(a.getAuthor()));
        }

    }

    private void listBookInACertainLanguage() {

        System.out.println("""
                Enter the search language according to convention:
                Ex: es - Spanish, en - English,
                    fr - French, pt - Portuguese
                """);
        String lang = scanner.nextLine();
        List<Book> books = bookRepository.findByLanguages(lang);
        if(books.size() <= 0) {
            System.out.println("Books not found in the database for that language");
        } else {
            books.forEach(b -> System.out.println(b.getBook()));
        }

    }

}
