// Behram Dossabhoy and Holland Wolf
// 15 May 2019
// CampusRecycling: A program that designs an efficient way to handle how recyclers pick up recycling bins.

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Graph<String> map = new Graph<>();
        File vertices = new File("src//vertices.csv");
        Scanner verticesInput = new Scanner(vertices);
        String[] vertex = verticesInput.nextLine().split(", ");
        for (String s : vertex)
            map.addVertex(s);

        map.makeAdjMat(vertex.length);

        File edges = new File("src//edges.csv");
        Scanner edgesInput = new Scanner(edges);
        while (edgesInput.hasNextLine())
        {
            String currentEdge = edgesInput.nextLine();
            String[] edge = currentEdge.split(", ");
            Vertex origin = map.findVertex(edge[0]);
            Vertex destination = map.findVertex(edge[1]);
            int weight = Integer.parseInt(edge[2]);
            map.addEdge(origin, destination, weight);
        }

        Scanner userInput = new Scanner(System.in);
        System.out.println("Which building would you like to start from? ");
        String input = userInput.nextLine();
        Vertex<String> startingVertex = map.findVertex(input);

        if (startingVertex != null)
        {
            long startTimeDijkstra = System.currentTimeMillis();
            map.dijkstra(map, startingVertex);
            System.out.println();
            long endTimeDijkstra = System.currentTimeMillis();
            long elapsedTimeDijkstra = endTimeDijkstra - startTimeDijkstra;
            System.out.println("The Dijkstra Algorithm took " + elapsedTimeDijkstra + " ms.");
            System.out.println();
            System.out.println();

            long startTimePrim = System.currentTimeMillis();
            map.primMST(map, startingVertex);
            System.out.println();
            long endTimePrim = System.currentTimeMillis();
            long elapsedTimePrim = endTimePrim - startTimePrim;
            System.out.println("The Dijkstra Algorithm took " + elapsedTimePrim + " ms.");
            System.out.println();
            System.out.println();

            System.out.println("Max Spanning Tree");
            map.maxST(map, startingVertex);
            System.out.println();
            System.out.println();

            if (elapsedTimeDijkstra < elapsedTimePrim)
                System.out.println("Dijkstra ran quicker than Prim-Jarnik.");
            if (elapsedTimePrim < elapsedTimeDijkstra)
                System.out.println("Prim-Jarnik ran quicker than Dijkstra.");

            System.out.println("DFS");
            map.DFS(map, startingVertex);
            System.out.println();
            System.out.println();

            System.out.println("BFS");
            map.BFS(map, startingVertex);
            System.out.println();
            System.out.println();
        }

        else System.out.println("Error");
    }
}
