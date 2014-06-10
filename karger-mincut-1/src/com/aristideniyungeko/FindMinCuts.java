package com.aristideniyungeko;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

/**
 * Script creates a jgrapht graph and finds min-cuts by repeating the Karger algorithm.
 */
public class FindMinCuts {

   public Graph<Integer, DefaultEdge> createGraph(String textFilePath) throws IOException {
      UndirectedGraph<Integer, DefaultEdge> graph = new Multigraph<>(DefaultEdge.class);

      BufferedReader br = new BufferedReader(new FileReader(textFilePath));
      String line;

      while((line = br.readLine()) != null) {
         String[] list = line.split("\\s+");
         Integer leftNode = Integer.parseInt(list[0]);
         graph.addVertex(leftNode);
         for(int i = 1; i < list.length; i++) {
            Integer rightNode = Integer.parseInt(list[i]);
            graph.addVertex(rightNode);
            if (!graph.containsEdge(rightNode, leftNode))
               graph.addEdge(leftNode, rightNode);
         }
      }

      br.close();

      return graph;
   }

   public Graph<Integer, DefaultEdge> cloneGraph(Graph<Integer, DefaultEdge> graph) {
      Graph<Integer, DefaultEdge> graphClone = new Multigraph<>(DefaultEdge.class);
      Set<Integer> vertexSet = graph.vertexSet();
      Set<DefaultEdge> edgeSet = graph.edgeSet();

      vertexSet.stream().forEach(graphClone::addVertex);
      edgeSet.stream().forEach(edge -> graphClone.addEdge(graph.getEdgeSource(edge),
                                                          graph.getEdgeTarget(edge)));

      return graphClone;
   }

   public int findMinCut(Graph<Integer, DefaultEdge> graph) {
      /*
       * Algorithm:
       * until length of vertex set is 2
       * + Get random edge E in edge set
       * + get source Vs and target Vt of E
       * + Find set S of edges adjacent to Vt
       * + For each edge in S except edges adjacent to Vs
       * + + add to the graph edge E2 where Vt is replaced with Vs
       * + remove Vt  and all edges adjacent to it from graph
       */
      Set<DefaultEdge> edgeSet = graph.edgeSet();
      Set<Integer> vertexSet = graph.vertexSet();
      DefaultEdge edgeToContract;
      Random randomizer = new Random();
      Integer sourceV;
      Integer targetV;

      for(int i = vertexSet.size(); i > 2; i--) {
         edgeToContract = (DefaultEdge) edgeSet.toArray()[randomizer.nextInt(edgeSet.size())];

         sourceV = graph.getEdgeSource(edgeToContract);
         targetV = graph.getEdgeTarget(edgeToContract);

         Set<DefaultEdge> edgesOfTargetV = graph.edgesOf(targetV);
         for(final DefaultEdge edge : edgesOfTargetV) {
            if(targetV.equals(graph.getEdgeTarget(edge))) {
               if (!sourceV.equals(graph.getEdgeSource(edge))) {
                  graph.addEdge(graph.getEdgeSource(edge), sourceV);
               }
            } else {
               if (!sourceV.equals(graph.getEdgeTarget(edge))) {
                  graph.addEdge(sourceV, graph.getEdgeTarget(edge));
               }
            }
         }
         graph.removeVertex(targetV);

      }
      return graph.edgeSet().size();
   }

   public static void main (String[] args) throws IOException {
      FindMinCuts worker = new FindMinCuts();
      Graph<Integer, DefaultEdge> graph = worker.createGraph("/provide/path/to/kargerMinCut.txt");
      double numTrials = Math.pow(graph.edgeSet().size(), 2.0) * Math.log(graph.edgeSet().size());
      int minCut = Integer.MAX_VALUE;
      int minCutTrial = 0;

      for (int i = 0; i < 300; i++) {
         minCutTrial = worker.findMinCut(worker.cloneGraph(graph));
         if (minCutTrial < minCut)
            minCut = minCutTrial;
      }

      System.out.print(minCut);
   }
}
