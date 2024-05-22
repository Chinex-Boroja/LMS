package com.chinexboroja.service.borrowing_record;

import com.chinexboroja.models.BorrowingRecord;

public interface BorrowingRecordService {

    BorrowingRecord borrowBook(Long bookId, Long patronId);
    BorrowingRecord returnBook(Long bookId, Long patronId);
}
