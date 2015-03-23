package com.aristideniyungeko.data_structures;

// from http://algs4.cs.princeton.edu/52trie/
public class Trie<Value> {
   private static final int R = 256; // Radix is the extended ASCII range
   private Node root;
   private int N; // the number of keys

   private static class Node {
      private Object value;
      private Node[] next = new Node[R];
   }

   public Value get(String key) {
      Node x = get(root, key, 0);

      if (x == null) {
         return null;
      }

      return (Value) x.value;
   }

   // d = depth
   private Node get(Node x, String key, int d) {
      if (x == null) {
         return null;
      }
      if (d == key.length()) {
         return x;
      }

      char c = key.charAt(d);
      return get(x.next[c], key, d+1);
   }

   public boolean contains(String key) {
      return get(key) != null;
   }

   public void put(String key, Value value) {
      // Deletes the key if the value is null the null key is not supported, unlike HashMap
      if (key == null) {
         return;
      }

      if (value == null) {
         delete(key);
      } else {
         root = put(root, key, value, 0);
      }
   }

   private Node put(Node x, String key, Value value, int d) {
      if (x == null) {
         x = new Node();
      }

      if (d == key.length()) {
         if (x == null) {
            N++;
         }

         x.value = value;
         return x;
      }

      char c = key.charAt(d);
      x.next[c] = put(x.next[c], key, value, d+1);
      return x;
   }

   public int size() {
      return N;
   }

   public boolean isEmpty() {
      return size() == 0;
   }

   public Iterable<String> keys() {
      return keysWithPrefix("");
   }

   public Iterable<String> keysWithPrefix(String prefix) {
      Queue<String> results = new Queue<>();
      Node x = get(root, prefix, 0);
      collect(x, new StringBuilder(prefix), results);
      return results;
   }

   private void collect(Node x, StringBuilder prefix, Queue<String> results) {
      if (x == null) {
         return;
      }

      if (x.value != null) {
         results.enqueue(prefix.toString());
      }

      for (char c = 0; c < R; c++) {
         prefix.append(c);
         collect(x.next[c], prefix, results);
         prefix.deleteCharAt(prefix.length() - 1);
      }
   }

   // Use . as a wildcard
   public Iterable<String> keysThatMatch(String pattern) {
      Queue<String> results = new Queue<>();
      collect(root, new StringBuilder(), pattern, results);
      return results;
   }

   private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
      if (x == null) {
         return;
      }

      int d = prefix.length();

      if (d == pattern.length()) {
         if (x.value != null) {
            results.enqueue(prefix.toString());
         }
         return;
      }

      char c = pattern.charAt(d);

      if (c == '.') {
         for (char ch = 0; ch < R; ch++) {
            prefix.append(ch);
            collect(x.next[ch], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() -1);
         }
      } else {
         prefix.append(c);
         collect(x.next[c], prefix,pattern, results);
         prefix.deleteCharAt(prefix.length() - 1);
      }
   }

   public String longestPrefixOf(String query) {
      int length = longestPrefixOf(root, query, 0, 0);
      return query.substring(0, length);
   }

   private int longestPrefixOf(Node x, String query, int d, int length) {
      if (x == null) {
         return length;
      }

      if (x.value != null) {
         length = d;
      }

      if (d == query.length()) {
         return d; // same as return d
      }

      char c = query.charAt(d);

      return longestPrefixOf(x.next[c], query, d+1, length);
   }

   public void delete(String key) {
      root = delete(root, key, 0);
      System.out.println("");
   }

   private Node delete(Node x, String key, int d) {
      if (x == null) {
         return null;
      }

      if (d == key.length()) {
         if (x.value != null) {
            N--;
         }
         x.value = null;
      } else {
         char c = key.charAt(d);
         x.next[c] = delete(x.next[c], key, d+1);
      }

      // for the last char Node in the key, null it if sub-trie is empty,
      // i.e all items in its next array are null
      if (x.value != null) {
         return x;
      }
      for (int c = 0; c < R; c++) {
         if (x.next[c] != null) {
            return x;
         }
      }

      return null;
   }

   public static void main(String[] args) {
      Trie<Integer> trie = new Trie<Integer>();

      String[] keys = {"she", "sells", "sea", "shells", "by", "the", "sea", "shore"};
      for (int i = 0; i < keys.length; i++) {
         trie.put(keys[i], i);
      }

      // null key is not allowed
      trie.put(null, 20);

      // print results
      if (trie.size() < 100) {
         System.out.println("Initial keys(\"\"):");
         for (String key : trie.keys()) {
            System.out.println(key + " " + trie.get(key));
         }
         System.out.println();
      }

      System.out.println("searching an unknown key returns null:");
      System.out.println("foo " + trie.get("foo"));
      System.out.println();

      // deleting an unknown key is harmless
      trie.delete("foo");

      System.out.println("longestPrefixOf(\"shellsort\"):");
      System.out.println(trie.longestPrefixOf("shellsort"));
      System.out.println();

      System.out.println("keysWithPrefix(\"shor\"):");
      for (String s : trie.keysWithPrefix("shor"))
         System.out.println(s);
      System.out.println();

      System.out.println("keysThatMatch(\".he.l.\"):");
      for (String s : trie.keysThatMatch(".he.l."))
         System.out.println(s);

      System.out.println();

      // test delete
      trie.delete("by");

      // print results
      if (trie.size() < 100) {
         System.out.println("Final keys(\"\"):");
         for (String key : trie.keys()) {
            System.out.println(key + " " + trie.get(key));
         }
         System.out.println();
      }
   }
}    