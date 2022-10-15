package Model;

import java.util.*;

public class RBTree<T extends Comparable<? super T>> {
    private Node<T> root;
    public RBTree() {
        root = null;
    }

    public RBTree(T item) {
        root = new Node<>(item, true);
    }

    public RBTree(Iterable<T> items) {
        for (T item : items) {
            insert(item);
        }
    }

    public Node<T> getRoot() {
        return root;
    }


    public RBTree(T[] items) {
        for (T item : items) {
            insert(item);
        }
    }

    public ArrayList<T> inOrder(){
        return inOrder(new ArrayList<>());
    }

    public <C extends Collection<T>> C inOrder(C collection) {
        inOrder(root, collection);
        return collection;
    }

    private void inOrder(Node<T> curr, Collection<T> result){
        if (curr != null) {
            inOrder(curr.neighbours[0], result);
            result.add(curr.getItem());
            inOrder(curr.neighbours[1], result);
        }
    }

    private void rightRotate(Node<T> node) {
        rotate(node, false);
    }

    private void leftRotate(Node<T> node) {
        rotate(node, true);
    }

    private void rotate(Node<T> node, boolean left){
        // 0 when left 1 otherwise
        int i = Boolean.compare(true, left);
        int alt_i = 1 - i;
        Node<T> root = node.neighbours[alt_i], temp = root.neighbours[i], p = node.parent();
        root.neighbours[i] = node;
        node.neighbours[alt_i] = temp;
        root.setParent(p);
        root.left_child = node.left_child;
        // 0 when left_child is true, 1 when false
        if (p == null) this.root = root;
        else p.neighbours[-node.left_child.compareTo(true)] = root;
        node.setParent(root);
        node.left_child = left;
        if (temp != null) {
            temp.setParent(node);
            temp.left_child = !left;
        }
    }

    public int blackHeight() {
        Node<T> curr = root;
        int h = 1;
        while (curr.neighbours[0] != null) {
            curr = curr.neighbours[0];
            if (curr.black) h++;
        }
        return h;
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
    private Node<T> BSTReplace(Node<T> x) {
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
        System.out.println(curr.getItem());
        if(this.height(root)==0){
            return null;
        } else if (curr == null) {
            // item not in tree
            return null;
        }
        if(item.compareTo(curr.getItem())<0){
          delete(item, curr.neighbours[0]);

        }
        else if(item.compareTo(curr.getItem())>0){
        delete(item, curr.neighbours[1]);
        }

        else{
            Node<T> u = BSTReplace(curr);
            boolean isTrue= ((u == null|| u.black)&&(curr.black));
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
                        if(curr.sibling()!= null){

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
                        u.left_child = true;
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
        swapValues(curr, u);
            delete(u.getItem(),u);
        }
        return null;

    }

    private void swapValues(Node<T> curr, Node<T> u) {
        T temp;
        temp = u.getItem();
        u.setItem(curr.getItem());
        curr.setItem(temp);
    }

    private void fixDoubleBlack(Node<T> curr) {
        if(curr==root){

            return;
        }

        Node<T> s = curr.sibling(), p = curr.parent();
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

    public void insert(T item) {
        if (root == null) {
            root = new Node<>(item, true);
            return;
        }
        insert(item, root);
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
        Node<T> p = node.neighbours[2], u = p.sibling(), g = p.neighbours[2];
        if (u == null || u.black) {
            if (node.left_child != p.left_child) {
                // inside case
                if (node.left_child) rightRotate(p);
                else leftRotate(p);
            }
            // outside case
            g.black = !g.black;
            if (node.left_child) {
                g.neighbours[0].black = !g.neighbours[0].black;
                rightRotate(g);
            } else {
                g.neighbours[1].black = !g.neighbours[1].black;
                leftRotate(g);
            }
            return;
        }
        // uncle red case
        p.black = u.black = true;
        if (g != null && g != root) {
            g.black = false;
            insert_fix(g);
        }
    }

    private int height(Node<T> node){
        if (node == null) return 0;
        else{
            int leftDepth = height(node.neighbours[0]);
            int rightDepth = height(node.neighbours[1]);
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }

    }

    public RBTree<T> merge(RBTree<T> o) {
        // LinkedList implements both Queue and List
        LinkedList<T> a = this.inOrder(new LinkedList<>()), b = o.inOrder(new LinkedList<>()), out = new LinkedList<>();
        while (!(a.isEmpty() || b.isEmpty())) {
            if (a.peek().compareTo(b.peek()) <= 0) {
                out.add(a.remove());
            } else {
                out.add(b.remove());
            }
        }
        out.addAll(a);
        out.addAll(b);
        return constructFromSorted(out);
    }

    public static <E extends Comparable<? super E>> RBTree<E> constructFromSorted(List<E> l) {
        if (l.size() <= 2) {
            return new RBTree<>(l);
        }
        // constructs a full binary tree with all black nodes
        // then continues to complete with all red nodes

        // levels are descending, the lowest full level is 1
        // for level == 0 when we do the second step
        // this number is passed
        int log2_s1 = 30 - Integer.numberOfLeadingZeros(l.size() + 1);

        RBTree<E> tree = new RBTree<>();
        int middle = l.size() / 2;
        tree.root = new Node<>(
                l.get(middle), true, null,
                constructFromSorted(l.subList(0, middle), log2_s1),
                constructFromSorted(l.subList(++middle, l.size()), log2_s1),
                null);
        return tree;
    }

    private static <E extends Comparable<? super E>> Node<E> constructFromSorted(List<E> l, int level) {
        // levels are descending, the lowest full level is 1
        if (l.size() < 2) {
            if (l.isEmpty()) return null;
            return new Node<>(l.get(0), level != 0);
        }
        int middle = l.size() / 2;
        return new Node<E>(
                l.get(middle), true, null,
                constructFromSorted(l.subList(0, middle), --level),
                constructFromSorted(l.subList(++middle, l.size()), level),
                null);
        // parent and left_child will get set when it is passed as left/right to constructor
    }

    public ArrayList<Node<T>> levelOrder() {
        ArrayList<Node<T>> result = new ArrayList<>();
        Queue<Node<T>> level = new LinkedList<>();
        level.add(root);
        result.add(root);
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
        StringBuilder result = new StringBuilder(String.format("%-3s b \n", root.getItem()));
        Queue<Node<T>> level = new LinkedList<>();
        level.add(root);
        char c;
        while (level.size() > 0) {
            int level_size = level.size();
            for (int i = 0; i < level_size; i++) {
                Node<T> node = level.remove();
                if (node.neighbours[0] != null) {
                    level.add(node.neighbours[0]);
                    if (node.neighbours[0].black) c = 'b';
                    else c = 'r';
                    result.append(String.format("%-3s %s ", node.neighbours[0].getItem(), c));
                } else {
                    result.append("      ");
                }
                if (node.neighbours[1] != null) {
                    level.add(node.neighbours[1]);
                    if (node.neighbours[1].black) c = 'b';
                    else c = 'r';
                    result.append(String.format("%-3s %s ", node.neighbours[1].getItem(), c));
                } else {
                    result.append("      ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}