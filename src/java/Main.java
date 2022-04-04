package java;

public class Main {
	public static void main(String[] args) {
		int nodeOne = 1;
		int nodeTwo = 2;
		int nodeThree = 3;
		int nodeFour = 4;
		int nodeFive = 5;
		int nodeFifty = 50;

		BinarySearchTree<Integer> tree = new BinarySearchTree<>(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);
		tree.insert(nodeFour);
		tree.insert(nodeFive);
		tree.insert(nodeFifty);
	}
}
