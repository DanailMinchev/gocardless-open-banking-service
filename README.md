# GoCardless open banking service

TODO

## Setup and run locally

### .env

Copy [.env.example](.env.example) into [.env](.env) file and import it into the run configuration of the application.

## Swagger

Swagger UI is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Flow

### Step 1.

Choose a bank.

Use `GET /api/v1/institutions/{countryCode}` endpoint.

OR

skip this step and use `SANDBOXFINANCE_SFIN0000` as `institution_id` if you wish to use mock-up institution (
see [Sandbox](https://developer.gocardless.com/bank-account-data/sandbox)).

### Step 2.

Build a link.

Use `POST /api/v1/requisition` endpoint.

### Step 3.

List bank accounts.

Use `GET /api/v1/requisition/{requisitionId}` endpoint.

### Step 4.

List transactions by bank account id.

Use `GET /api/v1/transactions/{accountId}/booked` endpoint.
