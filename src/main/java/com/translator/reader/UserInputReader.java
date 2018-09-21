package com.translator.reader;

import com.translator.model.UserInput;

import java.io.FileNotFoundException;

public interface UserInputReader {
    UserInput read() throws FileNotFoundException;
}
