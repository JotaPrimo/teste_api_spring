package com.testeapi.vagas.demo.services;

public class CPFService {
    public static boolean isValid(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula e verifica os dígitos verificadores
        try {
            int[] digits = new int[11];
            for (int i = 0; i < 11; i++) {
                digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += digits[i] * (10 - i);
            }

            int remainder = sum % 11;
            int expectedDigit1 = (remainder < 2) ? 0 : (11 - remainder);

            if (digits[9] != expectedDigit1) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += digits[i] * (11 - i);
            }

            remainder = sum % 11;
            int expectedDigit2 = (remainder < 2) ? 0 : (11 - remainder);

            return digits[10] == expectedDigit2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
