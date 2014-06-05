package com.aristideniyungeko;

import org.apache.commons.lang3.ArrayUtils;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Script creates a graph data structure and finds min-cuts by repeating the Karger algorithm.
 */
public class FindMinCuts {
   public Graph createGraph() throws IOException {
      UndirectedGraph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

      BufferedReader br = new BufferedReader(new FileReader("/Users/aristide/Desktop/kargerMinCut.txt"));
      String line;
      while((line = br.readLine()) != null) {
         String[] list = line.split("\\s+");
         Integer leftNode = Integer.parseInt(list[0]);
         graph.addVertex(leftNode);
         for(int i = 1; i < list.length; i++) {
            Integer rightNode = Integer.parseInt(list[i]);
            graph.addVertex(rightNode);
            graph.addEdge(leftNode, rightNode);
         }
// TODO: remove
//        System.out.println(ArrayUtils.toString(graph.edgeSet().toArray()));
      }

      if (br != null) {
         br.close();
      }

      return graph;
   }

   public void findMinCut(Graph graph) {
      /*
       * TODO:
       * + Get random edge E in edge set
       * + get source Vs and target Vt
       * + remove E
       * + Find set S of edges where source equal to Vt
       * + For each edge in S get target Vt2
       * + + create eadge E2 with source Vs and  target Vt2 and add it to graph
       * + remove Vt from graph
       * + repeat from the top until length of vertex set is 2
       */

   }

   public static void main (String[] args) throws IOException {
      FindMinCuts worker = new FindMinCuts();
      Graph graph = worker.createGraph();
      worker.findMinCut(graph);
   }
}
