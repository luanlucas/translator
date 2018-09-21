package com.translator.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInput {
    private String inputPath;

    private String outputPath;

    private String[] languages;
}
