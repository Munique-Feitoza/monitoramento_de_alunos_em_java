package com.academia.dao;

import model.Evento;
import java.sql.*;

public class EventoDAO {
    public void cadastrar(Evento evento) throws SQLException {
        Connection conn = ConexaoBD.conectar();
        String sql = "INSERT INTO eventos (nome, data) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, evento.getNome());
        stmt.setDate(2, Date.valueOf(evento.getData()));
        stmt.executeUpdate();
        conn.close();
    }
}
