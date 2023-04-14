package com.example.client;

import java.io.IOException;
import java.io.InputStream;
import io.netty.buffer.ByteBufInputStream; // new
import java.io.OutputStream;
import io.netty.buffer.ByteBufOutputStream; // new
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HuntedPazzlClient extends Application {

    private static final String SERVER_URL = "http://localhost:8080";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Enter your name:");
        TextField nameField = new TextField();
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            try {
                String playerName = nameField.getText();
                createGame(playerName); // Aplication works (Once)
                // Close window after.
                // TODO: Implement game screen and logic (Need client interface)

                

            } catch (IOException e) {
                System.err.println("Not Connected to Server!");;
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, nameField, playButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hunted Pazzl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Need rework
    private void createGame(String playerName) throws IOException {
        URL url = new URL(SERVER_URL );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        // writing to server (Client doesn't write to server)
        // This code tells the server client's name.
        String jsonInputString = "{\"playerName\": \"" + playerName + "\"}";
        System.out.println(jsonInputString);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");

        }
      
        // Read from server (Client should take information from server)
        // It should request the game from server.
        try (InputStream inputStream = connection.getInputStream()) {
            String response = new String(inputStream.readAllBytes());
            System.out.println(response);
        
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}