package datastructures;

import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key   = key;
            this.value = value;
        }
        public K getKey()   { return key; }
        public V getValue() { return value; }
    }

    private class Node {
        K key;
        V value;
        Node left, right;
        Node(K key, V value) {
            this.key   = key;
            this.value = value;
        }
    }

    private Node root;
    private int size = 0;

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }
        Node curr = root;
        while (true) {
            int cmp = key.compareTo(curr.key);
            if (cmp == 0) {
                curr.value = value;
                return;
            } else if (cmp < 0) {
                if (curr.left == null) {
                    curr.left = new Node(key, value);
                    size++;
                    return;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Node(key, value);
                    size++;
                    return;
                }
                curr = curr.right;
            }
        }
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp == 0) return curr.value;
            curr = (cmp < 0) ? curr.left : curr.right;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node curr = root;
        // ищем узел
        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            curr = (key.compareTo(curr.key) < 0) ? curr.left : curr.right;
        }
        if (curr == null) return;  // не найден

        // случай 1 и 2: не более одного ребёнка
        if (curr.left == null || curr.right == null) {
            Node child = (curr.left != null) ? curr.left : curr.right;
            if (parent == null) root = child;
            else if (parent.left == curr) parent.left = child;
            else parent.right = child;
        } else {
            // случай 3: два ребёнка → ищем минимальный в правом поддереве
            Node succParent = curr;
            Node succ = curr.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }
            // копируем данные
            curr.key   = succ.key;
            curr.value = succ.value;
            // удаляем «преемника»
            if (succParent.left == succ) succParent.left = succ.right;
            else succParent.right = succ.right;
        }
        size--;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            Node curr = root;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            Node node = stack.pop();
            // «спускаемся» в его правое поддерево
            Node curr = node.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            return new Entry<>(node.key, node.value);
        }
    }
}
