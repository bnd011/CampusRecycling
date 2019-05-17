public class Stack<Type> extends List
{
    public Stack()
    {
        super();
    }
    public void push(Type inserted)
    {
        super.insertBefore(inserted);
    }
    public void pop()
    {
        super.remove();
    }
    public Type peek()
    {
        return (Type)super.getValue();
    }

    public boolean isEmpty()
    {
        return super.isEmpty();
    }

    public boolean isFull()
    {
        return super.isFull();
    }
    public int getSize()
    {
        return super.getSize();
    }
}
