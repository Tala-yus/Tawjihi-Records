package application;

public class TawjihiDS {
	public static DoubleLinkedList dll = new DoubleLinkedList();
	public static AverageAVL averageAVL = new AverageAVL();
	public static SeatNumAVL seatnumAVL = new SeatNumAVL();

	public static String add(Student student) {
		DLLNode dllNode = new DLLNode(student);
		if (seatnumAVL.find(dllNode) == null) {
			dll.addAtLast(dllNode);
			seatnumAVL.insert(dllNode);
			averageAVL.insert(dllNode);
			return "Success";
		} else {
			return "Error: Student already exists";
		}
	}

	public static String delete(int seatNum) {
		DLLNode dllNode = dll.delete(seatNum);
		if (dllNode != null) {
			seatnumAVL.delete(dllNode);
			averageAVL.delete(dllNode);
			return "Student has been deleted " + dllNode.getStudent().toString();

		} else
			return "Error: Student Doesnt Exist!";
	}

	public static String update(int seatNum, String branch, double avg) {
		DLLNode dllNode = new DLLNode(new Student(seatNum, "", 0));
		DLLNode dlln = seatnumAVL.find(dllNode);

		if (dlln != null) {
			String s = dlln.getStudent().toString();
			delete(seatNum);
			add(new Student(seatNum, branch, avg));
			return s + "\n " + "has been updated to" + "\n " + find(seatNum);
		} else
			return "Error: Student Doesnt Exist!";

	}

	public static Student find(int seatNum) {
		DLLNode dllNode = new DLLNode(new Student(seatNum, "", 0));
		if (seatnumAVL.find(dllNode) != null) {
			return seatnumAVL.find(dllNode).getStudent();
		} else
			return null;
	}

	public static Student findPrev(int seatNum) {
		DLLNode dllNode = new DLLNode(new Student(seatNum, "", 0));
		DLLNode node = seatnumAVL.find(dllNode);
		if (node != null) {
			if (node.getPrev() != null) {
				return node.getPrev().getStudent();
			} else {
				return null;
			}

		} else
			return null;
	}

	public static Student findNext(int seatNum) {
		DLLNode dllNode = new DLLNode(new Student(seatNum, "", 0));
		DLLNode node = seatnumAVL.find(dllNode);
		if (node != null) {
			if (node.getNext() != null) {
				return node.getNext().getStudent();
			} else {
				return null;
			}

		} else
			return null;
	}

	public static String getAllByGrade(double grade) {
		DLLNode dllNode = new DLLNode(new Student(0, "", grade));
		if (averageAVL.find(dllNode) != null) {
			return averageAVL.find(dllNode);
		}
		return "Error: Students with the grade " + grade + " couldn't be found!";
	}

	public static void clear() {
		dll = new DoubleLinkedList();
		averageAVL = new AverageAVL();
		seatnumAVL = new SeatNumAVL();

	}
}
