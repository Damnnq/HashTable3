package datastructures;

@SuppressWarnings("unchecked")
public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + " : " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;      // число «бакетов»
    private int size = 0;    // число элементов

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        int h = key.hashCode() & 0x7fffffff;
        return h % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        // поиск
        for (HashNode<K, V> curr = head; curr != null; curr = curr.next) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (HashNode<K, V> curr = chainArray[index]; curr != null; curr = curr.next) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> curr = chainArray[index];
        HashNode<K, V> prev = null;
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev != null) prev.next = curr.next;
                else chainArray[index] = curr.next;
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> curr = chainArray[i]; curr != null; curr = curr.next) {
                if (curr.value.equals(value)) return true;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> curr = chainArray[i]; curr != null; curr = curr.next) {
                if (curr.value.equals(value)) return curr.key;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int getBucketCount() {
        return M;
    }
    public int getBucketSize(int index) {
        int count = 0;
        for (HashNode<K, V> curr = chainArray[index]; curr != null; curr = curr.next) {
            count++;
        }
        return count;
    }
}
