package com.example.gocardlessopenbanking.gocardless.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class GoCardlessOpenBankingClientConfiguration {

    @Bean
    public GoCardlessOpenBankingClientFactory goCardlessOpenBankingClientFactory(
            RestClient.Builder restClientBuilder,
            @Value("${app.gocardless.api-url}") String apiUrl
    ) {
        String apiUrlWithoutTrailingSlash = apiUrl.replaceFirst("/*$", "");
        return new GoCardlessOpenBankingClientFactory(restClientBuilder, apiUrlWithoutTrailingSlash);
    }

}
