package edu.school21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.school21.s21algorithms.GraphAlgorithms;
import edu.school21.s21algorithms.ant_algorithm.*;
import edu.school21.s21graph.Graph;

class AntTest {
    @Test
    void testAntsExample() {
        System.out.println("Testing Ants Algorithm...");
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph8.txt");
        TsmResult antResult = GraphAlgorithms.solveTravelingSalesmanProblem(graph);
        double ansDouble = 253.0;
        System.out.println(antResult.distance + "\n" + antResult.vertices.toString());
        Assertions.assertEquals(antResult.distance, ansDouble);

    }
}
