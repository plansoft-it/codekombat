package it.plansoft;

import lombok.Data;

@Data
public class FlightRequest {

	private int id;
	private int numPersons;
	private int numHandBaggage;
	private int numExtraBaggage;
	private String departureAirport;
	private String arrivalAirport;

}
