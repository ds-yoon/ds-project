//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
//A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon


public class PRedBlackBST<Key extends Comparable<Key>, Value>
 extends PBST<Key, Value>
{
 // Constructors: these just call the parent class constructors.
 public PRedBlackBST() { super(); }
 PRedBlackBST(Node r) { super(r); }

 // Color constants, just for convenience.
 static final boolean RED   = true;
 static final boolean BLACK = false;

 // The setRoot(Node r) method.  Its declared return type is still
 // PBST, but the actual type returned is PRedBlackBST.  It could
 // return this tree, if nothing has actually changed.
 PBST<Key,Value> setRoot(Node r) {
     return r==root ? this : new PRedBlackBST<Key,Value>(r);
 }

public PBST<Key, Value> put(Key key, Value val){
	Node newRoot = put(root,key, val);
	newRoot = newRoot.setColor(BLACK);
	return 	setRoot(newRoot);
}

 public Node put(Node h, Key key, Value val) { 
     if (h == null) {
         return new Node(key, val, null, null, RED);
     }

     int cmp = key.compareTo(h.key);
     if      (cmp < 0) h = h.setLeft(put(h.left,  key, val)); 
     else if (cmp > 0) h = h.setRight(put(h.right, key, val)); 
     else              h = h.setVal(val);

     // fix-up any right-leaning links
     if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h); 
     if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h); 
     if (isRed(h.left)  &&  isRed(h.right))     h = flipColors(h);

     return h;
 }

 // TODO: persistent version of rotateRight(h), two new nodes.
 // Node rotateRight(Node h) { ... }
 private Node rotateRight(Node h){
	 Node x = h.left;
	 Node newh = new Node(h.key, h.val, x.right, h.right, RED);
	 Node newx = new Node(x.key, x.val, x.left, newh, h.color);
	 return newx;
 }

 // TODO: persistent version of rotateLeft(h), two new nodes.
 
 private Node rotateLeft(Node h) {
    Node x = h.right;
	Node newh = new Node(h.key, h.val, h.left, x.left, RED);
	Node newx = new Node(x.key, x.val, newh, x.right, h.color);
     return newx;
 }

 // TODO: presistent version of flipColors(h), three new nodes.
private Node flipColors(Node h){
	Node nl = put(h.left, h.left.key, h.left.val);
	Node nr = put(h.right, h.right.key, h.right.val);
	nr = nr.setColor(BLACK);
	nl = nl.setColor(BLACK);
	Node newH = new Node(h.key, h.val, nl, nr, RED);
	return newH;
}

 // Just like IntIterator, a StackIterator lets us visits the keys
 // in order.  It uses a stack of O(H) nodes (where H is height)
 // to keep track of the unvisited parts of the tree.  It turns
 // out this approach is faster, only O(1) amortized time per visit.
 // Like IntIterator, we will not implement the remove() method, just
 // the hasNext() and next() methods.
 //
 // IDEA: for each Node x on the stack, we still need to visit x,
 // followed by all the nodes in its x.right subtree.
 // So, just to construct the initial stack, you should push the
 // "left spine" of the initial tree onto the stack.

 /* Note: this starts a C-style multiline comment!

 // This line uses StackIterator instead of IntIterator:
 public java.util.Iterator<Key> iterator() { return new StackIterator(); }

 class StackIterator implements java.util.Iterator<Key> {
     java.util.Stack<Node> todo = new java.util.Stack<Node>();
     // TODO: constructor, and the hasNext() and next() methods.
     // I'll give you remove, since we are not supporting deletion:
     public void remove() { // we cannot remove
         throw new UnsupportedOperationException();
     }
 }

 * end of the multiline comment */
public java.util.Iterator<Key> iterator() { return new StackIterator(); }

class StackIterator implements java.util.Iterator<Key> {
    java.util.Stack<Node> todo = new java.util.Stack<Node>();
    
    StackIterator() {
    	Node newRoot = root;
    	while (newRoot != null){
    		todo.push(newRoot);
    		newRoot = newRoot.left;
    		setRoot(newRoot);
    	}
    }
    public Key next() { 
    	Node n = todo.pop();
    	Key key = n.key;
    	n = n.right;
    	while (n != null){
    		todo.push(n);
    		n = n.left;
    	}
    	return key;
    }
    public boolean hasNext() { return !todo.isEmpty(); }
    public void remove() { // we cannot remove
        throw new UnsupportedOperationException();
    }
}
}
