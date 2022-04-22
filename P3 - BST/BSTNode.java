public class BSTNode<T extends Comparable<T>> {
    private T value;
    public BSTNode<T> right;
    public BSTNode<T> left;

    public boolean recInsert(T val){
        if(val.compareTo(value) == 0)
            return false;

        if(val.compareTo(value) < 0)
        {
            if(left == null)
            {
                left = new BSTNode<>(val);
                return true;
            } else {
                return left.recInsert(val);
            }
        } else {
            if(right == null)
            {
                right = new BSTNode<>(val);
                return true;
            } else {
                return right.recInsert(val);
            }
        }
    }

    //Implement the following

    public BSTNode(T val){
    	this.value = val;
    	this.left = null;
    	this.right = null;
    }

    public T getVal(){
    	return value;
    }

    public String toString() {
    	String L, V, R;
    	if(this.left == null) 
    		L = "L[]";
    	else
    		L = "L["+left.value+"]";
    	
    	if(this.right == null)
    		R = "R[]";
    	else
    		R = "R["+right.value+"]";
    	
    	V = "V["+this.value+"]";
    	
    	return L+V+R;
    }

    //ADD HELPER FUNCTIONS HERE
}
