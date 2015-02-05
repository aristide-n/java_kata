package com.aristideniyungeko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Script creates a HashMap backed graph and finds min-cuts by repeating the Karger algorithm.
 */
public class HashMapMinCutFinder {

   /**
    * see kargerMinCut.txt
    * @param textFilePath kargerMinCut.txt
    * @return
    * @throws IOException
    */
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

   /**
    * edge to contract is sourceVertex(S) <----> targetVertex (T)
    * Algorithm:
    * 1. append list of T's targets to list of S's targets
    * 2. lookup list of targets for each T target and replace all T entries with S
    * 3. remove S entries fro the list of S's targets (self loops)
    * 4. remove T entry for the graph
    *
    * @param sourceVertex
    * @param targetVertex
    * @param graph
    */
   private void contract(Integer sourceVertex,
                         Integer targetVertex,
                         Map<Integer, List<Integer>> graph) {

      graph.get(sourceVertex).addAll(graph.get(targetVertex));

      List<Integer> targetsOfTarget = graph.get(targetVertex);

      for (int i = 0; i < targetsOfTarget.size(); i++) {
         graph.get(targetsOfTarget.get(i)).replaceAll(
            vertex -> (vertex.equals(targetVertex)) ? sourceVertex : vertex
         );
      }

      graph.get(sourceVertex).removeIf(vertex -> (vertex.equals(sourceVertex)));

      graph.remove(targetVertex);
   }

   /**
    * Contract the graph down to 2 vertices by contracting random edges iteratively
    * @param graph
    * @return
    */
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

   public Map<Integer, List<Integer>> cloneGraph(Map<Integer, List<Integer>> graph) {
      Map<Integer, List<Integer>> clone = new HashMap<>();
      for (final Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
         clone.put(entry.getKey(), new ArrayList<>(entry.getValue()));
      }
      return clone;
   }

   public static void main (String[] args) throws IOException {
      HashMapMinCutFinder worker = new HashMapMinCutFinder();
      Map<Integer, List<Integer>> graph = worker.createGraph(
         // TODO use generic path
         "/Users/aristide/WORKSPACE/java_kata/karger-mincut-1/src/com/aristideniyungeko/kargerMinCut.txt"
      );
      int minCut = Integer.MAX_VALUE;
      int minCutTrial = 0;

      for (int i = 0; i < 100; i++) {
         minCutTrial = worker.findMinCut(worker.cloneGraph(graph));
         if (minCutTrial < minCut)
            minCut = minCutTrial;
      }

      System.out.print(minCut);
   }
}
