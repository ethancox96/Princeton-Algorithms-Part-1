/*
 * RandomizedQueue.java
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] itemArray;
    private int index;
    
    public RandomizedQueue()
    {
        itemArray = (Item[]) new Object[1];
        index = 0;
    }
    
    public boolean isEmpty()
    {
        return index == 0;
    }
    
    public int size()
    {
        return index;
    }
    
    public void enqueue(Item item)
    {
        if (item == null)
            throw new NullPointerException("item is null");
        
        if (index == itemArray.length) {
            resizeArray(index*2);
        }
        
        itemArray[index++] = item;
    }
    
    public Item dequeue()
    {
        if (index == 0)
            throw new NoSuchElementException("element does not exist");
        
        int randomIndex = StdRandom.uniform(index);
        Item item = itemArray[randomIndex];
        index--;
        itemArray[randomIndex] = itemArray[index];
        itemArray[index] = null;
        
        if (index > 0 && index == itemArray.length/4) {
            resizeArray(itemArray.length/2);
        }
        
        return item;
    }
    
    public Item sample()
    {
        if (index == 0)
            throw new NoSuchElementException("element does not exist");
        
        int randomIndex = StdRandom.uniform(index);
        Item item = itemArray[randomIndex];
        return item;
    }
    
    private void resizeArray(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        
        for (int i = 0; i < index; i++) {
            copy[i] = itemArray[i];
        }
        
        itemArray = copy;
    }
    
    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>
    {
        private int currentIndex;
        private Item[] newArray;
        
        private ArrayIterator()
        {
            currentIndex = 0;
            
            if (index > 0)
            {
                newArray =  itemArray.clone();
                StdRandom.shuffle(newArray, 0, index - 1);
            }
        }
        
        public boolean hasNext()
        {
            return currentIndex < index;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException("operation not supported");
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException("element does not exist");
            
            return newArray[currentIndex++];
        }
    }
    
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("3");
        rq.enqueue("4");
        rq.enqueue("5");
        rq.enqueue("6");
        rq.enqueue("7");
        
        /*rq.dequeue();
        rq.dequeue();
        rq.dequeue();*/
        
        Iterator<String> a = rq.iterator();
        //Iterator<String> b = rq.iterator();
        
        while (a.hasNext())
            System.out.println(a.next());
        
        //System.out.println(rq.isEmpty());
        //System.out.println(rq.size());
        System.out.printf("do foreach\n");
        
        //while (b.hasNext())
            //System.out.println(b.next());
        
        for (String s : rq)
            System.out.println(s);
    }
}



