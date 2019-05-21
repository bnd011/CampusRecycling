// Behram Dossabhoy and Holland Wolf
// 15 May 2019
// CampusRecycling: A program that designs an efficient way to handle how recyclers pick up recycling bins.

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
            float weight = Float.parseFloat(edge[2]);
            map.addEdge(origin, destination, weight);
        }

        //map.vertexList.first();

        Scanner userInput = new Scanner(System.in);
        System.out.println("Which building would you like to start from? ");
        String input = userInput.nextLine();
        Vertex<String> startingVertex = map.findVertex(input);

        if(startingVertex != null)
            map.dijkstra(map, startingVertex);
    }
}
