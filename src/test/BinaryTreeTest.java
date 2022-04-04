import classes.BinarySearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BinaryTreePrint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeTest {
	private static int nodeOne, nodeTwo, nodeThree, nodeFour, nodeFive, nodeFifty, nodeHundredAndOne;
	private static final int RANDOM_NODE_COUNT = 20;
	private static int[] randomNodes;
	private static BinarySearchTree<Integer> tree;

	@BeforeEach
	public void init() {
		nodeOne = 1;
		nodeTwo = 2;
		nodeThree = 3;
		nodeFour = 4;
		nodeFive = 5;
		nodeFifty = 50;
		nodeHundredAndOne = 101;
		tree = new BinarySearchTree<>(nodeThree);
	}

	@BeforeAll
	static void setup() {
		randomNodes = new int[RANDOM_NODE_COUNT];
		Random r = new Random();
		for (int i = 0; i < RANDOM_NODE_COUNT; i++) {
			randomNodes[i] = 1 + r.nextInt(99);
		}
	}

	@Test
	public void testInsert() {
		Assertions.assertEquals(nodeThree,
		                        tree.getRoot()
		                            .getElement());
		tree.insert(nodeTwo);
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testAddMultipleNodes() {
		for (Integer randomNode : randomNodes) {
			tree.insert(randomNode);
		}
		Assertions.assertEquals(nodeThree,
		                        tree.getRoot()
		                            .getElement());
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testContainsNode() {
		tree.insert(nodeOne);
		tree.insert(nodeFour);
		tree.insert(nodeFive);

		Assertions.assertTrue(tree.contains(nodeFour));
	}

	@Test
	public void testRemoveNode() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFour);
		tree.insert(nodeFive);

		BinaryTreePrint.printTree(tree);
		int beforeRemoval = tree.getSize();

		System.out.println("\n\n\n\t\t\t\t<!> BEFORE REMOVAL / AFTER REMOVAL <!>");
		// Verify the node gotten, is the one removed
		Assertions.assertTrue(tree.removeElement(nodeOne));

		// Verify the size of the tree has been reduced
		Assertions.assertEquals(beforeRemoval - 1, tree.getSize());

		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testRemoveNode_node_not_present() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);

		int beforeRemoval = tree.getSize();

		Assertions.assertFalse(tree.removeElement(nodeFour));

		Assertions.assertEquals(beforeRemoval, tree.getSize());
	}

	@Test
	public void testGetHeight() {
		tree.insert(nodeOne);
		tree.insert(nodeTwo);

		BinaryTreePrint.printTree(tree);

		Assertions.assertEquals(3, tree.getHeight());
	}

	@Test
	public void testLargeTreeFullTest() {
		// Add Root Node
		tree.insert(nodeFifty);

		// Add random nodes
		for (int node : randomNodes) {
			tree.insert(node);
		}

		System.out.println("\n\tBEFORE REBALANCE\n");
		BinaryTreePrint.printTree(tree);

		// Rebalance Nodes
		tree.rebalance();

		System.out.println("\n\tAFTER REBALANCE\n");
		BinaryTreePrint.printTree(tree);
	}

	@Test
	public void testPreOrder() {
		System.out.println("\n\tTesting pre order sort\n");

		tree.insert(nodeFour);
		tree.insert(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeFifty);
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeThree);
		expectedList.add(nodeOne);
		expectedList.add(nodeTwo);
		expectedList.add(nodeFour);
		expectedList.add(nodeFifty);
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
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeTwo);
		expectedList.add(nodeOne);
		expectedList.add(nodeFive);
		expectedList.add(nodeFifty);
		expectedList.add(nodeFour);
		expectedList.add(nodeThree);

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
		tree.insert(nodeFive);

		List<Integer> expectedList = new ArrayList<>();

		expectedList.add(nodeThree);
		expectedList.add(nodeOne);
		expectedList.add(nodeFour);
		expectedList.add(nodeTwo);
		expectedList.add(nodeFifty);
		expectedList.add(nodeFive);

		BinaryTreePrint.printTree(tree);

		List<Integer> postOrderedList = tree.levelOrder();

		Assertions.assertEquals(expectedList, postOrderedList);
	}

	@Test
	public void findMin() {
		tree.insert(nodeTwo);
		tree.insert(nodeOne);
		for (int node : randomNodes) {
			tree.insert(node);
		}

		Assertions.assertEquals(nodeOne, tree.findMin());
	}

	@Test
	public void findMax() {
		tree.insert(nodeFour);
		tree.insert(nodeHundredAndOne);
		for (int node : randomNodes) {
			tree.insert(node);
		}

		Assertions.assertEquals(nodeHundredAndOne, tree.findMax());
	}

}
