// Behram Dossabhoy and Holland Wolf
// 15 May 2019
// CampusRecycling: A program that designs an efficient way to handle how recyclers pick up recycling bins.

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File vertices = new File("src//vertices.csv");
        Scanner verticesInput = new Scanner(vertices);
        List<String> vertexList = new List<>();
        while (verticesInput.hasNextLine())
        {
            String currentVertex = verticesInput.nextLine();
            //System.out.println(currentVertex);
            vertexList.insertAfter(currentVertex);
        }
        System.out.println(vertexList);
    }
}
