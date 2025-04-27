package datastructures;

import java.util.Random;

public class TestHashTable {
    static class MyTestingClass {
        private int id;
        public MyTestingClass(int id) {
            this.id = id;
        }
        @Override
        public int hashCode() {
            return id;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyTestingClass)) return false;
            return this.id == ((MyTestingClass) o).id;
        }
        @Override
        public String toString() {
            return "Key(" + id + ")";
        }
    }

    static class Student {
        private String name;
        public Student(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Student(" + name + ")";
        }
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random rand = new Random();

        // 10000 элементов
        for (int i = 0; i < 10_000; i++) {
            int id = rand.nextInt(2000);     // чтобы были коллизии
            MyTestingClass key = new MyTestingClass(id);
            Student val = new Student("S" + i);
            table.put(key, val);
        }

        System.out.println("Total size: " + table.size());
        // печатаем
        for (int i = 0; i < table.getBucketCount(); i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i));
        }
    }
}
