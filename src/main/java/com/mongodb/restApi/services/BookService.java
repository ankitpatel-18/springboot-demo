package com.mongodb.restApi.services;


import com.mongodb.restApi.entity.Book;
import com.mongodb.restApi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create or Update Book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    // Get Book by ID
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }


    // Delete Book by ID
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    // Check if Book exists
    public boolean bookExists(String id) {
        return bookRepository.existsById(id);
    }
}
