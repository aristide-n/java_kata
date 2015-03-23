package com.aristideniyungeko.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable {
   private Node head;

   public void add(Object data) {
      Node n = new Node(data);
      n.next = head;
      head = n;
   }

   public Node delete(Object data) {
      if (head == null) {
         return null;
      }

      Node n = head;
      Node result;
      if (head.data == data) {
         result = head;
         head = head.next;
         return result;
      }

      while (n.next != null) {
         if (n.next.data == data) {
            result = n.next;
            n.next = n.next.next;
            return result;
         }
      }

      return null;
   }

   @Override
   public ListIterator iterator() {
      return new ListIterator();
   }

   private class ListIterator implements Iterator<Object> {
      private Node nextNode;

      public ListIterator() {
         nextNode = head;
      }

      @Override
      public boolean hasNext() {
         return nextNode != null;
      }

      @Override
      public Object next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }

         Object result = nextNode.data;
         nextNode = nextNode.next;
         return result;
      }
   }
}
