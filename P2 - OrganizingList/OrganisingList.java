/** Name: Israel
 * number:u04865503
 * */
public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    
    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        if(node == null) {
            return 0;
        }
        else {
            return node.key + sumRec(node.next);
        }
            
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        if(node.next == null) {
            node.data = node.key;
            return node.key;
        }
        else {
            node.data = (node.key * sumRec(node)) - dataRec(node.next);
            return node.data;//(node.key * sumRec(node)) - dataRec(node.next);
        }
            
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate data fields.
     * @return The data of the node before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        if(!contains(key))
            return null;
        
        int dataFieldBefore = 0;
        ListNode current = root, prev = null, prevPrev = null;
        while(current != null && current.key != key) {
            prevPrev = prev;
            prev = current;
            current = current.next;
        }
        
            // accessed node is root
            if(current == root) {
                dataFieldBefore = current.data;
                calculateData();
                return dataFieldBefore;
            } else {
                // swap accessed node with its predecessor
                if(prevPrev == null) {
                  // Node to change is one after the root
                    dataFieldBefore = current.data;
                    prev.next = current.next;
                    current.next = prev;
                    root = current;
                    calculateData();
                    return dataFieldBefore;
                } else {
                dataFieldBefore = current.data;
                prevPrev.next = current;
                prev.next = current.next;
                current.next = prev;
                calculateData();
                return dataFieldBefore;
                }
            }
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        if(contains(key)) { // key exists
            ListNode current = root;
            ListNode prev = null;
            while(current!=null && current.key != key) {
                prev = current;
                current = current.next;
            }
            // Node to delete is root
            if(current == root) {
                root = root.next;
                // update data fields
                calculateData();
            } else if (current!= null && current.next!= null) {
                prev.next = current.next; 
                // update data fields
                calculateData();
            } else {
                prev.next = null;
                // update data fields
                calculateData();
            }
        } else { // key does not exist
            return;  
        }
    }


    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        ListNode newNode = new ListNode(key);
        if(root == null) {
            root = newNode;
            calculateData();
        } else {
            ListNode current = root, prev = null;
            while(current != null && current.key != key) {
                prev = current;
                current = current.next;
            }
            if(current!= null && current.key == key) return; // do not allow duplicates
            prev.next = newNode; // newNode added to the end/back of list
            // update all nodes' data fields
            calculateData();
        }
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        if(root != null) {
           ListNode current = root;
           while(current != null && current.key != key) {
               current = current.next;
           }
           if(current!= null && current.key == key)
               return true;
           return false;
        }
       return false;
    }


    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }

    
}