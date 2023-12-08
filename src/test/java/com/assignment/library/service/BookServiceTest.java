package com.assignment.library.service;

import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.dto.BookDTO;
import com.assignment.library.model.Author;
import com.assignment.library.model.Book;
import com.assignment.library.repository.AuthorRepository;
import com.assignment.library.repository.BookRepository;
import com.assignment.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getAllBooks() {
        Flux<Book> books = Flux.just(
                new Book("1", "Science Fiction", "1", 5),
                new Book("2", "Fantasy", "2", 8)
        );

        bookRepository.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getAllBooks();

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void getBooksByGenre() {
        Flux<Book> books = Flux.just(
                new Book("1", "Science Fiction", "1", 5),
                new Book("2", "Science Fiction", "2", 8)
        );

        bookRepository.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getBooksByGenre("Science Fiction");

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void getBooksByGenreAndCopiesAvailable() {
        Flux<Book> books = Flux.just(
                new Book("1", "Science Fiction", "1", 5),
                new Book("2", "Fantasy", "2", 8),
                new Book("3", "Science Fiction", "3", 10)
        );

        bookRepository.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getBooksByGenreAndCopiesAvailable("Science Fiction", 7);

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void getBooksByAuthorsName() {
        Author author1 = new Author("1", "Author1", null, null);
        Author author2 = new Author("2", "Author2", null, null);

        authorRepository.saveAll(Flux.just(author1, author2)).blockLast();

        Flux<Book> books = Flux.just(
                new Book("1", "Science Fiction", "1", 5),
                new Book("2", "Fantasy", "1", 8),
                new Book("3", "Mystery", "2", 10)
        );

        bookRepository.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getBooksByAuthorsName("Author1:Author2");

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(3)
                .verifyComplete();
    }
}
