package com.chinexboroja.dto.patron;

public class Response<E> {
    private boolean status;
    private String message;
    private E data;

    public Response() {
    }

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(boolean status, E data) {
        this.status = status;
        this.data = data;
    }

    public Response(final boolean status, final String message, final E data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
            "status=" + status +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
    }
}
