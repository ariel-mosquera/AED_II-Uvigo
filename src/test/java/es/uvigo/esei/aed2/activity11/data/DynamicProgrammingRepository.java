package es.uvigo.esei.aed2.activity11.data;

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

import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;
import java.util.List;

public class DynamicProgrammingRepository{

  private final static List<Integer> KEYS_CHANGE = List.of(500, 200);
  private final static List<Integer> VALUES_CHANGE = List.of(20, 10);
  private final static List<Integer> KEYS_CHANGE_RESULT = List.of(500, 200);
  private final static List<Integer> VALUES_CHANGE_RESULT = List.of(2, 1);

  private final static List<String> KEYS_RUCKSACK_VOLUMES = List.of("car", "doll", "ball", "bike");
  private final static List<Integer> VALUES_RUCKSACK_VOLUMES = List.of(4, 5, 10, 3);
  private final static List<String> KEYS_RUCKSACK_WEIGHTS = List.of("car", "doll", "ball", "bike");
  private final static List<Integer> VALUES_RUCKSACK_WEIGHTS = List.of(3, 4, 5, 2);
  private final static List<String> KEYS_RUCKSACK_RESULT = List.of("doll", "ball");
   
  private <K, V> Map<K, V> createMapWith(List<K> keys, List<V> values) {
    Map<K, V> map = new HashMap<>();

    for (int i = 0; i < keys.size(); i++) {
      map.add(keys.get(i), values.get(i));
    }
    return map;
  }

  public Map<Integer, Integer> getMapChange() {
    return this.createMapWith(KEYS_CHANGE, VALUES_CHANGE);
  }

  public Map<Integer, Integer> getMapChangeResult() {
    return this.createMapWith(KEYS_CHANGE_RESULT, VALUES_CHANGE_RESULT);
  }

  public Map<String, Integer> getMapRucksackVolumes() {
    return this.createMapWith(KEYS_RUCKSACK_VOLUMES, VALUES_RUCKSACK_VOLUMES);
  }

  public Map<String, Integer> getMapRucksackWeights() {
    return this.createMapWith(KEYS_RUCKSACK_WEIGHTS, VALUES_RUCKSACK_WEIGHTS);
  }

  public List<String> getListRucksack() {
    return KEYS_RUCKSACK_RESULT;
  }

}
