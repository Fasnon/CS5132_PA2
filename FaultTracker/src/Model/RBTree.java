package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        Node<T> curr = root;
        int h = 2;
        while (curr.neighbours[0] != null) {
            curr = curr.neighbours[0];
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
    // find node that do not have a left child
    // in the subtree of the given node
    private Node<T> successor(Node<T> x) {
        Node<T> temp = x;

        while (temp.neighbours[0] != null)
            temp = temp.neighbours[0];
        return temp;
    }
    private Node<T> BSTreplace(Node<T> x) {
        // when node have 2 children
        if (x.neighbours[0] != null&& x.neighbours[1] != null)
        return successor(x.neighbours[1]);

        // when leaf
        if (x.neighbours[0] == null && x.neighbours[1] == null)
        return null;

        // when single child
        if (x.neighbours[0] != null)
            return x.neighbours[0];
        else
            return x.neighbours[1];
    }
    public Node<T> delete(T item, Node<T> curr){
        if(this.height(root)==0){
            return null;
        }
        if(item.compareTo(curr.getItem())<0){
          delete(item, curr.neighbours[0]);

        }
        else if(item.compareTo(curr.getItem())>0){
        delete(item, curr.neighbours[1]);
        }

        else{
            Node<T> u = BSTreplace(curr);
            boolean isTrue= ((u == null&&u.black == true)&&(curr.black==true));
            Node<T> p = curr.neighbours[2];
            if(u == null){    //red then black or red or null
                if(curr == root) {
                    root = null;

                }
                else{
                    if(isTrue){
                        // black then red
                           fixDoubleBlack(curr);


                    }
                    else{// pain
                        if(curr.uncle()!= null){

                            curr.black = false;
                        }
                    }
                    if(curr.left_child){
                        p.neighbours[0] = null;
                    }
                    else{

                        p.neighbours[1] = null;
                    }

                }
                return curr;
            }
            if(curr.neighbours[0] == null||curr.neighbours[1] == null) {

                if(curr== root) {
                    curr.setItem(u.getItem());
                   curr.neighbours[0] = null;
                    curr.neighbours[1] = null;
                }
                else{
                    if(curr.left_child){
                       p.neighbours[0] =u;

                    }
                    else{// pain
                        p.neighbours[1] = u;

                    }
                    u.neighbours[2] = p;
                    if(isTrue){
                        fixDoubleBlack(u);
                    }
                    else{
                        u.black = true;
                    }
                }
                return curr;
            }


//                Node<T> Temp = curr.neighbours[1];
//                while(Temp.neighbours[0]!= null){
//                    Temp = Temp.neighbours[0];
//                }
//                curr.setItem(Temp.getItem());
//                curr.neighbours[1] = delete(curr.getItem(), curr.neighbours[1]);
//
            
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
                curr.neighbours[0] = new Node<>(item, false, true, curr);
                insert_fix(curr.neighbours[0]);
                return;
            }
            insert(item, curr.neighbours[0]);
            return;
        } else if (c == 0) {
            throw new IllegalArgumentException(String.format("RB Tree already contains the value %s!", item));
        } else if (curr.neighbours[1] == null) {
            curr.neighbours[1] = new Node<>(item, false, false, curr);
            insert_fix(curr.neighbours[1]);
            return;
        }
        insert(item, curr.neighbours[1]);
    }

    private void insert_fix(Node<T> node) {
        // node passed is red
        if (node.neighbours[2].black) return;
        Node<T> u = node.uncle(), p = node.neighbours[2], g = p.neighbours[2];
        if (u == null) return;
        if (u.black) {
            if (node.left_child != p.left_child) {
                // inside case
                if (node.left_child) rightRotate(p);
                else leftRotate(p);
            }
            // outside case
            if (node.left_child) rightRotate(g);
            else leftRotate(g);
            return;
        }
        // uncle red case
        p.black = u.black = true;
        if (g != null) {
            g.black = false;
            insert_fix(g);
        }
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

    public ArrayList<Node<T>> levelOrder() {
        ArrayList<Node<T>> result = new ArrayList<>();
        Queue<Node<T>> level = new LinkedList<>();
        level.add(root);
        while (level.size() > 0) {
            int level_size = level.size();
            for (int i = 0; i < level_size; i++) {
                Node<T> node = level.remove();
                if (node.neighbours[0] != null) {
                    level.add(node.neighbours[0]);
                    result.add(node.neighbours[0]);
                }
                if (node.neighbours[1] != null) {
                    level.add(node.neighbours[1]);
                    result.add(node.neighbours[1]);
                }
            }
        }
        return result;
    }

    public String toString(){
        if (root == null) return "";
        StringBuilder result = new StringBuilder(String.format("%-2s \n", root.getItem()));
        Queue<Node<T>> level = new LinkedList<>();
        level.add(root);

        while (level.size() > 0) {
            int level_size = level.size();
            for (int i = 0; i < level_size; i++) {
                Node<T> node = level.remove();
                if (node.neighbours[0] != null) {
                    level.add(node.neighbours[0]);
                    result.append(String.format("%-2s ", node.neighbours[0].getItem()));
                } else {
                    result.append("   ");
                }
                if (node.neighbours[1] != null) {
                    level.add(node.neighbours[1]);
                    result.append(String.format("%-2s ", node.neighbours[1].getItem()));
                } else {
                    result.append("   ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}