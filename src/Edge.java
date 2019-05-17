import java.util.Arrays;

public class Edge
{
    private Vertex origin;
    private Vertex destination;

    public float getWeight()
    {
        return weight;
    }

    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    float weight;

    // constructor
    public Edge(Vertex orig, Vertex dest)
    {
        origin = orig;
        destination = dest;
    }
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
    public Vertex[] endpoints(Vertex origin, Vertex destination)
    {
        Vertex[] endpoints = new Vertex[2];
        endpoints[0] = origin;
        endpoints[1] = destination;
        return endpoints;
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
