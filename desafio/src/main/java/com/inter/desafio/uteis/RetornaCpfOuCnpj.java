package com.inter.desafio.uteis;

public class RetornaCpfOuCnpj {

    // Método para determinar se o número informado é CPF ou CNPJ e validá-lo
    public static String validarCpfOuCnpj(String numero) {
        if(numero == null) {
            throw new IllegalArgumentException("O número de entrada não pode ser nulo");
        }
        String cleaned = numero.replaceAll("\\D", "");
        if (cleaned.length() == 11 && ValidarCpf.isValidCPF(cleaned)) {
            return "Física";
        } else if (cleaned.length() == 14 && ValidarCnpj.isValidCNPJ(cleaned)) {
            return "Jurídica";
        } else {
            return "Não Informado";
        }
    }
}
