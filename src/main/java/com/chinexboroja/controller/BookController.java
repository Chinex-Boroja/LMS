package com.chinexboroja.controller;

import com.chinexboroja.dto.book.BookRequest;
import com.chinexboroja.dto.book.BookResponse;
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
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        final var response = bookService.getAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookResponse>> getBookById(@PathVariable Long id) {
        final var response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addNewBook(@Valid @RequestBody BookRequest bookRequest) {
        final var response = bookService.addNewBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        final var response = bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
        final var response = bookService.deleteBook(id);
        return ResponseEntity.ok(response);
    }

}
