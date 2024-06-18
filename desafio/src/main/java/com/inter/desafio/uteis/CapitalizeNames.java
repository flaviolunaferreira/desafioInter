package com.inter.desafio.uteis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CapitalizeNames {

    private static final Set<String> prepositions = new HashSet<>(Arrays.asList("da", "de", "do", "das", "dos", "e"));

    public static String capitalizeName(String name) {
        String[] words = name.toLowerCase().split("\\s+");
        StringBuilder capitalized = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (prepositions.contains(words[i]) && i != 0) {
                capitalized.append(words[i]);
            } else {
                capitalized.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1));
            }
            if (i < words.length - 1) {
                capitalized.append(" ");
            }
        }
        return capitalized.toString();
    }
}
