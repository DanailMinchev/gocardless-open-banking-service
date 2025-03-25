package com.example.gocardlessopenbanking.gocardless.repository;

import com.example.gocardlessopenbanking.gocardless.domain.GoCardlessOpenBankingTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoCardlessOpenBankingTokenRepository extends JpaRepository<GoCardlessOpenBankingTokenEntity, Long> {
}
