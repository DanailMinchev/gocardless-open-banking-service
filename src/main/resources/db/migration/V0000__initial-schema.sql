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

CREATE TABLE IF NOT EXISTS gocardless_requisitions
(
    id                 BIGSERIAL PRIMARY KEY,
    reference          UUID         NOT NULL,
    redirect_url       VARCHAR(255) NOT NULL,
    institution_id     VARCHAR(255) NOT NULL,
    requisition_id     VARCHAR(255) NOT NULL,
    requisition_status VARCHAR(255) NOT NULL,
    requisition_link   VARCHAR(255) NOT NULL
);
ALTER TABLE gocardless_requisitions
    ADD CONSTRAINT uc_gocardless_requisitions_reference UNIQUE (reference);
ALTER TABLE gocardless_requisitions
    ADD CONSTRAINT uc_gocardless_requisitions_redirect_url UNIQUE (redirect_url);
ALTER TABLE gocardless_requisitions
    ADD CONSTRAINT uc_gocardless_requisitions_requisition_id UNIQUE (requisition_id);
ALTER TABLE gocardless_requisitions
    ADD CONSTRAINT uc_gocardless_requisitions_requisition_link UNIQUE (requisition_link);
