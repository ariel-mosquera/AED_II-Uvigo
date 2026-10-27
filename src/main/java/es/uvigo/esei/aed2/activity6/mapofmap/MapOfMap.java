package es.uvigo.esei.aed2.activity6.mapofmap;


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

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class MapOfMap<T, E> implements Graph<T, E> {

  private final Map<Vertex<T>, Map<Vertex<T>, E>> mapOfVertices;

  public MapOfMap() {
    this.mapOfVertices = new HashMap<>();
  }
  
  @Override
  public boolean isEmpty() {
    return this.mapOfVertices.size() == 0;
  }

  @Override
  public int numberOfVertices() {
    return this.mapOfVertices.size();
  }

  @Override
  public boolean containsVertex(Vertex<T> vertex) {
    return this.mapOfVertices.getKeys().contains(vertex);
  }

  @Override
  public boolean containsEdge(Vertex<T> source, Vertex<T> target, E label) {
    if (!containsVertex(source) || !this.mapOfVertices.get(source).getKeys().contains(target)) {
      return false;
    }

    return this.mapOfVertices.get(source).get(target).equals(label);
  }

  @Override
  public Set<Vertex<T>> getVertices() {
    return this.mapOfVertices.getKeys();
  }

  @Override
  public Set<Edge<T, E>> getEdges() {
    Set<Edge<T, E>> toRet = new HashSet<>();

    for (Vertex<T> source : this.mapOfVertices.getKeys()) {
      for (Vertex<T> target : this.mapOfVertices.get(source).getKeys()) {
        toRet.add(new Edge<>(source, target, this.mapOfVertices.get(source).get(target)));
      }
    }

    return toRet;
  }

  @Override
  public Set<Vertex<T>> getAdjacentsVertex(Vertex<T> vertex) throws NullPointerException {
    if (this.isEmpty()) {
      return new HashSet<>();
    }

    return this.mapOfVertices.get(requireNonNull(vertex)).getKeys();
  }

  @Override
  public boolean addVertex(Vertex<T> vertex) throws NullPointerException {
    if (this.containsVertex(requireNonNull(vertex))) {
      return false;
    }

    this.mapOfVertices.add(vertex, new HashMap<>());
    return true;
  }

  @Override
  public boolean addEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException, IllegalArgumentException{
    if (!this.containsVertex(requireNonNull(source)) || !this.containsVertex(requireNonNull(target))) {
      throw new IllegalArgumentException("The vertex needs to be in the graph");
    }

    if (this.mapOfVertices.get(source).getKeys().contains(target)) {
      return false;
    }

    this.mapOfVertices.get(source).add(target, label);
    return true;
  }

  @Override
  public boolean removeVertex(Vertex<T> vertex) throws NullPointerException {
    if (!this.containsVertex(requireNonNull(vertex))) {
      return false;
    }

    this.mapOfVertices.remove(vertex);
    return true;
  }

  @Override
  public boolean removeEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException {
    if (!this.containsEdge(requireNonNull(source), requireNonNull(target), label)) {
      return false;
    }

    this.mapOfVertices.get(source).remove(target);
    return true;
  }
  
  @Override
  public void clear(){
    this.mapOfVertices.clear();  
  }

  @Override
  public void forEachVertex(Consumer<Vertex<T>> action){
    this.mapOfVertices.getKeys().forEach(action);
  }

  @Override
  public void forEachEdge(Consumer<Edge<T, E>> action){
    this.getEdges().forEach(action);
  }
}
