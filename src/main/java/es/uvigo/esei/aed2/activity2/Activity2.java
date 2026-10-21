package es.uvigo.esei.aed2.activity2;

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

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.binary.LinkedBinaryTree;

public class Activity2 {

  //exercise 1
  public static <T> boolean isComplete(BinaryTree<T> tree) {
    if (!tree.hasLeftChild() && !tree.hasRightChild())
      return true;

    if (tree.hasLeftChild() && tree.hasRightChild())
      return isComplete(tree.getLeftChild()) && isComplete(tree.getRightChild());

    return false;
  }

  // exercise 2
  public static <T> boolean areIdentical(BinaryTree<T> treeA, BinaryTree<T> treeB) {
    if (treeA.isEmpty() && treeB.isEmpty()) {
      return true;
    }

    if (treeA.isEmpty() || treeB.isEmpty()) {
      return false;
    }

    if (!treeA.getRootValue().equals(treeB.getRootValue())){
      return false;
    }

    return areIdentical(treeA.getLeftChild(), treeB.getLeftChild()) && areIdentical(treeA.getRightChild(), treeB.getRightChild());
  }

  //exercise 3
  public static <T> int countNodesInLevel(BinaryTree<T> tree, int level) {
    if (tree.isEmpty() || level < 0)
      return 0;

    if (level == 0)
      return 1;

    return countNodesInLevel(tree.getLeftChild(), level - 1) + countNodesInLevel(tree.getRightChild(), level - 1);
  }
  
  // private static <T> int countNodesInLevel(BinaryTree<T> tree, int level, int currLevel) {
  //   if (tree.isEmpty())
  //     return 0;

  //   if (currLevel == level)
  //     return 1;

  //   return countNodesInLevel(tree.getLeftChild(), level, currLevel + 1) + countNodesInLevel(tree.getRightChild(), level, currLevel + 1);
  // }

  //exercise 4
  public static <T> BinaryTree<T> removeLeaves(BinaryTree<T> tree) {
    if (tree.isEmpty() || (!tree.hasLeftChild() && !tree.hasRightChild())){
      return new LinkedBinaryTree<>();
    }

    BinaryTree<T> newTree = new LinkedBinaryTree<>(tree.getRootValue());

    newTree.setLeftChild(removeLeaves(tree.getLeftChild()));
    newTree.setRightChild(removeLeaves(tree.getRightChild()));

    return newTree;
  }

  //exercise 5
  public static <T> int calculateHeight(BinaryTree<T> tree) {
    if (tree.isEmpty())
      return -1;

   int leftTreeHeight = calculateHeight(tree.getLeftChild()) + 1;
   int rightTreeHeight = calculateHeight(tree.getRightChild()) + 1;
    
    return leftTreeHeight > rightTreeHeight? leftTreeHeight : rightTreeHeight;
  }

  //exercise 6
  public static BinaryTree<String> rebuild(String preorder, String inorder) {
    if (preorder.isEmpty() || inorder.isEmpty())
      return new LinkedBinaryTree<>();

    BinaryTree<String> newTree = new LinkedBinaryTree<>(String.valueOf(preorder.charAt(0)));
    int rootInorderIndex = inorder.indexOf(preorder.charAt(0));
    
    if (rootInorderIndex > 0) { // has left child
      newTree.setLeftChild(rebuild(preorder.substring(1, rootInorderIndex + 1), inorder.substring(0, rootInorderIndex)));
    }

    if (rootInorderIndex < inorder.length() - 1) { // has right child
      newTree.setRightChild(rebuild(preorder.substring(rootInorderIndex + 1), inorder.substring(rootInorderIndex + 1)));
    }

    return newTree;
  }

  //exercise 7
  public static <T extends Comparable<T>> boolean isSelectionTree(BinaryTree<T> tree) {
    if (tree.isEmpty()) {
      return true;
    }

    if (tree.hasLeftChild() ^ tree.hasRightChild()) {
      return false;
    }

    if (tree.hasLeftChild() && tree.hasRightChild()) {
      T leftValue = tree.getLeftChild().getRootValue();
      T rightValue = tree.getRightChild().getRootValue();
      T smaller = leftValue.compareTo(rightValue) < 0 ? leftValue : rightValue;
      
      if (tree.getRootValue().compareTo(smaller) == 0) {
        return isSelectionTree(tree.getLeftChild()) && isSelectionTree(tree.getRightChild());
      }
      else
        return false;
    }

    return true;
  }

  //exercise 8
  public static <T> boolean isPath(BinaryTree<T> tree, String path) {
    if (tree.isEmpty() && path.isEmpty()) {
      return true;
    }

    if ((tree.isEmpty() ^ path.isEmpty()) || !tree.getRootValue().equals(String.valueOf(path.charAt(0)))) {
      return false;
    }

    if (path.length() > 1) {
      return isPath(tree.getLeftChild(), path.substring(1)) || isPath(tree.getRightChild(), path.substring(1));
    }

    return true;
  }

  //exercise 9
  public static <T> BinaryTree<T> copyingToLevel(BinaryTree<T> tree, int level) {
    if (tree.isEmpty() || level < 0)
      return new LinkedBinaryTree<>();

    BinaryTree<T> copy = new LinkedBinaryTree<>(tree.getRootValue());
    
    if (level > 0) {
      copy.setLeftChild(copyingToLevel(tree.getLeftChild(), level - 1));
      copy.setRightChild(copyingToLevel(tree.getRightChild(), level - 1));
    }

    return copy;
  }

  //exercise 10
  public static <T> boolean isLevelK(BinaryTree<T> tree, T elem, int k) {
    if (tree.isEmpty() || k < 0) {
      return false;
    }

    if (tree.getRootValue().equals(elem) && k == 0){
      return true;
    }

    if (k > 0) {
      return isLevelK(tree.getLeftChild(), elem, k - 1) || isLevelK(tree.getRightChild(), elem, k - 1);
    }

    return false;
  }
}