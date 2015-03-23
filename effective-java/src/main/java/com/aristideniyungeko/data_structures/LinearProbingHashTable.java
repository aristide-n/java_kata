package com.aristideniyungeko.data_structures;

public class LinearProbingHashTable<Key, Value> {
   private static final int INIT_CAPACITY = 4;
   private static final double LOAD_FACTOR = 3.0 / 4.0;
   private int N; // number of mappings
   private int M; // capacity

   private Key[] keys;
   private Value[] values;

   public LinearProbingHashTable() {
      this(INIT_CAPACITY);
   }

   public LinearProbingHashTable(int capacity) {
      M = capacity;
      keys = (Key[]) new Object[M];
      values = (Value[]) new Object[M];
   }

   public int size() {
      return N;
   }

   public boolean isEmpty() {
      return size() == 0;
   }

   public boolean contains(Key key) {
      return get(key) != null;
   }

   private int hash(Key key) {
      return (key.hashCode() % 0x7fffffff) % M;
   }

   private void resize(int capacity) {
      LinearProbingHashTable<Key, Value> temp = new LinearProbingHashTable(capacity);

      for (int i = 0; i < M; i++) {
         if (keys[i] != null) {
            temp.put(keys[i], values[i]);
         }
      }

      keys = temp.keys;
      values = temp.values;
      M = temp.M;
   }

   public void put(Key key, Value value) {
      if (N >= M * LOAD_FACTOR) {
         resize(M * 2);
      }

      int i;
      for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
         if (keys[i].equals(key)) {
            values[i] = value;
            return;
         }
      }

      keys[i] = key;
      values[i] = value;
      N++;
   }

   public Value get(Key key) {
      for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
         if (keys[i].equals(key)) {
            return values[i];
         }
      }

      return null;
   }

   public void delete(Key key) {
      if (!contains(key)) {
         return;
      }

      int i = hash(key);

      while (!keys[i].equals(key)) {
         i = (i + 1) % M;
      }

      keys[i] = null;
      values[i] = null;

      // rehash cluster
      i = (i + 1) % M;
      while (keys[i] != null) {
         Key keyToRehash = keys[i];
         Value valueToRehash = values[i];
         keys[i] = null;
         values[i] = null;
         N--;
         put(keyToRehash, valueToRehash);
         i = (i + 1) % M;
      }

      N--;

      // size down by 1/2 if the table size is 1/8 full or less
      if (N > 0 && N <= M / 8) {
         resize(M / 2);
      }
   }


   public Iterable<Key> keys() {
      Queue<Key> queue = new Queue<>();
      for (int i = 0; i < M; i++)
         if (keys[i] != null) queue.enqueue(keys[i]);

      return queue;
   }

   public static void main(String[] args) {
      LinearProbingHashTable<String, Integer> hashTable = new LinearProbingHashTable<>();
      for (int i = 0; i < 10; i++) {
         String key = "foo" + i;
         hashTable.put(key, i);
      }

      for (int i = 5; i < 10; i++) {
         String key = "bar" + i;
         hashTable.put(key, i);
      }

      for (String s : hashTable.keys()) {
         System.out.println(s + " " + hashTable.get(s));
      }

      if (!hashTable.isEmpty()) {
         for (int i = 0; i < 10; i++) {
            String key = "foo" + i;
            hashTable.delete(key);
         }

         for (int i = 5; i < 10; i++) {
            String key = "bar" + i;
            hashTable.delete(key);
         }
      }
   }
}
