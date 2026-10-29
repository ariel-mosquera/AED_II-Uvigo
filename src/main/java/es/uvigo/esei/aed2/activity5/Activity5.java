package es.uvigo.esei.aed2.activity5;

import java.util.List;

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

import es.uvigo.esei.aed2.tree.nary.Tree;
import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;

public class Activity5 {

  // exercise 1
  public static int getSum(Tree<Integer> tree) {
    if (tree.isEmpty()) {
      return 0;
    }

    int sum = tree.getRootValue();
    Tree<Integer> child = tree.getLeftMostChild();

    while (!child.isEmpty()) {
      sum += getSum(child);
      child = child.getRightSibling();
    }

    return sum;
  }

  // exercise 2
  public static <T> boolean isEqualStructure(Tree<T> treeA, Tree<T> treeB) {
    if (treeA.isEmpty() && treeB.isEmpty()) {
      return true;
    }

    if (treeA.isEmpty() || treeB.isEmpty()) {
      return false;
    }

    Tree<T> childA = treeA.getLeftMostChild();
    Tree<T> childB = treeB.getLeftMostChild();

    while (!childA.isEmpty() && !childB.isEmpty() && isEqualStructure(childA, childB)) {
      childA = childA.getRightSibling();
      childB = childB.getRightSibling();
    }

    return childA.isEmpty() && childB.isEmpty();
  }

  // exercise 3
  public static <T> boolean is23Tree(Tree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    int numOfChildren = 0;
    Tree<T> child = tree.getLeftMostChild();

    while (!child.isEmpty() && is23Tree(child)) {
      numOfChildren++;
      child = child.getRightSibling();
    }

    return child.isEmpty() && (numOfChildren == 0 || numOfChildren == 2 || numOfChildren == 3);
  }

  // exercise 4
  public static <T extends Comparable<T>> boolean isSelection(Tree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    Tree<T> child = tree.getLeftMostChild();

    if (!child.isEmpty()) {
      T smaller = child.getRootValue();
      child = child.getRightSibling();

      while (!child.isEmpty() && isSelection(child)) {
        if (child.getRootValue().compareTo(smaller) < 0) {
          smaller = child.getRootValue();
        }

        child = child.getRightSibling();
      }

      return child.isEmpty() && tree.getRootValue().compareTo(smaller) == 0;
    }

    return true;
  }

  // exercise 5
  public static <T> int getLevel(Tree<T> tree, T value) {
    if (tree.isEmpty()) {
      return -1;
    }

    if (tree.getRootValue().equals(value)) {
      return 0;
    }

    Tree<T> child = tree.getLeftMostChild();
    int nearestLevel = Integer.MAX_VALUE;

    while (!child.isEmpty()) {
      int result = getLevel(child, value);

      if (result != -1) {
        nearestLevel = Math.min(result + 1, nearestLevel);
      }

      child = child.getRightSibling();
    }

    return nearestLevel == Integer.MAX_VALUE ? -1 : nearestLevel;
  }

  // exercise 6
  public static <T> int getGrade(Tree<T> tree) {
    if (tree.isEmpty()) {
      return 0;
    }

    Tree<T> child = tree.getLeftMostChild();
    int grade = 0;
    int children = 0;

    while (!child.isEmpty()) {
      children++;
      grade = Math.max(grade, getGrade(child));
      child = child.getRightSibling();
    }

    return Math.max(children, grade);
  }

  // exercise 7
  public static <T> int getHeight(Tree<T> tree) {
    if (tree.isEmpty()) {
      return 0;
    }

    Tree<T> child = tree.getLeftMostChild();
    int higherHeight = 0;

    while (!child.isEmpty()) {
      int childHeight = getHeight(child) + 1;

      higherHeight = Math.max(childHeight, higherHeight);

      child = child.getRightSibling();
    }

    return higherHeight;
  }

  // exercise 8
  public static <T> void breadthOrder(Tree<T> tree) {
    if (tree.isEmpty()) {
      return;
    }

    Queue<T> values = new LinkedQueue<>();

    values.add(tree.getRootValue());
    
    do {
      Tree<T> child = tree.getLeftMostChild();
      
      System.out.println(values.remove());

      while (!child.isEmpty()) {
        values.add(child.getRootValue());
        child = child.getRightSibling();
      }
    } while (!values.isEmpty());
  }

  // exercise 9
  public static int getNumberOfEvenValues(Tree<Integer> tree) {
    if (tree.isEmpty()) {
      return 0;
    }

    Tree<Integer> child = tree.getLeftMostChild();
    int counter = 0;

    if (tree.getRootValue() % 2 == 0) {
      counter++;
    }

    while (!child.isEmpty()) {
      counter += getNumberOfEvenValues(child);
      child = child.getRightSibling();
    }

    return counter;
  }

  // exercise 10
  public static <T> void getListLeaves(Tree<T> tree, List<T> list) {
    if (tree.isEmpty()) {
      return;
    }

    if (!tree.hasChildren()) {
      list.add(tree.getRootValue());
    }

    Tree<T> child = tree.getLeftMostChild();

    while (!child.isEmpty()) {
      getListLeaves(child, list);
      child = child.getRightSibling();
    }
  }

}