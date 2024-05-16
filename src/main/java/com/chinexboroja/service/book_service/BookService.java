package com.chinexboroja.service.book_service;

import com.chinexboroja.dto.book.BookRequest;
import com.chinexboroja.dto.book.BookResponse;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookResponse> getAllBooks();

    Optional<BookResponse> getBookById(Long bookId);

    BookResponse addNewBook(BookRequest bookRequest);

    BookResponse updateBook(Long bookId, BookRequest bookRequest);

    BookResponse deleteBook(Long bookId);
}
