package util;

import java.BinaryTree;
import java.BinaryTreeNode;
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
	private final static BinaryTreeNode<Integer> DUMMY_NODE = new BinaryTreeNode<>(DUMMY_VALUE);

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
	 * @param tree [BinaryTree] to print
	 */

	public static void printTree(BinaryTree tree) {
		ArrayList<BinaryTreeNode> parent = new ArrayList<>();

		TREE_HEIGHT = tree.getHeight() + Math.max(1, BUFFER_SPACE);

		parent.add(tree.getRoot());

		printTheTree(parent);
	}

	private static void printTheTree(List<BinaryTreeNode> nodeList) {
		if (TREE_HEIGHT == 0) {
			return;
		}

		int indent = (int) Math.pow(2, TREE_HEIGHT);
		TREE_HEIGHT--;

		StringBuilder lineInfoStringBuilder = new StringBuilder();
		ArrayList<BinaryTreeNode> childrenList = new ArrayList<>();
		for (BinaryTreeNode<Integer> node : nodeList) {
			childrenList.add(node.getLeftChild() != null ? node.getLeftChild() : DUMMY_NODE);
			childrenList.add(node.getRightChild() != null ? node.getRightChild() : DUMMY_NODE);

			lineInfoStringBuilder.append(indent(indent))
			                     .append(String.format("%2s",
			                                           (node.getElement() == DUMMY_VALUE) ? String.valueOf(MISSING_VALUE) : node.getElement()))
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
