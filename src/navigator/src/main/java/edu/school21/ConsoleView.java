package edu.school21;

import edu.school21.s21algorithms.GraphAlgorithms;
import edu.school21.s21algorithms.ant_algorithm.TsmResult;
import edu.school21.s21graph.Graph;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleView {

    private Graph graph = new Graph();

    private Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {
        int choice = 0;
        while (choice != 8) {
            showChoice();
            checkForInt();
            choice = scanner.nextInt();
            checkChoice(choice);
        }
    }

    private void showChoice() {
        System.out.println("Меню:");
        System.out.println("1. Загрузка исходного графа из файла");
        System.out.println("2. Обход графа в ширину с выводом результата обхода в консоль");
        System.out.println("3. Обход графа в глубину с выводом результата обхода в консоль");
        System.out.println(
                "4. Поиск кратчайшего пути между произвольными двумя вершинами с выводом результата в консоль");
        System.out.println(
                "5. Поиск кратчайших путей между всеми парами вершин в графе с выводом результирующей матрицы в консоль");
        System.out.println(
                "6. Поиск минимального остовного дерева в графе с выводом результирующей матрицы смежности в консоль");
        System.out.println("7. Решение задачи комивояжера с выводом результирующего маршрута и его длины в консоль");
        System.out.println("8. Закрыть приложение");
        System.out.print("Выберите пункт: ");
    }

    private void checkChoice(int choice) {
        switch (choice) {
            case 1:
                loadGraph();
                break;
            case 2:
                breadthFirstSearch();
                break;
            case 3:
                depthFirstSearch();
                break;
            case 4:
                getShortestPathBetweenVertices();
                break;
            case 5:
                getShortestPathsBetweenAllVertices();
                break;
            case 6:
                getLeastSpanningTree();
                break;
            case 7:
                solveTravelingSalesmanProblem();
                break;
            case 8:
                break;
            default:
                System.out.println("Неверный ввод. Попробуйте снова.");
        }
    }

    private void loadGraph() {
        System.out.println("Введите абсолютный путь к файлу:");
        try {
            graph.loadGraphFromFile(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void breadthFirstSearch() {
        if (checkGraphIsInit()) {
            System.out.println("От какой вершины:");
            checkForInt();
            int startVertex = scanner.nextInt();
            System.out.println(Arrays.toString(GraphAlgorithms.breadthFirstSearch(graph, startVertex)));
        }
    }

    private void depthFirstSearch() {
        if (checkGraphIsInit()) {
            System.out.println("От какой вершины:");
            checkForInt();
            int startVertex = scanner.nextInt();
            System.out.println(Arrays.toString(GraphAlgorithms.depthFirstSearch(graph, startVertex)));
        }
    }

    private void getShortestPathBetweenVertices() {
        if (checkGraphIsInit()) {
            System.out.println("От какой вершины:");
            checkForInt();
            int startVertex = scanner.nextInt();
            System.out.println("До какой вершины:");
            checkForInt();
            int endVertex = scanner.nextInt();
            try {
                int weight = GraphAlgorithms.getShortestPathBetweenVertices(graph, startVertex, endVertex);
                System.out.println(weight);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getShortestPathsBetweenAllVertices() {
        if (checkGraphIsInit()) {
            int[][] result = GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        }
    }

    private void getLeastSpanningTree() {
        if (checkGraphIsInit()) {
            int[][] result = GraphAlgorithms.getLeastSpanningTree(graph);
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        }
    }

    private void solveTravelingSalesmanProblem() {
        if (checkGraphIsInit()) {
            TsmResult result = GraphAlgorithms.solveTravelingSalesmanProblem(graph);
            for (int i = 0; i < result.vertices.size(); i++) {
                System.out.print(result.vertices.get(i));
            }
            System.out.println("\nDistance = " + result.distance);
        }
    }

    private boolean checkGraphIsInit() {
        boolean isInit = graph.getAdjacencyMatrix() != null;
        if (!isInit) {
            System.out.println("Граф не инициализирован");
        }
        return isInit;
    }

    private void checkForInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка: введите целое число.");
            scanner.next();
        }
    }

}
