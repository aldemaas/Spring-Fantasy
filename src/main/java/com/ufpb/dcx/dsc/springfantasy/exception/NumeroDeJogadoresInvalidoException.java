package com.ufpb.dcx.dsc.springfantasy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroDeJogadoresInvalidoException extends RuntimeException {
    public NumeroDeJogadoresInvalidoException(String message) {
        super(message);
    }
}

