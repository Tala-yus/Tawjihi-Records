package gui;

import java.io.File;
import java.io.FileNotFoundException;

import application.Controller;
import application.TawjihiDS;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends Stage {

	Alert alert = new Alert(AlertType.INFORMATION);
	BorderPane rootS = new BorderPane();
	HBox hbR = new HBox(10);
	HBox hb = new HBox(10);
	VBox vb = new VBox(30);
	TextField path = new TextField();
	Button choose = new Button("Choose File");
	Button read = new Button("Read");
	static CheckBox sci = new CheckBox("Scientific");
	static CheckBox lit = new CheckBox("Literary");
	static String s = ""; // to save the branch that get selected in the check box

	public Menu() {
		//styles 
		path.setFont(Font.font("Verdana", 20));
		path.setPrefSize(600, 50);
		path.setPromptText("Path");

		choose.setPrefSize(300, 50);
		choose.setTextFill(Color.WHITE);
		choose.setStyle("-fx-background-color:DEEPSKYBLUE;");
		choose.setFont(Font.font("Verdana", 20));

		read.setPrefSize(300, 50);
		read.setTextFill(Color.WHITE);
		read.setStyle("-fx-background-color:DEEPSKYBLUE;");
		read.setFont(Font.font("Verdana", 20));

		sci.setPrefSize(200, 50);
		sci.setTextFill(Color.WHITE);
		sci.setStyle("-fx-background-color:	Black;");
		sci.setFont(Font.font("Verdana", 20));
		sci.setAlignment(Pos.CENTER);
		sci.setBorder(Border.stroke(Paint.valueOf("Black")));

		lit.setPrefSize(200, 50);
		lit.setTextFill(Color.WHITE);
		lit.setStyle("-fx-background-color:Black;");
		lit.setFont(Font.font("Verdana", 20));
		lit.setAlignment(Pos.CENTER);
		lit.setBorder(Border.stroke(Paint.valueOf("Black")));

		hbR.getChildren().addAll(read, path, choose);
		hbR.setAlignment(Pos.CENTER);

		hb.getChildren().addAll(sci, lit);
		hb.setAlignment(Pos.CENTER);

		vb.getChildren().addAll(hbR, hb);
		vb.setAlignment(Pos.CENTER);

		rootS.setCenter(vb);

		//setting the scene 
		this.setScene(new Scene(rootS, 800, 600));
		this.show();

		choose.setOnAction(e -> {
			try {
				if (!path.getText().trim().isEmpty()) //if the text field isn't clear - clear it 
					path.clear();

				//file chooser stage
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose File");
				File selectedFile = fileChooser.showOpenDialog(this);
				path.setText(selectedFile.getPath()); //fill the path in the text field
			} catch (Exception c) { //if the user doesn't choose a file show an alert 
				alert.setContentText("Choose File");
				alert.show();
			}
		});

		read.setOnAction(e -> {
			TawjihiDS.clear(); // clear all the trees and the double linked list in case they were filled before
			String st = ""; 
			boolean read = false; // flag to determine whether at least one branch was selected or not,
			                      //false = not selected at all, true = selected
			if (path.getText().isEmpty()) {
				alert.setContentText("Path is Empty");
				alert.show();
			} else {

				try {
					if (sci.isSelected() && !lit.isSelected()) {
						s = sci.getText().trim();
						read = true; // assign true because one branch was selected
					} else if (!sci.isSelected() && lit.isSelected()) {
						s = lit.getText().trim(); //assign the check box content to the s string 
						read = true; // assign true because one branch was selected
					} else if (sci.isSelected() && lit.isSelected()) {
						s = sci.getText().trim() + " - " + lit.getText().trim();
						read = true; // assign true because at least one branch was selected
					} else {
						alert.setContentText("Please Select a Branch"); //if no branch was selected
						alert.show();
					}
					if (read == true) { //if a branch was selected
						if (s.equalsIgnoreCase(sci.getText().trim() + " - " + lit.getText().trim())) {//if both branches were selected 
							st = Controller.FileReaderAll(path.getText().trim()); //read all records
						} else {
							st = Controller.FileReader(path.getText().trim(), s); //read records based on branch
						}

						alert.setContentText(st); 
						new MainFunctions(); //calls the main operations stage
						alert.show();
					}
				} catch (FileNotFoundException e1) {
					alert.setContentText("File Not Found");
					alert.show();
				}

			}
		});

	}

}
