package gui;

import application.Student;
import application.TawjihiDS;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Find extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);
	BorderPane root = new BorderPane();

	VBox vb = new VBox(30);
	HBox hb = new HBox(60);
	HBox hb2 = new HBox(5);

	Label label = new Label("Enter Seat Number for a Record");

	TextField tf = new TextField();

	Button find = new Button("Search");

	Button prev = new Button("Prev");

	Button next = new Button("Next");

	TextArea ta2 = new TextArea();

	Button back = new Button("Back");

	Button backToMenu = new Button("Return to Menu");
	int temp = 0;

	public Find() {

		root.setStyle("-fx-background-color:lightblue");

		label.setFont(Font.font("Verdana", 30));

		tf.setFont(Font.font("Verdana", 20));
		tf.setPrefSize(50, 50);
		tf.setMaxWidth(400);
		tf.setPromptText("Seat Number");

		find.setPrefSize(100, 50);
		find.setFont(Font.font("Verdana", 20));
		find.setStyle("-fx-background-color:White;");

		prev.setPrefSize(100, 50);
		prev.setFont(Font.font("Verdana", 20));
		prev.setStyle("-fx-background-color:White;");

		next.setPrefSize(100, 50);
		next.setFont(Font.font("Verdana", 20));
		next.setStyle("-fx-background-color:White;");

		ta2.setFont(Font.font("Verdana", 20));
		ta2.setMaxHeight(50);

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		hb.getChildren().addAll(prev, find, next);
		hb.setAlignment(Pos.CENTER);

		hb2.getChildren().addAll(ta2);
		hb2.setAlignment(Pos.CENTER);

		vb.getChildren().addAll(label, tf, hb, hb2, back, backToMenu);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

		find.setOnAction(e -> {
			try {

				ta2.clear();

				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("Enter Seatnum Numerically");
					alert.show();
				} else {
					Student s = TawjihiDS.find(Integer.parseInt(tf.getText().trim()));
					if (s != null) {
						ta2.setText(s.toString());
						temp = s.getSeatnum();
					} else {
						alert.setContentText("Student Doesn't Exist");
						alert.show();
					}
				}
			} catch (NumberFormatException h) {
				alert.setContentText("Enter Seatnumber Numerically");
				alert.show();
			}
		});

		prev.setOnAction(e -> {
			try {
				ta2.clear();
				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("Enter Seatnum Numerically");
					alert.show();
				} else {

					Student s = TawjihiDS.findPrev(temp);
					if (s != null) {
						ta2.setText(s.toString());
						temp = s.getSeatnum();
					} else {
						alert.setContentText("Student Doesn't Exist");
						alert.show();
					}
				}
			} catch (NumberFormatException h) {
				alert.setContentText("Enter Seatnumber Numerically");
				alert.show();
			}
		});

		next.setOnAction(e -> {
			try {
				ta2.clear();
				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("Enter Seatnum Numerically");
					alert.show();
				} else {
					Student s = TawjihiDS.findNext(temp);
					if (s != null) {
						ta2.setText(s.toString());
						temp = s.getSeatnum();
					} else {
						alert.setContentText("Student Doesn't Exist");
						alert.show();
					}
				}
			} catch (NumberFormatException h) {
				alert.setContentText("Enter Seatnumber Numerically");
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

		Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());

		this.setScene(scene);

		this.show();

	}

}
