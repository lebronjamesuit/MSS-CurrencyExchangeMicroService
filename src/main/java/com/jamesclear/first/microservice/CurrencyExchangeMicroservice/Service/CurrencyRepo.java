package com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Bean.CurrencyExchange;

public interface CurrencyRepo extends JpaRepository<CurrencyExchange, Integer> {

	 CurrencyExchange findByFromAndTo(String from, String to);
	
}
