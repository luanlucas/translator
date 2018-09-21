package com.translator.service;

import com.translator.model.Translation;

import java.util.List;

public interface TranslateService {
    List<Translation> translate(String text, String[] languages) throws Exception;
}
