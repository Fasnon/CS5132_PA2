package Model;
public class Node<T> {
    private T item;
    Node<T>[] neighbours; //Package accessibility

    boolean black;
    Boolean left_child;
    // Constructor to create Node containing item of generic class T
    public Node(T item){ this.item = item; }

    // Constructor to create Node containing item of generic class T,
    // with numEdges number of edges (but child nodes unknown)
    public Node(T item, int numNeighbours){
        this(item);
        this.neighbours = new Node[numNeighbours];
    }
    public Node(T item, boolean black){
        this(item,3);


        this.black = black;
    }
    public Node(T item, boolean black, boolean left_child) {
        this(item, black);

        this.left_child = left_child;
    }
    public Node(T item, boolean black,Node<T> parent, boolean left_child) {
        this(item, black);
        this.neighbours[2] = parent;
        this.left_child = left_child;
    }
    public Node(T item, boolean black, boolean left_child, Node<T> left, Node<T> right){
        this(item, black, left_child);
        this.neighbours[0] = left;
        this.neighbours[1] = right;
    }
    public Node(RBNode<T> n){
        this(n.getItem(), n.black,  n.left_child, n.neighbours[0], n.neighbours[1]);
    }
    // Constructor to create Node containing item of generic class T,
    // with child nodes defined in the neighbours Node array
    public Node(T item, Node<T>[] neighbours){
        this(item);
        this.neighbours = new Node[neighbours.length];
        for (int i=0; i<neighbours.length; i++)
            this.neighbours[i] = new Node(neighbours[i]);
    }

    // Copy constructor to copy the Node object n
    public Node(Node<T> n){ this(n.item, n.neighbours); }

    // Accessor method returns item stored in Node
    public T getItem() { return item;}

    // Mutator method sets the item to be stored in Node
    public void setItem(T item) { this.item = item;}
    Node<T> uncle() {
        if (this.neighbours[2]== null) return null;
        if (left_child) return this.neighbours[2].neighbours[1];
        return this.neighbours[2].neighbours[0];
    }
    Node<T> parent(){
        return this.neighbours[2];
    }
}
