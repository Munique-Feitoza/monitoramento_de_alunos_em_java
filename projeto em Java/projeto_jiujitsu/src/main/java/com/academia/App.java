package com.academia;

import com.academia.controller.AlunoController;
import com.academia.controller.ProfessorController;
import com.academia.controller.TurmaController;
import com.academia.controller.EquipeController;
import com.academia.controller.EventoController;
import com.academia.controller.MenuPrincipalController;
import com.academia.util.ConsoleUtils;

public class App {

    public static void main(String[] args) {
        // Inicia o controlador do menu principal
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController();
        menuPrincipalController.exibirMenuPrincipal();
    }
}
