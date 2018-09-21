package com.translator.persister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.stream.Stream;

@Component
@Slf4j
public class FileSystemOperatorImpl implements FileSystemOperator {

    @Override
    public String readText(String filePath) {
        StringBuilder text = new StringBuilder();
        final Path path = Paths.get(filePath);
        try (final Stream<String> fileContent = Files.lines(path, Charset.forName(StandardCharsets.ISO_8859_1.name()))) {
            text = fileContent.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        } catch (IOException e) {
            log.error("[There was an error reading content from file {}]", filePath, e);
        }

        return text.toString();
    }

    @Override
    public void save(Map<String, InputStream> files) {
        files.entrySet().forEach(file -> {
            final Path path = Paths.get(file.getKey());
            try {
                Files.copy(file.getValue(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error("[There was an error copying content to file {}", file.getKey(), e);
            }
        });
    }
}
