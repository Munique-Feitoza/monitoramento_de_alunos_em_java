package com.academia.dao;

import model.Turma;
import java.sql.*;

public class TurmaDAO {
    public void cadastrar(Turma turma) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO turmas (nome, professor_id) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, turma.getNome());
        stmt.setInt(2, turma.getProfessorId());
        stmt.executeUpdate();
        conn.close();
    }
}
