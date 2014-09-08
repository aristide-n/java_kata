package com.aristideniyungeko;

import org.apache.commons.lang3.StringUtils;
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
public class JGraphTMincutFinder {

   /**
    * see kargerMinCut.txt
    * @param textFilePath kargerMinCut.txt
    */
   public Graph<Integer, DefaultEdge> createGraph(String textFilePath) throws IOException {
      // Multigraph allows multiple edges between vertices but prohibits self-loops
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

            // at initialization prevent duplicate edges that double the size of the graph
            if (!graph.containsEdge(rightNode, leftNode))
               graph.addEdge(leftNode, rightNode);
         }
      }

      br.close();

      return graph;
   }

   /**
    * These graphs are not natively deep-cloneable :( do it manually
    * @param graph
    * @return
    */
   public Graph<Integer, DefaultEdge> cloneGraph(Graph<Integer, DefaultEdge> graph) {
      Graph<Integer, DefaultEdge> graphClone = new Multigraph<>(DefaultEdge.class);
      Set<Integer> vertexSet = graph.vertexSet();
      Set<DefaultEdge> edgeSet = graph.edgeSet();

      vertexSet.stream().forEach(graphClone::addVertex);
      edgeSet.stream().forEach(edge -> graphClone.addEdge(graph.getEdgeSource(edge),
                                                          graph.getEdgeTarget(edge)));

      return graphClone;
   }

   /**
    * Algorithm:
    *
    * 1 Find set S of edges adjacent to targetV
    * 2 For each edge in S except edges adjacent to Vs
    * ++ add a new edge where sourceV is the target when targetV is the target and
    *    sourceV is not the source
    * ++ add a new edge where sourceV is the source when targetV is the source and
    *    sourceV is not the source
    * + remove targetV and all edges adjacent to it from graph
    *
    * @param edgeToContract
    * @param graph
    */
   private void contract (DefaultEdge edgeToContract, Graph<Integer, DefaultEdge> graph) {
      Integer sourceV = graph.getEdgeSource(edgeToContract);
      Integer targetV = graph.getEdgeTarget(edgeToContract);

      Set<DefaultEdge> edgesOfTargetV = graph.edgesOf(targetV);
      for(final DefaultEdge edge : edgesOfTargetV) {
         if(targetV.equals(graph.getEdgeTarget(edge))) {
            if (!sourceV.equals(graph.getEdgeSource(edge))) {
               graph.addEdge(graph.getEdgeSource(edge), sourceV);
            }
         } else if (!sourceV.equals(graph.getEdgeTarget(edge))) {
            graph.addEdge(sourceV, graph.getEdgeTarget(edge));

         }
      }
      graph.removeVertex(targetV);
   }

   /**
    * Contract the graph down to 2 vertices by contracting random edges iteratively
    *
    * @param graph
    * @return
    */
   public int findMinCut(Graph<Integer, DefaultEdge> graph) {
      Set<DefaultEdge> edgeSet = graph.edgeSet();
      Set<Integer> vertexSet = graph.vertexSet();
      DefaultEdge edgeToContract;
      Random randomizer = new Random();

      while (vertexSet.size() > 2) {
         edgeToContract = (DefaultEdge) edgeSet.toArray()[randomizer.nextInt(edgeSet.size())];
         this.contract(edgeToContract, graph);
      }

      return graph.edgeSet().size();
   }

   public static void main (String[] args) throws IOException {
      JGraphTMincutFinder worker = new JGraphTMincutFinder();
      Graph<Integer, DefaultEdge> graph = worker.createGraph(
         // TODO use generic path
         "/Users/aristide/WORKSPACE/java_kata/karger-mincut-1/src/com/aristideniyungeko/kargerMinCut.txt"
      );
      String foo = StringUtils.join(graph.edgeSet(), " , ");
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
