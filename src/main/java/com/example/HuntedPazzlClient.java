package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
                int gameId = createGame(playerName);
                // TODO: Implement game screen and logic

                

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, nameField, playButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hunted Pazzl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int createGame(String playerName) throws IOException {
        URL url = new URL(SERVER_URL + "/games");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        String jsonInputString = "{\"playerName\": \"" + playerName + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (InputStream inputStream = connection.getInputStream()) {
            String response = new String(inputStream.readAllBytes());
            return Integer.parseInt(response);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}