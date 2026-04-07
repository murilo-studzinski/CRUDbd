package com.crud.view;

import javax.swing.*;

public class MainScreen extends JFrame {
    private JLabel lblTitle;
    private JButton btnCategorias;
    private JButton btnProdutos;

    public MainScreen() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Principal - Sistema CRUD");
        setResizable(false);
        setSize(500, 400);

        getContentPane().setLayout(null);

        lblTitle = new JLabel("Bem-vindo ao Sistema CRUD!");
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        lblTitle.setBounds(80, 50, 350, 50);
        getContentPane().add(lblTitle);

        btnCategorias = new JButton("Cadastro de Categorias");
        btnCategorias.setBounds(100, 150, 300, 50);
        btnCategorias.addActionListener(evt -> btnCategoriasActionPerformed(evt));
        getContentPane().add(btnCategorias);

        btnProdutos = new JButton("Cadastro de Produtos");
        btnProdutos.setBounds(100, 250, 300, 50);
        btnProdutos.addActionListener(evt -> btnProdutosActionPerformed(evt));
        getContentPane().add(btnProdutos);
    }

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {
        CategoriaScreen categoriaScreen = new CategoriaScreen();
        categoriaScreen.setVisible(true);
    }

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {
        ProdutoScreen produtoScreen = new ProdutoScreen();
        produtoScreen.setVisible(true);
    }
}