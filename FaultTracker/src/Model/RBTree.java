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
}