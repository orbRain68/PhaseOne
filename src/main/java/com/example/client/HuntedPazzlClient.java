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

    private static final String SERVER_URL = "http://localhost:8080";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Enter your name:");
        TextField nameField = new TextField();
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            try {
                String playerName = nameField.getText();
                // TODO: Implement game screen and logic (Need client interface)
                createGame(playerName); // Aplication works (Once)
                Stage stage = (Stage) playButton.getScene().getWindow();  // get a handle to the stage
                stage.hide(); // Close window after.

            } catch (IOException e) {
                System.err.println("Not Connected to Server!");;
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, nameField, playButton);

        Scene scene = new Scene(root, 250, 200);
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Hunted Pazzl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void createGame(String playerName) throws IOException {
        // Client Recognise the server
        URL url = new URL(SERVER_URL );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        // Tells the user client's name.
        String jsonInputString = "{\"playerName\": \"" + playerName + "\"}";
        System.out.println(jsonInputString);
      
        // Read from server (Client should take information from server)
        try (InputStream inputStream = connection.getInputStream()) {
            String response = new String(inputStream.readAllBytes());
            System.out.println(response);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}