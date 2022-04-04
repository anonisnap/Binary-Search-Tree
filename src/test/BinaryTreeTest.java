import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BinaryTreePrint;

import java.BinarySearchTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeTest {
	private static int nodeOne, nodeTwo, nodeThree, nodeFour, nodeFive, nodeFifty;
	private static final int RANDOM_NODE_COUNT = 20;
	private static int[] randomNodes;
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
		randomNodes = new int[RANDOM_NODE_COUNT];
		Random r = new Random();
		for (int i = 0; i < RANDOM_NODE_COUNT; i++) {
			randomNodes[i] = r.nextInt(100);
		}
	}

	@Test
	public void testInsert() {
		Assertions.assertNull(tree.getRoot());
		tree.insert(nodeOne);
		Assertions.assertEquals(nodeOne,
		                        tree.getRoot()
		                            .getElement());
		tree.insert(nodeTwo);
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testAddMultipleNodes() {
		Assertions.assertNull(tree.getRoot());
		tree.insert(nodeFifty);
		for (Integer randomNode : randomNodes) {
			tree.insert(randomNode);
		}
		Assertions.assertEquals(nodeFifty,
		                        tree.getRoot()
		                            .getElement());
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRemoveNode() {
		tree.insert(nodeOne);
		tree.insert(nodeThree);
		tree.insert(nodeTwo);
		tree.insert(nodeFive);

		BinaryTreePrint.printTree(tree);
		int beforeRemoval = tree.getSize();

		System.out.println("\n\n\n\t\t\t\t<!> BEFORE REMOVAL / AFTER REMOVAL <!>");
		// Verify the node gotten, is the one removed
		Assertions.assertTrue(tree.removeElement(nodeTwo));

		// Verify the size of the tree has been reduced
		Assertions.assertEquals(beforeRemoval - 1, tree.getSize());

		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRemoveNode_node_not_present() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);

		int beforeRemoval = tree.getSize();

		Assertions.assertFalse(tree.removeElement(nodeThree));

		Assertions.assertEquals(beforeRemoval, tree.getSize());
	}

	@Test
	public void testGetHeight() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		BinaryTreePrint.printTree(tree);

		Assertions.assertEquals(2, tree.getHeight());
	}

	@Test
	public void testRebalance_right_right() {
		// Add a few nodes to the Tree
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		// Trees to check if balance is kept
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(0);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(0);

		// Ensure the Balance is not yet kept
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		Assertions.assertEquals(2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance the Tree
		tree.rebalance();
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Ensure Tree was correctly rebalanced
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() < 2);
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() > -2);
		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Add a few nodes to the Tree
		tree.insert(nodeFour);
		tree.insert(nodeFive);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance the Tree
		tree.rebalance();
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() < 2);
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() > -2);
		System.out.println("\n\tAFTER REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRebalance_left_left() {
		// Add a few nodes to the Tree
		tree.insert(nodeFive);
		tree.insert(nodeFour);
		tree.insert(nodeThree);

		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(0);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(0);
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance the Tree
		tree.rebalance();

		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Ensure Tree was correctly rebalanced
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() < 2);
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() > -2);
		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Add a few nodes to the Tree
		tree.insert(nodeTwo);
		tree.insert(nodeOne);

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance the Tree
		tree.rebalance();

		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() < 2);
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() > -2);
		System.out.println("\n\tAFTER REBALANCE (MORE NODES)\n");
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRebalance_left_right() {
		// Add a few nodes to the Tree
		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);

		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(0);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(0);
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(-3, tree1.getHeight() - tree2.getHeight());
		Assertions.assertEquals(2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		tree.rebalance();

		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		Assertions.assertEquals(1, tree1.getHeight() - tree2.getHeight());
		Assertions.assertEquals(0, tree2.getHeight());
		Assertions.assertEquals(-1, tree1.getHeight() - tree2.getHeight());

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRebalance_right_left() {
		// Add a few nodes to the Tree
		tree.insert(nodeOne);
		tree.insert(nodeFour);
		tree.insert(nodeThree);
		tree.insert(nodeTwo);

		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(0);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(0);
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Ensure the Balance is not yet kept
		Assertions.assertEquals(3, tree1.getHeight() - tree2.getHeight());
		Assertions.assertEquals(-2, tree1.getHeight() - tree2.getHeight());
		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		tree.rebalance();

		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		Assertions.assertEquals(-1, tree1.getHeight() - tree2.getHeight());
		Assertions.assertEquals(0, tree1.getHeight());
		Assertions.assertEquals(1, tree1.getHeight() - tree2.getHeight());

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testLargeTreeFullTest() {
		// Add Root Node
		tree.insert(nodeFifty);

		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(0);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(0);
		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		// Add random nodes
		for (int node : randomNodes) {
			tree.insert(node);
		}

		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance Nodes
		tree.rebalance();

		tree1.setRoot(tree.getRoot()
		                  .getRightChild());
		tree2.setRoot(tree.getRoot()
		                  .getLeftChild());

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() > -2);
		Assertions.assertTrue(tree1.getHeight() - tree2.getHeight() < 2);
	}

	@Test
	public void testPreOrder() {
		System.out.println("\n\tTesting pre order sort\n");

		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFifty);
		tree.insert(nodeThree);
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeFour);
		expectedList.add(nodeOne);
		expectedList.add(nodeTwo);
		expectedList.add(nodeFifty);
		expectedList.add(nodeThree);
		expectedList.add(nodeFive);

		BinaryTreePrint.printTree(tree);

		List<Integer> postOrderedList = tree.preOrder();

		Assertions.assertEquals(expectedList, postOrderedList);

	}

	@Test
	public void testInOrder() {
		System.out.println("\n\tTesting in order sort\n");

		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFifty);
		tree.insert(nodeThree);
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeOne);
		expectedList.add(nodeTwo);
		expectedList.add(nodeThree);
		expectedList.add(nodeFour);
		expectedList.add(nodeFive);
		expectedList.add(nodeFifty);

		BinaryTreePrint.printTree(tree);

		List<Integer> postOrderedList = tree.inOrder();

		Assertions.assertEquals(expectedList, postOrderedList);

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

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeThree);
		expectedList.add(nodeTwo);
		expectedList.add(nodeOne);
		expectedList.add(nodeFive);
		expectedList.add(nodeFifty);
		expectedList.add(nodeFour);

		BinaryTreePrint.printTree(tree);

		List<Integer> postOrderedList = tree.postOrder();

		Assertions.assertEquals(expectedList, postOrderedList);

	}

	@Test
	public void testLevelOrder() {
		System.out.println("\n\tTesting post order sort\n");

		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFifty);
		tree.insert(nodeThree);
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeFour);
		expectedList.add(nodeOne);
		expectedList.add(nodeFifty);
		expectedList.add(nodeTwo);
		expectedList.add(nodeThree);
		expectedList.add(nodeFive);

		BinaryTreePrint.printTree(tree);

		List<Integer> postOrderedList = tree.levelOrder();

		Assertions.assertEquals(expectedList, postOrderedList);
	}

}
