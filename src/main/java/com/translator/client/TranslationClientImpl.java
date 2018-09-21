package com.translator.client;

import com.translator.model.Translation;
import com.translator.model.UserInput;
import com.translator.persister.FileSystemOperator;
import com.translator.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TranslationClientImpl implements TranslationClient {

    private TranslateService translateService;

    private FileSystemOperator fileSystemOperator;

    @Autowired
    public TranslationClientImpl(final TranslateService translateService, final FileSystemOperator fileSystemOperator) {
        this.translateService = translateService;
        this.fileSystemOperator = fileSystemOperator;
    }

    @Override
    public void translate(final UserInput userInput) throws Exception {
        final String text = this.fileSystemOperator.readText(userInput.getInputPath());
        final List<Translation> translations = this.translateService.translate(text, userInput.getLanguages());
        final Map<String, InputStream> files = translations
                .stream()
                .collect(Collectors.toMap(translation -> userInput.getOutputPath().concat(translation.getTo()),
                                          this::getContent));

        this.fileSystemOperator.save(files);
    }

    private InputStream getContent(final Translation translation) {
        return new ByteArrayInputStream(translation.getText().getBytes());
    }
}
