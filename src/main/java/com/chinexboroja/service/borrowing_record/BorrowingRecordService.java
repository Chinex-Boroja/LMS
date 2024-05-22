package com.chinexboroja.service.borrowing_record;

import com.chinexboroja.dto.BorrowingRecordDto;
import com.chinexboroja.models.BorrowingRecord;

public interface BorrowingRecordService {

    BorrowingRecordDto borrowBook(Long bookId, Long patronId);
    BorrowingRecordDto returnBook(Long bookId, Long patronId);
}
