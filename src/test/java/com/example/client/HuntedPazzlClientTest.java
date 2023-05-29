package com.example.client;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HuntedPazzlClientTest extends ApplicationTest {
    private Button button;
    private HuntedPazzlClient huntedPazzlClient;


    @Override
    public void start(Stage stage) {
        stage.show();
        stage.toFront();
    }

    /**
     * Test the functionality of the button click event.
     * The button is retrieved using its ID and is then clicked.
     */
    @Test
    public void testButtonClick() {
        button = find("#Play"); // requires your button to be tagged with setId("Play")
        clickOn(button);   
    }
    
    /**
     * Finds a JavaFX node based on the provided query.
     *
     * @param query the query to locate the JavaFX node
     * @param <T> the type of the JavaFX node to find
     * @return the JavaFX node that matches the query
     */
    public <T extends Node> T find(final String query) {
        /** 
         * TestFX provides many operations to retrieve elements from the loaded GUI.
         * In this method, the JavaFX node is located based on the provided query and returned.
         */
        return lookup(query).query();
    }    
}

