package it.plansoft.codekombat;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Service
public class Solver {

    public Solver() {
    }

    public List<Integer> solve(Input input, List<Flight> flights) {
        Map<String, List<Flight>> allFlights = flights.stream().collect(Collectors.groupingBy(Flight::getDepartureAirport));

        List<List<Flight>> paths = findPaths(input.getDepartureAirport(), input.getArrivalAirport(), emptyList(), allFlights, emptyList());

        List<AbstractMap.SimpleEntry<Float, List<Integer>>> results = paths.stream()
                .filter(fl -> !fl.isEmpty())
                .map(fl -> new AbstractMap.SimpleEntry<>(totalPrice(input, fl), fl.stream().map(Flight::getId).collect(Collectors.toList())))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());

        return results.stream().findFirst().map(AbstractMap.SimpleEntry::getValue).orElse(emptyList());
    }

    public Float totalPrice(Input input, List<Flight> flights) {
        return flights.stream()
                .map(f -> totalPrice(input, f))
                .reduce(0f, Float::sum);
    }

    public Float totalPrice(Input input, Flight flight) {
        return input.getNumPersons() * flight.getPrice()
                + input.getNumExtraBaggage() * flight.getExtraBaggagePrice()
                + input.getNumHandBaggage() * flight.getHandBaggagePrice();
    }

    public List<Flight> validFlights(String departureAirport, Collection<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getDepartureAirport().equals(departureAirport))
                .collect(Collectors.toList());
    }

    public List<List<Flight>> findPaths(String departureAirport, String arrivalAirport, List<Flight> prevFlights, Map<String, List<Flight>> allFlights, List<String> visitedAirport) {
        List<List<Flight>> results = new ArrayList<>();
        if (visitedAirport.contains(departureAirport)) {
            return emptyList();
        }

        List<Flight> flights = allFlights.getOrDefault(departureAirport, emptyList());
        if (flights.isEmpty()) {
            return emptyList();
        }

        for (Flight flight : flights) {
            if (flight.getArrivalAirport().equals(arrivalAirport)) {
                results.add(Stream.concat(prevFlights.stream(), Stream.of(flight)).collect(Collectors.toList()));
            } else {
                List<List<Flight>> paths = findPaths(
                        flight.getArrivalAirport(),
                        arrivalAirport,
                        singletonList(flight),
                        allFlights,
                        Stream.concat(visitedAirport.stream(), Stream.of(departureAirport)).collect(Collectors.toList())
                );
                if (!paths.isEmpty()) {
                    for (List<Flight> path : paths) {
                        results.add(Stream.concat(prevFlights.stream(), path.stream()).collect(Collectors.toList()));
                    }
                }
            }
        }

        return results;
    }
}
