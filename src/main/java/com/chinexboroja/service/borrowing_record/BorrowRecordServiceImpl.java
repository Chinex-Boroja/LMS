package com.chinexboroja.service.borrowing_record;

import com.chinexboroja.models.Book;
import com.chinexboroja.models.BorrowingRecord;
import com.chinexboroja.repositories.BookRepository;
import com.chinexboroja.repositories.BorrowingRecordRepository;
import com.chinexboroja.repositories.PatronRepository;
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

    @Override
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {

        return null;
    }

    @Override
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        return null;
    }
}
