package Model;

public class RBTree<T extends Comparable<? super T>> {
    private Node<T> root;
    public RBTree() {
        root = null;
    }

    public RBTree(T[] items) {

    }

    private Node<T> rightRotate(Node<T> node){
        Node<T> left = node.left;
        Node<T> left_right = left.right;
        left.right = node;
        node.left = left_right;
        return left;
    }

    private Node<T> leftRotate(Node<T> node){
        Node<T> right = node.right;
        Node<T> right_left = right.left;
        right.left = node;
        node.right = right_left;
        return null;
    }
}