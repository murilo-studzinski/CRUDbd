package com.crud.view;

import com.crud.model.Categoria;
import com.crud.model.CategoriaDAO;
import com.crud.model.Produto;
import com.crud.model.ProdutoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProdutoScreen extends JFrame {
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblPreco;
    private JLabel lblCategoria;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JComboBox<Categoria> cbxCategoria;
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnNovo;
    private JTable tblProdutos;
    private JScrollPane scrollPane;

    public ProdutoScreen() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Categoria");
        loadCategorias();
        readJTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        setResizable(false);
        setSize(800, 550);

        getContentPane().setLayout(null);

        lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 50, 25);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(70, 20, 100, 25);
        txtId.setEditable(false);
        getContentPane().add(txtId);

        lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 50, 25);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(70, 60, 200, 25);
        getContentPane().add(txtNome);

        lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 100, 50, 25);
        getContentPane().add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(70, 100, 200, 25);
        getContentPane().add(txtPreco);

        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 140, 80, 25);
        getContentPane().add(lblCategoria);

        cbxCategoria = new JComboBox<>();
        cbxCategoria.setBounds(100, 140, 170, 25);
        getContentPane().add(cbxCategoria);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 180, 80, 35);
        btnSalvar.addActionListener(evt -> btnSalvarActionPerformed(evt));
        getContentPane().add(btnSalvar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(110, 180, 80, 35);
        btnAtualizar.addActionListener(evt -> btnAtualizarActionPerformed(evt));
        getContentPane().add(btnAtualizar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(200, 180, 80, 35);
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));
        getContentPane().add(btnExcluir);

        btnNovo = new JButton("Novo");
        btnNovo.setBounds(290, 180, 80, 35);
        btnNovo.addActionListener(evt -> btnNovoActionPerformed(evt));
        getContentPane().add(btnNovo);

        tblProdutos = new JTable();
        scrollPane = new JScrollPane(tblProdutos);
        scrollPane.setBounds(20, 230, 750, 270);
        getContentPane().add(scrollPane);

        tblProdutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (tblProdutos.getSelectedRow() != -1) {
                    txtId.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString());
                    txtNome.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1).toString());
                    txtPreco.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 2).toString());
                    String categoriaNome = tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 3).toString();

                    for (int i = 0; i < cbxCategoria.getItemCount(); i++) {
                        Categoria c = cbxCategoria.getItemAt(i);
                        if (c.getNome().equals(categoriaNome)) {
                            cbxCategoria.setSelectedItem(c);
                            break;
                        }
                    }
                }
            }
        });
    }

    public void loadCategorias() {
        CategoriaDAO cdao = new CategoriaDAO();
        List<Categoria> categorias = cdao.read();
        cbxCategoria.removeAllItems();

        for (Categoria c : categorias) {
            cbxCategoria.addItem(c);
        }
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
        modelo.setNumRows(0);

        ProdutoDAO pdao = new ProdutoDAO();
        for (Produto p : pdao.read()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getCategoria().getNome()
            });
        }
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Produto produto = new Produto();
            ProdutoDAO dao = new ProdutoDAO();

            produto.setNome(txtNome.getText());
            produto.setPreco(Double.parseDouble(txtPreco.getText()));
            produto.setCategoria((Categoria) cbxCategoria.getSelectedItem());

            dao.create(produto);

            readJTable();
            txtNome.setText("");
            txtPreco.setText("");
            txtId.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Preço inválido! Digite um número.");
        }
    }

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblProdutos.getSelectedRow() != -1) {
            try {
                Produto produto = new Produto();
                ProdutoDAO dao = new ProdutoDAO();

                produto.setId((int) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0));
                produto.setNome(txtNome.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                produto.setCategoria((Categoria) cbxCategoria.getSelectedItem());

                dao.update(produto);

                readJTable();
                txtNome.setText("");
                txtPreco.setText("");
                txtId.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Preço inválido! Digite um número.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para atualizar.");
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblProdutos.getSelectedRow() != -1) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente excluir este produto?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Produto produto = new Produto();
                ProdutoDAO dao = new ProdutoDAO();

                produto.setId((int) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0));
                dao.delete(produto);

                readJTable();
                txtNome.setText("");
                txtPreco.setText("");
                txtId.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
        }
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        txtId.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        cbxCategoria.setSelectedIndex(-1);
    }
}