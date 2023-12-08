package com.assignment.library.service;

import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.model.Author;
import com.assignment.library.repository.AuthorRepository;
import com.assignment.library.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getAllAuthors() {
        Flux<Author> authors = Flux.just(
                new Author("1", "Author1", null, null),
                new Author("2", "Author2", null, null)
        );

        authorRepository.saveAll(authors).blockLast();

        Flux<AuthorDTO> authorDTOFlux = authorService.getAllAuthors();

        StepVerifier.create(authorDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void saveAuthor() {
        Mono<AuthorDTO> authorDTO = Mono.just(new AuthorDTO("1", "New Author", null));

        StepVerifier.create(authorService.saveAuthor(authorDTO))
                .expectNextMatches(savedAuthorDTO ->
                        savedAuthorDTO.getId().equals("1") && savedAuthorDTO.getName().equals("New Author"))
                .verifyComplete();
    }

    @Test
    void getAuthorsByNameRegex() {
        Flux<Author> authors = Flux.just(
                new Author("1", "Author1", null, null),
                new Author("2", "Author2", null, null),
                new Author("3", "AnotherAuthor", null, null)
        );

        authorRepository.saveAll(authors).blockLast();

        Flux<AuthorDTO> authorDTOFlux = authorService.getAuthorsByNameRegex("Author");

        StepVerifier.create(authorDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
