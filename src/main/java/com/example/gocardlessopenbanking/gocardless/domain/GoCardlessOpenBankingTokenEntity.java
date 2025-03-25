package com.example.gocardlessopenbanking.gocardless.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "gocardless_ob_tokens")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GoCardlessOpenBankingTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @NotNull
    @Column(name = "access_token_issued_at", nullable = false)
    private OffsetDateTime accessTokenIssuedAt;

    @NotNull
    @Column(name = "access_token_expires_at", nullable = false)
    private OffsetDateTime accessTokenExpiresAt;

    @NotBlank
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @NotNull
    @Column(name = "refresh_token_issued_at", nullable = false)
    private OffsetDateTime refreshTokenIssuedAt;

    @NotNull
    @Column(name = "refresh_token_expires_at", nullable = false)
    private OffsetDateTime refreshTokenExpiresAt;

}
