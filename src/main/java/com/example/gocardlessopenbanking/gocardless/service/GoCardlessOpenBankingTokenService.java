package com.example.gocardlessopenbanking.gocardless.service;

import com.example.gocardlessopenbanking.gocardless.domain.GoCardlessOpenBankingTokenEntity;
import com.example.gocardlessopenbanking.gocardless.repository.GoCardlessOpenBankingTokenRepository;
import com.example.gocardlessopenbanking.gocardless.service.dto.NewTokenRequest;
import com.example.gocardlessopenbanking.gocardless.service.dto.NewTokenResponse;
import com.example.gocardlessopenbanking.gocardless.service.dto.RefreshTokenRequest;
import com.example.gocardlessopenbanking.gocardless.service.dto.RefreshTokenResponse;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Slf4j
@Service
@Validated
public class GoCardlessOpenBankingTokenService {

    private final RestClient apiClient;
    private final String secretId;
    private final String secretKey;
    private final GoCardlessOpenBankingTokenRepository goCardlessOpenBankingTokenRepository;

    @Autowired
    public GoCardlessOpenBankingTokenService(RestClient.Builder restClientBuilder,
                                             @Value("${app.gocardless.api-url}") String apiUrl,
                                             @Value("${app.gocardless.secret-id}") String secretId,
                                             @Value("${app.gocardless.secret-key}") String secretKey,
                                             GoCardlessOpenBankingTokenRepository goCardlessOpenBankingTokenRepository) {
        String apiUrlWithoutTrailingSlash = apiUrl.replaceFirst("/*$", "");

        this.apiClient = restClientBuilder
                .baseUrl(apiUrlWithoutTrailingSlash)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        this.secretId = secretId;
        this.secretKey = secretKey;

        this.goCardlessOpenBankingTokenRepository = goCardlessOpenBankingTokenRepository;
    }

    public String getAccessToken() {
        Page<GoCardlessOpenBankingTokenEntity> page = goCardlessOpenBankingTokenRepository.findAll(PageRequest.of(
                0,
                1,
                Sort.by(Sort.Direction.DESC, "accessTokenIssuedAt")
        ));

        if (page.getTotalElements() == 0) {
            throw new RuntimeException("No access token found");
        }

        return page.getContent().getFirst().getAccessToken();
    }

    @Transactional
    public void refreshToken(@Positive int numberOfTokensToRefresh) {
        log.info("Started 'refreshToken(...)' with numberOfTokensToRefresh = {}", numberOfTokensToRefresh);

        Page<GoCardlessOpenBankingTokenEntity> page = goCardlessOpenBankingTokenRepository.findAll(PageRequest.of(
                0,
                numberOfTokensToRefresh,
                Sort.by(Sort.Direction.ASC, "refreshTokenIssuedAt")
        ));

        log.info("Found {} tokens", page.getTotalElements());

        if (page.getTotalElements() == 0) {
            NewTokenResponse newTokenResponse = retrieveNewToken();
            GoCardlessOpenBankingTokenEntity newGoCardlessOpenBankingTokenEntity =
                    createGoCardlessOpenBankingTokenEntity(newTokenResponse);
            goCardlessOpenBankingTokenRepository.save(newGoCardlessOpenBankingTokenEntity);
            return;
        }

        for (GoCardlessOpenBankingTokenEntity entity : page.getContent()) {
            log.info("Refreshing token id {}", entity.getId());

            RefreshTokenResponse refreshTokenResponse = refreshToken(entity.getRefreshToken());

            updateGoCardlessOpenBankingTokenEntity(entity, refreshTokenResponse);

            goCardlessOpenBankingTokenRepository.save(entity);
        }

        log.info("Finished 'refreshToken(...)'");
    }

    private NewTokenResponse retrieveNewToken() {
        return apiClient
                .post()
                .uri("/api/v2/token/new/")
                .body(NewTokenRequest.builder()
                        .secretId(secretId)
                        .secretKey(secretKey)
                        .build()
                )
                .retrieve()
                .body(NewTokenResponse.class);
    }

    private GoCardlessOpenBankingTokenEntity createGoCardlessOpenBankingTokenEntity(NewTokenResponse newTokenResponse) {
        OffsetDateTime utcNow = OffsetDateTime.now(ZoneOffset.UTC);

        return GoCardlessOpenBankingTokenEntity.builder()
                .accessToken(newTokenResponse.getAccess())
                .accessTokenIssuedAt(utcNow)
                .accessTokenExpiresAt(utcNow.plusSeconds(newTokenResponse.getAccessExpires()))
                .refreshToken(newTokenResponse.getRefresh())
                .refreshTokenIssuedAt(utcNow)
                .refreshTokenExpiresAt(utcNow.plusSeconds(newTokenResponse.getRefreshExpires()))
                .build();
    }

    private RefreshTokenResponse refreshToken(String refreshToken) {
        return apiClient
                .post()
                .uri("/api/v2/token/refresh/")
                .body(
                        RefreshTokenRequest.builder()
                                .refresh(refreshToken)
                                .build()
                )
                .retrieve()
                .body(RefreshTokenResponse.class);
    }

    private void updateGoCardlessOpenBankingTokenEntity(GoCardlessOpenBankingTokenEntity entity,
                                                        RefreshTokenResponse refreshTokenResponse) {
        OffsetDateTime utcNow = OffsetDateTime.now(ZoneOffset.UTC);

        entity.setAccessToken(refreshTokenResponse.getAccess());
        entity.setAccessTokenIssuedAt(utcNow);
        entity.setAccessTokenExpiresAt(utcNow.plusSeconds(refreshTokenResponse.getAccessExpires()));

        if (Objects.nonNull(refreshTokenResponse.getRefresh())) {
            entity.setRefreshToken(refreshTokenResponse.getRefresh());
            entity.setRefreshTokenIssuedAt(utcNow);
            entity.setRefreshTokenExpiresAt(utcNow.plusSeconds(refreshTokenResponse.getRefreshExpires()));
        }
    }

}
