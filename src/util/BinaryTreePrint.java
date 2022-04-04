package util;

import data.BinaryTreeNode;

import java.BinarySearchTreeNode;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreePrint {
	// Character for indents and other separation | SHOULD BE KEPT AS A SINGLE CHARACTER
	private final static char separator = ' ';
	// Values from 0 and up, increases the separation between numbers
	private final static int BUFFER_SPACE = 1;
	// Character used to represent a missing value
	private final static char MISSING_VALUE = '_';
	// Value used for Dummy Nodes, used to define where a Node is missing (child nodes being null)
	private final static int DUMMY_VALUE = -1;
	// Value determined by Root Node Height. Used to determine the separation distance
	private static int TREE_HEIGHT;
	// Simple Dummy Node used to fill in for Null Child Values
	private final static BinarySearchTreeNode<Integer> DUMMY_NODE = new BinarySearchTreeNode<>(DUMMY_VALUE);

	/**
	 * <p>
	 * This method requires that there is a class BinaryTreeNode
	 * and that the field "root" is initialized
	 * as well as methods to set and get nodes and values:
	 * <br>
	 * E getElement() - returns the element stored in the node
	 * (integers can be used directly used due to javas autoboxing unboxing
	 * <br>
	 * BinaryTreeNode getLeftChild()) - returns a reference to leftChild
	 * <br>
	 * BinaryTreeNode getRightChild()) - returns a reference to rightChild
	 * </p>
	 * <p>
	 * The tree must be created elsewhere (possible in a Main or Test class).
	 * </p>
	 *
	 * @param root [BinaryTreeNode] acting as the Root for a Tree
	 */

	public static void printTree(BinarySearchTreeNode root) {
		ArrayList<BinarySearchTreeNode> parent = new ArrayList<>();

		TREE_HEIGHT = root.getHeight() + Math.max(1, BUFFER_SPACE);

		parent.add(root);

		printTheTree(parent);
	}

	private static void printTheTree(List<BinarySearchTreeNode> nodeList) {
		if (TREE_HEIGHT == 0) {
			return;
		}
//		System.out.print("List of Nodes to print: { ");
//		for (BinaryTreeNode node : nodeList) {
//			System.out.printf("%3s, ", node.getNodeValue());
//		}
//		System.out.println(" }");

		int indent = (int) Math.pow(2, TREE_HEIGHT);
		TREE_HEIGHT--;

		StringBuilder lineInfoStringBuilder = new StringBuilder();
		ArrayList<BinarySearchTreeNode> childrenList = new ArrayList<>();
		for (BinaryTreeNode<Integer> node : nodeList) {
			childrenList.add(node.getLeftChild() != null ? node.getLeftChild() : DUMMY_NODE);
			childrenList.add(node.getRightChild() != null ? node.getRightChild() : DUMMY_NODE);

			lineInfoStringBuilder.append(indent(indent))
			                     .append(String.format("%2s",
			                                           (node.getNodeValue() == DUMMY_VALUE) ? String.valueOf(MISSING_VALUE) : node.getNodeValue()))
			                     .append(indent(indent));

		}
		System.out.println(lineInfoStringBuilder);
		printTheTree(childrenList);
	}

	private static String indent(int amount) {
		return String.valueOf(separator)
		             .repeat(Math.max(1, amount - 1));
	}
}
