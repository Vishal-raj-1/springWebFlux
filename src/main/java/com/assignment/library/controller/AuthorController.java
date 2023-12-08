package com.assignment.library.controller;

import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.repository.AuthorRepository;
import com.assignment.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public Flux<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/nameRegex")
    public Flux<AuthorDTO> getAuthorsByNameRegex(@RequestParam String nameRegex){
        return authorService.getAuthorsByNameRegex(nameRegex);
    }

    @PostMapping
    public ResponseEntity<Mono<AuthorDTO>> saveAuthor(@RequestBody @Valid AuthorDTO authorDTO){
        return ResponseEntity.ok(authorService.saveAuthor(Mono.just(authorDTO)));
    }

}
