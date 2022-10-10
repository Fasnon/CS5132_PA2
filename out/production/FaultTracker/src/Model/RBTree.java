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

    private void rightRotate(RBNode<T> node){
        RBNode<T> left = node.left, left_right = left.right;
        left.right = node;
        node.left = left_right;
        left.parent = node.parent;
        left.left_child = node.left_child;
        node.parent = left;
        node.left_child = false;
        left_right.parent = node;
        left_right.left_child = true;
    }

    private void leftRotate(RBNode<T> node){
        RBNode<T> right = node.right, right_left = right.left;
        right.left = node;
        node.right = right_left;
        right.parent = node.parent;
        right.left_child = node.left_child;
        node.parent = right;
        node.left_child = true;
        right_left.parent = node;
        right_left.left_child = false;
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
    public void delete(T item){
        if(root == null)
            return;
        delete(item, root);

    }
    public void delete(T item, RBNode<T> curr){



    }
    private void insert(T item, RBNode<T> curr) {
        // Complete the recursive code for insertion below this comment
        int c = item.compareTo(curr.getItem());
        if (c < 0) {
            if (curr.left == null) {
                curr.left = new RBNode<>(item, false, curr, true);
                insert_fix(curr.left);
                return;
            }
            insert(item, curr.left);
        } else if (c == 0) {
            throw new IllegalArgumentException(String.format("RB Tree already contains the value %s!", item));
        } else if (curr.right == null) {
            curr.right = new RBNode<>(item, false, curr, false);
            insert_fix(curr.right);
            return;
        }
        insert(item, curr.right);
    }

    private void insert_fix(RBNode<T> node) {
        // node passed is red
        if (node.parent.black) return;
        RBNode<T> u = node.uncle(), p = node.parent, g = p.parent;
        if (u.black) {
            if (node.left_child != p.left_child) {
                // inside case
                if (node.left_child) rightRotate(p);
                else leftRotate(p);
            }
            // outside case
            if (node.left_child) rightRotate(g);
            else leftRotate(g);
        }
        // uncle red case
        p.black = u.black = true;
        g.black = false;
        insert_fix(g);
    }
}