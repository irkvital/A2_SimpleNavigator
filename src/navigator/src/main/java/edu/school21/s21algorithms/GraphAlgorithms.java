package edu.school21.s21algorithms;

import edu.school21.s21algorithms.ant_algorithm.*;
import edu.school21.s21graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.school21.dataStructures.*;

public class GraphAlgorithms {

    private GraphAlgorithms() {

    }

    public static Integer[] depthFirstSearch(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices() + 1];
        List<Integer> resultWay = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                resultWay.add(vertex);
                for (int i = graph.getNumVertices(); i >= 1; i--) {
                    if (graph.getAdjacencyMatrix()[vertex - 1][i - 1] != 0 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return resultWay.toArray(Integer[]::new);
    }

    public static int[][] getLeastSpanningTree(Graph graph) {
        int numVertices = graph.getNumVertices();
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        int[][] result = new int[numVertices][numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] minWeight = new int[numVertices];
        int[] parent = new int[numVertices];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[0] = 0;
        parent[0] = -1;
        for (int i = 0; i < numVertices - 1; i++) {
            findMinWeigh(numVertices, visited, minWeight, adjacencyMatrix, parent);
        }
        for (int i = 1; i < numVertices; i++) {
            result[i][parent[i]] = minWeight[i];
            result[parent[i]][i] = minWeight[i];
        }
        return result;
    }

    private static int findMinVertex(int numVertices, boolean[] visited, int[] minWeight) {
        int minVertex = -1;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && (minVertex == -1 || minWeight[i] < minWeight[minVertex])) {
                minVertex = i;
            }
        }
        visited[minVertex] = true;
        return minVertex;
    }

    private static void findMinWeigh(int numVertices, boolean[] visited, int[] minWeight, int[][] adjacencyMatrix,
            int[] parent) {
        int minVertex = findMinVertex(numVertices, visited, minWeight);
        for (int v = 0; v < numVertices; v++) {
            if (adjacencyMatrix[minVertex][v] != 0 && !visited[v] && adjacencyMatrix[minVertex][v] < minWeight[v]) {
                parent[v] = minVertex;
                minWeight[v] = adjacencyMatrix[minVertex][v];
            }
        }
    }

    public static Integer[] breadthFirstSearch(Graph graph, int startVertex) {
        Queue<Integer> queue = new Queue<>();
        List<Integer> resultWay = new ArrayList<>(graph.getNumVertices());
        queue.push(startVertex);
        resultWay.add(startVertex);
        while (queue.getSize() != 0) {
            int poppedVertex = queue.pop();
            int[] poppedVertexConnections = getVertexConnections(graph, poppedVertex);
            for (int i = 1; i <= poppedVertexConnections.length; i++) {
                if (isNotVisited(i, poppedVertexConnections, resultWay)) {
                    queue.push(i);
                    resultWay.add(i);
                }
            }
        }

        return resultWay.toArray(Integer[]::new);
    }

    public static int getShortestPathBetweenVertices(Graph graph, int vertex1, int vertex2)
            throws IndexOutOfBoundsException {
        int numVertices = graph.getNumVertices();
        int[] vertexMarks = initVertexMarks(numVertices, vertex1);
        if ((vertex1 > 0 && vertex1 <= numVertices) && (vertex2 > 0 && vertex2 <= numVertices)) {
            boolean[] visitedVertices = initAsNonVisitedVertices(numVertices);

            while (!areAllVerticesVisited(visitedVertices)) {
                int minMarkVertex = getNonVisitedVertexWithMinMark(vertexMarks, visitedVertices);
                int[] minMarkVertexConnections = getVertexConnections(graph, minMarkVertex);
                for (int i = 1; i <= minMarkVertexConnections.length; i++) {
                    int newMark = getNewMark(i - 1, minMarkVertex - 1, minMarkVertexConnections, visitedVertices,
                            vertexMarks);
                    if (newMark < vertexMarks[i - 1]) {
                        vertexMarks[i - 1] = newMark;
                    }
                }
                visitedVertices[minMarkVertex - 1] = true;
            }
        } else {
            throw new IndexOutOfBoundsException("No such vertices!");
        }

        return vertexMarks[vertex2 - 1];
    }

    public static int[][] getShortestPathsBetweenAllVertices(Graph graph) {
        int[][] shortestPaths = initPaths(graph);
        int numVertices = graph.getNumVertices();
        for (int k = 1; k <= numVertices; k++) {
            for (int i = 1; i <= numVertices; i++) {
                for (int j = 1; j <= numVertices; j++) {
                    if (shortestPaths[i - 1][j - 1] > shortestPaths[i - 1][k - 1] + shortestPaths[k - 1][j - 1]
                            && checkNotInfinite(shortestPaths[i - 1][k - 1], shortestPaths[k - 1][j - 1])) {

                        shortestPaths[i - 1][j - 1] = shortestPaths[i - 1][k - 1] + shortestPaths[k - 1][j - 1];
                    }
                }
            }
        }
        return shortestPaths;
    }

    public static TsmResult solveTravelingSalesmanProblem(Graph graph) {
        AntsField antsField = new AntsField(graph, 10, 1, 2);
        List<TsmResult> resultsList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Ant[] ants = createAnts(antsField);
            antsMovement(ants);
            pheromoneProcessing(ants, antsField);
            resultsList.add(getByMinRoute(ants));
        }

        return getFinalResult(resultsList);
    }

    private static TsmResult getFinalResult(List<TsmResult> resultsList) {
        double maxDouble = Double.MAX_VALUE;
        TsmResult finalResult = new TsmResult();
        for (int i = 0; i < resultsList.size(); i++) {
            if (resultsList.get(i).distance < maxDouble) {
                maxDouble = resultsList.get(i).distance;
                finalResult = resultsList.get(i);
            }
        }

        return finalResult;
    }

    private static TsmResult getByMinRoute(Ant[] ants) {
        TsmResult result = new TsmResult();
        result.distance = Double.MAX_VALUE;
        for (int i = 0; i < ants.length; i++) {
            if (ants[i].getRouteLength() < result.distance) {

                result.distance = ants[i].getRouteLength();
                result.vertices = ants[i].getRoute();
            }
        }

        return result;
    }

    private static void antsMovement(Ant[] ants) {
        for (int i = 0; i < ants.length; i++) {
            ants[i].antLogic();
        }
    }

    private static void pheromoneProcessing(Ant[] ants, AntsField antsField) {
        int getNumVertices = antsField.getNumVertices();
        for (int k = 0; k < ants.length; k++) {
            double sumPheromone = 0;
            double pheromonePrevious = 0;
            double addedPheromone = 0;
            for (int i = 1; i <= getNumVertices; i++) {
                for (int j = 1; j <= getNumVertices; j++) {
                    if (i != j) {
                        pheromonePrevious = (1 - antsField.getPheromoneDecay())
                                * antsField.getAntsWillArray()[i - 1][j - 1].pheromoneValue;
                        addedPheromone = addedPheromoneOnVertex(ants[k], i, j);

                        sumPheromone = pheromonePrevious + addedPheromone;
                        antsField.setPheromoneOnRoad(i - 1, j - 1, sumPheromone);
                    }

                }
            }
        }
    }

    private static double addedPheromoneOnVertex(Ant ant, int start, int end) {
        double added = 0;
        List<Integer> route = ant.getRoute();
        for (int i = 1; i < route.size(); i++) {
            if (route.get(i - 1) == start && route.get(i) == end) {
                added = ant.addPheromone();
            }
        }

        return added;
    }

    private static Ant[] createAnts(AntsField antsField) {
        Ant[] ants = new Ant[antsField.getNumVertices()];
        for (int i = 1; i <= antsField.getNumVertices(); i++) {
            ants[i - 1] = new Ant(antsField, i);
        }

        return ants;
    }

    private static boolean checkNotInfinite(int ik, int kj) {
        return ik < Integer.MAX_VALUE && kj < Integer.MAX_VALUE;
    }

    private static int[][] initPaths(Graph graph) {
        int numVertices = graph.getNumVertices();
        int[][] initedPaths = new int[numVertices][numVertices];
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                initedPaths[i][j] = adjacencyMatrix[i][j];
            }
        }

        return initedPaths;
    }

    private static int getNewMark(int i, int minMarkVertex, int[] minMarkVertexConnections, boolean[] visitedVertices,
            int[] vertexMarks) {
        int newMark = Integer.MAX_VALUE;
        if (minMarkVertexConnections[i] > 0 && !visitedVertices[i]) {
            newMark = vertexMarks[minMarkVertex] + minMarkVertexConnections[i];
        }

        return newMark;
    }

    private static int getNonVisitedVertexWithMinMark(int[] vertexMarks, boolean[] visitedVertices) {
        int minMark = Integer.MAX_VALUE;
        int minMarkVertex = 1;
        for (int i = 1; i <= vertexMarks.length; i++) {
            if (vertexMarks[i - 1] < minMark && !visitedVertices[i - 1]) {
                minMark = vertexMarks[i - 1];
                minMarkVertex = i;
            }
        }
        return minMarkVertex;
    }

    private static boolean areAllVerticesVisited(boolean[] visitedVertices) {
        boolean allVisited = true;
        for (boolean visitedVertex : visitedVertices) {
            if (!visitedVertex) {
                allVisited = false;
                break;
            }
        }
        return allVisited;
    }

    private static boolean[] initAsNonVisitedVertices(int numVertices) {
        boolean[] visitedVertices = new boolean[numVertices];
        for (int i = 1; i <= numVertices; i++) {
            visitedVertices[i - 1] = false;
        }

        return visitedVertices;
    }

    private static int[] initVertexMarks(int numVertices, int vertex1) {
        int[] vertexMarks = new int[numVertices];
        for (int i = 1; i <= numVertices; i++) {
            vertexMarks[i - 1] = Integer.MAX_VALUE;
        }
        vertexMarks[vertex1 - 1] = 0;

        return vertexMarks;
    }

    private static boolean isNotVisited(int i, int[] poppedVertexConnections, List<Integer> resultWay) {
        return poppedVertexConnections[i - 1] > 0 && !resultWay.contains(i);
    }

    private static int[] getVertexConnections(Graph graph, int vertex) {
        int[] connections = new int[graph.getNumVertices()];
        for (int i = 1; i <= graph.getNumVertices(); i++) {
            connections[i - 1] = graph.getAdjacencyMatrix()[vertex - 1][i - 1];
        }
        return connections;
    }

}
