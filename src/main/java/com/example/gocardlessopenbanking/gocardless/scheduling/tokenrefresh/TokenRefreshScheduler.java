package com.example.gocardlessopenbanking.gocardless.scheduling.tokenrefresh;

import com.example.gocardlessopenbanking.gocardless.service.GoCardlessOpenBankingTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenRefreshScheduler {

    private final GoCardlessOpenBankingTokenService goCardlessOpenBankingTokenService;

    @Scheduled(fixedRate = 6, timeUnit = TimeUnit.HOURS)
    public void refreshOpenBankingTokens() {
        log.info("Started 'Refresh open banking tokens' scheduled task");

        goCardlessOpenBankingTokenService.refreshToken(1);

        log.info("Finished 'Refresh open banking tokens' scheduled task");
    }

}
