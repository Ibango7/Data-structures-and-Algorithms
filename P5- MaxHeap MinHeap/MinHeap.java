/**
 * Name:Israel Bango
 * Number: u04865503
 * **/

@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    @Override
    public void insert(T elem) {
    	if(isFull()) return;
    	mH[++currentSize] = elem;
    	minHeapifyUP(currentSize);
    	
    }

    public T removeMin() {
    	if (isEmpty()) return null;
    	
    	// extract element from the root
    	// put element from last leaf in its place
    	T Lastkey = (T)mH[currentSize];
    	T removedKey = (T) mH[1];
    	mH[1] = Lastkey;
    	mH[currentSize] = null; 
    	currentSize--;
    	minHeapifyDown(1);	
    	return removedKey;
    }

    public void delete(T elem) {
    	if(isEmpty()) return;
    	int index = 0;
    	boolean isFound = false;
    	for(int i = 1; i <= currentSize; i++) {
    		if(elem.compareTo((T) mH[i]) == 0) {
    			isFound = true;
    			index = i;
    			break;
    		}
    	}
    	
    	if(isFound) {
    		T Lastkey = (T)mH[currentSize];
        	mH[index] = Lastkey;
        	mH[currentSize] = null; 
        	currentSize--;
        	minHeapifyDown(index);
    		
    	}else {
    		return;
    	}
    }


    //Helper functions
    
    public void minHeapifyUP(int nodeIndex) {
    	// check if inserted node is smaller than its parent
    	// if so swap smaller node with larger parent
    	Comparable<T> newNode = mH[nodeIndex];
    	while(nodeIndex > 1 && newNode.compareTo((T) mH[parent(nodeIndex)]) < 0) {
    		// swap newNode with parent node
    		mH[nodeIndex] = mH[parent(nodeIndex)];
    		nodeIndex = parent(nodeIndex);
    	}
    	mH[nodeIndex] = newNode;
    }
    
    public void minHeapifyDown(int nodeIndex) {
    	Comparable<T> root = mH[nodeIndex];
    	int leftChild = leftChild(nodeIndex);
    	int rightChild = rightChild(nodeIndex);
    	int smallestChild = 1;
    	
    	while(!isLeaf(nodeIndex)) {
    		if(leftChild != -1 && rightChild != -1) {
    			smallestChild = minChild(leftChild, rightChild);
    		}
    		
    		if(leftChild != -1 && rightChild  == -1) {
    			smallestChild = leftChild;
    		}
    		
    		if(leftChild == -1 && rightChild != -1) {
    			smallestChild = rightChild;
    		}
    		
    		if(root.compareTo((T) mH[smallestChild]) > 0) {
    			mH[nodeIndex] =  mH[smallestChild];
    			mH[smallestChild] = root;
    			nodeIndex = smallestChild;
    			root = mH[nodeIndex];
    			leftChild = leftChild(nodeIndex);
            	rightChild = rightChild(nodeIndex);
    		}else {
    			break;
    		}
    	}
    }


}