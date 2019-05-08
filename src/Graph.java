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
    public void addEdge(String v1, String v2)
    {
        Vertex origin = new Vertex(v1);
        Vertex destination = new Vertex(v2);
        adjList[v1].insertAfter(v2);
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
