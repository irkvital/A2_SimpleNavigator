package edu.school21;

import java.util.Arrays;
import org.junit.jupiter.api.*;

import edu.school21.s21algorithms.GraphAlgorithms;
import edu.school21.s21graph.Graph;

class BFSTests {

    @Test
    void testGraph_1() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph1.txt");
        Integer[][] bfsAnswers = { { 1, 2, 3, 4 },
                { 2, 1, 3, 4 },
                { 3, 2, 1, 4 },
                { 4, 2, 1, 3 } };
        System.out.println("Testing graph1.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] bfs = GraphAlgorithms.breadthFirstSearch(graph, i);
            Integer[] bfsExpected = bfsAnswers[i - 1];
            Assertions.assertArrayEquals(bfs, bfsExpected);
            System.out.println(Arrays.toString(bfs) + " <--> " + Arrays.toString(bfsExpected));
        }
    }

    @Test
    void testGraph_2() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph2.txt");
        Integer[][] bfsAnswers = { { 1, 2, 3, 4, 5 },
                { 2, 1, 4, 5, 3 },
                { 3, 1, 5, 2, 4 },
                { 4, 2, 1, 5, 3 },
                { 5, 2, 3, 1, 4 } };
        System.out.println("Testing graph2.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] bfs = GraphAlgorithms.breadthFirstSearch(graph, i);
            Integer[] bfsExpected = bfsAnswers[i - 1];
            Assertions.assertArrayEquals(bfs, bfsExpected);
            System.out.println(Arrays.toString(bfs) + " <--> " + Arrays.toString(bfsExpected));
        }
    }

    @Test
    void testGraph_3() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph3.txt");
        Integer[][] bfsAnswers = { { 1, 2, 8, 3, 4, 5, 6, 7 },
                { 2, 1, 3, 8, 4, 5, 6, 7 },
                { 3, 2, 4, 1, 5, 8, 6, 7 },
                { 4, 3, 5, 2, 6, 7, 1, 8 },
                { 5, 4, 6, 7, 3, 2, 1, 8 },
                { 6, 5, 7, 4, 3, 2, 1, 8 },
                { 7, 5, 6, 4, 3, 2, 1, 8 },
                { 8, 1, 2, 3, 4, 5, 6, 7 } };
        System.out.println("Testing graph3.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] bfs = GraphAlgorithms.breadthFirstSearch(graph, i);
            Integer[] bfsExpected = bfsAnswers[i - 1];
            Assertions.assertArrayEquals(bfs, bfsExpected);
            System.out.println(Arrays.toString(bfs) + " <--> " + Arrays.toString(bfsExpected));
        }
    }

    @Test
    void testGraph_4() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph4.txt");
        Integer[][] bfsAnswers = { { 1, 2, 5, 3, 4, 6 },
                { 2, 1, 3, 5, 4, 6 },
                { 3, 2, 4, 1, 5, 6 },
                { 4, 3, 5, 6, 2, 1 },
                { 5, 1, 2, 4, 3, 6 },
                { 6, 4, 3, 5, 2, 1 } };
        System.out.println("Testing graph4.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] bfs = GraphAlgorithms.breadthFirstSearch(graph, i);
            Integer[] bfsExpected = bfsAnswers[i - 1];
            Assertions.assertArrayEquals(bfs, bfsExpected);
            System.out.println(Arrays.toString(bfs) + " <--> " + Arrays.toString(bfsExpected));
        }
    }

}
