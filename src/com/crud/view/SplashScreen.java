package com.crud.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame {
    private JLabel lblTitle;
    private JProgressBar progressBar;
    private JLabel lblLoading;

    public SplashScreen() {
        initComponents();
        this.setLocationRelativeTo(null);
        startTimer();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(500, 300);

        getContentPane().setLayout(null);

        lblTitle = new JLabel("Sistema CRUD Java");
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        lblTitle.setBounds(80, 50, 350, 50);
        getContentPane().add(lblTitle);

        progressBar = new JProgressBar();
        progressBar.setBounds(50, 150, 400, 30);
        getContentPane().add(progressBar);

        lblLoading = new JLabel("Carregando...");
        lblLoading.setBounds(200, 190, 100, 30);
        getContentPane().add(lblLoading);
    }

    private void startTimer() {
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen login = new LoginScreen();
                login.setVisible(true);
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}