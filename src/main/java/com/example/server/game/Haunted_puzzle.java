package com.example.server.game;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Haunted_puzzle
 * 
 */
public class Haunted_puzzle extends Application {
    
    public void start(Stage primaryStage) throws Exception {
        // TODO: Implement game screen
         
        Label label = new Label("Game");
        TextField nameField = new TextField("Play the game here");
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            String playerName = nameField.getText();
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, nameField, playButton);

        Scene scene = new Scene(root, 500, 350);
        primaryStage.setTitle("Hunted Pazzl");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
public static void main(String[] args) {
    launch(args);
}

}