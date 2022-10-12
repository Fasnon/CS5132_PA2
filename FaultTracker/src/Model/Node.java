package Model;

import java.util.Arrays;

public class Node<T> {
    private T item;
    Node<T>[] neighbours; //Package accessibility

    boolean black;
    boolean left_child;
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

    public Node(T item, boolean black, boolean left_child, Node<T> left, Node<T> right, Node<T> parent){
        this(item, black);
        this.left_child = left_child;
        this.neighbours[0] = left;
        this.neighbours[1] = right;
        this.neighbours[2] = parent;
    }
    public Node(T item, boolean black, boolean left_child, Node<T> parent) {
        this(item, black, left_child, null, null, parent);
    }


    // Accessor method returns item stored in Node
    public T getItem() { return item;}

    // Mutator method sets the item to be stored in Node
    public void setItem(T item) { this.item = item;}
    Node<T> uncle() {
        if (this.neighbours[2] == null) return null;
        if (left_child) return this.neighbours[2].neighbours[1];
        return this.neighbours[2].neighbours[0];
    }
    Node<T> parent(){
        return this.neighbours[2];
    }

    @Override
    public String toString() {
        return "Node<" + item.toString() + ">";
    }
}
