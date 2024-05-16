package com.chinexboroja.dto.book;

import java.util.List;

public class BookResponse {

    private boolean status;
    private String message;
    private BookData data;
    private List<BookData> dataList;



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookData getData() {
        return data;
    }

    public void setData(BookData data) {
        this.data = data;
    }

    public List<BookData> getDataList() {
        return dataList;
    }

    public void setDataList(List<BookData> dataList) {
        this.dataList = dataList;
    }
}
