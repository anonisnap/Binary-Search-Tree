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
		size = inOrder().size();
		return size;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(E element) {
		return inOrder().contains(element);
	}

	public ArrayList<E> inOrder() {
		ArrayList<E> inOrderList = new ArrayList<>();
		// TODO
		return null;
	}

	public ArrayList<E> preOrder() {
		ArrayList<E> preOrderList = new ArrayList<>();
		// TODO
		return null;
	}

	public ArrayList<E> postOrder() {
		ArrayList<E> postOrderList = new ArrayList<>();
		// TODO
		return null;
	}

	public ArrayList<E> levelOrder() {
		ArrayList<E> levelOrderList = new ArrayList<>();
		// TODO
		return null;
	}

	public int getHeight() {
		return root.getHeight();
	}
}
