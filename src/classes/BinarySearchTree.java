package classes;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	private BinarySearchTreeNode<E> root;

	public BinarySearchTree(E rootElement) {
		super(rootElement);
		root = new BinarySearchTreeNode<>(rootElement);
		super.setRoot(root);
	}

	public boolean insert(E element) {
		if (contains(element)) {
			return false;
		}
		insertNode(root, element);
		return true;
	}

	private void insertNode(BinaryTreeNode<E> node, E element) {
		if (element.compareTo(node.getElement()) > 0) {
			if (node.getRightChild() == null) {
				BinarySearchTreeNode<E> newNode = new BinarySearchTreeNode<>(element);
				node.addRightChild(newNode);
			} else {
				insertNode(node.getRightChild(), element);
			}
		} else {
			if (node.getLeftChild() == null) {
				BinarySearchTreeNode<E> newNode = new BinarySearchTreeNode<>(element);
				node.addLeftChild(newNode);
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
		if (element.compareTo(node.getElement()) > 0) {
			if (element.compareTo(node.getRightChild()
			                          .getElement()) == 0) {
				BinaryTreeNode<E> tmpLeft = node.getRightChild()
				                                .getLeftChild();
				BinaryTreeNode<E> tmpRight = node.getRightChild()
				                                 .getRightChild();

				node.addRightChild(null);

				if (tmpLeft != null) {
					insertNode(node, tmpLeft.getElement());
				}
				if (tmpRight != null) {
					insertNode(node, tmpRight.getElement());
				}

			} else {
				removeNode(node.getRightChild(), element);
			}
		} else {
			if (element.compareTo(node.getLeftChild()
			                          .getElement()) == 0) {
				BinaryTreeNode<E> tmpLeft = node.getLeftChild()
				                                .getLeftChild();
				BinaryTreeNode<E> tmpRight = node.getLeftChild()
				                                 .getRightChild();

				node.addLeftChild(null);

				if (tmpRight != null) {
					insertNode(node, tmpRight.getElement());
				}
				if (tmpLeft != null) {
					insertNode(node, tmpLeft.getElement());
				}

			} else {
				removeNode(node.getLeftChild(), element);
			}
		}
	}

	public E findMax() {
		return maxNode(root).getElement();
	}

	private BinaryTreeNode<E> maxNode(BinaryTreeNode<E> node) {
		return (node.getRightChild() == null) ? node : maxNode(node.getRightChild());
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

		if (element.compareTo(node.getElement()) == 0) {
			return true;
		}

		if (element.compareTo(node.getElement()) > 0) {
			return containsNode(node.getRightChild(), element);
		}

		if (element.compareTo(node.getElement()) < 0) {
			return containsNode(node.getLeftChild(), element);
		}
		return false;
	}

	public void rebalance() {
		ArrayList<E> inOrderElementList = inOrder();
		root = balancedTreeFromArray(inOrderElementList, 0, inOrderElementList.size() - 1);
	}

	private BinarySearchTreeNode<E> balancedTreeFromArray(ArrayList<E> elementList, int startPoint, int endPoint) {
		// Credit to ritikranjan12 on leetcode for the "inspiration"
		if (startPoint > endPoint) {
			// If you hit this, you may or may not have messed up. Go get some help
			return null;
		}

		// Calculate midpoint for next "tree roots"
		int midPoint = startPoint + (endPoint - startPoint) / 2;

		// Create a new tmp Root element
		BinarySearchTreeNode<E> root = new BinarySearchTreeNode<>(elementList.get(midPoint));

		// Add children to tmp Root
		root.addLeftChild(balancedTreeFromArray(elementList, startPoint, midPoint - 1));
		root.addRightChild(balancedTreeFromArray(elementList, midPoint + 1, endPoint));

		// Return new Root, this is the new Tree
		return root;
	}
}
