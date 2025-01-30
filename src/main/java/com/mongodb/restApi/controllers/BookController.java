package com.mongodb.restApi.controllers;


import com.mongodb.restApi.entity.Book;
import com.mongodb.restApi.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Get Data From RestAPI By ID

    @Operation(
            summary = "Fetch Data From Book API and Insert into DB",
            description = "TO fetch a Book by their unique ID from the RestAPI and Insert it Into Local Database."
    )
    @GetMapping("/api/{id}")
    public String insertBooksData(@PathVariable String id) {

        return bookService.insertBookData(id);
    }

    // Create or update Book
    @Operation(
            summary = "Insert manual book data into Local DB",
            description = "TO Insert Manual book data by sending data of Book object in request body"
    )
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // Get all Books
    @Operation(
            summary = "To get All book data from mongoDB ",
            description = "TO fetch all book data inserted in MongoDB"
    )
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get Book by ID
    @Operation(
            summary = "Get single book data by Id from mongoDB ",
            description = "TO fetch an book data inserted in MongoDB By Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Book by ID
    @Operation(
            summary = "Delete a Book by ID",
            description = "To delete an book detail from MongoDB by their unique ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        if (bookService.bookExists(id)) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Book by ID
    @Operation(
            summary = "Update a Book by ID",
            description = "To Update an book detail in MongoDB by their unique ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        if (bookService.bookExists(id)) {
            book.setId(id);  // Ensure the ID is set for updating
            Book updatedBook = bookService.saveBook(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
