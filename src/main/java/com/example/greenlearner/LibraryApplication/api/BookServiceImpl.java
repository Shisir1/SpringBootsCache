package com.example.greenlearner.LibraryApplication.api;

import com.example.greenlearner.LibraryApplication.dto.Book;
import com.example.greenlearner.LibraryApplication.repository.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService{

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book addBook(Book book) {
        logger.info("adding book with id - {}",book.getId());
        return bookRepository.save(book);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(Book book) {
        bookRepository.updateAddress(book.getId(), book.getName());
        logger.info("book updated with new name");
        return book;
    }

    @Override
    @Cacheable(cacheNames = "books",key = "#id")
    public Book getBook(long id) {
        logger.info("fetching book from db");
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        } else {
            return new Book();
        }
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "Book has been deleted from the database!!!";
    }
}
