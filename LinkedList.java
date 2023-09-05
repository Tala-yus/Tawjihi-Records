package application;

public class LinkedList {
	private SLLNode head;

	public SLLNode getHead() {
		return head;
	}

	public void setHead(SLLNode head) {
		this.head = head;
	}

	public void addAtHead(DLLNode dllNode) {
		SLLNode newNode = new SLLNode(dllNode);
		if (head == null) {
			head = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
	}

	public void delete(DLLNode dllNode) {
		SLLNode curr = head;
		SLLNode next = curr;
		if (curr == null) {
			return;
		} else {
			if (curr.getReference().getStudent().getSeatnum() == dllNode.getStudent().getSeatnum()) {
				head = curr.getNext();
			}
			while (curr != null) {
				next = curr.getNext();
				if (next != null
						&& next.getReference().getStudent().getSeatnum() == dllNode.getStudent().getSeatnum()) {
					curr.setNext(next.getNext());
					break;
				}
				curr = curr.getNext();
			}
		}
	}

	public String traverse() {
		String output = "";
		SLLNode curr = head;
		while (curr != null) {
			output += curr.getReference().getStudent().toString() + " => ";
			curr = curr.getNext();
		}
		return output;
	}

	public boolean isEmpty() {
		return head == null;
	}
}
