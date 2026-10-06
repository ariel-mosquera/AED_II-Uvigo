package es.uvigo.esei.aed2.activity3.implementation;
/*-
 * #%L
 * AEDII - Activities
 * %%
import java.util.function.Consumer;
import static java.util.Objects.requireNonNull;
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

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;
import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

  private LinkedBinaryTreeNode<T> rootNode;

  public LinkedBinaryTree() {
    rootNode = null;
  }

  public LinkedBinaryTree(T value) throws NullPointerException {
    this(new LinkedBinaryTreeNode<>(requireNonNull(value, "Null values are not allowed!")));
  }

  public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) throws NullPointerException {
    requireNonNull(value, "Null values are not allowed!");

    rootNode = new LinkedBinaryTreeNode<>(value, buildNodeFromTree(leftChild), buildNodeFromTree(rightChild));
  }

  private static <T> LinkedBinaryTreeNode<T> buildNodeFromTree(BinaryTree<T> tree) {
    if (tree.isEmpty())
      return null;

    return new LinkedBinaryTreeNode<T>(
        tree.getRootValue(),
        tree.hasLeftChild() ? buildNodeFromTree(tree.getLeftChild()) : null,
        tree.hasRightChild() ? buildNodeFromTree(tree.getRightChild()) : null);
  }

  private LinkedBinaryTree(LinkedBinaryTreeNode<T> root) { // permite a partir de un nodo, crear un árbol
    rootNode = root;
  }

  // Métodos lanzan excepción
  @Override
  public T getRootValue() throws EmptyTreeException {
    if (this.isEmpty())
      throw new EmptyTreeException("The tree is empty, impossible to get a value");

    return rootNode.getValue();
  }

  @Override
  public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
    if (this.isEmpty())
      throw new EmptyTreeException("The tree is empty, impossible to set value");

    requireNonNull(value, "Null values are not allowed");

    rootNode.setValue(value);
  }

  @Override
  public boolean contains(T value) {
    return contains(rootNode, value);
  }

  private boolean contains(LinkedBinaryTreeNode<T> parent, T value) {
    if (parent == null)
      return false;

    return parent.getValue().equals(value) || contains(parent.getLeftNode(), value)
        || contains(parent.getRightNode(), value);
  }

  @Override
  public boolean hasLeftChild() {
    return !isEmpty() && rootNode.hasLeftNode();
  }

  @Override
  public BinaryTree<T> getLeftChild() throws EmptyTreeException {
    if (isEmpty())
      throw new EmptyTreeException();

    return hasLeftChild()? new LinkedBinaryTree<>(rootNode.getLeftNode()) : new LinkedBinaryTree<>();
  }

  @Override
  public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
    if (isEmpty())
      throw new EmptyTreeException("Can't set left child in an empty tree");

    requireNonNull(leftChild, "Null values are not allowed");

    rootNode.setLeftNode(buildNodeFromTree(leftChild));
  }

  @Override
  public void removeLeftChild() throws EmptyTreeException {
    if (isEmpty())
      throw new EmptyTreeException("Can't remove left child from an empty tree");

    rootNode.setLeftNode(null);
  }

  @Override
  public boolean hasRightChild() {
    return !isEmpty() && rootNode.hasRightNode();
  }

  @Override
  public BinaryTree<T> getRightChild() throws EmptyTreeException {
    if (isEmpty())
      throw new EmptyTreeException();

    return hasRightChild() ? new LinkedBinaryTree<>(rootNode.getRightNode()) : new LinkedBinaryTree<>();
  }

  @Override
  public void setRightChild(BinaryTree<T> RightChild) throws EmptyTreeException, NullPointerException {
    if (isEmpty())
      throw new EmptyTreeException("Can't set right child in an empty tree");

    requireNonNull(RightChild, "Null values are not allowed");

    rootNode.setRightNode(buildNodeFromTree(RightChild));
  }

  @Override
  public void removeRightChild() throws EmptyTreeException {
    if (isEmpty())
      throw new EmptyTreeException("Can't remove right child from an empty tree");

    rootNode.setRightNode(null);
  }

  @Override
  public void clear() {
    rootNode = null;
  }

  @Override
  public boolean isEmpty() {
    return rootNode == null;
  }

  @Override
  public void forEachInOrder(Consumer<T> action) {
    if (!isEmpty())
      forEachInOrder(rootNode, action);
    
  }

  private static <T> void forEachInOrder(LinkedBinaryTreeNode<T> node, Consumer<T> action) {
    if (node != null) {
      forEachInOrder(node.getLeftNode(), action);
      action.accept(node.getValue());
      forEachInOrder(node.getRightNode(), action);
    }
  }

  @Override
  public void forEachPreOrder(Consumer<T> action) {
    if (!isEmpty())
      forEachPreOrder(rootNode, action);
  }

  private static <T> void forEachPreOrder(LinkedBinaryTreeNode<T> node, Consumer<T> action) {
    if (node != null) {
      action.accept(node.getValue());
      forEachPreOrder(node.getLeftNode(), action);
      forEachPreOrder(node.getRightNode(), action);
    }
  }

  @Override
  public void forEachPostOrder(Consumer<T> action) {
    if (!isEmpty())
      forEachPostOrder(rootNode, action);
  }

  private static <T> void forEachPostOrder(LinkedBinaryTreeNode<T> node, Consumer<T> action) {
    if (node != null) {
      forEachPostOrder(node.getLeftNode(), action);
      forEachPostOrder(node.getRightNode(), action);
      action.accept(node.getValue());
    }
  }

  @Override
  public void forEachLevelOrder(Consumer<T> action) {
    Queue<LinkedBinaryTreeNode<T>> queue = new LinkedQueue<>();
    queue.add(rootNode);

    do {
      LinkedBinaryTreeNode<T> node = queue.remove();
      action.accept(node.getValue());

      if (node.hasLeftNode())
        queue.add(node.getLeftNode());

      if (node.hasRightNode())
        queue.add(node.getRightNode());
    } while (!queue.isEmpty());
  }
}