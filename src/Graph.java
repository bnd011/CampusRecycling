public class Graph
{
    private int numV = 0; // number of vertices
    private Vertex vertexList[]; // array of vertices
    private int adjMat[][]; // adjacency matrix
    private boolean directed = false;

    // constructor
    // adapted from https://www.geeksforgeeks.org/graph-and-its-representations/
    public Graph (int numV)
    {
        this.numV = numV;
        vertexList = new Vertex[numV];
        adjMat = new int[numV][numV];
        numV = 0;
        for (int i = 0; i < numV; i++)
        {
            for (int j = 0; j < numV; j++)
            {
                adjMat[i][j] = 0;
            }
        }

    }

    // lists all edges
    public List allEdges()
    {

    }

    // traverses through the graph using Dijkstra's algorithm
    public void dijkstra(Vertex v1)
    {
    }

    // takes 2 vertices as parameters and returns the edge connecting them or null if not adjacent
    public Edge getEdge(Vertex origin, Vertex destination)
    {

    }

    // adds an edge to adjList for an undirected graph
    // adapted from Textbook: Data Structures and Algorithms in Java by Robert Lafore
    // https://rineshpk.weebly.com/uploads/1/8/2/0/1820991/data_structures_and_algorithms_in_javatqw_darksiderg.pdf
    public void addEdge(int v1, int v2)
    {
        adjMat[v1][v2] = 1;
        adjMat[v2][v1] = 1;
    }

    // removes an edge from adjList
    public void removeEdge()
    {

    }

    // adds a vertex and its edges
    // adapted from Textbook: Data Structures and Algorithms in Java by Robert Lafore
    // https://rineshpk.weebly.com/uploads/1/8/2/0/1820991/data_structures_and_algorithms_in_javatqw_darksiderg.pdf
    public void addVertex(String label)
    {
        vertexList[numV++] = new Vertex(label);
    }

    // removes a vertex and its edges
    public void removeVertex()
    {

    }

    // returns the degree of a vertex
    public int degree()
    {

    }

    // returns all outgoing edges of a vertex
    public Edge incidentEdges()
    {

    }

    // returns the sum of the weights of the edges
    public int edgeSum()
    {

    }

    //based off textbook example (p. 636)
    private boolean visited[] = new boolean[numV];
    Vertex<String>[] parents = new Vertex[numV];
    private void DFS(Graph g, Vertex v1, visited, parents)
    {
        visited[v1] = true;
        for (Edge e : v1.incidentEdges())
        {
            Vertex<String> v2 = e.opposite(v1);
            if (!visited[v2])
                DFS(g, v2);
        }
    }


}
