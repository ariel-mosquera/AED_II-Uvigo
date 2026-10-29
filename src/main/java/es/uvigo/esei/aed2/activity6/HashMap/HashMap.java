package es.uvigo.esei.aed2.activity6.HashMap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.HashSet;

import static java.util.Objects.requireNonNull;

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

import es.uvigo.esei.aed2.map.Map;

public class HashMap<K, V> implements Map<K, V> {
  private static final int CAPACITY = 50;

  private int size;
  private List<Pair<K, V>>[] map;

  public HashMap() {
    this(CAPACITY);
  }

  @SuppressWarnings("unchecked")
  private HashMap(int capacity) throws IllegalArgumentException {
    this.map = (List<Pair<K, V>>[]) new List[capacity];
    this.size = 0;

    for (int i = 0; i < this.map.length; i++) {
      this.map[i] = new LinkedList<>();
    }
  }

  private int calculateIndex(K key) {
    return Math.abs(key.hashCode()) % 50;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public V get(K key) {
    if (key == null) {
      return null;
    }

    int index = calculateIndex(key);

    for (Pair<K,V> pair : map[index]) {
      if (pair.getKey().equals(key)) {
        return pair.getValue();
      }
    }

    return null;
  }

  @Override
  public void add(K key, V value) throws NullPointerException {
    requireNonNull(value);
    int index = calculateIndex(requireNonNull(key));
    List<Pair<K, V>> indexList = this.map[index];

    for (int i = 0; i < indexList.size(); i++) {
      if (indexList.get(i).getKey().equals(key)) {
        indexList.get(i).setValue(value);
        return;
      }
    }

    indexList.addLast(new Pair<>(key, value));
    this.size++;
  }

  @Override
  public V remove(K key) {
    int index = (key == null)? -1 : calculateIndex(key);
    int listSize = (index == -1)? -1 : this.map[index].size();
    int removeIndex = -1;

    for (int i = 0; i < listSize; i++) {
      if (this.map[index].get(i).getKey().equals(key)) {
        removeIndex = i;
        break;
      }
    }

    if (removeIndex == -1) {
      return null;
    }

    this.size--;
    return this.map[index].remove(removeIndex).getValue();
  }

  @Override
  public Set<K> getKeys() {
    Set<K> keysSet = new HashSet<>();

    for (List<Pair<K, V>> list : this.map) {
      for (Pair<K, V> pair : list) {
        keysSet.add(pair.getKey());
      }
    }

    return keysSet;
  }

  @Override
  public Iterator<V> getValues() {
    LinkedList<V> values = new LinkedList<>();

    for (List<Pair<K, V>> list : this.map) {
      for (Pair<K, V> pair : list) {
        values.add(pair.getValue());
      }
    }

    return values.iterator();
  }

  @Override
  public void clear() {
    for (List<Pair<K, V>> list : this.map) {
      list.clear();
    }
    this.size = 0;
  }

  @Override
  public void forEach(BiConsumer<K, V> action) {
    for (List<Pair<K, V>> list : this.map) {
      for (Pair<K, V> pair : list) {
        action.accept(pair.getKey(), pair.getValue());
      }
    }
  }

  private static class Pair<K, V> {

    private final K k;
    private V v;

    public Pair(K k, V v) {
      this.k = k;
      this.v = v;
    }

    public K getKey() {
      return k;
    }

    public V getValue() {
      return v;
    }

    public void setValue(V v) {
      this.v = v;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Pair) {
        Pair<?, ?> hp = (Pair<?, ?>) other;
        return this.k.equals(hp.k) && this.v.equals(hp.v);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(this.k) ^ Objects.hashCode(this.v);
    }
  }

}