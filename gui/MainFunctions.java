package gui;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainFunctions extends Stage {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	BorderPane root = new BorderPane();
	VBox vb = new VBox(40);

	Label label = new Label(Menu.s.trim());
	Button insert = new Button("Insert a Record");
	Button update = new Button("Update a Record");
	Button delete = new Button("Delete a Record");
	Button find = new Button("Find a Record");
	Button print = new Button("Print");
	Button hei = new Button("Height");
	Button getA = new Button("Get All");
	Button back = new Button("BACK");

	MainFunctions() {
		//styles
		label.setFont(Font.font("Verdana", 30));

		insert.setPrefSize(300, 50);
		insert.setTextFill(Color.WHITE);
		insert.setStyle("-fx-background-color:DEEPSKYBLUE;");
		insert.setFont(Font.font("Verdana", 20));

		update.setPrefSize(300, 50);
		update.setTextFill(Color.WHITE);
		update.setStyle("-fx-background-color:DEEPSKYBLUE;");
		update.setFont(Font.font("Verdana", 20));

		delete.setPrefSize(300, 50);
		delete.setTextFill(Color.WHITE);
		delete.setStyle("-fx-background-color:DEEPSKYBLUE;");
		delete.setFont(Font.font("Verdana", 20));

		find.setPrefSize(300, 50);
		find.setTextFill(Color.WHITE);
		find.setStyle("-fx-background-color:DEEPSKYBLUE;");
		find.setFont(Font.font("Verdana", 20));

		print.setPrefSize(300, 50);
		print.setTextFill(Color.WHITE);
		print.setStyle("-fx-background-color:DEEPSKYBLUE;");
		print.setFont(Font.font("Verdana", 20));

		hei.setPrefSize(300, 50);
		hei.setTextFill(Color.WHITE);
		hei.setStyle("-fx-background-color:DEEPSKYBLUE;");
		hei.setFont(Font.font("Verdana", 20));

		getA.setPrefSize(300, 50);
		getA.setTextFill(Color.WHITE);
		getA.setStyle("-fx-background-color:DEEPSKYBLUE;");
		getA.setFont(Font.font("Verdana", 20));

		back.setPrefSize(300, 50);
		back.setTextFill(Color.WHITE);
		back.setStyle("-fx-background-color:DEEPSKYBLUE;");
		back.setFont(Font.font("Verdana", 20));

		vb.getChildren().addAll(label, insert, update, delete, find, print, hei, getA, back);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

		insert.setOnAction(e -> {
			this.close(); //close this stage
			new Insert(); //calls insert stage
		});

		update.setOnAction(e -> {
			this.close();
			new Update(); //calls update stage
		});
		delete.setOnAction(e -> {
			this.close();
			new Delete(); //calls delete stage

		});
		find.setOnAction(e -> {
			this.close();
			new Find(); //calls find stage
		});
		print.setOnAction(e -> {
			this.close();
			new Print(); //calls print stage

		});
		hei.setOnAction(e -> {
			this.close();
			new Height(); //calls height stage

		});
		getA.setOnAction(e -> {
			this.close();
			new GetAll(); //calls get all stage
		});
		back.setOnAction(e -> {
			this.close();
			

		});
		this.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
		this.show();
	}
}
