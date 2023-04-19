package com.example.server.game;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Haunted_puzzle  implements ActionListener{

    private static JButton btn, btn2;
    private static JFrame frame;

    public static void Client(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 500, 500);
        Container container = frame.getContentPane(); 
        container.setLayout(null);

         btn = new JButton("solve");
        btn.setBounds(150, 10, 200, 40);
        container.add(btn);
        btn.addActionListener(new Haunted_puzzle(){
            public void actionPerformed(ActionEvent arg0) {

                if (arg0.getSource() == btn){
                    JOptionPane.showMessageDialog(frame, "this is ");
               // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            }
        });

        frame.setVisible(true);

         btn2 = new JButton("unsolve");
        btn2.setBounds(150, 70, 200, 40);
        container.add(btn2);
        btn2.addActionListener(new Haunted_puzzle(){
            public void actionPerformed(ActionEvent arg0) {

                if (arg0.getSource() == btn2){
                    JOptionPane.showMessageDialog(frame, "this is ");
               // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            }
        });


        frame.setVisible(true);

    }

    public static void main( String arge[]){
    Client();

    }


    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
    
}
