package com.liuchao.securitydemo.security.exception;

public class MyException extends Exception {
    private String message;

    public MyException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
