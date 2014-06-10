package com.aristideniyungeko;

import org.apache.commons.exec.util.MapUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.UnaryOperator;

/**
 * Script creates a HashMap backed graph and finds min-cuts by repeating the Karger algorithm.
 */
public class HashMapMinCutFinder {

   public Map<Integer, List<Integer>> createGraph(String textFilePath) throws IOException {
      Map<Integer, List<Integer>> graph = new HashMap<>();

      BufferedReader br = new BufferedReader(new FileReader(textFilePath));
      String line;

      while((line = br.readLine()) != null) {
         String[] vertices = line.split("\\s+");
         Integer source = Integer.parseInt(vertices[0]);
         List<Integer> targets = new ArrayList<>();

         for (int i = 1; i < vertices.length; i++) {
            targets.add(Integer.parseInt(vertices[i]));
         }

         graph.put(source, targets);
      }

      br.close();

      return graph;
   }

   private void contract(Integer sourceVertex, Integer targetVertex, Map<Integer, List<Integer>> graph) {

      /*
      """Contracts two vertices from a randomly chosen edge in graph G into one single vertex
       vert1 = the first vertex
       vert2 = the second vertex, which will be shrinked into vert1
       G = the input graph, represented by a dictionary"""

       G[vert1].extend(G[vert2]) # append vert2's list to vert1's list
       for adj_vert in G[vert2]: # for every adjacent node of vert2
           lst = G[adj_vert]
           for i in range(0, len(lst)): # scan its list and replace vert2 with vert1
               if lst[i] == vert2:
                   lst[i] = vert1

       while vert1 in G[vert1]: # remove self-loop in vert1's list
           G[vert1].remove(vert1)
       del G[vert2] # remove vert2 and its list from the graph

       */

      /*
      I can add and rm from graph here,
      I can mutate value from graph here, i.e add an element in the list
       */

   }

   public int findMinCut(Map<Integer, List<Integer>> graph) {

      Random randomizer = new Random();

      while(graph.size() > 2) {
         Integer sourceV = (Integer) graph.keySet().toArray()[randomizer.nextInt(graph.size())];
         List<Integer> targets = graph.get(sourceV);
         Integer targetV = targets.get(randomizer.nextInt(targets.size()));
         contract(sourceV, targetV, graph);
      }

      return graph.get(graph.keySet().toArray()[0]).size();
   }

   public static void main (String[] args) throws IOException {
      HashMapMinCutFinder worker = new HashMapMinCutFinder();
      Map<Integer, List<Integer>> graph = worker.createGraph("/Users/aristide/WORKSPACE/java_kata/karger-mincut-1/src/com/aristideniyungeko/kargerMinCut.txt");
      int minCut = Integer.MAX_VALUE;
      int minCutTrial = 0;

//      for (int i = 0; i < 300; i++) {
         minCutTrial = worker.findMinCut(MapUtils.copy(graph));
         if (minCutTrial < minCut)
            minCut = minCutTrial;
//      }

//      System.out.println(graph.keySet().toString());

      System.out.print(minCut);
   }
}
