package classes;

public class BinaryTreeNode<E> {
	private E element;
	private BinaryTreeNode<E> leftChild, rightChild;

	public BinaryTreeNode(E element) {
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BinaryTreeNode<E> getLeftChild() {
		return leftChild;
	}

	public void addLeftChild(BinaryTreeNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode<E> getRightChild() {
		return rightChild;
	}

	public void addRightChild(BinaryTreeNode<E> rightChild) {
		this.rightChild = rightChild;
	}


}
