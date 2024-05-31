package com.woliveira1728.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author authors;

    private String languages;

    private int downloadCount;

    public Book() {}

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        Author authors = new Author(dataBook.authors().get(0));
        this.authors = authors;
        this.languages = dataBook.languages().get(0);
        this.downloadCount = dataBook.downloadCount();
    }

    public Book (Long idDB, String title, Author authors, String languages, int downloadCount ) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthors() { return authors; }

    public void setAuthors() { this.authors = authors; }

    public String getLanguages() { return languages; }

    public void setLanguages(String languages) { this.languages = languages; }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }


    public String getBook() {
        return
                "###############     BOOK    ################" +
                "\nTitle: " + title +
                "\nAuthor: " + authors +
                "\nLanguages: " + languages +
                "\nDownload count: " + downloadCount +
                "\n#########################################\n";
    }

    @Override
    public String toString() {
        return
                "{" +
                "\nTitle: " + title +
                "\nLanguages: " + languages +
                "\nDownload count: " + downloadCount +
                "\n}";
    }
}
