package it.plansoft;

import lombok.Data;

@Data
public class Flight {

	private Long id;
	private Double price;
	private Double handBaggagePrice;
	private Double extraBaggagePrice;
	private String departureAirport;
	private String arrivalAirport;

}
