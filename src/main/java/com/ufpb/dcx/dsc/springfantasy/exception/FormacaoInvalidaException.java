package com.ufpb.dcx.dsc.springfantasy.exception;

public class FormacaoInvalidaException extends RuntimeException {

    public FormacaoInvalidaException(String message) {
        super(message);
    }

    public FormacaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
