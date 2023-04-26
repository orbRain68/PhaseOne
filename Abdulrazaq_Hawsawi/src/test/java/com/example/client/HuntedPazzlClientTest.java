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

    @Test
    public void testButtonClick(){
        button = find("#Play"); // requires your button to be tagged with setId("button")
        clickOn(button);   
    }
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }    
}
