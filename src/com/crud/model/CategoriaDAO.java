package com.crud.model;

import com.crud.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {

    public void create(Categoria categoria) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO categorias (nome) VALUES (?)");
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar categoria: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con);
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar statement: " + ex.getMessage());
            }
        }
    }

    public List<Categoria> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM categorias");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler categorias: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar recursos: " + ex.getMessage());
            }
        }

        return categorias;
    }

    public void update(Categoria categoria) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE categorias SET nome = ? WHERE id = ?");
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con);
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar statement: " + ex.getMessage());
            }
        }
    }

    public void delete(Categoria categoria) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM categorias WHERE id = ?");
            stmt.setInt(1, categoria.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con);
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar statement: " + ex.getMessage());
            }
        }
    }
}