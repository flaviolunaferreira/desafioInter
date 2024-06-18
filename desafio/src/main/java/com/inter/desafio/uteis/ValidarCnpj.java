package com.inter.desafio.uteis;

public class ValidarCnpj {

    // Método para validar CNPJ
    public static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesos = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos2[i];
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;

        return cnpj.endsWith("" + digito1 + digito2);
    }

}
