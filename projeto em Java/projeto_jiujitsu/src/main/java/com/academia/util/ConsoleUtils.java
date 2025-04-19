package com.academia.util;

import java.util.Scanner;

public class ConsoleUtils {

    private static Scanner scanner = new Scanner(System.in);

    // Método para ler uma string do console
    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    // Método para ler um número inteiro do console
    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
            scanner.next(); // Limpar o buffer
            System.out.print(mensagem);
        }
        return scanner.nextInt();
    }

    // Método para ler um número de ponto flutuante do console
    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida! Por favor, digite um número válido.");
            scanner.next(); // Limpar o buffer
            System.out.print(mensagem);
        }
        return scanner.nextDouble();
    }

    // Método para limpar o buffer do scanner
    public static void limparBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Limpar o buffer de linha
        }
    }
}
