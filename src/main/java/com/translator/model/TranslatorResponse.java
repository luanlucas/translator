package com.translator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslatorResponse {
   private List<Translation> translations;
}
