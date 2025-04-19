package com.academia.dao;

import model.Professor;
import java.sql.*;

public class ProfessorDAO {
    public void cadastrar(Professor prof) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO professores (nome, especialidade) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, prof.getNome());
        stmt.setString(2, prof.getEspecialidade());
        stmt.executeUpdate();
        conn.close();
    }
}
