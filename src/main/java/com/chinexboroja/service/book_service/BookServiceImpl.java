package com.chinexboroja.service.book_service;

import com.chinexboroja.dto.BookRequest;
import com.chinexboroja.exceptions.BadRequestException;
import com.chinexboroja.exceptions.NoContentException;
import com.chinexboroja.exceptions.NotFoundException;
import com.chinexboroja.models.Book;
import com.chinexboroja.repositories.BookRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NoContentException("Books not found");
        }

        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        Book book = getBookEntityById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found with id " + bookId);
        }

        return bookRepository.findById(bookId);
    }

    @Override
    public Book addNewBook(BookRequest bookRequest) {

        if (isBookAlreadyExists(bookRequest)) {
            throw new BadRequestException("Book with ISBN " + bookRequest.getIsbn() + " already exists");
        }
        final Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        book.setIsbn(bookRequest.getIsbn());

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookRequest bookRequest) {
        Book book = getBookEntityById(bookId);

        if (book == null) {
            throw new NotFoundException("Book not found with id " + bookId);
        }

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        book.setIsbn(bookRequest.getIsbn());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = getBookEntityById(bookId);
        bookRepository.delete(book);
    }

    private Book getBookEntityById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Book with the id " + id + " not found"));
    }

    private boolean isBookAlreadyExists(BookRequest bookRequest) {

        Optional<Book> existingBook = bookRepository.findByIsbn(bookRequest.getIsbn());
        return existingBook.isPresent();
    }

}
