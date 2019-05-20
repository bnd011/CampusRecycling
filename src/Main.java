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
        //while (verticesInput.hasNextLine())
        //{
            //String currentVertex = verticesInput.nextLine();
            //vertex = currentVertex.split(", ");
            for (String s : vertex)
            {
                map.addVertex(s);
            }

            //System.out.println(currentVertex);
            //vertexList.insertAfter(currentVertex);
        //}
        map.makeAdjMat(vertex.length);

        File edges = new File("src//edgesTest.csv");
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

        Vertex<String> startingVertex = new Vertex<>("A.E. Phillips Laboratory School");
        map.BFS(map, startingVertex);

        System.out.println(map);
    }
}
