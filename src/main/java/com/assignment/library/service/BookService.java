package com.assignment.library.service;

import com.assignment.library.converter.AuthorConverter;
import com.assignment.library.converter.BookConverter;
import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.dto.BookDTO;
import com.assignment.library.dto.SaveBookDTO;
import com.assignment.library.repository.AuthorRepository;
import com.assignment.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public Flux<BookDTO> getAllBooks() {
        return bookRepository.findAll().map(BookConverter::entityToDTO);
    }

    public Flux<BookDTO> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public Flux<BookDTO> getBooksByGenreAndCopiesAvailable(String genre, int copiesAvailable) {
        return bookRepository.findByGenreAndCopiesAvailable(genre, copiesAvailable);
    }


    public Flux<BookDTO> getBooksByAuthorsName(String authorList) {
        String[] authors = authorList.split(":");
        return Flux.fromArray(authors)
                .flatMap(authorName -> authorRepository.findByName(authorName)
                        .flatMapMany(this::getBooks));
    }
    private Flux<BookDTO> getBooks(AuthorDTO authorDTO){
        if(authorDTO != null && authorDTO.getId() != null){
            return bookRepository.findByAuthorIds(authorDTO.getId());
        }
        else{
            return Flux.empty();
        }
    }
}

