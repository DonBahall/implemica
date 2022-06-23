package Task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task2 {

    private static int NumOfCities;
    private final ArrayList<String> cityName = new ArrayList<>(NumOfCities);
    private ArrayList<Integer>[] adjacencyList;
    private ArrayList<Integer>[] weightMatrix;
    private static final int INF = Integer.MAX_VALUE;
    private static int[] distance;
    private static int pathsNum;
    private static int[][] pathArray;

    /**
     * Method responsible for entering all the data and writing it to the ArrayLists
     */
    private void inputData() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the number of cities ");
        NumOfCities = scan.nextInt();

        //Initialize the list of adjacencies of the graph of the dimension cityNum
        adjacencyList = new ArrayList[NumOfCities];
        for (int i = 0; i < NumOfCities; ++i) {
            adjacencyList[i] = new ArrayList<>();
        }
        //Initialization of the list in which the weights of the edges (cost of passage)
        weightMatrix = new ArrayList[NumOfCities];
        for (int i = 0; i < NumOfCities; ++i) {
            weightMatrix[i] = new ArrayList<>();
        }
        System.out.println();
        for (int i = 0; i < NumOfCities; i++) {
            System.out.print("Input city name, index " + (i + 1) + ": ");
            String name = scan.next();
            cityName.add(name);
            System.out.println("-----------------------");
            System.out.print("Input the number of neighbours of city " + name + " ");
            int neighboursNum = scan.nextInt();
            for (int j = 0; j < neighboursNum; j++) {
                System.out.print("Input index of a city connected to " + name + " ");
                adjacencyList[i].add(scan.nextInt() - 1);
                System.out.print("Input the transportation cost to " + name + " ");
                weightMatrix[i].add(scan.nextInt());
            }
            System.out.println();
        }
        System.out.print("Input the number of paths to find ");
        pathsNum = scan.nextInt();
        pathArray = new int[pathsNum][2];
        for (int i = 0; i < pathsNum; i++) {
            System.out.print("Input source ");
            String citySource;
            do {
                citySource = scan.next();
            } while (!cityName.contains(citySource));
            pathArray[i][0] = cityName.indexOf(citySource);

            System.out.print("Input destination ");
            String cityDestination;
            do {
                cityDestination = scan.next();
            } while (!cityName.contains(cityDestination));
            pathArray[i][1] = cityName.indexOf(cityDestination);
        }
        System.out.println();
    }

    /**
     * Dijkstra's algorithm for finding the shortest distances from the starting vertex
     *
     * @param s array containing the start and end vertices, between which the distance is sought
     */
    private void dejkstra(int s) {
        //An array for storing information about traversed and not passed vertices
        boolean[] used = new boolean[NumOfCities];
        //At begin all vertices is not passed
        Arrays.fill(used, false);

        distance = new int[NumOfCities];
        Arrays.fill(distance, INF);

        distance[s] = 0; //The shortest distance to the starting vertex is 0
        for (int j = 0; j < NumOfCities; ++j) {
            int v = -1; //Initially  assume that the path from the starting vertex of vertex v does not exist
            int distV = INF; //The distance to the vertex is infinity
            //Choose the vertex, the shortest distance to which has not yet been found
            for (int i = 0; i < NumOfCities; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < distance[i]) {
                    continue;
                }
                v = i;
                distV = distance[i];
            }
            //consider all the arcs coming from the vertex
            for (int i = 0; i < adjacencyList[v].size(); ++i) {
                int u = adjacencyList[v].get(i);
                int weight = weightMatrix[v].get(i);
                // change vertex distance and ancestor
                if (distance[v] + weight < distance[u]) {
                    distance[u] = distance[v] + weight;
                }
            }
            //Mark the vertex v viewed, before it was found the shortest distance
            used[v] = true;
        }
    }

    /**
     * method that displays the search result
     */
    private void outputData() {
        for (int i = 0; i < pathsNum; i++) {
            dejkstra(pathArray[i][0]);
            if (distance[pathArray[i][1]] != INF) {
                System.out.println("The paths of minimum cost between cities  " + distance[pathArray[i][1]]);
            } else System.out.println("There is no way between these cities");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the number of tests ");
        int testNumber = scan.nextInt();
        try {
            for (int i = 0; i < testNumber; i++) {
                Task2 task2 = new Task2();
                task2.inputData();
                System.out.println("Output");
                task2.outputData();
                System.out.println();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The entered data is not allowed. Try again");
        }
    }
}