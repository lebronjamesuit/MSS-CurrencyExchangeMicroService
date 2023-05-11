package com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Bean.CurrencyExchange;
import com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Service.CurrencyRepo;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ServerProperties serverProperties;
	
	@Autowired
	private CurrencyRepo currencyRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange exchangeCurrency(@PathVariable("from") String from
								  ,@PathVariable("to") String to) {
		
		// both can be useful
		System.out.println(environment.getProperty("local.server.port"));
		System.out.println(serverProperties.getPort());
		
		String port = serverProperties.getPort().toString();
		
		CurrencyExchange currencyExchange = currencyRepo.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("no found");
		}
		currencyExchange.setEnvironment(port);
			
		return currencyExchange;
	}
}
