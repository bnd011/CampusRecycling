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
        // List<String> vertexList = new List<>();
        while (verticesInput.hasNextLine())
        {
            String currentVertex = verticesInput.nextLine();
            String[] vertex = currentVertex.split(", ");
            for (String s : vertex) map.addVertex(s);
            //System.out.println(currentVertex);
            //vertexList.insertAfter(currentVertex);
        }

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

        map.allEdges();



        System.out.println(map);
    }
}
