package com.academia.service;

import com.academia.dao.EventoDAO;
import com.academia.model.Evento;
import java.util.List;

public class EventoService {
    private EventoDAO eventoDAO;

    public EventoService() {
        this.eventoDAO = new EventoDAO();
    }

    public void cadastrarEvento(Evento evento) {
        eventoDAO.save(evento);
    }

    public void atualizarEvento(Evento evento) {
        eventoDAO.update(evento);
    }

    public void excluirEvento(int eventoId) {
        eventoDAO.delete(eventoId);
    }

    public List<Evento> listarEventos() {
        return eventoDAO.findAll();
    }

    public Evento buscarEventoPorId(int eventoId) {
        return eventoDAO.findById(eventoId);
    }
}
