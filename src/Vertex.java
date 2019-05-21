public class Vertex<Type extends Comparable>
{
    private String data;
    public boolean visited;
    private int index;

    // constructor
    public Vertex(String data)
    {
        this.data = data;
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
    public String toString()
    {
        return data.toString();
    }

}