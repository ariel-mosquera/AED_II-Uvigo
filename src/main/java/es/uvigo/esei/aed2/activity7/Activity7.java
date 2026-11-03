package es.uvigo.esei.aed2.activity7;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;

public class Activity7 {

  // exercise 1
  public static <T, E> Set<Vertex<T>> getPredecessors(Graph<T, E> graph, Vertex<T> vertex) {
    Set<Vertex<T>> predSet = new HashSet<>();
    Set<Vertex<T>> vertices = graph.getVertices();

    for (Vertex<T> v : vertices) {
      if (graph.getAdjacentsVertex(v).contains(vertex)) {
        predSet.add(v);
      }
    }

    return predSet;
  }

  // exercise 2
  public static <T, E> boolean isRegular(Graph<T, E> graph) {
    if (graph.isEmpty()) {
      return true;
    }

    int numOfAdj = -1;

    for (Vertex<T> v : graph.getVertices()) {
      if (numOfAdj == -1) {
        numOfAdj = graph.getAdjacentsVertex(v).size();

      } else if (numOfAdj != graph.getAdjacentsVertex(v).size()) {
        return false;

      }
    }

    return true;
  }

  // exercise 3
  public static <T, E> boolean isConnectedFromVertex(Graph<T, E> graph, Vertex<T> vertex) {
    if (graph.isEmpty()) {
      return false;
    }
    
    Set<Vertex<T>> visited = new HashSet<>();

    depthFirstSearch(graph, vertex, visited);

    return visited.size() == graph.getVertices().size();
  }

  private static <T,E> void depthFirstSearch(Graph<T,E> graph, Vertex<T> vertex, Set<Vertex<T>> visited) {
    visited.add(vertex);
    for (Vertex<T> adjVertex : graph.getAdjacentsVertex(vertex)) {
      if (!visited.contains(adjVertex)) {
        depthFirstSearch(graph, adjVertex, visited);
      }
    }
  }

  // exercise 4
  public static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target) {
    if (graph.isEmpty()) {
      return false;
    }

    Set<Vertex<T>> visited = new HashSet<>();

    depthFirstSearch(graph, source, visited);

    return visited.contains(target);
  }

  //TODO: exercise 5 (incomplete)
  public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
    if (graph.isEmpty() || path.isEmpty()) {
      return false;
    }

    if (path.getFirst().equals(path.getLast())) {
      int pathSize = path.size();

      for (int i = 0; i < pathSize - 1; i++) {
        if (!graph.getAdjacentsVertex(path.get(i)).contains(path.get(i + 1))) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  // exercise 6
  public static <T, E> int numberOfConnectedComponents(Graph<T, E> graph) {
    // TODO: Implementa el cálculo del número de componentes conexas en el grafo
    return 0;
  }

  // exercise 7
  public static <T, E> Map<Vertex<T>, Integer> BFSShortestPath(Graph<T, E> graph, Vertex<T> start) {
    // TODO: Implementa el cálculo del número de componentes conexas en el grafo
    return null;
  }
}