package edu.school21;

import edu.school21.s21algorithms.GraphAlgorithms;
import edu.school21.s21graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeastSpanningTreeTests {

        @Test
        void testLeastSpanningTree1() {
                System.out.println("LeastSpanning...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "src/test/java/edu/school21/graphExamples/txts/graph5.txt");
                int[][] floydAnswers = { { 0, 7, 9, 0, 0, 0 },
                                { 7, 0, 0, 0, 0, 0 },
                                { 9, 0, 0, 0, 0, 2 },
                                { 0, 0, 0, 0, 6, 0 },
                                { 0, 0, 0, 6, 0, 9 },
                                { 0, 0, 2, 0, 9, 0 } };
                int[][] resArr = GraphAlgorithms.getLeastSpanningTree(graphWiki);
                Assertions.assertArrayEquals(floydAnswers, resArr);
        }

        @Test
        void testLeastSpanningTree2() {
                System.out.println("LeastSpanning...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "src/test/java/edu/school21/graphExamples/txts/graph6.txt");
                int[][] floydAnswers = { { 0, 0, -2, 0 },
                                { 0, 0, 0, -1 },
                                { -2, 0, 0, 2 },
                                { 0, -1, 2, 0 } };
                int[][] resArr = GraphAlgorithms.getLeastSpanningTree(graphWiki);
                Assertions.assertArrayEquals(floydAnswers, resArr);
        }

        @Test
        void testLeastSpanningTree3() {
                System.out.println("LeastSpanning...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "src/test/java/edu/school21/graphExamples/txts/graph7.txt");
                int[][] floydAnswers = { { 0, 1, 0, 0 },
                                { 1, 0, 0, 1 },
                                { 0, 0, 0, 1 },
                                { 0, 1, 1, 0 } };
                int[][] resArr = GraphAlgorithms.getLeastSpanningTree(graphWiki);
                Assertions.assertArrayEquals(floydAnswers, resArr);
        }

}
