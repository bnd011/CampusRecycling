public class Vertex
{
    private String data;
    private boolean visited;
    private int index;

    // constructor
    public Vertex(String data)
    {
        visited = false;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

}