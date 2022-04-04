package java;

import java.util.ArrayList;

public class BinaryTree<E> {
	private BinaryTreeNode<E> root;
	private int size;

	public BinaryTreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<E> root) {
		this.root = root;
	}

	public int getSize() {
		size = sizeFromNode(root);
		return size;
	}

	private int sizeFromNode(BinaryTreeNode<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + sizeFromNode(node.getLeftChild()) + sizeFromNode(node.getRightChild());
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(E element) {
		return containsNode(root, element);
	}

	private boolean containsNode(BinaryTreeNode<E> node, E element) {
		if (node == null) {
			return false;
		}
		return node.getElement()
		           .equals(element) || containsNode(node.getLeftChild(), element) || containsNode(node.getRightChild(), element);
	}

	public ArrayList<E> inOrder() {
		ArrayList<E> l = new ArrayList<>();
		traverse("in", l, root);
		return l;
	}

	public ArrayList<E> preOrder() {
		ArrayList<E> l = new ArrayList<>();
		traverse("pre", l, root);
		return l;
	}

	public ArrayList<E> postOrder() {
		ArrayList<E> l = new ArrayList<>();
		traverse("post", l, root);
		return l;
	}

	public ArrayList<E> levelOrder() {
		ArrayList<E> l = new ArrayList<>();

		for (int i = 0; i < getHeight(); i++) {
			levelOrderAddToList(l, root, i);
		}

		return l;
	}

	private void levelOrderAddToList(ArrayList<E> list, BinaryTreeNode<E> node, int level) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.getElement());
		} else if (level > 1) {
			levelOrderAddToList(list, node.getLeftChild(), level - 1);
			levelOrderAddToList(list, node.getRightChild(), level - 1);
		}
	}

	private void traverse(String order, ArrayList<E> list, BinaryTreeNode<E> node) {
		if (node == null) {
			return;
		}
		if (order.equals("pre")) {
			list.add(node.getElement());
		}

		traverse(order, list, node.getLeftChild());

		if (order.equals("in")) {
			list.add(node.getElement());
		}

		traverse(order, list, node.getRightChild());

		if (order.equals("post")) {
			list.add(node.getElement());
		}

	}

	public int getHeight() {
		return nodeHeight(root);
	}

	private int nodeHeight(BinaryTreeNode<E> node) {
		if (node == null || node.getLeftChild() == null && node.getRightChild() == null) {
			return 0;
		}
		return Math.max(nodeHeight(node.getLeftChild()), nodeHeight(node.getRightChild())) + 1;
	}
}
