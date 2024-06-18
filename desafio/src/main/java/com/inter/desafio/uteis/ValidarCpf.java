package com.inter.desafio.uteis;

public class ValidarCpf {

    // Método para validar CPF
    public static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * pesos[i];
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * pesos2[i];
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;

        return cpf.endsWith("" + digito1 + digito2);
    }

}
