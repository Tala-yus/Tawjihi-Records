package application;

import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(TawjihiDS.add(new Student(123, "Scientific", 55)));
		System.out.println(TawjihiDS.add(new Student(133, "Scientific", 59)));
		System.out.println(TawjihiDS.add(new Student(143, "Scientific", 65)));
		System.out.println(TawjihiDS.add(new Student(153, "Scientific", 75)));

		System.out.println(TawjihiDS.seatnumAVL.height());
		System.out.println(TawjihiDS.seatnumAVL.printTree());

	}

}