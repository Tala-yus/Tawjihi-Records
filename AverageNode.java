package application;

public class AverageNode implements Comparable<AverageNode> {

	private LinkedList list;

	private AverageNode right;

	private AverageNode left;

	public AverageNode(DLLNode dllNode) {
		this.list = new LinkedList();
		list.addAtHead(dllNode);
		right = left = null;
	}

	public LinkedList getList() {
		return list;
	}

	public void setList(LinkedList list) {
		this.list = list;
	}

	public AverageNode getRight() {
		return right;
	}

	public void setRight(AverageNode right) {
		this.right = right;
	}

	public AverageNode getLeft() {
		return left;
	}

	public void setLeft(AverageNode left) {
		this.left = left;
	}

	public boolean hasRight() {
		return (right != null);
	}

	public boolean hasLeft() {
		return (left != null);
	}

	@Override
	public String toString() {
		return list.traverse();
	}

	@Override
	public int compareTo(AverageNode o) {
		if (list.getHead().getReference().getStudent().getAverage() > o.getList().getHead().getReference().getStudent()
				.getAverage()) {
			return 1;
		} else if (list.getHead().getReference().getStudent().getAverage() < o.getList().getHead().getReference()
				.getStudent().getAverage()) {
			return -1;
		}
		return 0;
	}
}
