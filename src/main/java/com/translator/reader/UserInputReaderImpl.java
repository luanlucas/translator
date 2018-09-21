package com.translator.reader;

import com.translator.model.UserInput;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class UserInputReaderImpl {
    private static final Scanner INPUT_READER = new Scanner(System.in);

    private static final Pattern LANGUAGES_SEPARATOR = Pattern.compile(",");

    public UserInput read() throws FileNotFoundException {
        System.out.println("Input file with absolute path: ");
        final String inputPath = INPUT_READER.nextLine();
        this.validateInputpath(inputPath);
        System.out.println("Output directory: ");
        final String outputPath = INPUT_READER.nextLine();
        this.validateOutputPath(outputPath);
        System.out.println("Languages to be translated to separated by comma: ");
        final String[] languages = LANGUAGES_SEPARATOR.split(INPUT_READER.nextLine());

        return UserInput
                .builder()
                .inputPath(inputPath)
                .outputPath(outputPath)
                .languages(languages)
                .build();
    }

    private void validateInputpath(final String inputPath) throws FileNotFoundException {
        final Path path = Paths.get(inputPath);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new FileNotFoundException(String.format("[Error: Either path %s doesn't exist or it is a directory.]", inputPath));
        }
    }

    private void validateOutputPath(final String outputPath) throws FileNotFoundException {
        final Path path = Paths.get(outputPath);
        if (Files.notExists(path) || !Files.isDirectory(path)) {
            throw new FileNotFoundException(String.format("[Error: Either path %s doesn't exist or it is not a directory.]", outputPath));
        }
    }
}
