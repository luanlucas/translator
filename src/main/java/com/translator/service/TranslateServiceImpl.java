package com.translator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.translator.client.AzureClient;
import com.translator.model.RequestBody;
import com.translator.model.Translation;
import com.translator.model.TranslatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class TranslateServiceImpl implements TranslateService {

    private final ObjectMapper objectMapper;

    private final AzureClient azureClient;

    @Autowired
    public TranslateServiceImpl(final ObjectMapper objectMapper, final AzureClient azureClient) {
        this.objectMapper = objectMapper;
        this.azureClient = azureClient;
    }

    @Override
    public List<Translation> translate(final String text, final String[] languages) throws IOException {
        final String jsonText = this.objectMapper.writeValueAsString(new RequestBody[] {new RequestBody(text)});
        final String translation = this.azureClient.translate(Arrays.asList(languages), jsonText);
        final List<TranslatorResponse> response = this.objectMapper.readValue(translation,
                this.objectMapper
                        .getTypeFactory()
                        .constructCollectionType(List.class, TranslatorResponse.class));

        return response
                .stream()
                .collect(ArrayList::new,
                    (list, translatorResponse) -> list.addAll(translatorResponse.getTranslations()),
                    Collection::addAll);
    }
}
