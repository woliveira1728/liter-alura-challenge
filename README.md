# LiterAlura

LiterAlura is a Java application based on Spring Boot that allows users to search for books by title, list registered books and authors, and more. The application consumes data from the Gutendex API and stores information about books and authors in a relational database using JPA.

## Project Structure

The project is organized into the following packages:

- `com.woliveira1728.LiterAlura`
    - `LiterAluraApplication`: Main class that initializes the Spring Boot application.
- `com.woliveira1728.LiterAlura.principal`
    - `Principal`: Class responsible for displaying the menu and handling user interactions.
- `com.woliveira1728.LiterAlura.model`
    - `Author`: Class representing an author.
    - `Book`: Class representing a book.
    - `DataBook`: Helper class for deserializing book data from the API.
    - `DataAuthor`: Helper class for deserializing author data from the API.
- `com.woliveira1728.LiterAlura.repository`
    - `AuthorRepository`: JPA repository interface for handling author data.
    - `BookRepository`: JPA repository interface for handling book data.
- `com.woliveira1728.LiterAlura.service`
    - `ConsumptionApi`: Class for consuming data from the Gutendex API.
    - `ConvertsData`: Class for converting and handling JSON data.

## Requirements

- Java 17 or higher
- Spring Boot 3.0 or higher
- Relational database (e.g., MySQL, PostgreSQL)
- Maven 3.6 or higher

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/user/LiterAlura.git
   cd LiterAlura
2. Configure the application.properties file with your database information:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/literalura
   spring.datasource.username=user
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
## Usage
When the application is running, you will see the main menu in the console. The available options are:
1. Search book by title: Allows you to search for a book by title using the Gutendex API.
2. List registered books: Displays all books stored in the database.
3. List registered authors: Displays all authors stored in the database.
4. List living authors in a given year: Displays authors who were alive in a given year.
5. List books in a certain language: Displays books in a specific language.

To select an option, type the corresponding number and follow the instructions in the console. 