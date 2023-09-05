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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GetAll extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane root = new BorderPane();

	VBox vb = new VBox(30);

	Label label = new Label("Enter Grade");

	TextField tf = new TextField();

	Button get = new Button("Get");

	TextArea ta = new TextArea();

	Button back = new Button("Back");

	Button backToMenu = new Button("Return to Menu");

	GetAll() {
		root.setStyle("-fx-background-color:lightblue");

		label.setFont(Font.font("Verdana", 30));

		tf.setFont(Font.font("Verdana", 20));
		tf.setPrefSize(50, 50);
		tf.setMaxWidth(400);
		tf.setPromptText("Seat Number");

		get.setPrefSize(100, 50);
		get.setFont(Font.font("Verdana", 20));
		get.setStyle("-fx-background-color:White;");

		ta.setFont(Font.font("Verdana", 20));
		ta.setMaxHeight(350);

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		vb.getChildren().addAll(label, tf, get, ta, back, backToMenu);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

		get.setOnAction(e -> {
			try {
				if (tf.getText().trim().isEmpty()) {
					alert.setContentText("PLease Enter a Grade");
					alert.show();
				} else {
					if (Double.parseDouble(tf.getText().trim()) < 50 || Double.parseDouble(tf.getText().trim()) > 100) {
						alert.setContentText("Grade should be between 50 and 100");
						alert.show();

					} else {
						ta.setText(TawjihiDS.getAllByGrade(Double.parseDouble(tf.getText().trim())));

					}
				}
			} catch (NumberFormatException f) {
				alert.setContentText("Grade should be in numbers");
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
