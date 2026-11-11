package es.uvigo.esei.aed2.activity6.relatedwords;

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

import java.util.LinkedList;
import java.util.List;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.graph.AdjacencyList;

import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;

public class RelatedWords {
  /**
   * Construye un grafo que representa las relaciones entre las palabras.
   * 
   * @param words palabras a relacionar.
   * @return grafo de palabras relacionadas.
   */
  public static Graph<String, Integer> buildGraph(List<String> words) {
    Graph<String, Integer> relationGraph = new AdjacencyList<>();
    Map<String, List<String>> dictionary = buildDictionary(words);

    words.forEach((word) -> relationGraph.addVertex(new Vertex<String>(word)));

    for (String key : dictionary.getKeys()) {
      List<String> currList = dictionary.get(key);

      for (int i = 0; i < currList.size() - 1; i++) {
        for (int j = i + 1; j < currList.size(); j++) {
          Vertex<String> source = new Vertex<String>(currList.get(i));
          Vertex<String> target = new Vertex<String>(currList.get(j));
          relationGraph.addEdge(source, target, 0);
          relationGraph.addEdge(target, source, 0);
        }
      }
    }

    return relationGraph;
  }

  private static Map<String, List<String>> buildDictionary(List<String> words) {
    Map<String, List<String>> dictionary = new HashMap<>();

    for (String word : words) {
      StringBuilder builder = new StringBuilder(word);

      for (int i = 0; i < builder.length(); i++) {
        char replacedChar = builder.charAt(i);
        builder.setCharAt(i, '_');
        String key = builder.toString();
        List<String> relatedWords = dictionary.get(key);

        if (relatedWords == null) {
          relatedWords = new LinkedList<>();
          dictionary.add(key, relatedWords);
        }

        relatedWords.add(word);
        builder.setCharAt(i, replacedChar);
      }
    }

    return dictionary;
  }
}
