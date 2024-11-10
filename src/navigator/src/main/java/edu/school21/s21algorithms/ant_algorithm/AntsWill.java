package edu.school21.s21algorithms.ant_algorithm;

public class AntsWill {
    public double pheromoneValue = 0;
    public double inverseToDistanceValue = 0;

    public AntsWill(double pheromoneValue, double inverseToDistanceValue) {
        this.pheromoneValue = pheromoneValue;
        this.inverseToDistanceValue = inverseToDistanceValue;
    }
}
