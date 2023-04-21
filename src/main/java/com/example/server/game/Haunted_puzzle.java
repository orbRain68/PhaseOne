package com.example.server.game;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;


public class Haunted_puzzle  implements ActionListener{
    
    private static JFrame frame;
    private static JButton button, btn, btn2;
    private static JPanel panel,btnPanel;
    private static BufferedImage myUnsolved, mySolved;
    private static JLabel picLabel;

    /**
     * @param playerNameString get Name from Client.
     * @throws IOException
     */
    private static void Game(String playerNameString) throws IOException{
        frame = createFrame("Haunted Puzzle Game: " + playerNameString); // Name of puzzle and player.
        myUnsolved = ImageIO.read(new File("C:/Users/me/OneDrive - University of Prince Mugrin/Construction/Project_Phase_One/demo/src/main/resources/com/example/server/game/Unsolved.jpg"));
        mySolved = ImageIO.read(new File("C:/Users/me/OneDrive - University of Prince Mugrin/Construction/Project_Phase_One/demo/src/main/resources/com/example/server/game/Unsolved.jpg"));
        picLabel = new JLabel(new ImageIcon(myUnsolved)); // Set unsolved state.

        
        btnPanel = createBtnPanel();
        panel = createGamePanel();

        btn = createButton("Solve");
        btn.setLocation(208, 10);
        btnPanel.add(btn);
        btn.addActionListener(new Haunted_puzzle(){
            public void actionPerformed(ActionEvent arg0) {
                
                if (arg0.getSource() == btn){
                    JOptionPane.showMessageDialog(frame, "this is ");
                    // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            }
        });
        
        btn2 = createButton("Blank");
        btn2.setLocation(303, 10);
        btnPanel.add(btn2);
        btn2.addActionListener(new Haunted_puzzle(){
            public void actionPerformed(ActionEvent arg0) {
                
                if (arg0.getSource() == btn2){
                    JOptionPane.showMessageDialog(frame, "this is ... ");
                    // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            }
        });
        frame.add(btnPanel);        
        
        panel.add(picLabel);
        frame.add(panel);
        frame.setVisible(true); // Visible.
    }
    /**
     * @return frame
     * Sepreate mothed: that create frame along with settings.
     */
    private static JFrame createFrame(String gameNameString) { 
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame(gameNameString);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 626, 790);
        frame.setResizable(false);
        frame.setLayout(null);
        return frame;
    }
    /**
     * @param texString
     * @return
     * Sepreate mothed: That create Buttons along with settings. 
     */
    private static JButton createButton(String texString) {    
        button = new JButton(texString, null);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setSize(90, 20);

        return button;
    }
    /**
     * @return
     * Sepreate mothed: That create a panel which holds game screan.
     */
    private static JPanel createGamePanel() {
        panel = new JPanel();
        panel.setSize(616, 750);
        panel.setLocation(0, 40); //15 40
        panel.setBackground(Color.BLACK);
        return panel; 
    }
    /**
     * @return
     * Sepreate mothed: That create a panel which holds action buttons.
     */
    private static JPanel createBtnPanel() {
        panel = new JPanel();
        panel.setSize(616, 40);
        return panel;
    }

    public static void main( String arge[]) throws IOException{
    Game("Player"); // testing the game

    }
    public static void stratGame(String player) throws IOException {
        Game(player);
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
    
}
