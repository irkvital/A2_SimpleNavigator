package edu.school21.s21algorithms.ant_algorithm;

import edu.school21.s21graph.Graph;

public class AntsField {
    private AntsWill[][] antsWillArray = null;
    private int[][] adjacencyMatrix = null;
    private int numVertices = 0;
    private double alfaCoef = 1.0;
    private double betaCoef = 1.0;
    private double pheromoneDecay = 0.2;
    private double pheromoneInit = 0.5;

    public AntsField(Graph graph, double inverseCoef, double alfaCoef, double betaCoef) {
        this.alfaCoef = alfaCoef;
        this.betaCoef = betaCoef;
        this.numVertices = graph.getNumVertices();
        this.adjacencyMatrix = graph.getAdjacencyMatrix();
        antsWillArray = new AntsWill[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                antsWillArray[i][j] = new AntsWill(Math.pow(pheromoneInit, this.alfaCoef),
                        adjacencyMatrix[i][j] > 0 ? Math.pow(inverseCoef / adjacencyMatrix[i][j], this.betaCoef) : 0);
            }
        }

    }

    public double getPheromoneDecay() {
        return pheromoneDecay;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public AntsWill[][] getAntsWillArray() {
        return antsWillArray;
    }

    public void setPheromoneOnRoad(int i, int j, double pheromone) {
        antsWillArray[i][j].pheromoneValue = pheromone;
    }

}
