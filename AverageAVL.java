package application;

public class AverageAVL {
	private AverageNode root;

	public int height() {
		return height(root);
	}

	private int height(AverageNode root) {
		if (root == null) {
			return 0;
		} else {
			int lheight = height(root.getLeft()); // get the height of left subtrees
			int rheight = height(root.getRight()); // get the height of right subtrees 

			if (lheight > rheight) {          //find the max between the right and left height and add 1 
				return (lheight + 1);         //
			} else                            // 
				return (rheight + 1);         //
		}
	}

	private String printCurrentLevel(AverageNode root, int level) {
		String s = "";
		if (root == null) {
			return "";
		}
		if (level == 1) {
			s += root.getList().traverse() + "\n";
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

	private AverageNode rebalance(AverageNode nodeN) {
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

	private int getHeightDifferance(AverageNode nodeN) {
		return height(nodeN.getLeft()) - height(nodeN.getRight());
	}

	private AverageNode rotateRight(AverageNode nodeN) {
		AverageNode nodeC = nodeN.getLeft();
		nodeN.setLeft(nodeC.getRight());
		nodeC.setRight(nodeN);
		return nodeC;
	}

	private AverageNode rotateLeft(AverageNode nodeN) {
		AverageNode nodeC = nodeN.getRight();
		nodeN.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeN);
		return nodeC;
	}

	private AverageNode rotateRightLeft(AverageNode nodeN) {
		AverageNode nodeC = nodeN.getRight();
		nodeN.setRight(rotateRight(nodeC));
		return rotateLeft(nodeN);
	}

	private AverageNode rotateLeftRight(AverageNode nodeN) {
		AverageNode nodeC = nodeN.getLeft();
		nodeN.setLeft(rotateLeft(nodeC));
		return rotateRight(nodeN);
	}

	public void insert(DLLNode dllNode) {
		if (root == null) {
			root = new AverageNode(dllNode);
		} else {
			AverageNode rootNode = root;
			addEntry(dllNode, rootNode);
			root = rebalance(rootNode);
		}
	}

	public void addEntry(DLLNode dllNode, AverageNode rootNode) {
		AverageNode newNode = new AverageNode(dllNode);
		assert rootNode != null;
		if (newNode.compareTo(rootNode) < 0) {
			if (rootNode.hasLeft()) {
				AverageNode leftChild = rootNode.getLeft();
				addEntry(dllNode, leftChild);
				rootNode.setLeft(rebalance(leftChild));
			} else {
				rootNode.setLeft(newNode);
			}
		} else if (newNode.compareTo(rootNode) > 0) {
			if (rootNode.hasRight()) {
				AverageNode rightChild = rootNode.getRight();
				addEntry(dllNode, rightChild);
				rootNode.setRight(rebalance(rightChild));
			} else {
				rootNode.setRight(newNode);
			}
		} else {
			rootNode.getList().addAtHead(dllNode);
		}
	}

	public AverageNode delete(DLLNode dllNode) {
		AverageNode toDelete = new AverageNode(dllNode);
		AverageNode temp = delete(toDelete, root, dllNode);
		return temp;
	}

	private AverageNode delete(AverageNode toDelete, AverageNode current, DLLNode dllNode) {
		AverageNode parent = root;
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
					parent.getLeft().getList().delete(dllNode);
					if (parent.getLeft().getList().isEmpty()) {
						parent.setLeft(null);
					}
				} else {
					parent.getRight().getList().delete(dllNode);
					if (parent.getRight().getList().isEmpty()) {
						parent.setRight(null);
					}
				}
			}
		} else if (current.hasLeft() && !current.hasRight()) {
			if (current == root) {
				root.getList().delete(dllNode);
				if (root.getList().isEmpty())
					root = current.getLeft();
			} else if (isLeftChild) {
				parent.getLeft().getList().delete(dllNode);
				if (parent.getLeft().getList().isEmpty())
					parent.setLeft(current.getLeft());
			} else {
				parent.getRight().getList().delete(dllNode);
				if (parent.getRight().getList().isEmpty())
					parent.setRight(current.getLeft());
			}
		} else if (current.hasRight() && !current.hasLeft()) {
			if (current == root) {
				root.getList().delete(dllNode);
				if (root.getList().isEmpty())
					root = current.getRight();
			} else if (isLeftChild) {
				parent.getList().delete(dllNode);
				if (parent.getList().isEmpty())
					parent.setLeft(current.getRight());
			} else {
				parent.getList().delete(dllNode);
				if (parent.getList().isEmpty())
					parent.setRight(current.getRight());
			}
		} else {
			current.getList().delete(dllNode);
			if (current.getList().isEmpty()) {
				AverageNode successor = getSuccessor(current);
				if (current == root) {
					root = successor;
				} else if (isLeftChild) {
					parent.setLeft(successor);
				} else {
					parent.setRight(successor);
				}
				successor.setLeft(current.getLeft());
			}
		}

		return current;
	}

	private AverageNode getSuccessor(AverageNode node) {
		AverageNode parentOfSuccessor = node;
		AverageNode successor = node;
		AverageNode current = node.getRight();
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

	public String find(DLLNode toFind) {
		if (root == null) {
			return null;
		}
		return find(toFind, root);
	}

	public String find(DLLNode toFind, AverageNode current) {
		if (current != null) {
			AverageNode node = new AverageNode(toFind);
			if (node.compareTo(current) == 0) {
				return current.getList().traverse();
			} else if (node.compareTo(current) < 0 && current.hasLeft()) {
				return find(toFind, current.getLeft());
			} else if (node.compareTo(current) > 0 && current.hasRight()) {
				return find(toFind, current.getRight());
			}
		}
		return null;
	}
}
