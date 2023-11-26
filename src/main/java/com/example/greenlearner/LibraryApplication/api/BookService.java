package com.example.greenlearner.LibraryApplication.api;

import com.example.greenlearner.LibraryApplication.dto.Book;

public interface BookService {
    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(long id);

    String deleteBook(long id);
}
