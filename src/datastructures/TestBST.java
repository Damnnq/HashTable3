package datastructures;

public class TestBST {
    public static void main(String[] args) {
        BST<String, Integer> tree = new BST<>();
        tree.put("delta",   4);
        tree.put("alpha",   1);
        tree.put("beta", 3);
        tree.put("bravo",   2);
        tree.put("echo",    5);

        System.out.println("Size: " + tree.size());
        System.out.println("Get 'beta': " + tree.get("beta"));
        System.out.println("Get 'foxtrot': " + tree.get("foxtrot"));  // null

        System.out.println("\nIn order traversal:");
        for (var entry : tree) {
            System.out.println("key is " + entry.getKey() + " and value is " + entry.getValue());
        }

        tree.delete("beta");
        System.out.println("\nAfter deleting 'beta', size: " + tree.size());
        System.out.println("Traversal again:");
        for (var entry : tree) {
            System.out.println("key is " + entry.getKey() + " and value is " + entry.getValue());
        }
    }
}
