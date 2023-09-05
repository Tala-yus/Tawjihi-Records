package application;

public class DLLNode {
	private Student student;
	private DLLNode next;
	private DLLNode prev;

	public DLLNode(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public DLLNode getNext() {
		return next;
	}

	public void setNext(DLLNode next) {
		this.next = next;
	}

	public DLLNode getPrev() {
		return prev;
	}

	public void setPrev(DLLNode prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return student.toString();
	}
}
