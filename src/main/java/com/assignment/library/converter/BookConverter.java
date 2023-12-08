package com.assignment.library.converter;

import com.assignment.library.dto.BookDTO;
import com.assignment.library.model.Book;
import org.springframework.beans.BeanUtils;

public class BookConverter {
    public static BookDTO entityToDTO (Book book){
        BookDTO dto=new BookDTO();
        BeanUtils.copyProperties(book,dto);
        return dto;
    }
    public static Book dtoToEntity (BookDTO dto){
        Book book=new Book();
        BeanUtils.copyProperties(dto,book);
        return book;
    }
}