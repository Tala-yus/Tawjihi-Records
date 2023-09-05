package gui;

import application.TawjihiDS;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Print extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	Alert alert = new Alert(AlertType.INFORMATION);

	BorderPane root = new BorderPane();

	VBox vb = new VBox(10);

	HBox bhb = new HBox(30);

	HBox hb = new HBox(30);

	HBox hb2 = new HBox(30);

	HBox hb3 = new HBox(30);

	Label label = new Label("Double Linked");
	Label label2 = new Label("Average AVL  ");
	Label label3 = new Label("Seatnum AVL ");

	Button printL = new Button("Print");

	Button printA = new Button("Print");

	Button printA2 = new Button("Print");

	TextArea ta = new TextArea();
	TextArea ta2 = new TextArea();
	TextArea ta3 = new TextArea();
	Button back = new Button("Back");
	Button backToMenu = new Button("Back To Menu");

	public Print() {
		root.setStyle("-fx-background-color:lightblue");

		printL.setPrefSize(200, 50);
		printL.setTextFill(Color.WHITE);
		printL.setStyle("-fx-background-color:DEEPSKYBLUE;");
		printL.setFont(Font.font("Verdana", 20));

		printA.setPrefSize(200, 50);
		printA.setTextFill(Color.WHITE);
		printA.setStyle("-fx-background-color:DEEPSKYBLUE;");
		printA.setFont(Font.font("Verdana", 20));

		printA2.setPrefSize(200, 50);
		printA2.setTextFill(Color.WHITE);
		printA2.setStyle("-fx-background-color:DEEPSKYBLUE;");
		printA2.setFont(Font.font("Verdana", 20));

		label.setFont(Font.font("Verdana", 30));
		label2.setFont(Font.font("Verdana", 30));
		label3.setFont(Font.font("Verdana", 30));

		ta.setFont(Font.font("Verdana", 20));
		ta.setMaxHeight(350);

		ta2.setFont(Font.font("Verdana", 20));
		ta2.setMaxHeight(350);

		ta3.setFont(Font.font("Verdana", 20));
		ta3.setMaxHeight(350);

		back.setPrefSize(300, 50);
		back.setStyle("-fx-background-color:White;");
		back.setFont(Font.font("Verdana", 20));

		backToMenu.setPrefSize(400, 50);
		backToMenu.setStyle("-fx-background-color:White;");
		backToMenu.setFont(Font.font("Verdana", 20));

		hb.getChildren().addAll(printL, label, ta);
		hb2.getChildren().addAll(printA, label2, ta2);
		hb3.getChildren().addAll(printA2, label3, ta3);

		hb.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);
		hb3.setAlignment(Pos.CENTER);

		vb.getChildren().addAll(bhb, hb, hb2, hb3, back, backToMenu);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

		printL.setOnAction(e -> {
			ta.clear();
			ta.setText(TawjihiDS.dll.traverse());

		});

		printA.setOnAction(e -> {
			ta2.clear();
			ta2.setText(TawjihiDS.averageAVL.printTree());
		});

		printA2.setOnAction(e -> {
			ta3.clear();
			ta3.setText(TawjihiDS.seatnumAVL.printTree());
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
