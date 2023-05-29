package com.example.server.game;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

/**
 * The Haunted_puzzle class represents a game window for a haunted puzzle game.
 * It allows players to solve or reset the puzzle and displays the puzzle image accordingly.
 */
public class Haunted_puzzle implements ActionListener {

    private static JFrame frame;
    private static JButton button, btn, btn2;
    private static JPanel panel, btnPanel;
    private static BufferedImage myUnsolved, mySolved;
    private static JLabel picLabel;
    private static JLayeredPane layeredPane, gameLayeredPane;

    /**
     * Creates a new game window with the player's name.
     *
     * @param playerNameString the name of the player
     * @throws IOException if there is an error reading the puzzle images
     */
    private static void Game(String playerNameString) throws IOException {
        // Create the game window
        frame = createFrame("Haunted Puzzle Game: " + playerNameString);

        // Load the puzzle images
        myUnsolved = ImageIO.read(new File("C:/Users/me/OneDrive - University of Prince Mugrin/Construction/Project_Phase_One/demo/src/main/resources/com/example/server/game/Unsolved.jpg"));
        mySolved = ImageIO.read(new File("C:/Users/me/OneDrive - University of Prince Mugrin/Construction/Project_Phase_One/demo/src/main/resources/com/example/server/game/Solved.jpg"));

        // Set the initial puzzle state to unsolved
        picLabel = new JLabel(new ImageIcon(myUnsolved));

        // Create the button panel, layered pane, and game panel
        btnPanel = createBtnPanel();
        gameLayeredPane = createGameLayeredPane();
        panel = createGamePanel();

        // Create the "Solve" button and add it to the button panel
        btn = createButton("Solve");
        btn.setLocation(208, 10);
        btnPanel.add(btn);
        // Add an action listener to the "Solve" button that shows the solved puzzle image
        btn.addActionListener(new Haunted_puzzle() {
            public void actionPerformed(ActionEvent arg0) {
                // Change the puzzle image to the solved state
                picLabel = new JLabel(new ImageIcon(mySolved));
                panel = createGamePanel();

                // Update the game panel with the solved image
                gameLayeredPane.removeAll();
                panel.add(picLabel);
                gameLayeredPane.add(panel);
                gameLayeredPane.repaint();
                gameLayeredPane.revalidate();
            }
        });

        // Create the "Blank" button and add it to the button panel
        btn2 = createButton("Blank");
        btn2.setLocation(303, 10);
        btnPanel.add(btn2);
        // Add an action listener to the "Blank" button that shows the unsolved puzzle image
        btn2.addActionListener(new Haunted_puzzle() {
            public void actionPerformed(ActionEvent arg0) {
                // Change the puzzle image to the unsolved state
                picLabel = new JLabel(new ImageIcon(myUnsolved));
                panel = createGamePanel();

                // Update the game panel with the unsolved image
                gameLayeredPane.removeAll();
                panel.add(picLabel);
                gameLayeredPane.add(panel);
                gameLayeredPane.repaint();
                gameLayeredPane.revalidate();
            }
        });

        frame.add(btnPanel);
        panel.add(picLabel);
        gameLayeredPane.add(panel);
        frame.add(gameLayeredPane);
        frame.setVisible(true); // Visible
}    /**
* Creates a new game window with the given name.
*
* @param gameNameString the name of the game
* @return the created JFrame
*/
private static JFrame createFrame(String gameNameString) {
   // Set the look and feel of the JFrame to the system's default
   JFrame.setDefaultLookAndFeelDecorated(true);

   // Create a new JFrame with the given name
   frame = new JFrame(gameNameString);

   // Set the default close operation of the JFrame to exit the application
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   // Set the size of the JFrame to 626x790 pixels
   frame.setBounds(0, 0, 626, 790);

   // Set the location of the JFrame to the center of the screen
   frame.setLocationRelativeTo(null);

   // Set the JFrame to not be resizable
   frame.setResizable(false);

   // Set the layout of the JFrame to null
   frame.setLayout(null);

   // Return the created JFrame
   return frame;
}

/**
* Creates a new JButton with the given text and default settings.
*
* @param buttonText the text displayed on the button
* @return the created JButton
*/
private static JButton createButton(String buttonText) {
   // Create a new JButton with the given text and null icon
   button = new JButton(buttonText, null);

   // Set the size of the JButton to 90x20 pixels
   button.setSize(90, 20);

   // Return the created JButton
   return button;
}

/**
* Creates a new JLayeredPane that acts as a container for the game.
*
* @return the created JLayeredPane
*/
private static JLayeredPane createGameLayeredPane() {
   // Create a new JLayeredPane
   layeredPane = new JLayeredPane();

   // Set the bounds of the JLayeredPane to 0, 40 and 616x750 pixels
   layeredPane.setBounds(0, 40, 616, 750);

   // Set the layout of the JLayeredPane to null
   layeredPane.setLayout(null);

   // Return the created JLayeredPane
   return layeredPane;
}

/**
* Creates a new JPanel that holds the game screen.
*
* @return the created JPanel
*/
private static JPanel createGamePanel() {
   // Create a new JPanel
   panel = new JPanel();

   // Set the size of the JPanel to 616x720 pixels
   panel.setSize(616, 720);

   // Set the background color of the JPanel to black
   panel.setBackground(Color.BLACK);

   // Return the created JPanel
   return panel;
}

/**
* Creates a new JPanel that holds the action buttons.
*
* @return the created JPanel
*/
private static JPanel createBtnPanel() {
   // Create a new JPanel
   panel = new JPanel();

   // Set the size of the JPanel to 616x40 pixels
   panel.setSize(616, 40);

   // Return the created JPanel
   return panel;
}

/**
* Starts the game with the default player name "Player".
*
* @param args command line arguments (not used)
* @throws IOException if there is an error reading the puzzle images
*/
    
    public static void main( String arge[]) throws IOException{
        // Start the game with the default player name "Player"
        Game("Player");
    }
    
    /**
     * @param player
     * @throws IOException
     * Separate method: Start the game with the given player name.
     */
    public static void stratGame(String player) throws IOException {
        // Start the game with the given player name
        Game(player);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // This method is not currently used, but is required to implement the ActionListener interface
    }
}