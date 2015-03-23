package com.aristideniyungeko.data_structures;

import java.util.Iterator;

/**
 * Binary search tree with unique nodes.
 */
public class BST implements Iterable {
   private Node root;

   public void insert(Integer data) {
      root = insert(root, data);
   }

   public boolean search(Integer data) {
      return search(root, data);
   }

   public void delete(Integer data) {
      root = delete(root, data);
   }

   public void preOrderTraversal() {
      preOrderHelper(root);
   }

   public void inOrderTraversal() {
      inOrderHelper(root);
   }

   public void postOrderTraversal() {
      postOrderHelper(root);
   }

   public int height() {
      return height(root);
   }

   public int width() {
      int max = 0;

      for (int k = 0; k <= height(); k++) {
         int temp = width(root, k);
         if (temp > max) {
            max = temp;
         }
      }

      return max;
   }

   private int width(Node n, int depth) {
      if (n == null) {
         return 0;
      } else if (depth == 0) {
         return 1;
      } else {
         return width(n.left, depth - 1) + width(n.right, depth - 1);
      }
   }

   public Iterator iterator() {
      return new TreeIterator();
   }

   private Node insert(Node n, Integer newData) {
      if (n == null) {
         return new Node(newData);
      }

      if (compare(n.data, newData) == 0) {
         return n;
      }

      if (compare(n.data, newData) < 0) {
         n.left = insert(n.left, newData);
      } else {
         n.right = insert(n.right, newData);
      }

      return n;
   }

   private boolean search(Node n, Integer findData) {
      if (n == null) {
         return false;
      } else if (compare(n.data, findData) ==0) {
         return true;
      } else if (compare(n.data, findData) < 0) {
         return search(n.left, findData);
      } else {
         return search(n.right, findData);
      }
   }

   private Node delete(Node n, Integer deleteData) {
      if (n == null) {
         throw new RuntimeException("not found");
      } else if (compare(n.data, deleteData) < 0) {
         n.left = delete(n.left, deleteData);
      } else if (compare(n.data, deleteData) > 0) {
         n.right = delete(n.right, deleteData);
      } else {
         if (n.left == null) { // replace the node by its only child
            return n.right;
         } else if (n.right == null){ // replace the node by its only child
            return n.left;
         } else {  // node has 2 children
            n.data = retrieveData(n.left); // replace by rightmost descendant of left child
            n.left = delete(n.left, n.data); // delete rightmost descendant of left child
         }
      }

      return n;
   }

   private int compare(Integer x, Integer y) {
      return y-x;
   }

   private Integer retrieveData(Node n) {
      while (n.right != null) {
         n = n.right;
      }

      return n.data;
   }

   private void preOrderHelper(Node n) {
      if (n != null) {
         System.out.print(n + " ");
         preOrderHelper(n.left);
         preOrderHelper(n.right);
      }
   }

   private void inOrderHelper(Node n) {
      if (n != null) {
         inOrderHelper(n.left);
         System.out.print(n + " ");
         inOrderHelper(n.right);
      }

   }

   private void postOrderHelper(Node n) {
      if (n != null) {
         postOrderHelper(n.left);
         postOrderHelper(n.right);
         System.out.print(n + " ");
      }
   }

   private int height(Node n) {
      if (n == null) {
         return -1;
      } else {
         return 1 + Math.max(height(n.left), height(n.right));
      }
   }

   public String toString() {
      final StringBuffer sb = new StringBuffer();

      for (Object n : this) {
         sb.append(n).append(" ");
      }

      return sb.toString();
   }

   private class Node {
      private Integer data;
      private Node left, right;

      public Node(Integer data) {
         this.data = data;
      }

      @Override
      public String toString() {
         return data.toString();
      }
   }

   // pre-order Iterator
   private class TreeIterator implements Iterator {
      Stack stack = new Stack();

      public TreeIterator() {
         if (root != null) {
            stack.push(root);
         }
      }

      @Override
      public boolean hasNext() {
         return !stack.isEmpty();
      }

      @Override
      public Integer next() {
         Node current = (Node)stack.peek();

         if (current.left != null) {
            stack.push(current.left);
         } else {
            Node temp = (Node)stack.pop();
            while (temp.right == null) {
               if (stack.isEmpty()) {
                  return current.data;
               }
               temp = (Node) stack.pop();
            }
            stack.push(temp.right);
         }
         return current.data;
      }
   }

   public static void main(String[] args) {
      "In an attempt to provide a fast implementation, early versions of the Java String class provided a hashCode".hashCode();
      Integer[] arr = {1,5,2,7,4,0,6};
      BST bst = new BST();

      for (Integer n : arr) {
         bst.insert(n);
      }

      System.out.println("- pre-order traversal");
      bst.preOrderTraversal();
      System.out.println();

      System.out.println("- test delete and in-order traversal");
      bst.delete(2);
      bst.inOrderTraversal();
      System.out.println();

      System.out.println("- test post-order traversal");
      bst.postOrderTraversal();
      System.out.println();

      System.out.println("- test search");
      System.out.println("search 2: " + bst.search(2));
      System.out.println("search 1: " + bst.search(1));
      System.out.println();

      System.out.println("- test iterator");
      for (Object n : bst) {
         System.out.print(n + " ");
      }
      System.out.println();
      System.out.println(bst);
      System.out.println();

      System.out.println("- test height and width");
      System.out.println("height: " + bst.height());
      System.out.println("width: " + bst.width());
   }
}
