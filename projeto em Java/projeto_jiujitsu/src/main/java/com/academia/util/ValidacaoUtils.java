package com.academia.util;

public class ValidacaoUtils {

    // Método para validar se uma string não está vazia ou nula
    public static boolean validarStringNaoVazia(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Método para validar se um número inteiro é positivo
    public static boolean validarNumeroPositivo(int numero) {
        return numero > 0;
    }

    // Método para validar se um número de ponto flutuante é positivo
    public static boolean validarNumeroPositivo(double numero) {
        return numero > 0.0;
    }

    // Método para validar um CPF (apenas exemplo básico, pode ser melhorado)
    public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    // Método para validar se uma data de nascimento é válida (exemplo básico)
    public static boolean validarDataNascimento(String dataNascimento) {
        return dataNascimento != null && dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}");
    }
}
