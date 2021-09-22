package it.plansoft;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		for (int i = 1; i < 7; i++) {
			final ObjectMapper objectMapper = new ObjectMapper();
			var flightsJson = Files.readString(Paths.get("java/files/" + i + "/flights.json"), StandardCharsets.UTF_8);
			var flightRequestJson = Files.readString(Paths.get("java/files/" + i + "/flight_request.json"), StandardCharsets.UTF_8);

			var flightList = objectMapper.readValue(flightsJson, new TypeReference<List<Flight>>() {});
			var flightRequest = objectMapper.readValue(flightRequestJson, FlightRequest.class);

			var airports = flightList.stream()
					.map(flight -> List.of(flight.getArrivalAirport(), flight.getDepartureAirport()))
					.flatMap(List::stream)
					.distinct()
					.map(Airport::new)
					.collect(Collectors.toList());
			calculateResult(airports, flightList, flightRequest);
		}


	}

	private static void calculateResult(List<Airport> airports, List<Flight> flightList, FlightRequest flightRequest) {
		var flightRelations = flightList.stream()
				.map(flight -> {
					var weight = (flight.getPrice() * flightRequest.getNumPersons())
							+ (flight.getHandBaggagePrice() * flightRequest.getNumHandBaggage())
							+ (flight.getExtraBaggagePrice() * flightRequest.getNumExtraBaggage());

					return FlightRelation.builder()
							.id(flight.getId())
							.to(new Airport(flight.getArrivalAirport()))
							.from(new Airport(flight.getDepartureAirport()))
							.totalPrice(weight)
							.build();
				})
				.collect(Collectors.toList());

		SimpleDirectedWeightedGraph<Airport, FlightRelation> graph = new SimpleDirectedWeightedGraph<>(FlightRelation.class);
		airports.forEach(graph::addVertex);

		for (FlightRelation flightRelation : flightRelations) {
			FlightRelation edge = graph.addEdge(flightRelation.getFrom(), flightRelation.getTo());
			edge.setId(flightRelation.getId());
			edge.setFrom(flightRelation.getFrom());
			edge.setTo(flightRelation.getTo());
			edge.setTotalPrice(flightRelation.getTotalPrice());
			graph.setEdgeWeight(edge, flightRelation.getWeight());
		}

		var from = new Airport(flightRequest.getDepartureAirport());
		var to = new Airport(flightRequest.getArrivalAirport());

		DijkstraShortestPath<Airport, FlightRelation> shortestPath = new DijkstraShortestPath<>(graph);
		var finalPath = shortestPath.getPath(from, to);

		var result = finalPath.getEdgeList()
				.stream()
				.map(FlightRelation::getId)
				.collect(Collectors.toList());

		System.out.println(result);
		//System.out.println(finalPath.getWeight());
	}
}
