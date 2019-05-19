public class Graph
{
    private int numV = 0; // number of vertices
    private List<Vertex> vertexList; // array of vertices
    private Edge adjMat[][]; // adjacency matrix
    private Queue queue;
    private boolean directed = false;

    public void setDirected(boolean directed)
    {
        this.directed = directed;
    }

    // constructor
    // adapted from https://www.geeksforgeeks.org/graph-and-its-representations/
    public Graph (int numV)
    {
        this.numV = numV;
        vertexList = new List<>();
        adjMat = new Edge[numV][numV];
    }

    // lists all edges
    public List allEdges()
    {
        List edgeList = null;
        for (int i = 0; i < adjMat.length; i++)
        {
            for (int j = 0; j < adjMat[i].length; j++)
            {
                if (adjMat[i][j] != null)
                    edgeList.insertAfter(adjMat[i][j]);
            }
        }
        return edgeList;
    }

    // takes 2 vertices as parameters and returns the edge connecting them or null if not adjacent
    public Edge getEdge(Vertex origin, Vertex destination)
    {
        if (adjMat[origin.getIndex()][destination.getIndex()] != null)
            return adjMat[origin.getIndex()][destination.getIndex()];
        return null;
    }

    // adds an edge to adjList for an undirected graph
    // adapted from Textbook: Data Structures and Algorithms in Java by Robert Lafore
    // https://rineshpk.weebly.com/uploads/1/8/2/0/1820991/data_structures_and_algorithms_in_javatqw_darksiderg.pdf
    public void addEdge(Vertex origin, Vertex destination)
    {
        adjMat[origin.getIndex()][destination.getIndex()] = new Edge(origin, destination);
        adjMat[destination.getIndex()][origin.getIndex()] = new Edge(destination, origin);
    }

    public void addEdge(Vertex origin, Vertex destination, float weight)
    {
        adjMat[origin.getIndex()][destination.getIndex()] = new Edge(origin, destination);
        adjMat[destination.getIndex()][origin.getIndex()] = new Edge(destination, origin);
    }

    // removes an edge from adjMat
    public void removeEdge(Vertex origin, Vertex destination)
    {
        adjMat[origin.getIndex()][destination.getIndex()] = null;
        adjMat[destination.getIndex()][origin.getIndex()] = null;
    }

    // adds a vertex and its edges
    public void addVertex(String label)
    {
        Vertex newVertex = new Vertex(label);
        vertexList.insertAfter(newVertex);
        vertexList.getValue().setIndex(vertexList.getPos());
        numV++;
        updateAdjMat();
    }

    // update the adjacency matrix (at least when a vertex is inserted)
    public void updateAdjMat()
    {
        Edge[][] oldAdjMat = adjMat;
        Edge[][] newAdjMat = new Edge[numV][numV];
        if (oldAdjMat != null)
        {
            for (int i = 0; i < oldAdjMat.length; i++)
            {
                for (int j = 0; j < oldAdjMat[i].length; j++)
                {
                    newAdjMat[i][j] = oldAdjMat[i][j];
                }
            }
        }
    }

    // removes a vertex and its edges
   /* public void removeVertex()
    {

    }*/

    // returns the degree of a vertex
    public int degree(int row)
    {
        int i = 0;
        for (int j = 0; j < adjMat[row].length; j++)
        {
            if (adjMat[row] != null)
                i++;
        }
        return i;
    }

    // returns all outgoing edges of a vertex
    public List incidentEdges(Vertex v)
    {
        List outgoingEdges = new List<Edge>();
        for (int i = 0; i < adjMat.length; i++)
        {
            if (adjMat[v.getIndex()][i] != null)
                outgoingEdges.insertAfter(adjMat[v.getIndex()][i]);
        }
        return outgoingEdges;
    }

    // returns the sum of the weights of the edges
    public float edgeSum(Vertex v)
    {
        float result = 0;
        for (int i = 0; i < adjMat.length; i++)
        {
            if (adjMat[v.getIndex()][i] != null)
                result += adjMat[v.getIndex()][i].getWeight();
        }
        return result;
    }

    // Thanks, again, HARVEY, AUSTINS!
    public void DFS(Graph g, Vertex u)
    {
        Vertex[] parents = new Vertex[numV];
        Stack<Vertex> stack = new Stack<>();

        stack.push(u);
        u.visited = true;
        while (!stack.isEmpty())
        {
            for (int i = 0; i < adjMat.length; i++)
            {
                vertexList.setPos(i);
                if ((adjMat[stack.peek().getIndex()][i] != null) && (!vertexList.getValue().visited))
                {
                    vertexList.getValue().visited = true;
                    Vertex temp = adjMat[stack.peek().getIndex()][i].opposite(stack.peek());
                    parents[temp.getIndex()] = stack.peek();
                    stack.push(temp);
                    i = -1;
                }
            }
            stack.pop();
        }

        for (int i = 0; i < vertexList.getSize(); i++)
        {
            vertexList.setPos(i);
            vertexList.getValue().visited = false;
        }
    }

    // Thanks, again, HARVEY, AUSTINS!
    public void BFS(Graph g, Vertex u)
    {
        Vertex[] parents = new Vertex[numV];
        Queue<Vertex> queue = new Queue<>();

        queue.enqueue(u);
        u.visited = true;
        while (!queue.isEmpty())
        {
            for (int i = 0; i < adjMat.length; i++)
            {
                vertexList.setPos(i);
                if ((adjMat[queue.peek().getIndex()][i] != null) && (!vertexList.getValue().visited))
                {
                    vertexList.getValue().visited = true;
                    Vertex temp = adjMat[queue.peek().getIndex()][i].opposite(queue.peek());
                    parents[temp.getIndex()] = queue.peek();
                    queue.enqueue(temp);
                }
            }
            queue.dequeue();
        }

        for (int i = 0; i < vertexList.getSize(); i++)
        {
            vertexList.setPos(i);
            vertexList.getValue().visited = false;
        }
    }

    // traverses through the graph using Dijkstra's algorithm
    // Austin helped me with this
    public void dijkstra(Graph g, Vertex u)
    {
        int[] cost = new int[numV]; // shortest known distance from "s"
        Vertex[] path = new Vertex[numV]; // preceding Vertex in path;
        boolean unknown[] = new boolean[numV]; // all false initially;

        for (int i = 0; i < cost.length; i++)
        {
            cost[i] = Integer.MAX_VALUE;
        }
        cost[u.getIndex()] = 0;

        //iterate the vertex which costs least
        for (int i = 0; i < cost.length; i++)
        {
            int next_Index = (minVertex(cost, unknown));
            unknown[next_Index] = true;
            if (g.incidentEdges(vertexList.get(next_Index)) != null)
            {
                for (int j = 0; j < adjMat.length; j++)
                {
                    if (adjMat[next_Index][j] != null &&!unknown[j])
                    {
                        cost[j] = Math.min(cost[j], (cost[next_Index] + adjMat[next_Index][j].getWeight()));
                        if(cost[j]== (cost[next_Index] + adjMat[next_Index][j].getWeight()))// updated*
                        {
                            path[j] = vertexList.get(next_Index);
                        }
                    }
                }
            }
            else
            {
                System.out.println("No Path");
            }
            System.out.println("The distance for " + u + " to " + vertexList.get(next_Index)+ " will be "+ cost[vertexList.get(next_Index).getIndex()] );

        }
        printPath(g,path,u);
    }
}