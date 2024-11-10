package edu.school21.s21graph;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void loadGraphFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            int size = Integer.parseInt(bufferedReader.readLine().trim());
            adjacencyMatrix = new int[size][size];
            String line;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                for (int col = 0; col < size; col++) {
                    adjacencyMatrix[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
            numVertices = size;
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка загрузки графа из файла: " + e.getMessage());
        }
    }

    public void exportGraphToDot(String filename) {
        try {
            Path filePath = Paths.get("src/main/java/edu/school21/graphDot/" + filename + ".dot");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath.toFile()));

            bufferedWriter.write("graph " + filename + "{\n");
            for (int i = 0; i < numVertices; i++) {
                bufferedWriter.write("\t" + i + ";\n");
            }
            for (int i = 0; i < numVertices; i++) {
                for (int j = i + 1; j < numVertices; j++) {
                    if (adjacencyMatrix[i][j] != 0) {
                        bufferedWriter.write("\t" + i + " -- " + j + " [label=\"" + adjacencyMatrix[i][j] + "\"];\n");
                    }
                }
            }
            bufferedWriter.write("}");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMatrix() {
        System.out.println(numVertices);
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
