package edu.school21.s21algorithms.ant_algorithm;

import java.util.Random;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ant {
    private AntsField antsField = null;
    private int currentVertex = 0;
    private int startVertex = 0;
    private double qValue = 4.0;
    private List<Integer> route = new ArrayList<>();
    private int routeLength = 0;

    public Ant(AntsField antsField, int startVertex) {
        this.antsField = antsField;
        this.startVertex = startVertex;
        this.currentVertex = startVertex;

    }

    public double getRouteLength() {
        return routeLength;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public double addPheromone() {
        return qValue / routeLength;
    }

    public void antLogic() {
        boolean[] visitedVertices = new boolean[antsField.getNumVertices()];
        visitedVertices[startVertex - 1] = true;
        route.add(startVertex);
        while (!areAllVerticesVisited(visitedVertices)) {
            int vertexBeforeStep = currentVertex;
            currentVertex = makeDecision(visitedVertices);
            route.add(currentVertex);
            routeLength += antsField.getAdjacencyMatrix()[vertexBeforeStep - 1][currentVertex - 1];
            visitedVertices[currentVertex - 1] = true;
        }
        route.add(startVertex);
        routeLength += antsField.getAdjacencyMatrix()[currentVertex - 1][startVertex - 1];
    }

    public int makeDecision(boolean[] visitedVertices) {
        AntsWill[] connections = getConnections(currentVertex);
        Map<Integer, Double> wills = new HashMap<>();
        for (int i = 1; i <= connections.length; i++) {
            if (!visitedVertices[i - 1]) {
                wills.put(i - 1, connections[i - 1].pheromoneValue
                        * connections[i - 1].inverseToDistanceValue);
            }
        }
        double[] willsArray = arrayFromWills(wills);
        double totalWill = willsSum(wills);
        double[] probabilities = probabilitiesCalc(willsArray, totalWill);

        return makeRandomChoice(probabilities, wills, willsArray) + 1;
    }

    private double[] arrayFromWills(Map<Integer, Double> wills) {
        double[] arr = new double[wills.size()];
        int i = 0;
        for (Map.Entry<Integer, Double> wEntry : wills.entrySet()) {
            arr[i] = wEntry.getValue();
            i++;
        }

        return arr;
    }

    private double willsSum(Map<Integer, Double> wills) {
        double sum = 0;
        for (Map.Entry<Integer, Double> wEntry : wills.entrySet()) {
            sum += wEntry.getValue();
        }

        return sum;
    }

    private int makeRandomChoice(double[] probabilities, Map<Integer, Double> wills, double[] willsArray) {
        Random randomObj = new Random();
        double generatedNum = randomObj.nextDouble();
        int chosenVertex = 0;
        double[] probabilitiesArr = createProbabilitiesArr(probabilities);
        for (int i = 1; i < probabilitiesArr.length; i++) {
            if (generatedNum >= probabilitiesArr[i - 1] && generatedNum < probabilitiesArr[i]) {
                for (Map.Entry<Integer, Double> wEntry : wills.entrySet()) {
                    if (wEntry.getValue() == willsArray[i - 1]) {
                        chosenVertex = wEntry.getKey();
                        break;
                    }

                }
                break;
            }
        }

        return chosenVertex;
    }

    private AntsWill[] getConnections(int vertex) {
        AntsWill[][] antsWillArr = antsField.getAntsWillArray();
        int numVertices = antsField.getNumVertices();

        AntsWill[] connections = new AntsWill[numVertices];
        for (int i = 1; i <= numVertices; i++) {
            connections[i - 1] = antsWillArr[vertex - 1][i - 1];
        }

        return connections;
    }

    private double[] createProbabilitiesArr(double[] probabilities) {

        // Arrays.sort(probabilities);
        double[] probabilitiesArr = new double[probabilities.length + 1];
        probabilitiesArr[0] = 0;
        for (int i = 1; i < probabilitiesArr.length; i++) {
            probabilitiesArr[i] = probabilitiesArr[i - 1] + probabilities[i - 1];
        }

        return probabilitiesArr;
    }

    private double[] probabilitiesCalc(double[] willsArray, double total) {
        double[] probabilities = new double[willsArray.length];
        for (int i = 0; i < willsArray.length; i++) {
            probabilities[i] = willsArray[i] / total;
        }

        return probabilities;
    }

    private boolean areAllVerticesVisited(boolean[] visitedVertices) {
        boolean allVisited = true;
        for (boolean visitedVertex : visitedVertices) {
            if (!visitedVertex) {
                allVisited = false;
                break;
            }
        }
        return allVisited;
    }

}
