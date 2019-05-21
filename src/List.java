// Austin Harvey
// Used whoever's functioning linked list this is (link below) as a reference while coding this and worked with Behram on this for many, many, many hours. "too many"- Behram
// https://gist.github.com/BillPepsiOfPepsiCo/ad23a7c1e381f139ccac156207afff82
// Most of what I got out of the link above is accounting for every possible null, beforehand I was encountering so many pointer exceptions, this helped me see every possible case accounted for
public class List<Type>
{
    // We don't actually have to set a max size with linked lists
    // But it is a good idea.
    // Just picture an infinite loop adding to the list! :O
    public static final int MAX_SIZE = 200000;

    private Node<Type> head;
    private Node<Type> tail;
    private Node<Type> curr;
    private int num_items;

    // constructor
    // remember that an empty list has a "size" of -1 and its "position" is at -1
    public List()
    {
        this.head = this.tail = this.curr = null;
        this.num_items= 0;

    }

    // copy constructor
    // clones the list l and sets the last element as the current
    public List(List<Type> l)
    {
        Node<Type> n = l.head;

        this.head = this.tail = this.curr = null;
        this.num_items = 0;

        while (n != null)
        {
            this.insertAfter(n.getData());
            n = n.getLink();
        }
    }

    // navigates to the beginning of the list
    public void first()
    {
        this.curr= this.head;
    }

    // navigates to the end of the list
    // the end of the list is at the last valid item in the list
    public void last()
    {
        this.curr= this.tail;
    }

    // navigates to the specified element (0-index)
    // this should not be possible for an empty list
    // this should not be possible for invalid positions
    public void setPos(int pos)
    {

        if (pos >= 0 && pos<num_items)// else if the input is greater than 0 and less than the number of items
        {
            int i= 0;
            Node<Type> posSetter= head; // new node to set the position = to the head to start traversing the list
            while (i< pos && posSetter != null) //when the i is less inputted position and the node is not null
            {
                posSetter= posSetter.getLink(); //moves to the next item in the list. eventually this list will be equal to the right position
                i++;
            }
            this.curr= posSetter; //sets the current to the position setter node
        }
        else
        {
            setPos(num_items-1); //Essentially if the position it tries to set is too large, it runs the function again with a value that will set it to the end of the list
        }

    }

    // navigates to the previous element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void prev()
    {
        if (this.getPos() == 0) //do nothing in the case of it being the first item
        {
            return;
        }
        Node<Type> original = curr;
        first();
        if (this.isEmpty())
        {
            return; //if an empty list, there's nothing it can do so it exits the function
        }
        while(this.curr != null)
        {
            if (this.curr.getLink() ==original) //while current is not null, get a link to the next one until its link equals the original position of n
            {
                break;
            }
            next();
        }
    }


    // navigates to the next element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void next()
    {
        if (curr != tail)
        {
                curr = curr.getLink();
        }
    }

    // returns the location of the current element (or -1)
    public int getPos() //returns the position variable
    { //Used Behram as inspiration
        if (isEmpty())
        {
            return -1;
        }
        else
        {
            Node positionMarker = head;
            int i = 0;
            while(positionMarker != curr && positionMarker != null)
            {
                positionMarker = positionMarker.getLink();
                i++;
            }
            return i;
        }
    }

    // returns the value of the current element (or -1)
    public Type getValue() // returns the data value if the current is not null
    {
        if (curr != null)
        {
            return curr.getData();
        }
        else
        {
            return null;
        }

    }

    // returns the size of the list
    // size does not imply capacity
    public int getSize() //returns the number of items, as that int gains one each time the insert functions are used and loses if the remove is used
    {
        return num_items;
    }

    // inserts an item before the current element
    // the new element becomes the current
    // this should not be possible for a full list
    public void insertBefore(Type data)
    {
        if (this.isFull() != true)
        {
            Node<Type> newNode= new Node<Type>();
            newNode.setData(data);
            if (head == null) //if the head is null, makes the new node the head
            {
                head= newNode;
                num_items++;
                first();
            }
            else if(tail== null) // essentially, if the tail is currently not existing and you're trying to insert before, the old head is made into the tail and the new node is made into the head
            {
                Node<Type> formerHead= head;
                formerHead.setLink(null);
                this.tail= formerHead;
                newNode.setLink(tail);
                this.head= newNode;
                curr= head;
                num_items++;
            }
            else if (this.curr== head) //if the current node is the head, the new node is linked to the head and the new node is converted to the head, as inserting before the head should make it the head
            {
                newNode.setLink(head);
                head= newNode;
                first();
                num_items++;
            }
            else //in all other cases where the new node should be somewhere in the middle of the list (i.e. not head or tail), goes back one and inserts after
            {
                prev();
                insertAfter(data);
            }
        }
    }

    // inserts an item after the current element
    // the new element becomes the current
    // this should not be possible for a full list
    // Thanks Behram who thanked Dr. Lori
    public void insertAfter(Type data)
    {
        if (this.getSize() == MAX_SIZE)
        {
            return;
        }
        Node newNode = new Node<Type>();
        newNode.setData(data);
        //System.out.println(n.getData());
        if (this.head== null) //if there currently isn't a head, the new node is made to the head
        {
            head= newNode;
            first();
        }
        else if(tail== null) //if there currently isn't a tail, this new item should technically (if I'm reasoning this right) be the second insert, thus the tail is made to be the new node and the head is linked to the tail
        {
            tail= newNode;
            head.setLink(tail);
            last();
        }
        else if (curr == tail) //if the current is the tail, the tail needs to be pushed back one and the previous tail needs to be linked to the new tail
        {
            tail.setLink(newNode);
            newNode.setLink(null);
            tail=newNode;
            curr= tail;
            last();
        }
        else //else creates two new temporary variables, one equal to the original current and one equal to the original next
        {
            Node<Type> originalCurr= curr;
            next();
            Node<Type> nextInsert= curr;

            originalCurr.setLink(newNode); //the original's link is set to the new and the new's is set to the next
            newNode.setLink(nextInsert);
            curr= newNode; // I spent hours... upon hours looking in every little place for why my lists were in the wrong order... I just had never set the current to the new in these instances... oof.
        }
//        if (head== null) This one just related to an earlier problem and should no longer be a thing that's ever called, figured I'd leave it in to display my earlier failures
//        {
//            System.out.println("oof");
//        }
        num_items++;
    }

    // removes the current element
    // this should not be possible for an empty list
    public void remove()
    {
        if (this.getSize() == 0) //if the size 0, you can't remove anything, get out of the function
        {
            return;
        }
        if (curr== null) //if there's no current, what are you even removing?
        {
            return;
        }
        if (curr== head) // if what your removing is the head
        {
            Node<Type> next = curr.getLink();
            if (next != null && next== tail) //if there is currently a next and the next is a tail, the tail becomes the new head, and the tail is null
            {
                head=tail;
                tail= null;
                head.setLink(null);
                num_items--;
                first();
            }
            else if (next== null) //if there is nothing next, then removing the head yields null and the list is now empty
            {
                head=null;
                num_items--;
            }
            else //the head is now equal to the next item
            {
                head= head.getLink();
                num_items--;
                first();
            }
        }
        else if(curr== tail) //if the tail is to be removed
        {
            prev();
            if(curr == head&& curr != null) //if the new tail is the head, the tail is null and the head has no link
            {
                tail= null;
                head.setLink(null);
                num_items--;
                first();
            }
            else //the tail is just a regular item which is set to the tail and the new tail's link is null
            {
                tail= curr;
                tail.setLink(null);
                num_items--;
                last();
            }
        }
        else //in all other scenarios (ie in the middle of the list), set a temp variable to the item before your original current,
        // then move forward to the original currents after, then set the before's link to the one after current
        {
            Node<Type> beforeCurr, afterCurr;
            prev();
            beforeCurr= curr;
            next();
            next();
            afterCurr=curr;
            beforeCurr.setLink(afterCurr);
            curr=afterCurr;
            num_items--;
        }
    }

    // replaces the value of the current element with the specified value
    // this should not be possible for an empty list
    public void Replace(Type data)
    {
            curr.setData(data);
    }

    // returns if the list is empty
    public boolean isEmpty()
    {
        if (this.getSize() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // returns if the list is full
    public boolean isFull()
    {
        if (this.getSize()== MAX_SIZE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // returns if two lists are equal (by value)
    public boolean Equals(List<Type> l)
    {
        if (this.getSize() != l.getSize())
        {
            return false;
        }
        Node<Type> thisNode= head;
        Node<Type>otherNode= l.head;
        while (thisNode != null)
        {
            if(thisNode.getData() != otherNode.getData())
            {
                return false;
            }
            thisNode= thisNode.getLink();
            otherNode= otherNode.getLink();
        }
        return true;

    }

    // returns the concatenation of two lists
    // l should not be modified
    // l should be concatenated to the end of *this
    // the returned list should not exceed MAX_SIZE elements
    // the last element of the new list is the current
    public List<Type> add(List<Type> l)
    {
        List newList = new List(this);
        Node newNode = l.head;
        newList.last();
        for (int i = newList.getSize(); i < MAX_SIZE && newNode != null; i++)
        {
            newList.insertAfter(newNode.getData());
            newNode = newNode.getLink();
        }
        return newList;
    }

    // returns a string representation of the entire list (e.g., 1 2 3 4 5)
    // the string "NULL" should be returned for an empty list

    public String toString()
    {
        String s = "";
        int i = this.getSize();
        if (i > 0)
        {
            Node n = head;
            while (n.getLink() != null)
            {
                s = s + n.getData() + ", ";
                n= n.getLink();
                i--;
            }
            s= s + n.getData();
            return s;
        }
        return "NULL";
    }
}