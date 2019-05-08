public class Graph
{
    private int numV = 0;
    private List<String> adjList[];
    private boolean directed = false;

    // constructor
    // adapted from https://www.geeksforgeeks.org/graph-and-its-representations/
    public Graph (int numV)
    {
        this.numV = numV;
        adjList = new List[numV];
        for (int i = 0; i < numV; i++)
            adjList[i] = new List<>();
    }

    // lists all edges
    public List allEdges()
    {

    }

    // traverses through the graph using Dijkstra's algorithm
    public Vertex dijkstra()
    {

    }

    public Edge getEdge(Vertex origin, Vertex destination)
    {

    }

    // adds an edge to adjList for an undirected graph
    // adapted from https://www.geeksforgeeks.org/graph-and-its-representations/
    public void addEdge(Graph graph, int origin, int destination)
    {

    }

    // removes an edge from adjList
    public void removeEdge()
    {

    }

    // adds a vertex and its edges
    public void addVertex()
    {

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



}
