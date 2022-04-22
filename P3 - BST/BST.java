/*Name:Israel Bango
 * Number:u04865503
 **/
public class BST<T extends Comparable<T>> {
    protected BSTNode<T> root;

    public BST(){};

    public boolean insert(T val){
        if(root == null){
            root = new BSTNode<>(val);
            return true;
        } else {
            return root.recInsert(val);
        }
    }

    //Place code below
    public int numNodes() {
    	if(isEmpty()) {
    		return 0;
    	}
    	else {
    		return countNode(root);
    	}
    }

    public Object[] toArray(){
    	if(isEmpty()) return null;
    	int size = numNodes();
    	arr = new Object [size];
    	inOrderArray(root);
    	this.i = 0;
    	return arr;
    }

    public boolean contains(T val){
    	if(isEmpty())
    		return false;
    	return contains(root, val);
    }

    public boolean isEmpty(){
    	return root == null;
    }

    public BSTNode<T> find(T val){
    	if(!contains(val))
    		return null;
    	else {
    		return find(root, val);
    	}
    }

    public int height(){
    	if(isEmpty()) return -1;
    	return height(root);
    	
    }

    public Object[] nodesOnHeight(int h) {
    	if(h < 0 || root == null || h > height()) {
    		return null;
    	}
    	 nodesArr = new Object[(int) Math.pow(2, h)];
    	 nodesOnHeight(root, h, 0 );
    	 this.y = 0;
    	 return nodesArr;
    }

    public String DFT() {
    	if(root == null) return "";
    	
    	int len = numNodes();
    	String result ="";
    	for(int i = 0; i <= len-1 ; i++) { 
    		if(i == len-1) 
				result+=toArray()[i].toString();
    		 else if(i < len ) 
    			result+=toArray()[i].toString()+";";	
    	}
    	
    	return result;
    }

    public String BFT() {
    	if(root == null) {
    		return "";
    	}
    	
    	QueueList queue = new QueueList();
    	BSTNode<T> p = root;
    	queue.enqueue(p);
    	
    	
    	String result ="";
    	int len = numNodes();
    	Object [] nodes = new Object[len];
    	int i = 0; 
    	while(!queue.isEmpty()) {
    		p = queue.dequeue();
    		nodes[i++] = p;
    		if(p.left != null)
    			queue.enqueue(p.left);
    		if(p.right != null)
    			queue.enqueue(p.right);
    	}
    	
       	for(int j = 0; j <= len-1  ; j++) {
    		if(j == len-1)
    			result+=nodes[j].toString();
    		else if(j < len ) 
    			result+=nodes[j].toString()+";";
    	}
    	
       	return result;
    }

    public T maxVal(){
    	if(root == null) return null;
    	return maxVal(root);
    	
    }

    public T minVal(){
    	if(root == null) return null;
    	return minVal(root);
    }

    //=================ADD HELPER FUNCTIONS HERE============
    private Object[] nodesArr;
    private int y = 0;
    
    private void nodesOnHeight(BSTNode<T> node, int h, int currLevel /*, Object[] arr, int index*/) {
    	if(node == null) {
    		return;
    	}
    	
    	if(currLevel == h) {
    		nodesArr[y++] = node;
    	}
    	
    	nodesOnHeight(node.left, h, currLevel+1 /*, arr, index*/);
    	nodesOnHeight(node.right, h, currLevel+1 /*, arr, index*/);
    }
    
   
    private T maxVal(BSTNode<T> node) {
    	if(node.right != null) {
    		return maxVal(node.right);
    	}
    	return node.getVal();
    		
    }
    private T minVal(BSTNode<T> node) {
    	if(node.left != null) {
    		return minVal(node.left);
    	}
    	return node.getVal();
    		
    }
    
    private int countNode(BSTNode<T> node) {
    	int count = 1;
    	if(node == null) {
    		return 0;
    	} else {
    		count += countNode(node.left);
    		count += countNode(node.right);
    		return count;
    	}
    }
    
    private boolean contains(BSTNode<T> node, T val) {
    	if(val.compareTo(node.getVal()) == 0)
    		return true;
    	else if (val.compareTo(node.getVal()) < 0) {
    		// go left
    		if(node.left == null) {
    			return false;
    		} else {
    			return contains(node.left, val);
    		}
    	} else {
    		if(node.right == null) {
    			return false;
    		} else {
    			return contains(node.right, val);
    		}
    	}
    }
    
   private BSTNode<T> find(BSTNode<T> node, T val) {
	   if(val.compareTo(node.getVal()) == 0)
		   return node;
	   else if (val.compareTo(node.getVal()) < 0) {
		   // check left
		   if(node.left == null) {
			   return null;
		   } else {
			   return find(node.left, val);
		   }
	   } else {
		   if(node.right == null) {
			   return null;
		   } else {
			   return find(node.right, val);
		   }
	   }
   }
    
   private int height(BSTNode <T> node) {
	   if(node == null) {
		   return -1;
	   } else {
		   int heightOfLeft = height(node.left); 
		   int heightOfRight = height(node.right);
		   return maxHeight(heightOfLeft, heightOfRight) + 1;
	   }
   }
   
   private int maxHeight (int a, int b) {
	   return a > b ? a:b;
   }
      
    private Object [] arr; // stores array of nodes in BST
    private int i = 0;
    public void inOrderArray(BSTNode<T> node) {
    	if(node != null) {
    		inOrderArray(node.left);
    		arr[i++] = node;
    		inOrderArray(node.right);
    	}
    }
    
    // ============queue implementation as a helper class =========
    private class QueueList {
    	 class queueNode {
    		BSTNode<T> node;
    		queueNode next;
    		queueNode(BSTNode<T> v){
    			node = v;
    			next = null;
    		}
    	}
    	
    	queueNode front, rear;
		QueueList () {
    		front = rear = null;
    	}
		
    	public void enqueue(BSTNode<T> node) {
    		queueNode newNode = new queueNode (node);
    		// if queue is empty
    		if(rear == null) {
    			front = newNode;
    			rear = newNode;
    			return;
    		}
    		
    		// add newNode to end of queue
    		rear.next = newNode;
    		rear = newNode;
    	}
    	
    	public BSTNode<T> dequeue() {
    		if(front == null) return null;
    		BSTNode<T> tmp = front.node;
    		front = front.next;
    		if(front == null) rear = null;
    		return tmp;
    	}
    	
    	public boolean isEmpty() {
    		return front == null;
    	}
    	
    }
}
