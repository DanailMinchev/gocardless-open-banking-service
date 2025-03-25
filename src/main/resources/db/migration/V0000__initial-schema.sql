CREATE TABLE IF NOT EXISTS gocardless_ob_tokens
(
    id                       BIGSERIAL PRIMARY KEY,
    access_token             VARCHAR(3000)            NOT NULL,
    access_token_issued_at   TIMESTAMP WITH TIME ZONE NOT NULL,
    access_token_expires_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    refresh_token            VARCHAR(3000)            NOT NULL,
    refresh_token_issued_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    refresh_token_expires_at TIMESTAMP WITH TIME ZONE NOT NULL
);
