package it.plansoft.codekombat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private Solver solver;

    @BeforeEach
    void setUp() {
        solver = new Solver();
    }

    @Test
    void RETURNS_LIST_OF_ID_OF_VALID_FLIGHTS() {
        List<Flight> result = solver.validFlights("CWB", asList(
                new Flight(1, 142.5f, 28.5f, 42.75f, "CWB", "MFE"),
                new Flight(2, 190f, 38f, 57f, "ROC", "HHH")
        ));

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void RETURNS_LIST_OF_PATHS_FOR_SINGLE_FLIGHT() {
        Map<String, List<Flight>> allFlights = Map.of(
                "CWB", singletonList(new Flight(1, 142.5f, 28.5f, 42.75f, "CWB", "MFE")),
                "ROC", singletonList(new Flight(2, 190f, 38f, 57f, "ROC", "HHH"))
        );

        List<List<Flight>> paths = solver.findPaths("CWB", "MFE", emptyList(), allFlights, emptyList());

        assertEquals(1, paths.size());
        assertEquals(1, paths.get(0).get(0).getId());
    }

    @Test
    void RETURNS_LIST_OF_PATHS_FOR_TWO_JUMP_FLIGHT() {
        Map<String, List<Flight>> allFlights = Map.of(
                "HHH", singletonList(new Flight(1, 142.5f, 28.5f, 42.75f, "HHH", "MFE")),
                "ROC", singletonList(new Flight(2, 190f, 38f, 57f, "ROC", "HHH"))
        );

        List<List<Flight>> paths = solver.findPaths("ROC", "MFE", emptyList(), allFlights, emptyList());

        assertEquals(1, paths.size());
//        assertEquals(asList(2, 1), paths.get(0));
    }

    @Test
    void RETURNS_LIST_OF_PATHS_FOR_FIVE_JUMP_FLIGHT() {
        Map<String, List<Flight>> allFlights = Map.of(
                "EEE", singletonList(new Flight(1, 142.5f, 28.5f, 42.75f, "EEE", "FFF")),
                "CCC", singletonList(new Flight(2, 190f, 38f, 57f, "CCC", "DDD")),
                "DDD", singletonList(new Flight(3, 190f, 38f, 57f, "DDD", "EEE")),
                "BBB", singletonList(new Flight(4, 190f, 38f, 57f, "BBB", "CCC")),
                "AAA", singletonList(new Flight(5, 190f, 38f, 57f, "AAA", "BBB"))
        );

        List<List<Flight>> paths = solver.findPaths("AAA", "FFF", emptyList(), allFlights, emptyList());

        assertEquals(1, paths.size());
//        assertEquals(asList(5,4,2,3,1), paths.get(0));
    }

    @Test
    void RETURNS_LIST_OF_MULTIPLE_PATHS() {
        Map<String, List<Flight>> allFlights = Map.of(
                "AAA", Arrays.asList(
                        new Flight(1, 142.5f, 28.5f, 42.75f, "AAA", "BBB"),
                        new Flight(2, 190f, 38f, 57f, "AAA", "CCC")),
                "BBB", singletonList(new Flight(3, 190f, 38f, 57f, "BBB", "DDD")),
                "DDD", singletonList(new Flight(4, 190f, 38f, 57f, "DDD", "CCC"))
        );

        List<List<Flight>> paths = solver.findPaths("AAA", "CCC", emptyList(), allFlights, emptyList());

        assertEquals(2, paths.size());
//        assertTrue(paths.contains(singletonList(2)));
//        assertTrue(paths.contains(asList(1,3,4)));
    }

    @Test
    void SUM_PRICES_OF_ONE_FLIGHT() {
        Float totalPrice = solver.totalPrice(new Input(1, 2, 2, 2, "AAA", "BBB"), new Flight(1, 55.5f, 55.5f, 55.5f, "AAA", "BBB"));

        assertEquals(333f, totalPrice);
    }
}
