package com.translator.client;

import com.translator.model.UserInput;

public interface TranslationClient {
    void translate(UserInput userInput) throws Exception;
}
