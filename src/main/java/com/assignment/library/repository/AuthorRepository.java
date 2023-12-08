package com.assignment.library.repository;

import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.model.Author;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Mono<AuthorDTO> findByName(String authorName);
    @Query("{name : {$regex : ?0} }")
    Flux<AuthorDTO> findByNameRegex(String nameRegex);
}
