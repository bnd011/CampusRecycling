public class Queue<Type> extends List
{
  public Queue()
  {
      super();
  }

  public void enqueue(Type enqueuedItem)
  {
      super.last();
      insertAfter(enqueuedItem);
  }
  public void dequeue()
  {
      super.first();
      super.remove();
  }
  public Type peek()
  {
      super.first();
      return (Type)super.getValue();
  }

}
