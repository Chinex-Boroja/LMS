package com.chinexboroja.controller;

import com.chinexboroja.dto.BookRequest;
import com.chinexboroja.dto.Response;
import com.chinexboroja.models.Book;
import com.chinexboroja.service.book_service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Response<?>> getAllBooks() {
        final List<Book> responseList = bookService.getAllBooks();
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Books retrieved successfully",
                responseList),
            HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getBookById(@PathVariable Long id) {
        final Optional<Book> response = bookService.getBookById(id);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Book retrieved successfully",
                response),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<?>> addNewBook(@Valid @RequestBody BookRequest bookRequest) {
        final Book response = bookService.addNewBook(bookRequest);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Books added successfully",
                response),
            HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        final Book response = bookService.updateBook(id, bookRequest);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Books updated successfully",
                response),
            HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Books deleted successfully",
                null),
            HttpStatus.OK);
    }

}
