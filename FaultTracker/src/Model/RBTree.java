package Model;

public class RBTree<T extends Comparable<? super T>> {
    private RBNode<T> root;
    public RBTree() {
        root = null;
    }

    public RBTree(T[] items) {

    }

    private RBNode<T> rightRotate(RBNode<T> node){
        RBNode<T> left = node.left;
        RBNode<T> left_right = left.right;
        left.right = node;
        node.left = left_right;
        return left;
    }

    private RBNode<T> leftRotate(RBNode<T> node){
        RBNode<T> right = node.right;
        RBNode<T> right_left = right.left;
        right.left = node;
        node.right = right_left;
        return null;
    }
}