package com.aristideniyungeko.data_structures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Graph {
   private int V; // number of vertices
   private int E; // number of edges
   private boolean matrix[][];
   private boolean visited[]; // sentinels for visited status during search
   private Object[] values; // vertex values

   // Create graph without edges
   public Graph(Object[] values) {
      if (V > 0) {
         throw new RuntimeException("invalid");
      }

      this.V = values.length;
      this.E = 0;
      this.values = values;
      this.matrix = new boolean[V][V];
   }

   // add random edges up to e count, and values
   public Graph(Object[] values, int e) {
      this(values);

      if (e < 0 || e > V*V) {
         throw new RuntimeException("invalid");
      }

      for(int i = 0; i <= e; i++) {
         int v = (int) (V * Math.random());
         int w = (int) (V * Math.random());
         addEdge(v, w);
      }
   }

   public int getV() {
      return V;
   }

   public int getE() {
      return E;
   }

   public void addEdge(int v, int w) {
      if (!matrix[v][w]) {
         E++;
      }

      matrix[v][w] = true;
      matrix[w][v] = true;
   }

   public boolean contains(int v, int w) {
      return matrix[v][w];
   }

   public Iterable<Integer> neighbors(int v) {
      return new MatrixIterator(v);
   }

   private class MatrixIterator implements Iterator<Integer>, Iterable<Integer> {
      private int v, w = 0;

      MatrixIterator(int v) {
         this.v = v;
      }

      public Iterator<Integer> iterator() {
         return this;
      }

      public boolean hasNext() {
         while (w < V) {
            if (matrix[v][w]) {
               return true;
            }

            w++;
         }
         return false;
      }

      public Integer next() {
         if (hasNext()) {
            return w++;
         }

         throw new NoSuchElementException();
      }
   }

   // takes quadratic time
   public String toString() {
      final String NEW_LINE = System.getProperty("line.separator");
      final StringBuilder sb = new StringBuilder();

      sb.append(V + " " + E + NEW_LINE);

      for (int v = 0; v < V; v++) {
         sb.append(v + ", " + values[v] + " : ");
         for (int w : neighbors(v)) {
            sb.append(w + " ");
         }

         sb.append(NEW_LINE);
      }

      return sb.toString();
   }

   public Integer depthFirstSearch(int v, Object val) {
      visited = new boolean[V];
      return depthFirstSearchInternal(v, val);
   }

   private Integer depthFirstSearchInternal(int v, Object val) {
      visited[v] = true;

      if (values[v].equals(val)) {
         return v;
      } else {
         Integer res = null;
         Iterator<Integer> neighbors = neighbors(v).iterator();

         while(res == null && neighbors.hasNext()) {
            int w = neighbors.next();
            if (!visited[w]) {
               res = depthFirstSearchInternal(w, val);
            }
         }

         return res;
      }
   }

   public Integer breadthFirstSearch(int v, Object val) {
      if (values[v].equals(val)) {
         return v;
      } else {
         visited = new boolean[V];
         visited[v] = true;
         Integer res = null;
         Queue<Integer> queue = new Queue<>();
         queue.enqueue(v);

         while (res == null && !queue.isEmpty()) {
            for (int w : neighbors(queue.dequeue())) {
               if (values[w].equals(val)) {
                  res = w;
                  break;
               } else if (!visited[w]) {
                  visited[w] = true;
                  queue.enqueue(w);
               }
            }
         }

         return res;
      }
   }

   public static void main(String[] args) {
      Graph G = new Graph(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9 ,0, 11}, 100);
      System.out.println(G);
      System.out.println(G.depthFirstSearch(5,9));
      System.out.println(G.depthFirstSearch(2,12));
      System.out.println(G.breadthFirstSearch(5,9));
      System.out.println(G.breadthFirstSearch(2,12));
   }
}
