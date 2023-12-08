package com.assignment.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
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
