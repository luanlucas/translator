package com.translator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.translator.client.AzureClient;
import com.translator.config.TranslateServiceConfiguration;
import com.translator.model.Translation;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TranslateServiceConfiguration.class)
public class TranslateServiceTest {

    private TranslateService translateService;

    @Autowired
    private AzureClient azureClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
        Mockito.doReturn(this.getTranslatedText())
                .when(this.azureClient)
                .translate(Mockito.anyList(),
                        Mockito.anyString());

        this.translateService = new TranslateServiceImpl(this.objectMapper, this.azureClient);
        List<Translation> translations = this.translateService.translate(this.getTextToTranslate(), new String[] {"pt"});

        Assert.assertThat(translations, Matchers.hasSize(1));
        Assert.assertThat(translations, Matchers.hasItem(this.getTranslation()));
    }

    private Translation getTranslation() {
        final Translation translation = new Translation();
        translation.setText("Olá, mundo!");
        translation.setTo("pt");

        return translation;
    }

    private String getTranslatedText() {
        return "[{\"translations\":[{\"text\":\"Olá, mundo!\",\"to\":\"pt\"}]}]";
    }

    private String getTextToTranslate() {
        return "Hello, world!";
    }
}