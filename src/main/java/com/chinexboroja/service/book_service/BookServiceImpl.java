package com.chinexboroja.service.book_service;

import com.chinexboroja.dto.BookData;
import com.chinexboroja.dto.BookRequest;
import com.chinexboroja.dto.BookResponse;
import com.chinexboroja.models.Book;
import com.chinexboroja.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookData> bookDataList = books.stream()
            .map(this::mapToBookData)
            .collect(Collectors.toList());

        return (List<BookResponse>) createBookResponseList(true, "Books retrieved successfully", bookDataList);
    }

    @Override
    public Optional<BookResponse> getBookById(Long bookId) {
        Book book = getBookEntityById(bookId);
        BookData bookData = mapToBookData(book);

        return Optional.of(createBookResponse(true, "Book retrieved successfully", bookData));
    }

    @Override
    public BookResponse addNewBook(BookRequest bookRequest) {
        Book book = mapToEntity(bookRequest);
        Book savedBook = bookRepository.save(book);
        BookData bookData = mapToBookData(savedBook);

        return createBookResponse(true, "Book added successfully", bookData);
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest bookRequest) {
        Book book = getBookEntityById(bookId);

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        book.setIsbn(bookRequest.getIsbn());

        Book updatedBook = bookRepository.save(book);
        BookData bookData = mapToBookData(updatedBook);
        return createBookResponse(true, "Book updated successfully", bookData);
    }

    @Override
    public BookResponse deleteBook(Long bookId) {
        Book book = getBookEntityById(bookId);
        bookRepository.delete(book);
        return createBookResponse(true, "Book deleted successfully", null);
    }

    private Book getBookEntityById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book with the id " + id + " not found"));
    }

    private BookData mapToBookData(Book book) {
        return new BookData(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getPublicationYear(),
            book.getIsbn()
        );
    }

    private BookResponse createBookResponse(boolean status, String message, BookData data) {
        BookResponse response = new BookResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    private BookResponse createBookResponseList(boolean status, String message, List<BookData> dataList) {
        BookResponse response = new BookResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setData((BookData) dataList);
        return response;
    }

    private Book mapToEntity(BookRequest bookRequest) {
        return new Book(
            bookRequest.getTitle(),
            bookRequest.getAuthor(),
            bookRequest.getPublicationYear(),
            bookRequest.getIsbn()
        );
    }
}
