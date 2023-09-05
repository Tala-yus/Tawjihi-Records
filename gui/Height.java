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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Height extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane root = new BorderPane();

	VBox vb = new VBox(10);

	HBox hb = new HBox(30);

	HBox hb2 = new HBox(30);

	Label label = new Label("Height");

	Button height = new Button("Average AVL");

	Button height2 = new Button("SeatNumber AVL");

	TextArea ta = new TextArea();

	TextArea ta2 = new TextArea();

	Button back = new Button("Back");

	Button backToMenu = new Button("Return to Menu");

	public Height() {

		root.setStyle("-fx-background-color:lightblue");
		label.setFont(Font.font("Verdana", 30));

		height.setPrefSize(200, 50);
		height.setTextFill(Color.WHITE);
		height.setStyle("-fx-background-color:DEEPSKYBLUE;");
		height.setFont(Font.font("Verdana", 20));

		height2.setPrefSize(200, 50);
		height2.setTextFill(Color.WHITE);
		height2.setStyle("-fx-background-color:DEEPSKYBLUE;");
		height2.setFont(Font.font("Verdana", 20));

		ta.setFont(Font.font("Verdana", 20));
		ta.setMaxHeight(100);

		ta2.setFont(Font.font("Verdana", 20));
		ta2.setMaxHeight(100);

		hb.getChildren().addAll(height, ta);
		hb2.getChildren().addAll(height2, ta2);

		hb.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		vb.getChildren().addAll(label, hb, hb2, back, backToMenu);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

		height.setOnAction(e -> {
			ta.clear();
			ta.setText(String.valueOf(TawjihiDS.averageAVL.height()));
		});

		height2.setOnAction(e -> {
			ta2.clear();
			ta2.setText(String.valueOf(TawjihiDS.seatnumAVL.height()));
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
