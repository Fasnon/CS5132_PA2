package Model;

public class Pain {
    public static void main(String [] args){
        RBTree<Integer> test= new RBTree<Integer>();
        test.insert(2);
        test.insert(3);
        System.out.println(test.blackHeight());


    }
}
