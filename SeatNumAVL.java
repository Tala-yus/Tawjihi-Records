package application;

public class SeatNumAVL {
	SeatNumNode root;

	public int height() {
		return height(root);
	}

	private int height(SeatNumNode root) {
		if (root == null) {
			return 0;
		} else {
			int lheight = height(root.getLeft());
			int rheight = height(root.getRight());

			if (lheight > rheight) {
				return (lheight + 1);
			} else
				return (rheight + 1);
		}
	}

	private String printCurrentLevel(SeatNumNode root, int level) {
		String s = "";
		if (root == null) {
			return "";
		}
		if (level == 1) {
			s += root.getReference().getStudent().toString() + "\n";
		} else if (level > 1) {
			s += printCurrentLevel(root.getLeft(), level - 1);
			s += printCurrentLevel(root.getRight(), level - 1);
		}
		return s;
	}

	public String printTree() {
		if (root == null) {
			return "Error: Tree is empty";
		}
		String output = "";
		int h = height();
		for (int i = 1; i <= h; i++) {
			output += "Level " + i + "\n" + printCurrentLevel(root, i);
		}
		return output;
	}

	private SeatNumNode rebalance(SeatNumNode nodeN) {
		int diff = getHeightDifferance(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifferance(nodeN.getLeft()) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifferance(nodeN.getRight()) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	private int getHeightDifferance(SeatNumNode nodeN) {
		return height(nodeN.getLeft()) - height(nodeN.getRight());
	}

	private SeatNumNode rotateRight(SeatNumNode nodeN) {
		SeatNumNode nodeC = nodeN.getLeft();
		nodeN.setLeft(nodeC.getRight());
		nodeC.setRight(nodeN);
		return nodeC;
	}

	private SeatNumNode rotateLeft(SeatNumNode nodeN) {
		SeatNumNode nodeC = nodeN.getRight();
		nodeN.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeN);
		return nodeC;
	}

	private SeatNumNode rotateRightLeft(SeatNumNode nodeN) {
		SeatNumNode nodeC = nodeN.getRight();
		nodeN.setRight(rotateRight(nodeC));
		return rotateLeft(nodeN);
	}

	private SeatNumNode rotateLeftRight(SeatNumNode nodeN) {
		SeatNumNode nodeC = nodeN.getLeft();
		nodeN.setLeft(rotateLeft(nodeC));
		return rotateRight(nodeN);
	}

	public void insert(DLLNode dllNode) {
		if (root == null) {
			root = new SeatNumNode(dllNode);
		} else {
			SeatNumNode newNode = new SeatNumNode(dllNode);
			SeatNumNode rootNode = root;
			addEntry(newNode, rootNode);
			root = rebalance(rootNode);
		}
	}

	public void addEntry(SeatNumNode newNode, SeatNumNode rootNode) {
		assert rootNode != null;
		if (newNode.compareTo(rootNode) < 0) {
			if (rootNode.hasLeft()) {
				SeatNumNode leftChild = rootNode.getLeft();
				addEntry(newNode, leftChild);
				rootNode.setLeft(rebalance(leftChild));
			} else {
				rootNode.setLeft(newNode);
			}
		} else if (newNode.compareTo(rootNode) > 0) {
			if (rootNode.hasRight()) {
				SeatNumNode rightChild = rootNode.getRight();
				addEntry(newNode, rightChild);
				rootNode.setRight(rebalance(rightChild));
			} else {
				rootNode.setRight(newNode);
			}
		}
	}

	public SeatNumNode delete(DLLNode dllNode) {
		SeatNumNode toDelete = new SeatNumNode(dllNode);
		SeatNumNode temp = delete(toDelete, root);
		return temp;
	}

	private SeatNumNode delete(SeatNumNode toDelete, SeatNumNode current) {
		SeatNumNode parent = root;
		if (root == null) {
			return null; // tree is empty
		}
		boolean isLeftChild = false;
		while (current != null && toDelete.compareTo(current) != 0) {
			parent = current;
			if (toDelete.compareTo(current) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else if (toDelete.compareTo(current) > 0) {
				current = current.getRight();
				isLeftChild = false;
			} else
				break;
		}
		if (current == null) {
			return null;
		} else if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) {
				root = null;
			} else {
				if (isLeftChild) {
					parent.setLeft(null);
				} else
					parent.setRight(null);
			}
		} else if (current.hasLeft() && !current.hasRight()) {
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else
				parent.setRight(current.getLeft());
		} else if (current.hasRight() && !current.hasLeft()) {
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else
				parent.setRight(current.getRight());
		} else {
			SeatNumNode successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}

		return current;
	}

	private SeatNumNode getSuccessor(SeatNumNode node) {
		SeatNumNode parentOfSuccessor = node;
		SeatNumNode successor = node;
		SeatNumNode current = node.getRight();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public DLLNode find(DLLNode toFind) {
		if (root == null) {
			return null;
		}
		return find(toFind, root);
	}

	public DLLNode find(DLLNode toFind, SeatNumNode current) {
		if (current != null) {
			SeatNumNode node = new SeatNumNode(toFind);
			if (node.compareTo(current) == 0) {
				return current.getReference();
			} else if (node.compareTo(current) < 0 && current.hasLeft()) {
				return find(toFind, current.getLeft());
			} else if (node.compareTo(current) > 0 && current.hasRight()) {
				return find(toFind, current.getRight());
			}
		}
		return null;
	}
}
