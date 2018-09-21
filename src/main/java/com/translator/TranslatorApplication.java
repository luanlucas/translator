package com.translator;

import com.translator.client.TranslationClient;
import com.translator.model.UserInput;
import com.translator.reader.UserInputReaderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class TranslatorApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(TranslatorApplication.class, args);
		UserInputReaderImpl userInputReader = applicationContext.getBean(UserInputReaderImpl.class);
		TranslationClient translationClient = applicationContext.getBean(TranslationClient.class);

		UserInput userInput = userInputReader.read();
		translationClient.translate(userInput);

		System.exit(1);
	}
}
