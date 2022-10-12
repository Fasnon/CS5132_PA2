package Model;

import java.util.Random;

public class TestRBTree {
    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>(new Integer[] {4, 2, -3, 7, -2, -4, 5, 3, -9, 0, -6});
        System.out.println(tree.inOrder());
        System.out.println(tree);

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            try {
                tree.insert(random.nextInt(-100000, 100000));
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                System.out.println(tree);
                throw e;
            }
        }
        System.out.println(tree);
    }
}
