package com.chinexboroja.service.borrowing_record;

import com.chinexboroja.dto.BorrowingRecordDto;
import com.chinexboroja.exceptions.BadRequestException;
import com.chinexboroja.exceptions.NotFoundException;
import com.chinexboroja.models.Book;
import com.chinexboroja.models.BorrowingRecord;
import com.chinexboroja.models.Patron;
import com.chinexboroja.repositories.BookRepository;
import com.chinexboroja.repositories.BorrowingRecordRepository;
import com.chinexboroja.repositories.PatronRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordServiceImpl implements BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;

    private final BookRepository bookRepository;

    private final PatronRepository patronRepository;

    public BorrowRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository,
                                   PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    @Override
    public BorrowingRecordDto borrowBook(Long bookId, Long patronId) {
        final Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book not found with ID: " + bookId));

        final Patron patron = patronRepository.findById(bookId)
            .orElseThrow(() -> new NotFoundException("Patron not found with ID: " + patronId));

        final BorrowingRecord checkBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (checkBorrowingRecord != null) {
            throw new BadRequestException("Book is already by this patron");
        }

        final BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDateTime.now());
        borrowingRecord.setReturnDate(LocalDateTime.now().plusDays(7));

        final BorrowingRecord savedRecord = borrowingRecordRepository.save(borrowingRecord);

        return convertToDto(savedRecord);
    }

    @Transactional
    @Override
    public BorrowingRecordDto returnBook(Long bookId, Long patronId) {
        final BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);

        if (borrowingRecord == null) {
            throw new NotFoundException("Borrowing record not found for book ID: " + bookId + " and patron ID " + patronId);
        }

        borrowingRecord.setReturnDate(LocalDateTime.now());
        final BorrowingRecord updatedRecord = borrowingRecordRepository.save(borrowingRecord);

        return convertToDto(updatedRecord);
    }

    private BorrowingRecordDto convertToDto(BorrowingRecord borrowingRecord) {
        return new BorrowingRecordDto(
            borrowingRecord.getId(),
            borrowingRecord.getBook().getId(),
            borrowingRecord.getBook().getTitle(),
            borrowingRecord.getPatron().getId(),
            borrowingRecord.getPatron().getFirstName() + " " + borrowingRecord.getPatron().getLastName(),
            borrowingRecord.getBorrowDate(),
            borrowingRecord.getReturnDate()
        );
    }
}
