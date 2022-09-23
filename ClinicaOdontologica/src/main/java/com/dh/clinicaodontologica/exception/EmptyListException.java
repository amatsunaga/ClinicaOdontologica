package com.dh.clinicaodontologica.exception;

import lombok.Getter;

@Getter
public class EmptyListException extends Exception {
    private String message;

    public EmptyListException(String message) {
        this.message = message;
    }
}
