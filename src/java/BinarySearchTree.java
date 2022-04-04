package java;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	private BinarySearchTreeNode<E> root;

	public BinarySearchTree(E rootElement) {
		super(rootElement);
		root = new BinarySearchTreeNode<>(rootElement);
	}

	public boolean insert(E element) {
		if (contains(element)) {
			return false;
		}
		insertNode(root, element);
		return true;
	}

	private void insertNode(BinaryTreeNode<E> node, E element) {
		if (node.getElement()
		        .compareTo(element) > 0) {
			if (node.getRightChild() == null) {
				node.addRightChild(new BinarySearchTreeNode<>(element));
			} else {
				insertNode(node.getRightChild(), element);
			}
		} else {
			if (node.getLeftChild() == null) {
				node.addLeftChild(new BinarySearchTreeNode<>(element));
			} else {
				insertNode(node.getLeftChild(), element);
			}
		}
	}

	public boolean removeElement(E element) {
		if (!contains(element)) {
			return false;
		}
		removeNode(root, element);
		return true;
	}

	private void removeNode(BinaryTreeNode<E> node, E element) {
		if (node.getElement()
		        .compareTo(element) > 0) {
			if (node.getRightChild()
			        .getElement()
			        .compareTo(element) == 0) {
				BinaryTreeNode<E> tmpLeft = node.getRightChild()
				                                .getLeftChild();
				BinaryTreeNode<E> tmpRight = node.getRightChild()
				                                 .getRightChild();

				node.addRightChild(null);

				insertNode(node, tmpLeft.getElement());
				insertNode(node, tmpRight.getElement());
			} else {
				removeNode(node.getRightChild(), element);
			}
		} else {
			if (node.getLeftChild()
			        .getElement()
			        .compareTo(element) == 0) {
				BinaryTreeNode<E> tmpLeft = node.getLeftChild()
				                                .getLeftChild();
				BinaryTreeNode<E> tmpRight = node.getLeftChild()
				                                 .getRightChild();

				node.addLeftChild(null);

				insertNode(node, tmpRight.getElement());
				insertNode(node, tmpLeft.getElement());
			} else {
				removeNode(node.getLeftChild(), element);
			}
		}
	}

	public E findMax() {
		return maxNode(root).getElement();
	}

	private BinaryTreeNode<E> maxNode(BinaryTreeNode<E> node) {
		return (node.getRightChild() == null) ? node : minNode(node.getRightChild());
	}

	public E findMin() {
		return minNode(root).getElement();
	}

	private BinaryTreeNode<E> minNode(BinaryTreeNode<E> node) {
		return (node.getLeftChild() == null) ? node : minNode(node.getLeftChild());
	}

	public boolean contains(E element) {
		return containsNode(root, element);
	}

	private boolean containsNode(BinaryTreeNode<E> node, E element) {
		if (node == null) {
			return false;
		}
		return node.getElement()
		           .compareTo(element) == 0 || (node.getElement()
		                                            .compareTo(element) > 0) ? containsNode(node.getRightChild(), element) : containsNode(
				node.getLeftChild(),
				element);
	}

	public void rebalance() {
		ArrayList<E> inOrderElementList = inOrder();
		root = (BinarySearchTreeNode<E>) balancedTreeFromArray(inOrderElementList, 0, inOrderElementList.size() - 1);
	}

	private BinaryTreeNode<E> balancedTreeFromArray(ArrayList<E> elementList, int startPoint, int endPoint) {
		// Credit to ritikranjan12 on leetcode for the "inspiration"
		if (startPoint > endPoint) {
			// If you hit this, you may or may not have messed up. Go get some help
			return null;
		}

		// Calculate midpoint for next "tree roots"
		int midPoint = startPoint + (endPoint - startPoint) / 2;

		// Create a new tmp Root element
		BinaryTreeNode<E> root = new BinaryTreeNode<>(elementList.get(midPoint));

		// Add children to tmp Root
		root.addLeftChild(balancedTreeFromArray(elementList, startPoint, midPoint - 1));
		root.addRightChild(balancedTreeFromArray(elementList, midPoint + 1, endPoint));

		// Return new Root, this is the new Tree
		return root;
	}
}
