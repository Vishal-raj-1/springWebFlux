package com.assignment.library.controller;

import com.assignment.library.dto.BookDTO;
import com.assignment.library.dto.SaveBookDTO;
import com.assignment.library.model.Book;
import com.assignment.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public Flux<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/byGenre")
    public Flux<BookDTO> getBooksByGenre(@RequestParam String genre){
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping("/byGenreAndCopiesAvailable")
    public Flux<BookDTO> getBooksByGenreAndCopiesAvailable(@RequestParam String genre, @RequestParam int copiesAvailable){
        return bookService.getBooksByGenreAndCopiesAvailable(genre, copiesAvailable);
    }

    @GetMapping("/byAuthorsName")
    public Flux<BookDTO> getBooksByAuthorsNames(@RequestParam String authorList){
        return bookService.getBooksByAuthorsName(authorList);
    }

    @PostMapping
    public ResponseEntity<Mono<BookDTO>> saveBook(@RequestBody @Valid SaveBookDTO book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

}
