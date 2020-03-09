package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame f1 = new JFrame();
        f1.setBounds(0, 0, 1350, 720);
        f1.setTitle("Game");
        f1.setResizable(false);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePlay gamePlay = new GamePlay();
        f1.add(gamePlay);
        f1.setVisible(true);
    }
}
