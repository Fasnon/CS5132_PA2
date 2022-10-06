package Model;

public class RBTree<T extends Comparable<? super T>> {
    private RBNode<T> root;
    public RBTree() {
        root = null;
    }

    public RBTree(T item) {
        root = new RBNode<>(item, true);
    }

    public RBTree(T[] items) {
        for (T item : items) {
            insert(item);
        }
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

    public int blackHeight() {
        RBNode<T> curr = root;
        int h = 2;
        while (curr.left != null) {
            curr = curr.left;
            if (curr.black) h++;
        }
        return h;
    }

    public void insert(T item) {
        if (root == null) {
            root = new RBNode<>(item, true);
            return;
        }
        insert(item, root);
    }

    private void insert(T item, RBNode<T> curr) {
        // Complete the recursive code for insertion below this comment
        int c = item.compareTo(curr.getItem());
        if (c < 0) {
            if (curr.left == null) {
                curr.left = new RBNode<>(item, false, curr);
                // todo: fix red-red
                return;
            }
            insert(item, curr.left);
        } else if (c == 0) {
            throw new IllegalArgumentException(String.format("RB Tree already contains the value %s!", item));
        } else if (curr.right == null) {
            curr.right = new RBNode<>(item, false, curr);
            // todo: fix red-red
            return;
        }
        insert(item, curr.right);
    }

}