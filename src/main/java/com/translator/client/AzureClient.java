package com.translator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "azureClient", url = "${azure.translator.url}", path = "${azure.translator.path}")
public interface AzureClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String translate(@RequestParam(value = "to") final List<String> languages, final String text);
}
