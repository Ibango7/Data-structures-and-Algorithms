/**
 * Name: Israel Bango
 * Student Number: u04865503
 */

public class SplayTree<T extends Comparable<T>> {

	protected enum SplayType {
		SPLAY,
		SEMISPLAY,
		NONE
	}	

	protected Node<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}
	
	protected String preorder(Node<T> node, boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem 
	* 		 	The element to be inserted into the tree
	* @return 
	*			Returns true if the element was successfully inserted into the tree. 
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {
		
		if(contains(elem)) {
			return false;
		}
		
		boolean flag = false;
		Node<T> currNode = root;
		Node<T> prevNode = null;
		
		while(currNode != null) {
			prevNode = currNode;
			
			if(currNode.elem.compareTo(elem) < 0) {
				currNode = currNode.right;
			} else {
				currNode = currNode.left;
			}
		}
		
		// if root is null this is the first node to tree
		if(root == null) {
			root = new Node<T>(elem); // new node is root
			flag = true;
		} else if(prevNode.elem.compareTo(elem)<0) {
			prevNode.right = new Node<T>(elem);
			flag = true;
		} else {
			prevNode.left = new Node<T>(elem);
			flag = true;
		}
		
		return flag;
	}
	
	/**
	* Checks whether a given element is already in the tree.
	* @param elem 
	* 		 	The element being searched for in the tree
	* @return 
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {
		//Your code goes here
		Node<T> current = root;
		while(current != null) {
			if(current.elem.compareTo(elem) == 0) {
				return true;
			} else if(elem.compareTo(current.elem) < 0 ) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		return false;
	}
	
	/**
	 * Accesses the node containing elem. 
	 * If no such node exists, the node should be inserted into the tree.
	 * If the element is already in the tree, the tree should either be semi-splayed so that 
	 * the accessed node moves up and the parent of that node becomes the new root or be splayed 
	 * so that the accessed node becomes the new root.
	 * @param elem
	 *			The element being accessed
	 * @param type
	 *			The adjustment type (splay or semi-splay or none)
	 */
	public void access(T elem, SplayType type) {
		if(contains(elem)) {
			// node is the tree
			if(type == SplayType.SPLAY) {
				this.splay(this.getNode(elem));
			} else if(type == SplayType.SEMISPLAY) {
				this.semisplay(this.getNode(elem));
			} else if(type == SplayType.NONE) {
				// does nothing
			}
		} else { // node not in the tree
			//insert node in the tree
			this.insert(elem);
		}
	}
	
	/**
	 * Semi-splays the tree using the parent-to-root strategy
	 * @param node
	 *			The node the parent of which will be the new root
	 */
	protected void semisplay(Node<T> node) {
		Node<T> child_parent =  getParent(node);
		Node<T> child_grandParent = getGrandParent(node);
		
		while(node != root) { // while node is not the root i.e: root has valid parent
			child_parent = getParent(node);
			child_grandParent = getGrandParent(node);
			//case 1: Parent is the root
			if(child_parent == root) { // 
				// rotate accessed node(child) about parent
				if(child_parent!= null && child_parent.left == node) {
					// right rotation
					 this.rotateRight(node);
				} else if(child_parent!= null && child_parent.right == node) {
					//left rotation
					//parent becomes the right child's left
					this.rotateLeft(node);
				}
			
				//case 2: Homogeneous configuration
				
			} else if(child_parent!=null && child_grandParent.left == child_parent && child_parent.left == node 
					|| child_parent!=null && child_grandParent.right == child_parent && child_parent.right == node){
				
					if(child_grandParent.left == child_parent) {
						// 2 right rotations
						//1 Rotate Parent about grand parent
						 this.rotateRight(child_parent);
						//2 Rotate child about parent
						//this.rotateRight(node);
						
					} else if(child_grandParent.right == child_parent) {
						//2 left rotations
						//1 P about G
						this.rotateLeft(child_parent);
						//this.rotateLeft(null, child_grandParent, child_parent);
						
						//2 Rotate child about parent
						// this.rotateLeft(node);
					}
				
				//case 3: Heterogeneous configuration
			} else {
				
						if(child_parent!=null && child_parent.left == node && child_grandParent.right == child_parent) {
							// right rotation
							this.rotateRight(node);
							//System.out.println("Yes 1");
							this.rotateLeft(node);
							
						} else if(child_parent!=null && child_parent.right == node && child_grandParent.left == child_parent) {
							//left rotation
							// child/node about parent
							this.rotateLeft(node);
							// child node about G
							this.rotateRight(node);
						}

					}
			   }
		
	}

	/**
	 * Splays the tree using the node-to-root strategy
	 * @param node
	 *			The node which will be the new root
	 */
	protected void splay(Node<T> node) {
		Node<T> child_parent =  getParent(node);
		Node<T> child_grandParent = getGrandParent(node);
		
		while(node != root) { // while node is not the root i.e: root has valid parent
			child_parent = getParent(node);
			child_grandParent = getGrandParent(node);
			//case 1: Parent is the root
			if(child_parent == root) { // 
				// rotate accessed node(child) about parent
				if(child_parent!= null && child_parent.left == node) {
					// right rotation
					 this.rotateRight(node);
				} else if(child_parent!= null && child_parent.right == node) {
					//left rotation
					//parent becomes the right child's left
					this.rotateLeft(node);
				}
			
				//case 2: Homogeneous configuration
				
			} else if(child_parent!=null && child_grandParent.left == child_parent && child_parent.left == node 
					|| child_parent!=null && child_grandParent.right == child_parent && child_parent.right == node){
				
					if(child_grandParent.left == child_parent) {
						// 2 right rotations
						//1 Rotate Parent about grand parent
						 this.rotateRight(child_parent);
						//2 Rotate child about parent
						this.rotateRight(node);
						
					} else if(child_grandParent.right == child_parent) {
						//2 left rotations
						//1 P about G
						this.rotateLeft(child_parent);
						//this.rotateLeft(null, child_grandParent, child_parent);
						
						//2 Rotate child about parent
						 this.rotateLeft(node);
					}
				
				//case 3: Heterogeneous configuration
			} else {
				
						if(child_parent!=null && child_parent.left == node && child_grandParent.right == child_parent) {
							// right rotation
							this.rotateRight(node);
							//System.out.println("Yes 1");
							this.rotateLeft(node);
							
						} else if(child_parent!=null && child_parent.right == node && child_grandParent.left == child_parent) {
							//left rotation
							// child/node about parent
							this.rotateLeft(node);
							// child node about G
							this.rotateRight(node);
						}

					}
			   }
		
		   }
	
	// perform right rotation
	private void rotateRight(/*Node<T> Gparent, Node<T> Parent,*/ Node<T> Access_child ) {

		Node<T> Parent = getParent(Access_child);
		Node<T> Gparent = getGrandParent(Access_child);
		
		if(Parent == root) {
			Node<T> temp= Access_child.right;
			Access_child.right = Parent;
			Access_child.right.left = temp; //Parent.left  = temp;
			root = Access_child;
		}else {
			Node<T> temp= Access_child.right;
			Access_child.right = Parent;
			Parent.left = temp;
			
			if(Gparent.right == Parent) {
				Gparent.right = Access_child;
			}else {
				Gparent.left = Access_child;
			}
		}
	
	}
	
	
	 // perform left rotation                        
	private  void rotateLeft(/*Node<T> Gparent, Node<T> Parent,*/ Node<T> Access_child ) {
			
		Node<T> Parent = getParent(Access_child);
		Node<T> Gparent = getGrandParent(Access_child);
		
		if(Parent == root) {
			Node<T> temp= Access_child.left;
			Access_child.left = Parent;
			Access_child.left.right = temp; // Parent.right = temp
			root = Access_child;
		}else {
			Node<T> temp= Access_child.left;
			Access_child.left = Parent;
			Parent.right = temp;
			
			if(Gparent.right == Parent) {
				Gparent.right = Access_child;
			}else {
				Gparent.left = Access_child;
			}
		}
		
	}
	
	// return node if found in the tree or return null
	private Node<T> getNode(T elem){
		//Your code goes here
		Node<T> current = root;
		while(current != null) {
			if(current.elem.compareTo(elem) == 0) {
				return current;
			} else if(elem.compareTo(current.elem) < 0 ) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		return null;
	}
	
	
	
	// returns Grand parent of node
	public Node<T>  getGrandParent(Node<T> node) {
		if(node!=null)
			if(root == null || (root!=null &&root.elem.compareTo(node.elem) == 0)) {
				// it has no parent
				return null;
			}
		
		
		Node<T> current = root;
		Node<T> parent = null;
		Node<T> Gparent = null;
		int count = 0;
		while(current !=null) {
			if(count > 0) {
				if(current.elem != node.elem) {
					Gparent = parent;
				}
			}
			if(node!=null && node.elem.compareTo(current.elem) < 0) {
				parent = current;
				current = current.left;
			} else if(node!=null && node.elem.compareTo(current.elem) >0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
			count++;
		}

		return current != null ? Gparent: null;
	}
	
	// returns  parent of node
	public Node<T>  getParent(Node<T> node) {
	
		if(node!=null)
			if(root == null || (root!=null && root.elem.compareTo(node.elem) == 0)) {
				// it has no parent
				return null;
			}
		
		
		Node<T> current = root;
		Node<T> parent = null;
		while(current !=null) {
			if(node!=null && node.elem.compareTo(current.elem) < 0) {
				parent = current;
				current = current.left;
			} else if(node!=null && node.elem.compareTo(current.elem) >0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
		}

		return current != null ? parent: null;
	}
	
}