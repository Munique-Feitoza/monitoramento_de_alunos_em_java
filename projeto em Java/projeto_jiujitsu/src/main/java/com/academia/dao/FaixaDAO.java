package com.academia.dao;

import model.Faixa;
import java.sql.*;

public class FaixaDAO {
    public void cadastrar(Faixa faixa) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO faixas (cor, grau) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, faixa.getCor());
        stmt.setInt(2, faixa.getGrau());
        stmt.executeUpdate();
        conn.close();
    }
}
