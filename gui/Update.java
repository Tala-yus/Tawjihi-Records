package gui;

import application.Student;
import application.TawjihiDS;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Update extends Stage {

	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane upd = new BorderPane();

	VBox vb = new VBox(40);

	HBox hb = new HBox(40);

	Label label = new Label("Update");
	TextField tf = new TextField();
	TextField tf2 = new TextField();
	TextField tf3 = new TextField();
	Button get = new Button("Get");
	Button update = new Button("Update");
	Button back = new Button("Back");
	Button backToMenu = new Button("Back To Menu");
	String seat = "";
	String br = "";
	String avg = "";
	String branchSelected = Menu.s.trim();

	Update() {

		upd.setStyle("-fx-background-color:lightblue");
		label.setFont(Font.font("Verdana", 30));

		tf.setFont(Font.font("Verdana", 20));
		tf.setPrefSize(50, 50);
		tf.setMaxWidth(400);
		tf.setPromptText("Seat Number");

		tf2.setFont(Font.font("Verdana", 20));
		tf2.setPrefSize(50, 50);
		tf2.setMaxWidth(400);
		tf2.setPromptText("Branch");

		tf3.setFont(Font.font("Verdana", 20));
		tf3.setPrefSize(50, 50);
		tf3.setMaxWidth(400);
		tf3.setPromptText("Grade");

		get.setPrefSize(300, 50);
		get.setStyle("-fx-background-color:White;");
		get.setFont(Font.font("Verdana", 20));

		update.setPrefSize(300, 50);
		update.setStyle("-fx-background-color:White;");
		update.setFont(Font.font("Verdana", 20));

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		vb.getChildren().addAll(label, tf, tf2, tf3, get, update);
		vb.setAlignment(Pos.CENTER);
		upd.setCenter(vb);

		hb.getChildren().addAll(back, backToMenu);
		hb.setAlignment(Pos.CENTER);

		upd.setBottom(hb);

		get.setOnAction(e -> {
			try {
				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("Please enter a seat number ");
					alert.show();
				} else {
					Student s = TawjihiDS.find(Integer.parseInt(tf.getText().trim()));
					if (s == null) {
						alert.setContentText("Student Doesn't Exist");
						alert.show();
					} else {
						alert.setContentText(s.toString());
						alert.show();
						tf.setText(String.valueOf(s.getSeatnum()));
						tf2.setText(s.getBranch());
						tf3.setText(String.valueOf(s.getAverage()));
						seat = tf.getText().trim();
						br = tf2.getText().trim();
						avg = tf3.getText().trim();
					}
				}
			} catch (NumberFormatException g) {
				alert.setContentText("Enter Proper Seatnumber");
				alert.show();
			}
		});

		update.setOnAction(e -> {
			try {

				if (tf.getText().trim().isEmpty() || tf2.getText().trim().isEmpty() || tf3.getText().trim().isEmpty()) {
					alert.setContentText("Please Fill All Fields");
					alert.show();
				} else {
					if (!tf.getText().trim().equals(seat)) {
						alert.setContentText("You can't update seat number");
						alert.show();
						tf.setText(seat);
					} else if (tf2.getText().trim().equals(br) && tf3.getText().trim().equals(avg)) {
						alert.setContentText("No changes were made");
						alert.show();
					} else if (!tf2.getText().trim().equalsIgnoreCase(branchSelected) && !branchSelected
							.equalsIgnoreCase((Menu.sci.getText().trim() + " - " + Menu.lit.getText().trim()).trim())) {
						alert.setContentText("You can only Update On " + branchSelected);
						alert.show();

					} else if (Double.parseDouble(tf3.getText().trim()) < 50
							|| Double.parseDouble(tf3.getText().trim()) > 100) {
						alert.setContentText("Grade should be between 50 and 100");
						alert.show();

					} else {
						if (tf2.getText().trim().equalsIgnoreCase("Literary")
								|| tf2.getText().trim().equalsIgnoreCase("Scientific")) {

							alert.setContentText(TawjihiDS.update(Integer.parseInt(tf.getText().trim()),
									tf2.getText().trim(), Double.parseDouble(tf3.getText().trim())));
							alert.show();
						} else {

							alert.setContentText("Branch Doesn't Exist");
							alert.show();

						}
					}
				}

			} catch (NumberFormatException s) {

				alert.setContentText("Enter Grade in numbers");
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
		Scene scene = new Scene(upd, screenSize.getWidth(), screenSize.getHeight());
		this.setScene(scene);
		this.show();
	}

}
