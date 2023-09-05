package gui;

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

public class Delete extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane deleteR = new BorderPane();

	VBox deleteV = new VBox(30);

	Label deleteL = new Label("Enter Seat Number for a Record");

	TextField deleteTF = new TextField();

	Button delete = new Button("Delete");

	TextArea deleteTA = new TextArea();

	Button back = new Button("Back");

	Button backToMenu = new Button("Return to Menu");


	Delete() {

		deleteR.setStyle("-fx-background-color:lightblue");

		deleteL.setFont(Font.font("Verdana", 30));

		deleteTF.setFont(Font.font("Verdana", 20));
		deleteTF.setPrefSize(50, 50);
		deleteTF.setMaxWidth(400);
		deleteTF.setPromptText("Seat Number");

		delete.setPrefSize(100, 50);
		delete.setFont(Font.font("Verdana", 20));
		delete.setStyle("-fx-background-color:White;");

		deleteTA.setFont(Font.font("Verdana", 20));
		deleteTA.setPrefSize(50, 50);
		deleteTA.setMaxWidth(600);

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		deleteV.getChildren().addAll(deleteL, deleteTF, delete, deleteTA, back, backToMenu);
		deleteV.setAlignment(Pos.CENTER);
		deleteR.setCenter(deleteV);

		delete.setOnAction(e -> {
			try {
				if (deleteTF.getText().trim().isEmpty()) {
					alert.setContentText("Please Enter a Seat Number");
					alert.show();
				} else {
					deleteTA.setText(TawjihiDS.delete(Integer.parseInt(deleteTF.getText().trim())));
				}
			} catch (NumberFormatException f) {
				alert.setContentText("SeatNum should be numbers");
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
		Scene scene = new Scene(deleteR, screenSize.getWidth(), screenSize.getHeight());
		this.setScene(scene);
		this.show();
	}
}
