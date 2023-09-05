package application;

import javafx.application.Application;

import javafx.stage.Stage;

import gui.*;

// Tala Maraaba 1190126
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			new Menu(); // calls the menu (file reader) stage
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
