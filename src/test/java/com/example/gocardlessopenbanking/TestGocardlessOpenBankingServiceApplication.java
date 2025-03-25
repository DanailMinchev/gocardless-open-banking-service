package com.example.gocardlessopenbanking;

import org.springframework.boot.SpringApplication;

public class TestGocardlessOpenBankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(GocardlessOpenBankingServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
