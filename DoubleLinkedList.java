package application;

public class DoubleLinkedList {
	DLLNode head;

//	public boolean add(DLLNode newNode) {
//		if (head == null) {
//			head = newNode;
//			head.setNext(newNode);
//			head.setPrev(newNode);
//		} else {
//			DLLNode curr = head;
//			if (curr.getStudent().getSeatnum() == newNode.getStudent().getSeatnum()) {
//				return false;
//			} else if (curr.getStudent().getSeatnum() > newNode.getStudent().getSeatnum()) {
//				newNode.setNext(curr);
//				newNode.setPrev(curr.getPrev());
//				curr.getPrev().setNext(newNode);
//				curr.setPrev(newNode);
//				head = newNode;
//			} else {
//				while (curr.getNext() != head && curr.getStudent().getSeatnum() < newNode.getStudent().getSeatnum()) {
//					curr = curr.getNext();
//				}
//				if (curr.getStudent().getSeatnum() == newNode.getStudent().getSeatnum()) {
//					return false;
//				} else if (curr.getStudent().getSeatnum() < newNode.getStudent().getSeatnum()) {
//					newNode.setNext(curr.getNext());
//					newNode.setPrev(curr);
//					curr.getNext().setPrev(newNode);
//					curr.setNext(newNode);
//				} else {
//					newNode.setNext(curr);
//					newNode.setPrev(curr.getPrev());
//					curr.getPrev().setNext(newNode);
//					curr.setPrev(newNode);
//				}
//			}
//		}
//		return true;
//	}

	// TO-DO add at last
//	public boolean addAtLast(DLLNode newNode) {
//		if (head == null) {
//			head = newNode;
//			head.setNext(newNode);
//			head.setPrev(newNode);
//		} else {
//			DLLNode curr = head;
//			while (curr.getNext() != head) {
//				curr = curr.getNext();
//			}
//			if (curr.getStudent().getSeatnum() == newNode.getStudent().getSeatnum()) {
//				return false;
//			} else {
//				newNode.setNext(curr.getNext());
//				newNode.setPrev(curr);
//				curr.getNext().setPrev(newNode);
//				curr.setNext(newNode);
//			}
//		}
//		return true;
//
//	}

	public void addAtLast(DLLNode newNode) {
		if (head == null) {
			head = newNode;
			head.setNext(newNode);
			head.setPrev(newNode);
		} else {
			DLLNode curr = head.getPrev();
			newNode.setNext(curr.getNext());
			newNode.setPrev(curr);
			curr.getNext().setPrev(newNode);
			curr.setNext(newNode);
		}
	}

	public DLLNode delete(int seatnum) {
		DLLNode curr = head;
		if (curr == null) {
			return null;
		}
		if (curr.getStudent().getSeatnum() == seatnum) {
			curr.getNext().setPrev(curr.getPrev());
			curr.getPrev().setNext(curr.getNext());
			head = curr.getNext();
			return curr;
		}
		if (curr.getNext() != head) {
			curr = curr.getNext();
		} else {
			return null;
		}
		while (curr != head) {
			if (curr.getStudent().getSeatnum() == seatnum) {
				curr.getNext().setPrev(curr.getPrev());
				curr.getPrev().setNext(curr.getNext());
				return curr;
			}
			curr = curr.getNext();
		}
		return null;
	}

	public DLLNode find(int element) {
		if (head != null) {
			DLLNode node = new DLLNode(new Student(element, "", 0));
			DLLNode curr = head;
			while (curr.getNext() != head) {
				if (curr.getStudent().getSeatnum() == node.getStudent().getSeatnum()) {
					return curr;
				}
				curr = curr.getNext();

			}
			return null;
		} else
			return null;

	}

	public String traverse() {
		String output = "";
		DLLNode curr = head;
		if (curr == null) {
			return "List is Empty!";
		}
		output += curr.getStudent().toString();
		if (curr.getNext() != head) {
			curr = curr.getNext();
			while (curr != head) {
				output += "\n " + " => " + curr.getStudent().toString();
				curr = curr.getNext();
			}
		}
		return output;
	}
}
