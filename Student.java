package application;

public class Student {
	private int seatnum;
	private String branch;
	private double average;

	public Student(int seatnum, String branch, double average) {
		this.seatnum = seatnum;
		this.branch = branch;
		this.average = average;
	}

	public int getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return "Seat Number: " + seatnum + ",Branch: " + branch + ",Average: " + average;
	}
}
