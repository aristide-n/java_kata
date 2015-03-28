package com.aristideniyungeko.data_structures;

import java.util.Iterator;

public class Queue<T> implements Iterable {
   private Node first, last;

   public void enqueue(T data) {
      if (first == null) {
         first = new Node(data);
         last = first;
      } else {
         last.next = new Node(data);
         last = last.next;
      }
   }

   public T dequeue() {
      if (first != null) {
         Object result = first.data;
         first = first.next;
         if (first == null)
            last = null; // empty list
         return (T) result;
      }
      return null;
   }

   public boolean isEmpty() {
      return first == null;
   }

   @Override
   public Iterator<T> iterator() {
      return new QueueIterator();
   }

   private class QueueIterator implements Iterator<T> {
      private Node current;

      public QueueIterator() {
         current = first;
      }

      public boolean hasNext() {
         return current != null;
      }

      public T next() {
         T data = null;

         if (hasNext()) {
            data = (T) current.data;
            current = current.next;
         }

         return data;
      }
   }
}
