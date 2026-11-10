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

  private static <T, E> void depthFirstSearch(Graph<T, E> graph, Vertex<T> vertex, Set<Vertex<T>> visited) {
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

    return thereIsPathBetweenVertices(graph, source, target, visited);
  }

  private static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target,
      Set<Vertex<T>> visited) {
    if (source.equals(target)) {
      return true;
    }

    visited.add(source);

    for (Vertex<T> adjVertex : graph.getAdjacentsVertex(source)) {
      if (!visited.contains(adjVertex) && thereIsPathBetweenVertices(graph, adjVertex, target, visited)) {
        return true;
      }
    }

    return false;
  }

  // TODO: exercise 5 (incomplete)
  public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
    if (!path.getFirst().equals(path.getLast()) || graph.isEmpty() || path.isEmpty()) {
      return false;
    }

    int pathSize = path.size();

    if (pathSize == 1) {
      return graph.getAdjacentsVertex(path.getFirst()).contains(path.getFirst());
    }

    Set<Vertex<T>> visited = new HashSet<>();

    for (int i = 0; i < pathSize - 1; i++) {
      Vertex<T> curr = path.get(i);

      if (visited.contains(curr) || !graph.getAdjacentsVertex(curr).contains(path.get(i + 1))) {
        return false;
      }

      visited.add(curr);
    }

    return true;
  }

  // exercise 6
  public static <T, E> int numberOfConnectedComponents(Graph<T, E> graph) {
    Set<Vertex<T>> visited = new HashSet<>();
    int sum = 0;

    for (Vertex<T> vertex : graph.getVertices()) {
      if (!visited.contains(vertex)) {
        sum++;
        depthFirstSearch(graph, vertex, visited);
      }
    }

    return sum;
  }

  // exercise 7
  public static <T, E> Map<Vertex<T>, Integer> BFSShortestPath(Graph<T, E> graph, Vertex<T> start) {
    Queue<Vertex<T>> toExplore = new LinkedQueue<>();
    Set<Vertex<T>> visited = new HashSet<>();
    Map<Vertex<T>, Integer> shortestPaths = new HashMap<>();
    
    toExplore.add(start);
    visited.add(start);
    shortestPaths.add(start, 0);

    do {
      Vertex<T> current = toExplore.remove();
      int currentDistance = shortestPaths.get(current);
      
      for (Vertex<T> adj : graph.getAdjacentsVertex(current)) {
        if (!visited.contains(adj)) {
          toExplore.add(adj);
          visited.add(adj);
          shortestPaths.add(adj, currentDistance + 1);
        }
      }
    } while (!toExplore.isEmpty());

    return shortestPaths;
  }
}