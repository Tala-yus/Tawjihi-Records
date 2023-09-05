package application;

public class SeatNumNode implements Comparable<SeatNumNode> {
	private DLLNode reference;
	private SeatNumNode left;
	private SeatNumNode right;

	public SeatNumNode(DLLNode reference) {
		this.reference = reference;
		right = left = null;
	}

	public DLLNode getReference() {
		return reference;
	}

	public void setReference(DLLNode reference) {
		this.reference = reference;
	}

	public SeatNumNode getLeft() {
		return left;
	}

	public void setLeft(SeatNumNode left) {
		this.left = left;
	}

	public SeatNumNode getRight() {
		return right;
	}

	public void setRight(SeatNumNode right) {
		this.right = right;
	}

	public boolean hasRight() {
		return (right != null);
	}

	public boolean hasLeft() {
		return (left != null);
	}

	@Override
	public String toString() {
		return reference.getStudent().toString();
	}

	@Override
	public int compareTo(SeatNumNode o) {
		if (reference.getStudent().getSeatnum() > o.getReference().getStudent().getSeatnum()) {
			return 1;
		} else if (reference.getStudent().getSeatnum() < o.getReference().getStudent().getSeatnum()) {
			return -1;
		}
		return 0;
	}
}
