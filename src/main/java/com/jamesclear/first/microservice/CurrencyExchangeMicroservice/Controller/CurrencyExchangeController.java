package com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Bean.CurrencyExchange;
import com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Service.CurrencyRepo;

import java.util.List;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
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
		
		logger.info("Log at CurrencyExchangeController.class, path exchangeCurrency");
		//currency-exchange-service,
		// Trace id: 7db302f5ab910385889429ef76018255,f97b4bb4d6ac7cc0
		
		String port = serverProperties.getPort().toString();
		
		CurrencyExchange currencyExchange = currencyRepo.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("no found");
		}
		currencyExchange.setEnvironment(port);
			
		return currencyExchange;
	}

	@GetMapping("/currency-exchange/all")
	public List<CurrencyExchange> exchangeCurrency() {
		return currencyRepo.findAll();
	}

}
