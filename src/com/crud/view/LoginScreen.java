package com.crud.view;

import com.crud.model.UsuarioDAO;
import javax.swing.*;

public class LoginScreen extends JFrame {
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JLabel lblMensagemErro;

    public LoginScreen() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setTitle("Login - Sistema CRUD");
        setResizable(false);
        setSize(400, 300);

        getContentPane().setLayout(null);

        lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 50, 100, 30);
        getContentPane().add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(150, 50, 200, 30);
        getContentPane().add(txtLogin);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 100, 100, 30);
        getContentPane().add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 100, 200, 30);
        getContentPane().add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(150, 150, 100, 40);
        btnEntrar.addActionListener(evt -> btnEntrarActionPerformed(evt));
        getContentPane().add(btnEntrar);

        lblMensagemErro = new JLabel("");
        lblMensagemErro.setBounds(50, 200, 300, 30);
        getContentPane().add(lblMensagemErro);
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.autenticar(login, senha)) {
            MainScreen main = new MainScreen();
            main.setVisible(true);
            dispose();
        } else {
            lblMensagemErro.setText("Login ou senha inválidos!");
            lblMensagemErro.setForeground(java.awt.Color.RED);
        }
    }
}