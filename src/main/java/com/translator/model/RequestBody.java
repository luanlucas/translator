package com.translator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBody {
    private final String text;

    public RequestBody(final String text) {
        this.text = text;
    }
}
