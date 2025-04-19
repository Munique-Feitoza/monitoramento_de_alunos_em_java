package com.academia.dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void cadastrar(Aluno aluno) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO alunos (nome, idade, faixa_id, equipe_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, aluno.getNome());
        stmt.setInt(2, aluno.getIdade());
        stmt.setInt(3, aluno.getFaixaId());
        stmt.setInt(4, aluno.getEquipeId());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Aluno> listar() throws SQLException {
        List<Aluno> lista = new ArrayList<>();
        Connection conn = ConexaoBD.conectar();
        String sql = "SELECT * FROM alunos";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Aluno a = new Aluno(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("idade"),
                rs.getInt("faixa_id"),
                rs.getInt("equipe_id")
            );
            lista.add(a);
        }
        conn.close();
        return lista;
    }
}
