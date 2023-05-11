package com.jamesclear.first.microservice.CurrencyExchangeMicroservice.Bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CurrencyExchange {

	/*
	 * "id":10001, "from":"USD", "to":"INR", "conversionMultiple":65.00,
	 * "environment":"8000 instance-id"
	 */
	  @GeneratedValue	
	  @Id
      private int id;
	  
	  @Column(name = "currency_from")
	  private String from;
	  
	  @Column(name = "currency_to")
	  private String to;
	  
	  private double conversionMultiple;
	  
	  private String environment;
	
	  
	  public CurrencyExchange() {
		super();
		// TODO Auto-generated constructor stub
	  }
	  
	public CurrencyExchange(int id, String from, String to, double conversionMultiple, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.environment = environment;
	}
	
	public int getId() {
		return id;
	  }
	
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(double conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	  	  
}
