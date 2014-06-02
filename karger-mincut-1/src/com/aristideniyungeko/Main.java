package com.aristideniyungeko;

import org.apache.commons.lang3.ArrayUtils;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * TODO: documentation.
 */
public class Main {
   public void doStuff() throws IOException {
      UndirectedGraph<Integer, Pair> graph = new SimpleGraph<>(Pair.class);

      BufferedReader br = new BufferedReader(new FileReader("/Users/aristide/Desktop/kargerMinCut.txt"));
      String line;
      while((line = br.readLine()) != null) {
         String[] list = line.split("\\s+");
         System.out.println(ArrayUtils.toString(list));
         //TODO: add edges and vertices to the graph
      }
      if (br != null) {
         br.close();
      }
   }

   public static void main (String[] args) throws IOException {
      Main worker = new Main();
      worker.doStuff();
   }
}
