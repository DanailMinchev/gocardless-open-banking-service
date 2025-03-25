# GoCardless open banking service

TODO

## Setup and run locally

### .env

Copy [.env.example](.env.example) into [.env](.env) file and import it into the run configuration of the application.

## Swagger

Swagger UI is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Flow

### Step 1.

Choose a Bank.

Use `GET /api/v1/institutions/{countryCode}` endpoint.

OR

skip this step and use `SANDBOXFINANCE_SFIN0000` as `institution_id` if you wish to use mock-up institution (see [Sandbox](https://developer.gocardless.com/bank-account-data/sandbox)).

### Step 2.

Build a Link.

Use `POST /api/v1/requisition/{institutionId}` endpoint.
