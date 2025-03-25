package com.example.gocardlessopenbanking.gocardless.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class GoCardlessOpenBankingClientFactory {

    private final RestClient.Builder restClientBuilder;
    private final String apiUrl;

    private final Map<String, GoCardlessOpenBankingClientFacade> clients = new ConcurrentHashMap<>();

    public GoCardlessOpenBankingClientFacade getClientFor(String accessToken) {
        return clients.computeIfAbsent(accessToken, token -> {
            GoCardlessOpenBankingClientWrapper clientWrapper = new GoCardlessOpenBankingClientWrapper(createApiClient(token));
            return new GoCardlessOpenBankingClientFacade(clientWrapper);
        });
    }

    private RestClient createApiClient(String accessToken) {
        return restClientBuilder
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", String.format("Bearer %s", accessToken))
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
