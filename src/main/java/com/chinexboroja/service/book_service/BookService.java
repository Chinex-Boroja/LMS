package com.chinexboroja.service.book_service;

import com.chinexboroja.dto.BookRequest;
import com.chinexboroja.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long bookId);

    Book addNewBook(BookRequest bookRequest);

    Book updateBook(Long bookId, BookRequest bookRequest);

    void deleteBook(Long bookId);
}
