package Model;
public class RBNode<T> {
    private T item;
    // Package accessibility
    boolean black;
    Boolean left_child;
    RBNode<T> left;
    RBNode<T> right;
    RBNode<T> parent;

    public RBNode(T item, boolean black){
        this.item = item;
        this.black = black;
    }

    public RBNode(T item, boolean black, RBNode<T> parent, boolean left_child) {
        this(item, black);
        this.parent = parent;
        this.left_child = left_child;
    }

    public RBNode(T item, boolean black, RBNode<T> parent, boolean left_child, RBNode<T> left, RBNode<T> right){
        this(item, black, parent, left_child);
        this.left = left;
        this.right = right;
    }

    // Copy constructor to copy the Node object n
    public RBNode(RBNode<T> n){
        this(n.item, n.black, n.parent, n.left_child, n.left, n.right);
    }

    // Accessor method returns item stored in Node
    public T getItem() {
        return item;
    }

    // Mutator method sets the item to be stored in Node
    public void setItem(T item) {
        this.item = item;
    }

    RBNode<T> uncle() {
        if (parent == null) return null;
        if (left_child) return parent.right;
        return parent.left;
    }
}
