package com.assignment.library.converter;

import com.assignment.library.dto.AuthorDTO;
import com.assignment.library.model.Author;
import org.springframework.beans.BeanUtils;

public class AuthorConverter {
    public static AuthorDTO entityToDTO(Author author){
        AuthorDTO dto = new AuthorDTO();
        BeanUtils.copyProperties(author,dto);
        return dto;
    }
    public static Author dtoToEntity (AuthorDTO dto){
        Author author = new Author();
        BeanUtils.copyProperties(dto,author);
        return author;
    }
}
