package com.academia.dao;

import model.Equipe;
import java.sql.*;

public class EquipeDAO {
    public void cadastrar(Equipe equipe) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO equipes (nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, equipe.getNome());
        stmt.executeUpdate();
        conn.close();
    }
}
