package com.assignment.library.dto;

import com.assignment.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookDTO {
    private Book book;
    private String authorName;
}
