package com.assignment.library.repository;

import com.assignment.library.dto.BookDTO;
import com.assignment.library.model.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Flux<BookDTO> findByGenre(String genre);
    @Query("{genre : ?0, copiesAvailable : { $gt : ?1} }")
    Flux<BookDTO> findByGenreAndCopiesAvailable(String genre, int copiesAvailable);
    Flux<BookDTO> findByAuthorIds(List<String> authorIds);
}
