package Model;

import java.util.ArrayList;

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
    public ArrayList<T> inOrder(){
        ArrayList<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }
    private void inOrder(Node<T> curr, ArrayList<T> result){
        if (curr != null) {
            inOrder(curr.neighbours[0], result);
            result.add(curr.getItem());
            inOrder(curr.neighbours[1], result);
        }
    }
    private void rightRotate2(Node<T> node){
        Node<T> left = node.neighbours[0], left_right = left.neighbours[1];
        left.neighbours[1] = node;
        node.neighbours[0] = left_right;
        left.neighbours[2] = node.parent();
        left.left_child = node.left_child;
        node.neighbours[2] = left;
        node.left_child = false;
        left_right.neighbours[2] = node;
        left_right.left_child = true;
    }
    private Node<T> rightRotate(Node<T> node){
        // Complete the code below this comment
        Node<T> root = node.neighbours[0];
        if(root.neighbours[1]!=null){
            Node<T> temp = root.neighbours[1];
            root.neighbours[1] = node;
            node.neighbours[0] = temp;


        }
        else{

            root.neighbours[1] = node;
            node.neighbours[0] = null;

        }
        return root;
    }

    // Method to perform the left rotation of a subtree with node as the root
    private Node<T> leftRotate(Node<T> node){
        Node<T> root = node.neighbours[1];
        if(root.neighbours[0]!=null){
            Node<T> temp = root.neighbours[0];
            root.neighbours[0] = node;
            node.neighbours[1] = temp;


        }
        else{

            root.neighbours[0] = node;
            node.neighbours[1] = null;

        }
        return root;
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
        h--;
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
    public RBNode<T> delete(T item, RBNode<T> curr){
        if(this.height(root)==0){
            return null;
        }
        if(item.compareTo(curr.getItem())<0){
            curr.left = delete(item, curr.right);

        }
        else if(item.compareTo(curr.getItem())>0){
            curr.right= delete(item, curr.left);
        }

        else{

            if(curr.left == null){  //red then black or red or null
                if(curr.black == false) {
                    if (curr.right == null) { // null
                        curr = null;
                        return null;
                    }
                    else { // red or black or null or whatever
                        curr = curr.right;
                        curr.black = true;
                    }
                }
                else{
                    if(curr.right!=null&&curr.right.black!=true){
                        // black then red
                            curr = curr.right;
                            curr.black = true;


                    }
                    else{// pain
                        curr = curr.right;
                        fixDoubleBlack(curr);
                    }

                }
            }
            else if(curr.right == null) {
                curr =  curr.left;
                if(curr.black == false) {
                    if (curr.left == null) { // null
                        curr = null;
                        return null;
                    }
                    else { // red or black or null or whatever
                        curr = curr.left;
                        curr.black = true;
                    }
                }
                else{
                    if(curr.left!=null){
                        if(curr.left.black!=true){ // black then red
                            curr = curr.left;
                            curr.black = true;

                        }

                    }
                    else{// pain


                    }

                }

            }
            else {

                RBNode<T> Temp = curr.right;
                while(Temp.left!= null){
                    Temp = Temp.left;
                }
                curr.setItem(Temp.getItem());
                curr.right = delete(curr.getItem(), curr.right);
            }
        }
        return null;

    }

    private void fixDoubleBlack(Node<T> curr) {
        if(curr==root){

            return;
        }

        Node<T> s = curr.uncle(), p = curr.parent();
        if(s==null){
                fixDoubleBlack(curr.parent());

        }
        else{
            if(!s.black){

                p.black = false;
                s.black = true;
                if(s.left_child){
                    rightRotate(p);
                }
                else{
                    leftRotate(p);
                }
                fixDoubleBlack(s);
            }
            else{
                if((s.neighbours[0]!=null&&(!s.neighbours[0].black)||(s.neighbours[1]!=null&&(!(s.neighbours[1].black))))){
                    if((s.neighbours[0]!=null&&(!s.neighbours[0].black))){
                        if(s.left_child){
                            s.neighbours[0].black = s.black;
                            s.black = p.black;
                            rightRotate(p);

                        }
                        else{
                            s.neighbours[0].black = p.black;
                            rightRotate(s);
                            leftRotate(p);


                        }

                    }
                    else{

                        if(s.left_child){
                            s.neighbours[1].black = p.black;
                            leftRotate(s);
                            rightRotate(p);


                        }
                        else{
                            s.neighbours[1].black= s.black;
                            s.black = p.black;
                            leftRotate(p);

                        }
                    }
                    p.black = true;

                }
                else{

                    s.black = false;
                    if(p.black)
                        fixDoubleBlack(p);
                    else
                        p.black = true;
                }



            }
        }



    }



    private void insert(T item, Node<T> curr) {
        // Complete the recursive code for insertion below this comment
        int c = item.compareTo(curr.getItem());
        if (c < 0) {
            if (curr.neighbours[0] == null) {
                curr.neighbours[0] = new Node<>(item, false, curr, true);
                insert_fix(curr.neighbours[0]);
                return;
            }
            insert(item, curr.neighbours[0]);
        } else if (c == 0) {
            throw new IllegalArgumentException(String.format("RB Tree already contains the value %s!", item));
        } else if (curr.neighbours[1] == null) {
            curr.neighbours[1] = new Node<>(item, false, curr, false);
            insert_fix(curr.neighbours[1]);
            return;
        }
        insert(item, curr.neighbours[1]);
    }

    private void insert_fix(Node<T> node) {
        // node passed is red
        if (node.neighbours[2].black) return;
        Node<T> u = node.uncle(), p = node.neighbours[2], g = p.neighbours[2];
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
    private int height(RBNode<T> node){
        if (node == null) return 0;
        else{
            int leftDepth = height(node.left);
            int rightDepth = height(node.right);
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }

    }
    public RBTree<T> merge(RBTree<T> a){
        ArrayList<T> thisone = inOrder();
        ArrayList<T> B = inOrder();
        RBTree<T> ret = new RBTree<T>();
        for (int index1 = 0, index2 = 0; index2 < B.size(); index1++) {
            if (index1 == thisone.size() || thisone.get(index1).compareTo(B.get(index2))>0) {
                thisone.add(index1, B.get(index2++));
            }
        }
        for(T as : thisone){
            ret.insert(as);


        }
        return ret;


    }
}