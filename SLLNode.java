package application;

public class SLLNode {
	private DLLNode reference;
	private SLLNode next;

	public SLLNode(DLLNode reference) {
		this.reference = reference;
		this.next = null;
	}

	public DLLNode getReference() {
		return reference;
	}

	public void setReference(DLLNode reference) {
		this.reference = reference;
	}

	public SLLNode getNext() {
		return next;
	}

	public void setNext(SLLNode next) {
		this.next = next;
	}
}
