package com.chinexboroja.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BorrowingRecordDto {

    private Long id;
    @NotNull(message = "Book ID is required")
    private Long bookId;
    private String bookTitle;
    @NotNull(message = "Patron ID is required")
    private Long patronId;
    private String patronName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BorrowingRecordDto() {}

    public BorrowingRecordDto(Long id, Long bookId, String bookTitle, Long patronId, String patronName,
                              LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.patronId = patronId;
        this.patronName = patronName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Long getPatronId() {
        return patronId;
    }


    public String getPatronName() {
        return patronName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

}
