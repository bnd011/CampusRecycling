public class Edge
{
    Vertex origin = new Vertex();
    Vertex destination = new Vertex();

    public Vertex getOrigin()
    {
        return origin;
    }

    public void setOrigin(Vertex origin)
    {
        this.origin = origin;
    }

    public Vertex getDestination()
    {
        return destination;
    }

    public void setDestination(Vertex destination)
    {
        this.destination = destination;
    }

    // returns both vertices
    public Vertex endpoints(Vertex origin, Vertex destination)
    {
        return origin, destination;
    }

    // returns the other vertex that is not passed in as a parameter
    // adapted from https://algs4.cs.princeton.edu/43mst/Edge.java.html
    public Vertex opposite(Vertex v)
    {
        if (v == origin)
            return destination;
        else if (v == destination)
            return origin;
        else throw new IllegalArgumentException("Illegal vertex");
    }
}
