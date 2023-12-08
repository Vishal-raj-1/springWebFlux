package com.assignment.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    @NotBlank(message = "Book.genre must be present")
    private String genre;
    @NotBlank(message = "Book.authorId must be present")
    private String authorId;
    @NotNull
    @Positive(message = "Book.copiesAvailable must be present")
    private int copiesAvailable;
}
