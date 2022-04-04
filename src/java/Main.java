package java;

public class Main {
	public static void main(String[] args) {
		Integer nodeOne = 1;
		Integer nodeTwo = 2;
		Integer nodeThree = 3;
		Integer nodeFour = 4;
		Integer nodeFive = 5;
		Integer nodeFifty = 50;

		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.setRoot(nodeOne);
		tree.insert(nodeTwo);
		tree.insert(nodeThree);
		tree.insert(nodeFour);
		tree.insert(nodeFive);
		tree.insert(nodeFifty);
	}
}
