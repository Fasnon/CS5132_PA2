package Model;

public class Node<T> {
    private T item;
    Node<T>[] neighbours; //Package accessibility

    boolean black;
    Boolean left_child; // nullable
    // Constructor to create Node containing item of generic class T
    public Node(T item){ this.item = item; }

    // Constructor to create Node containing item of generic class T,
    // with numEdges number of edges (but child nodes unknown)
    public Node(T item, int numNeighbours){
        this(item);
        this.neighbours = (Node<T>[]) new Node[numNeighbours];
    }

    // left child is nullable
    public Node(T item, boolean black, Boolean left_child, Node<T> left, Node<T> right, Node<T> parent){
        this(item, 3);
        this.black = black;
        this.left_child = left_child;
        this.neighbours[0] = left;
        this.neighbours[1] = right;
        if (left != null) {
            left.setParent(this);
            left.left_child = true;
        }
        if (right != null) {
            right.setParent(this);
            right.left_child = false;
        }
        this.neighbours[2] = parent;
    }

    public Node(T item, boolean black){
        this(item, black, null, null, null, null);
    }

    public Node(T item, boolean black, boolean left_child, Node<T> parent) {
        this(item, black, left_child, null, null, parent);
    }


    // Accessor method returns item stored in Node
    public T getItem() { return item;}

    // Mutator method sets the item to be stored in Node
    public void setItem(T item) { this.item = item;}

    Node<T> sibling() {
        if (parent() == null) return null;
        if (left_child) return parent().neighbours[1];
        return parent().neighbours[0];
    }
    Node<T> parent() {
        return this.neighbours[2];
    }

    void setParent(Node<T> p) {
        this.neighbours[2] = p;
    }

    @Override
    public String toString() {
        return "Node<" + item.toString() + ">";
    }
}
