/*
 *  Name: Israel Bango
 *  number: u04865503
 * */

import java.util.Random;
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{
	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}

	SkipList()
	{
		this(4);
	}

	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}

	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty()
	{
	    return root[0] == null;
	}

	public void insert(T key)
	{
		SkipListNode<T>[] current = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];
		SkipListNode<T> newNode;
		int level, index;
		
		current[maxLevel-1] = root[maxLevel-1];
		prev[maxLevel-1] = null;
		
		for(level = maxLevel-1; level >= 0; level--) {
		    while(current[level] != null && current[level].key.compareTo(key) < 0) {
		        prev[level] =  current[level];
		        current[level] = current[level].next[level];
		    }
		    
		    if(current[level] != null && current[level].key.compareTo(key) == 0) {
		        // don't include duplicates
		        return;
		    }
		    
		    if(level > 0) {
		        if(prev[level] == null) {
		            current[level-1] = root[level-1];
		        } else {
		            current[level-1] = prev[level].next[level-1];
		            prev[level-1] = prev[level];
		        }
		    }
		}
		
		// choose level randomly
		level = chooseLevel();
		
		newNode = new SkipListNode<T>(key, level+1);
		for(index = 0; index <= level; index++) {
		    newNode.next[index] = current[index];
		    if(prev[index] == null)
		        root[index] = newNode;
		    else prev[index].next[index] =  newNode;
		}
		
	}	

	public T first()
	{
	  if(isEmpty())
	      return null;
	 return root[0].key;
	}

	public T last()
	{
	    if(isEmpty()) // List is Empty
            return null;
	    	    
	    int level; 
	    SkipListNode<T>current;
	    
//	    traverse until you find a valid level/ non-null
	       for(level = maxLevel-1; level >=0 && root[level] == null; level--);
	    
	    current = root[level];    
	
	     while(true) {
	         if(current.next[level] != null ) {
	             current = current.next[level];
	         }else {
	             if (level > 0)
	                 level--;
	             else if(level == 0 && current.next[level] != null)
	                 current = current.next[level];
	             else if (level == 0 && current.next[level] == null) break;
	         }
	      }
	  
	    return current.key;
	}	

	public T search(T key)
	{
	    if(isEmpty()) // List is empty
	        return null;
	    
	    int level =  maxLevel-1;
        SkipListNode<T> prev, current;
        
//        Traverse until highest non-null level
        while(level >=0 && root[level] == null) {
            level--;
        }
        
        prev = current = root[level];
        
        while(true) {
            if(key == current.key) { // key is found
                return key; 
            } else if (key.compareTo(current.key) < 0) {
                if(level == 0)  // can't go down any further
                    return null;
                else if (current == root[level])
                    current = root[--level];
                else 
                    current = prev.next[--level];
            } else {
                prev = current;
                if(current.next[level] != null) {
                    current = current.next[level];
                } else {
                    for(level--; level >=0 && current.next[level] == null; level--);
                    if(level >=0)
                        current = current.next[level];
                    else return null;
                }
                
            }
        }
	    
	}
	
	
	
	
	
	
}