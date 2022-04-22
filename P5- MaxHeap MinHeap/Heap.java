/**
 * Name:Israel Bango
 * Number: u04865503
 * **/

@SuppressWarnings("unchecked")
public abstract class Heap<T extends Comparable<T>> {

    int capacity;
    Comparable<T> mH[];
    int currentSize;

    public Heap(int capacity) {
        this.capacity = capacity;
        mH = new Comparable[capacity+1]; //Use index positions 1 to capacity 
        currentSize = 0;
    }

    protected int getCapacity(){
        return capacity;
    }

    protected int getCurrentSize(){
        return currentSize;
    }

    public void display() {
        for(int i = 1; i <= currentSize; i++) {
            System.out.print(mH[i] + " ");
        }
        System.out.println("");
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    protected boolean isEmpty() {
    	return currentSize == 0;
    }

    public abstract void insert(T elem);


    //Helper functions
    public int parent(int i) {
    	return (i/2);
    }
    
    public int leftChild(int i) {
    	return (2*i) > currentSize? -1:(2*i);
    }
    
    public int rightChild(int i) {
    	return (2*i + 1) > currentSize? -1: (2*i + 1);
    }
    
    public boolean isFull() {
    	return currentSize+1 == mH.length;
    }
    
    public boolean isLeaf(int index) {
    	if(leftChild(index) == -1 && rightChild(index) == -1) return true;
    	return false;
    }
    public int minChild(int i, int y) {	
    		if(mH[i].compareTo((T) mH[y]) < 0)
        		return i;
        	else
        		return y;
    }
    
    public int maxChild(int i, int y) {
    	if(mH[i].compareTo((T) mH[y]) > 0)
    		return i;
    	else
    		return y;
    }

}