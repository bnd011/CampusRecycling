public class Edge
{
    Vertex origin = new Vertex();
    Vertex destination = new Vertex();

    public Vertex endpoints()
    {
    }

    public Vertex opposite(Vertex v)
    {

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
}
