package com.ufpb.dcx.dsc.springfantasy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrcamentoInsuficienteException extends RuntimeException {
    private BigDecimal custoTotal;
    private BigDecimal patrimonio;

    public OrcamentoInsuficienteException(String message, BigDecimal custoTotal, BigDecimal patrimonio) {
        super(message);
        this.custoTotal = custoTotal;
        this.patrimonio = patrimonio;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public BigDecimal getPatrimonio() {
        return patrimonio;
    }
}

