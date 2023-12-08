package com.assignment.library.service;

import com.assignment.library.converter.AuthorConverter;
import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.model.Author;
import com.assignment.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public Flux<AuthorDTO> getAllAuthors(){
        return authorRepository.findAll().map(AuthorConverter::entityToDTO);
    }

    public Mono<AuthorDTO> saveAuthor(Mono<AuthorDTO> authorDTO){
        return authorDTO
                .map(AuthorConverter::dtoToEntity)
                .flatMap(authorRepository::save)
                .map(AuthorConverter::entityToDTO);
    }

    public Flux<AuthorDTO> getAuthorsByNameRegex(String nameRegex){
        return authorRepository.findByNameRegex(nameRegex);
    }
}
