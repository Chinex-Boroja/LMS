package com.chinexboroja.controller;

import com.chinexboroja.dto.BorrowingRecordDto;
import com.chinexboroja.dto.patron.Response;
import com.chinexboroja.service.borrowing_record.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Response<?>> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        final BorrowingRecordDto borrowingRecordResponse = borrowingRecordService.borrowBook(bookId, patronId);

        return new ResponseEntity<>(
            new Response<>(
                true,
                "Book borrowed successfully",
                borrowingRecordResponse),
            HttpStatus.OK);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Response<?>> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        final BorrowingRecordDto borrowingRecordResponse = borrowingRecordService.borrowBook(bookId, patronId);

        return new ResponseEntity<>(
            new Response<>(
                true,
                "Book returned successfully",
                borrowingRecordResponse),
            HttpStatus.OK);
    }
}
