public class Graph<Type extends Comparable>
{
    private int numV = 0; // number of vertices
    public List<Vertex> vertexList; // array of vertices
    private Edge adjMat[][]; // adjacency matrix
    private boolean directed = false;

    // constructor
    public Graph()
    {
        numV = 0;
        adjMat = null;
        vertexList = new List<>();
        directed = false;
    }

    public Graph (int numV)
    {
        this.numV = numV;
        vertexList = new List<>();
        adjMat = new Edge[numV][numV];
        makeAdjMat(numV);
    }

    public void makeAdjMat(int numV)
    {
        adjMat = new Edge[numV][numV];
        for (int i = 0; i < numV; i++)
        {
            for (int j = 0; j < numV; j++)
            {
                adjMat[i][j] = null;
            }
        }
    }

    public void setDirected(boolean directed)
    {
        this.directed = directed;
    }

    // lists all edges
    public List allEdges()
    {
        List edgeList = null;
        for (Edge[] edges : adjMat)
        {
            for (Edge edge : edges)
            {
                if (edge != null)
                    edgeList.insertAfter(edge);
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

    public void addEdge(Vertex origin, Vertex destination, int weight)
    {
        int originIndex = origin.getIndex();
        int destIndex = destination.getIndex();
        Edge e = new Edge(origin, destination, weight);
        Edge f = new Edge(destination, origin, weight);
        adjMat[originIndex][destIndex] = e;
        adjMat[destIndex][originIndex] = f;
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
        vertexList.last();
        numV++;
        vertexList.insertAfter(newVertex);
        vertexList.getValue().setIndex(vertexList.getPos());
        updateAdjMat();
    }

    //Thanks, Austin.
    public Vertex findVertex(String data)
    {
        for (int i = 0; i < vertexList.getSize(); i++)
        {
            vertexList.setPos(i);
            if (vertexList.getValue().getData().compareTo(data) == 0)
                return vertexList.getValue();
        }

        System.out.println("Vertex not found.");
        return null;
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
    public int edgeSum(Vertex v)
    {
        int result = 0;
        for (int i = 0; i < adjMat.length; i++)
        {
            if (adjMat[v.getIndex()][i] != null)
                result += adjMat[v.getIndex()][i].getWeight();
        }
        return result;
    }

    // Thanks, again, Austin!
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
        g.printPath(g, parents, u);
    }

    // Thanks, again, Austin!
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
        g.printPath(g, parents, u);
    }

    // traverses through the graph using Dijkstra's algorithm
    // Austin helped me with this (of course)
    public void dijkstra(Graph g, Vertex u)
    {
        boolean[] known = new boolean[numV]; // all false initially;
        int[] cost = new int[numV]; // shortest known distance from "s"
        Vertex[] path = new Vertex[numV]; // preceding Vertex in path;

        // initialize the arrays
        for (int i = 1; i < known.length; i++)
            known[i] = false;
        known[u.getIndex()] = true;


        for (int i = 0; i < cost.length; i++)
            cost[i] = Integer.MAX_VALUE;
        cost[u.getIndex()] = 0;

        for (int i = 0; i < adjMat.length; i++)
        {
            if (adjMat[u.getIndex()][i] != null)
            {
                cost[i] = adjMat[u.getIndex()][i].getWeight();
                path[i] = u;
            }
        }

        for (int i = 0; i < cost.length - 1; i++)
        {
            int next = findMinVertex(cost, known);
            if(next == -1)
                continue;
            known[next] = true;
            for (int j = 0; j < adjMat.length; j++)
            {
                if (!known[j] && adjMat[next][j] != null)
                {
                    cost[j] = Math.min(cost[j], (cost[next] + adjMat[next][j].getWeight()));
                    if (cost[j] == (cost[next] + adjMat[next][j].getWeight()))
                    {
                        vertexList.setPos(next);
                        path[j] = vertexList.getValue();
                    }
                }
            }
        }
        g.printPath(g, path, u);
    }

    // referenced Geeks for Geeks and totalhorizon.com
    public int primMST(Graph g, Vertex u)
    {
        Vertex[] mst = new Vertex[numV]; //store mst array
        int[] cost = new int[numV]; //used to pick min weight
        boolean[] known = new boolean[numV];

        for (int i = 0; i < cost.length ; i++)
            cost[i] = Integer.MAX_VALUE; //makes all infinity
        cost[u.getIndex()] = 0; //making the key 0 so the first index picked is zero

        int distance = 0;
        for (int i = 0; i < cost.length ; i++)
        {
            int next = (findMinVertex(cost, known)); //calls minVertex and adds in the minMST and key
            if(next == -1)
                continue;
            known[next] = true;

            //had to borrow this second for loop from Austin
            for (int k = 0; k < adjMat.length; k++)
            {
                if (adjMat[next][k] != null && !known[k])
                {
                    cost[k] = adjMat[next][k].getWeight();
                    vertexList.setPos(next);
                    mst[k] = vertexList.getValue();
                }
            }
            distance = distance + cost[next];
        }
        System.out.println("The distance for the Minimum Spanning Tree is: " + distance);
        printPath(g, mst, u);
        return distance;
    }

    //this is literally just the opposite of prim
    public int maxST(Graph g, Vertex u)
    {
        Vertex[] mst = new Vertex[numV]; //store mst array
        int[] cost = new int[numV]; //used to pick min weight
        boolean[] known = new boolean[numV];

        for (int i = 0; i < cost.length ; i++)
            cost[i] = Integer.MIN_VALUE; //makes all infinity

        cost[u.getIndex()] = 0; //making the key 0 so the first index picked is zero

        int distance = 0;
        for (int i = 0; i < cost.length ; i++)
        {
            int next = (findMaxVertex(cost, known)); //calls minVertex and adds in the minMST and key
            if (next == -1)
                continue;
            known[next] = true;

            //had to borrow this second for loop from Austin
            for(int k = 0; k < adjMat.length; k++)
            {
                if (adjMat[next][k] != null && !known[k])
                {
                    cost[k] = adjMat[next][k].getWeight();
                    vertexList.setPos(next);
                    mst[k] = vertexList.getValue();
                }
            }
            distance = distance + cost[i];
        }
        System.out.println("The distance for the Maximum Spanning Tree is: " + distance);
        printPath(g, mst, u);
        return distance;
    }

    public int findMinVertex (int[] cost, boolean[] known)
    {
        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < cost.length; i++)
        {
            if ((cost[i] < minCost) && !known[i])
            {
                minIndex = i;
                minCost = cost[i];

            }
        }
        return minIndex;
    }

    //exact opposite of find min vertex
    public int findMaxVertex (int[] cost, boolean[] known)
    {
        int maxCost = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < cost.length; i++)
        {
            if ((cost[i] > maxCost) && !known[i])
            {
                maxIndex = i;
                maxCost = cost[i];
            }
        }
        return maxIndex;
    }

    public void printPath(Graph g, Vertex[] path, Vertex u)
    {
        for (int i = 0; i < path.length; i++)
        {
            List pathList = new List<>();
            Vertex v = path[i];
            if (path[i] == null)
            {
                vertexList.setPos(i);
                System.out.println(vertexList.getValue());
            }

            else
            {
                vertexList.setPos(i);
                pathList.insertBefore(vertexList.getValue());
                while (!v.equals(u))
                {
                    pathList.insertBefore(v);
                    v = path[v.getIndex()];
                }

                pathList.insertBefore(u);
                System.out.println(pathList);
            }
        }
    }
}