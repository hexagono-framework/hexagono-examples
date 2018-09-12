package com.github.hexagonoframework.example.application.command;

import com.github.hexagonoframework.example.application.ApplicationException;

public class RegisterBookmarkException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    
    private final transient ErrorCode errorCode;
    
    public enum ErrorCode {
        INVALID_BOOKMARK_NAME,
        INVALID_BOOKMARK_DESCRIPTION,
        INVALID_BOOKMARK_URL,
        BOOKMARK_NAME_ALREADY_EXISTS,
        BOOKMARK_URL_ALREADY_EXISTS
    }
    
    public RegisterBookmarkException(ErrorCode errorCode, Throwable throwable) {
        super(throwable.getMessage(), throwable);
        this.errorCode = errorCode;
    }
    
    public RegisterBookmarkException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    @Override
    public String errorCode() {
        return errorCode.name();
    }
    
    public ErrorCode error() {
        return errorCode;
    }
    
}
