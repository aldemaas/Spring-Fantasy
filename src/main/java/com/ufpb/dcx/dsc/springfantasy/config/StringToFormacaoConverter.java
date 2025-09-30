package com.ufpb.dcx.dsc.springfantasy.config;

import com.ufpb.dcx.dsc.springfantasy.enums.Formacao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFormacaoConverter implements Converter<String, Formacao> {

    @Override
    public Formacao convert(String source) {
        return Formacao.fromDescricao(source);
    }
}
