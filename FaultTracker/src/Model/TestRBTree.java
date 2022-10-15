package Model;

public class TestRBTree {
    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>(new Integer[]{1, 2, 7, 5, 8, 4, 6});
        System.out.println(tree.inOrder());
        System.out.println(tree);
        tree.insert(3);
        System.out.println(tree.inOrder());
        System.out.println(tree);

        tree = new RBTree<>(new Integer[] {4, 2, -3, 7, -2, -4, 5, 3, 9, 0, -6, -1});
        System.out.println("initial");
        System.out.println(tree.inOrder());
        System.out.println(tree);

         tree.delete(-2);

        System.out.println("aft del");
        System.out.println(tree.inOrder());
        System.out.println(tree);

        RBTree<Integer> other = new RBTree<>(new Integer[] {8, -8, -7, -9, 6, -10, 10, -5, 1});
        System.out.println("other");
        System.out.println(other.inOrder());
        System.out.println(other);

        RBTree<Integer> merged = tree.merge(other);
        System.out.println("aft merge");
        System.out.println(merged.inOrder());
        System.out.println(merged);
    }
}
