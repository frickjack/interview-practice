package codechef;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import codechef.Dijkstra.Road;

public class DijkstraTest {
    
    @Test public void testDijkstra() {
        final var graph = new Dijkstra(
            new HashSet<>(
                Arrays.asList("Chicago", "Cleveland", "Peoria", "Lafayette", "Detroit", "Milwaukee")
            )
        );
        graph.addRoad("Chicago", "Cleveland", 300
        ).addRoad("Chicago", "Milwaukee", 200
        ).addRoad("Peoria", "Lafayette", 180
        ).addRoad("Lafayette", "Cleveland", 200
        ).addRoad("Cleveland", "Detroit", 200
        ).addRoad("Milwaukee", "Detroit", 500
        );
        final List<Dijkstra.Road> expectedPeoriaToDetroit = Arrays.asList(
            new Road("Peoria", "Lafayette", 180),
            new Road("Lafayette", "Cleveland", 200),
            new Road("Cleveland", "Detroit", 200)
        );
        final List<Road> peoriaToDetroit = graph.shortestPath("Peoria", "Detroit");
        assertEquals("Peoria to Detroit", expectedPeoriaToDetroit, peoriaToDetroit);
    }
}
