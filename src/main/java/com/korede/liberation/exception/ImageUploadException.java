package com.korede.liberation.exception;

public class ImageUploadException extends Exception{

    public ImageUploadException() {
        super();
    }

    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageUploadException(Throwable cause) {
        super(cause);
    }

    protected ImageUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
