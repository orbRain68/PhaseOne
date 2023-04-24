package com.example.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HuntedPazzlClient extends Application {
    // The URL of the server that the client will connect to
    private static final String SERVER_URL = "http://localhost:8080";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create UI elements: a label, a text field, and a button
        Label label = new Label("Enter your name:");
        TextField nameField = new TextField();
        Button playButton = new Button("Play");

        // Set up an event listener for the button click
        playButton.setOnAction(event -> {
            try {
                String playerName = nameField.getText();
                // TODO: Implement game screen and logic (Need client interface)
                // Call the createGame method to connect to the server
                createGame(playerName);
                // Close the window after the game starts
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.close();

            } catch (IOException e) {
                // Handle the case where the client cannot connect to the server
                System.err.println("Not Connected to Server!");;
            }
        });

        // Create a vertical box layout to hold the UI elements
        VBox root = new VBox();
        root.getChildren().addAll(label, nameField, playButton);

        // Set up the scene with the root layout and a fixed size
        Scene scene = new Scene(root, 250, 200);
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Hunted Pazzle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to connect to the server and send the client's name
    private void createGame(String playerName) throws IOException {
        // Create a URL object and open a connection to the server
        URL url = new URL(SERVER_URL );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Content-Base", playerName);
        connection.setDoOutput(true);

        // Create a JSON string with the client's name and send it to the server
        String jsonInputString = "{\"playerName\": \"" + playerName + "\"}";
        System.out.println(jsonInputString);
        

        // Read the server's response and print it to the console
        try (InputStream inputStream = connection.getInputStream()) {
            String response = new String(inputStream.readAllBytes());
            System.out.println(response);
        }
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}