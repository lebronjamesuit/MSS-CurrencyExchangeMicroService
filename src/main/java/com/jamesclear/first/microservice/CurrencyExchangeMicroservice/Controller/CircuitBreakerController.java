package com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CurrentTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import reactor.core.publisher.Mono;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	private  final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private static final String CircuitBreakerName = "circuitBreaker-def";
	private static final String RetryName = "retry-abc";
	
	
	@GetMapping("/retry/abc")
	@Retry(name = RetryName , fallbackMethod = "justFallBackRetry")
	public String sampleApi() {
		logger.info("Minh Retry name retry-abc called");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:5000/some-dummy-url", 
					String.class);
		return forEntity.getBody();
		
		//return "minh";
		
	}
	
	public String justFallBackRetry(Exception e) {
		return "No method circuitBreaker/abc" ;
		
 	}
	
	
	@GetMapping("/circuitBreaker/def")
	@CircuitBreaker(name = CircuitBreakerName, fallbackMethod = "justFallBackSecondAPI" )
	public String secondApi() {
		logger.info("Minh @CircuitBreaker name circuitBreaker-def calledreceived");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:5000/some-dummy-url", 
					String.class);
		return forEntity.getBody();
		
		//return "minh";
		
	}
		
	public String justFallBackSecondAPI(Exception e){
			
		LocalTime localTime = LocalTime.now();
		 
		logger.info(   dtf.format(localTime)+  " Fall back Exception/circuitBreaker/def");
		return " Fall back Exception/circuitBreaker/def ";
	}
	
	
	@GetMapping("/no/circuit/def")
	public String thridApi() {
		logger.info("Minh NO Circuit thridApi");
		logger.info(LocalDate.now().toString());
		return "minh pretend is fails";
		
	}
	
	
}
