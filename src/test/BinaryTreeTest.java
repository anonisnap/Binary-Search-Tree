package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BinaryTreePrint;

import java.BinarySearchTree;
import java.BinarySearchTreeNode;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTreeTest {
	private static int nodeOne, nodeTwo, nodeThree, nodeFour, nodeFive, nodeFifty;
	private static int RANDOM_NODE_COUNT = 20;
	private static BinarySearchTreeNode<Integer>[] randomNodes;
	private static BinarySearchTree<Integer> tree;

	@BeforeEach
	public void init() {
		tree = new BinarySearchTree<>(nodeThree);
		nodeOne = 1;
		nodeTwo = 2;
		nodeThree = 3;
		nodeFour = 4;
		nodeFive = 5;
		nodeFifty = 50;
	}

	@BeforeAll
	static void setup() {
		randomNodes = new BinarySearchTreeNode[RANDOM_NODE_COUNT];
		Random r = new Random();
		for (int i = 0; i < RANDOM_NODE_COUNT; i++) {
			randomNodes[i] = new BinarySearchTreeNode<>(r.nextInt(100));
		}
	}

	@Test
	public void testNodeToString() {
		System.out.println("\n\n" + randomNodes[0].toString());
	}

	@Test
	public void testinsert() {
		Assertions.assertNull(tree.getRootNode());
		tree.insert(nodeOne);
		Assertions.assertEquals(nodeOne, tree.getRootNode());
		tree.insert(nodeTwo);
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testAddMultipleNodes() {
		Assertions.assertNull(tree.getRootNode());
		tree.insert(nodeFifty);
		for (BinaryTreeNode<Integer> randomNode : randomNodes) {
			tree.insert(randomNode);
		}
		Assertions.assertEquals(nodeFifty, tree.getRootNode());
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRemoveNode() {
		tree.insert(nodeOne);
		tree.insert(nodeThree);
		tree.insert(nodeTwo);
		tree.insert(nodeFive);

		BinaryTreePrint.printTree(tree.getRootNode());
		int beforeRemoval = tree.getSize();

		System.out.println("\n\n\n\t\t\t\t<!> BEFORE REMOVAL / AFTER REMOVAL <!>");
		// Verify the node gotten, is the one removed
		Assertions.assertEquals(nodeTwo, tree.removeNode(nodeTwo));

		// Verify the size of the tree has been reduced
		Assertions.assertEquals(beforeRemoval - 1, tree.getSize());

		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRemoveNode_node_not_present() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);

		int beforeRemoval = tree.getSize();

		Assertions.assertTrue(tree.removeElement(nodeThree));

		Assertions.assertEquals(beforeRemoval, tree.getSize());
	}

	@Test
	public void testGetHeight() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		BinaryTreePrint.printTree(tree.getRootNode());

		Assertions.assertEquals(0, nodeThree.getHeight());
		Assertions.assertEquals(1, nodeTwo.getHeight());
		Assertions.assertEquals(2, nodeOne.getHeight());
	}

	@Test
	public void testGetBalance() {
		tree.insert(nodeTwo);
		tree.insert(nodeOne);
		tree.insert(nodeThree);
		tree.insert(nodeFive);
		tree.insert(nodeFour);

		BinaryTreePrint.printTree(tree.getRootNode());

		Assertions.assertEquals(2, nodeTwo.getBalance());
		Assertions.assertEquals(0, nodeOne.getBalance());
		Assertions.assertEquals(2, nodeThree.getBalance());
		Assertions.assertEquals(-1, nodeFive.getBalance());
		Assertions.assertEquals(0, nodeFour.getBalance());
	}

	@Test
	public void testRotateLeft() {
		// Add nodes in increasing order
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		// Assert nodes are unbalanced
		Assertions.assertEquals(2, tree.getBalance());
		System.out.println("\n\tBEFORE ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rotateLeft();
		// Assert nodes are balanced
		Assertions.assertEquals(0, tree.getBalance());
		System.out.println("\n\tAFTER ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRotateRight() {
		// Add nodes in increasing order
		tree.insert(nodeThree);
		tree.insert(nodeTwo);
		tree.insert(nodeOne);

		// Assert nodes are unbalanced
		Assertions.assertEquals(-2, tree.getBalance());
		System.out.println("\n\tBEFORE ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rotateRight();
		// Assert nodes are balanced
		Assertions.assertEquals(0, tree.getBalance());
		System.out.println("\n\tAFTER ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testLargeRotateLeft() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);
		tree.insert(nodeFour);
		tree.insert(nodeFive);

		// Very unbalanced Tree
		Assertions.assertEquals(4, tree.getBalance());
		System.out.println("\n\tBEFORE ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rotateLeft();
		// Still slightly unbalanced tree
		Assertions.assertEquals(2, tree.getBalance());
		System.out.println("\n\tAFTER 1st ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rotateLeft();
		// Assert nodes are balanced
		Assertions.assertEquals(0, tree.getBalance());
		System.out.println("\n\tAFTER 2nd ROTATION\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRotateOnSingularNode() {
		tree.insert(nodeOne);

		// Print the Tree
		BinaryTreePrint.printTree(tree.getRootNode());
		Assertions.assertThrows(NullPointerException.class, () -> tree.rotateLeft());
		Assertions.assertThrows(NullPointerException.class, () -> tree.rotateRight());
	}

	@Test
	public void testRebalance_right_right() {
		// Add a few nodes to the Tree
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(2, tree.getBalance());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Rebalance the Tree
		tree.rebalance();
		// Ensure Tree was correctly rebalanced
		Assertions.assertTrue(tree.getBalance() < 2);
		Assertions.assertTrue(tree.getBalance() > -2);
		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Add a few nodes to the Tree
		tree.insert(nodeFour);
		tree.insert(nodeFive);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(2, tree.getBalance());
		System.out.println("\n\tBEFORE REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Rebalance the Tree
		tree.rebalance();
		Assertions.assertTrue(tree.getBalance() < 2);
		Assertions.assertTrue(tree.getBalance() > -2);
		System.out.println("\n\tAFTER REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRebalance_left_left() {
		// Add a few nodes to the Tree
		tree.insert(nodeFive);
		tree.insert(nodeFour);
		tree.insert(nodeThree);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-2, tree.getBalance());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Rebalance the Tree
		tree.rebalance();
		// Ensure Tree was correctly rebalanced
		Assertions.assertTrue(tree.getBalance() < 2);
		Assertions.assertTrue(tree.getBalance() > -2);
		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Add a few nodes to the Tree
		tree.insert(nodeTwo);
		tree.insert(nodeOne);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-2, tree.getBalance());
		System.out.println("\n\tBEFORE REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Rebalance the Tree
		tree.rebalance();
		Assertions.assertTrue(tree.getBalance() < 2);
		Assertions.assertTrue(tree.getBalance() > -2);
		System.out.println("\n\tAFTER REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRebalance_left_right() {
		// Add a few nodes to the Tree
		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-3, tree.getBalance());
		Assertions.assertEquals(2,
		                        tree.getRootNode()
		                            .getLeftChild()
		                            .getBalance());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rebalance();
		Assertions.assertEquals(1, tree.getBalance());
		Assertions.assertTrue(tree.getRootNode()
		                          .getLeftChild()
		                          .isLeafNode());
		Assertions.assertEquals(-1,
		                        tree.getRootNode()
		                            .getRightChild()
		                            .getBalance());

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testRebalance_right_left() {
		// Add a few nodes to the Tree
		tree.insert(nodeOne);
		tree.insert(nodeFour);
		tree.insert(nodeThree);
		tree.insert(nodeTwo);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(3, tree.getBalance());
		Assertions.assertEquals(-2,
		                        tree.getRootNode()
		                            .getRightChild()
		                            .getBalance());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		tree.rebalance();
		Assertions.assertEquals(-1, tree.getBalance());
		Assertions.assertTrue(tree.getRootNode()
		                          .getRightChild()
		                          .isLeafNode());
		Assertions.assertEquals(1,
		                        tree.getRootNode()
		                            .getLeftChild()
		                            .getBalance());

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());
	}

	@Test
	public void testLargeTreeFullTest() {
		// Add Root Node
		tree.insert(nodeFifty);

		// Add random nodes
		for (BinaryTreeNode<Integer> node : randomNodes) {
			tree.insert(node);
		}

		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		// Rebalance Nodes
		tree.rebalance();

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree.getRootNode());

		Assertions.assertTrue(tree.getBalance() > -2);
		Assertions.assertTrue(tree.getBalance() < 2);
	}

	@Test
	public void testPostOrderSort() {
		System.out.println("\n\tTesting post order sort\n");

		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFifty);
		tree.insert(nodeThree);
		tree.insert(nodeFive);

		List<BinaryTreeNode<Integer>> expectedList = new ArrayList<>();

		expectedList.add(nodeThree);
		expectedList.add(nodeTwo);
		expectedList.add(nodeOne);
		expectedList.add(nodeFive);
		expectedList.add(nodeFifty);
		expectedList.add(nodeFour);

		BinaryTreePrint.printTree(tree.getRootNode());

		List<BinaryTreeNode<Integer>> postOrderedList = tree.getPostOrderList();

		Assertions.assertEquals(expectedList, postOrderedList);

	}

}
