package com.crud.model;

import com.crud.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public boolean autenticar(String login, String senha) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean autenticado = false;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                autenticado = true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao autenticar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar recursos: " + ex.getMessage());
            }
        }

        return autenticado;
    }
}