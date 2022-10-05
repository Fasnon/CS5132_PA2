package Model;
public class Node<T> {
    private T item;
    // Package accessibility
    Node<T> left;
    Node<T> right;

    public Node(T item){
        this.item = item;
    }

    public Node(T item, Node<T> left, Node<T> right){
        this(item);
        this.left = left;
        this.right = right;
    }

    // Copy constructor to copy the Node object n
    public Node(Node<T> n){
        this(n.item, n.left, n.right);
    }

    // Accessor method returns item stored in Node
    public T getItem() {
        return item;
    }

    // Mutator method sets the item to be stored in Node
    public void setItem(T item) {
        this.item = item;
    }
}
