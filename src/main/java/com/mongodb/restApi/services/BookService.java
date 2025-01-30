package com.mongodb.restApi.services;


import com.mongodb.restApi.config.WebClientConfig;
import com.mongodb.restApi.entity.Book;
import com.mongodb.restApi.repository.BookRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Fetch Data From Rest API
    public String insertBookData(String id){
        String apiUrl = "https://softwium.com/api/books/" + id;
        Book bookData =   webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Book.class) // Convert response to Book object
                .block();  //Wait for Completion of Call.
        if(bookData==null) {
            return "Failed to fetch data from the Book API.";
        }
        Optional<Book> existingData = bookRepository.findById(id);
        if(existingData.isEmpty()){
            bookRepository.save(bookData);
            return "Data inserted successfully.";
        }
        else {
            return "Data already exists in Database.";
        }
    }

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
