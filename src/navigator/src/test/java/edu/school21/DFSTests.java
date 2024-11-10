package edu.school21;

import edu.school21.s21algorithms.GraphAlgorithms;
import edu.school21.s21graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DFSTests {
    @Test
    void testGraph_1() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "src/test/java/edu/school21/graphExamples/txts/graph1.txt");
        Integer[][] dfsAnswers = { { 1, 2, 3, 4 },
                { 2, 1, 3, 4 },
                { 3, 2, 1, 4 },
                { 4, 2, 1, 3 } };
        System.out.println("Testing graph1.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] dfs = GraphAlgorithms.depthFirstSearch(graph, i);
            Integer[] dfsExpected = dfsAnswers[i - 1];
            Assertions.assertArrayEquals(dfs, dfsExpected);
            System.out.println(Arrays.toString(dfs) + " <--> " + Arrays.toString(dfsExpected));
        }
    }

    @Test
    void testGraph_2() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "src/test/java/edu/school21/graphExamples/txts/graph2.txt");
        Integer[][] dfsAnswers = { { 1, 2, 4, 5, 3 },
                { 2, 1, 3, 5, 4 },
                { 3, 1, 2, 4, 5 },
                { 4, 2, 1, 3, 5 },
                { 5, 2, 1, 3, 4 } };
        System.out.println("Testing graph2.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] dfs = GraphAlgorithms.depthFirstSearch(graph, i);
            Integer[] dfsExpected = dfsAnswers[i - 1];
            Assertions.assertArrayEquals(dfs, dfsExpected);
            System.out.println(Arrays.toString(dfs) + " <--> " + Arrays.toString(dfsExpected));
        }
    }

    @Test
    void testGraph_3() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "src/test/java/edu/school21/graphExamples/txts/graph3.txt");
        Integer[][] dfsAnswers = { { 1, 2, 3, 4, 5, 6, 7, 8 },
                { 2, 1, 8, 3, 4, 5, 6, 7 },
                { 3, 2, 1, 8, 4, 5, 6, 7 },
                { 4, 3, 2, 1, 8, 5, 6, 7 },
                { 5, 4, 3, 2, 1, 8, 6, 7 },
                { 6, 5, 4, 3, 2, 1, 8, 7 },
                { 7, 5, 4, 3, 2, 1, 8, 6 },
                { 8, 1, 2, 3, 4, 5, 6, 7 } };
        System.out.println("Testing graph3.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] dfs = GraphAlgorithms.depthFirstSearch(graph, i);
            Integer[] dfsExpected = dfsAnswers[i - 1];
            Assertions.assertArrayEquals(dfs, dfsExpected);
            System.out.println(Arrays.toString(dfs) + " <--> " + Arrays.toString(dfsExpected));
        }
    }

    @Test
    void testGraph_4() {
        Graph graph = new Graph();
        graph.loadGraphFromFile(
                "src/test/java/edu/school21/graphExamples/txts/graph4.txt");
        Integer[][] dfsAnswers = { { 1, 2, 3, 4, 5, 6 },
                { 2, 1, 5, 4, 3, 6 },
                { 3, 2, 1, 5, 4, 6 },
                { 4, 3, 2, 1, 5, 6 },
                { 5, 1, 2, 3, 4, 6 },
                { 6, 4, 3, 2, 1, 5 } };
        System.out.println("Testing graph4.txt...");
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            Integer[] dfs = GraphAlgorithms.depthFirstSearch(graph, i);
            Integer[] dfsExpected = dfsAnswers[i - 1];
            Assertions.assertArrayEquals(dfs, dfsExpected);
            System.out.println(Arrays.toString(dfs) + " <--> " + Arrays.toString(dfsExpected));
        }
    }
}
