package com.aristideniyungeko.data_structures;

public class Stack {
   private Node top;

   public void push(Object data) {
      Node n = new Node(data);
      n.next = top;
      top = n;
   }

   public Object pop() {
      if (top != null) {
         Object result = top.data;
         top = top.next;
         return result;
      }
      return null;
   }

   public Object peek() {
      if (top != null) {
         return top.data;
      }
      return null;
   }

   public boolean isEmpty() {
      return peek() == null;
   }

}
