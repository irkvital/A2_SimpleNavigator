package edu.school21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.school21.s21algorithms.*;
import edu.school21.s21graph.Graph;

class ShortestPathTest {

        @Test
        void testDejkstraWikiExample() {
                System.out.println("Testing ShortestPath...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph5.txt");
                Assertions.assertEquals(0, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 1));
                Assertions.assertEquals(7, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 2));
                Assertions.assertEquals(9, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 3));
                Assertions.assertEquals(20, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 4));
                Assertions.assertEquals(20, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 5));
                Assertions.assertEquals(11, GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 1, 6));
                Assertions.assertThrows(java.lang.IndexOutOfBoundsException.class,
                                () -> GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 2, -10));
                Assertions.assertThrows(java.lang.IndexOutOfBoundsException.class,
                                () -> GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 2, 10));
                Assertions.assertThrows(java.lang.IndexOutOfBoundsException.class,
                                () -> GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, -10, 2));
                Assertions.assertThrows(java.lang.IndexOutOfBoundsException.class,
                                () -> GraphAlgorithms.getShortestPathBetweenVertices(graphWiki, 10, 2));
        }

        @Test
        void testFloydWikiExample() {
                System.out.println("Testing ShortestPath...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph6.txt");
                int[][] floydAnswers = { { 0, -1, -2, 0 },
                                { 4, 0, 2, 4 },
                                { 5, 1, 0, 2 },
                                { 3, -1, 1, 0 } };
                int[][] resArr = GraphAlgorithms.getShortestPathsBetweenAllVertices(graphWiki);
                Assertions.assertArrayEquals(floydAnswers, resArr);

        }

        @Test
        void testFloydWikiExample2() {
                System.out.println("Testing ShortestPath...");
                Graph graphWiki = new Graph();
                graphWiki.loadGraphFromFile(
                                "/home/vital/A2_SimpleNavigator_v1.0_Java-1/src/navigator/src/test/java/edu/school21/graphExamples/txts/graph7.txt");
                int[][] floydAnswers = { { 0, 1, 3, 2 },
                                { 2147483647, 0, 2, 1 },
                                { 2147483647, 2147483647, 0, 2147483647 },
                                { 2147483647, 2147483647, 1, 0 } };
                int[][] resArr = GraphAlgorithms.getShortestPathsBetweenAllVertices(graphWiki);
                Assertions.assertArrayEquals(floydAnswers, resArr);

        }

}
