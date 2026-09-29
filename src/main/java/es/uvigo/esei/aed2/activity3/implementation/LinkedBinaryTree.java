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

  private LinkedBinaryTree(LinkedBinaryTreeNode<T> root) {
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
    return rootNode.hasLeftNode();
  }

  @Override
  public BinaryTree<T> getLeftChild() throws EmptyTreeException {
    if (isEmpty())
      throw new EmptyTreeException();

    if (!hasLeftChild())
      return new LinkedBinaryTree<>();

      return new LinkedBinaryTree()
  }

  @Override
  public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeLeftChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasRightChild() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BinaryTree<T> getRightChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeRightChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
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
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void forEachPreOrder(Consumer<T> action) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void forEachPostOrder(Consumer<T> action) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void forEachLevelOrder(Consumer<T> action) {
    throw new UnsupportedOperationException("Not supported yet.");

  }
}