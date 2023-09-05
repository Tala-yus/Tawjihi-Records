package gui;

import application.Student;
import application.TawjihiDS;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Insert extends Stage {

	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane ins = new BorderPane();

	VBox insVB = new VBox(30);

	Label label = new Label("Enter seat number,branch,average");

	TextField tf = new TextField();

	Button insert = new Button("Insert");

	Button back = new Button("Back");

	Button backToMenu = new Button("Return to Menu");

	String branchSelected = Menu.s.trim();

	Insert() {
		ins.setStyle("-fx-background-color:lightblue");

		label.setFont(Font.font("Verdana", 30));

		tf.setFont(Font.font("Verdana", 20));
		tf.setPrefSize(50, 50);
		tf.setMaxWidth(400);

		insert.setPrefSize(300, 50);
		insert.setStyle("-fx-background-color:White;");
		insert.setFont(Font.font("Verdana", 20));

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		insVB.getChildren().addAll(label, tf, insert, back, backToMenu);
		insVB.setAlignment(Pos.CENTER);
		ins.setCenter(insVB);

		insert.setOnAction(e -> {
			try {
				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("Please Insert the Record");
					alert.show();
				} else {
					String[] words = tf.getText().trim().split(",");
					if (branchSelected.equalsIgnoreCase(words[1].trim()) || branchSelected
							.equalsIgnoreCase((Menu.sci.getText().trim() + " - " + Menu.lit.getText().trim()).trim())) {
						if (words.length < 3 || words.length > 3) {
							alert.setContentText("Submit Record Properly, 3 attributes only");
							alert.show();
						} else {
							if ((words[0].length() < 8 || words[0].length() > 8)
									&& (Double.parseDouble(words[2]) < 50 || Double.parseDouble(words[2]) > 100)) {
								alert.setContentText("Grade should be between 50 and 100" + "\n"
										+ "and Seatnumber should be 8 digits");

								alert.show();

							} else if (words[0].length() < 8 || words[0].length() > 8) {
								alert.setContentText("Seat number should be 8 digits / numbers");
								alert.show();
							} else if (Double.parseDouble(words[2]) < 50 || Double.parseDouble(words[2]) > 100) {
								alert.setContentText("Grade should be between 50 and 100");
								alert.show();

							}

							else {
								if (words[1].trim().equalsIgnoreCase("Literary")
										|| words[1].trim().equalsIgnoreCase("Scientific")) {

									int seat = Integer.parseInt(words[0]);
									String branch = words[1].trim();
									double avg = Double.parseDouble(words[2]);

									alert.setContentText(TawjihiDS.add(new Student(seat, branch, avg)));
									alert.show();

								} else {
									alert.setContentText("Branch Doesn't Exist");
									alert.show();
								}
							}
						}
					} else {
						alert.setContentText("You can only insert on " + branchSelected);
						alert.show();
					}
				}
			} catch (NumberFormatException f) {
				alert.setContentText("SeatNum / Grade should be numbers");
				alert.show();
			}
		});
		back.setOnAction(e -> {
			this.close();
			new MainFunctions();
		});
		backToMenu.setOnAction(e -> {
			this.close();
			
		});

		this.setScene(new Scene(ins, screenSize.getWidth(), screenSize.getHeight()));
		this.show();
	}
}
