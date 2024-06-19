package com.inter.desafio.uteis;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AjustarParaUltimoDiaUtil {

    // Ajustando a data para sexta feira quando cair no final de samana
    private LocalDate ajustarParaUltimoDiaUtil(LocalDate date) {

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return date.minusDays(2);
        }
        return date;
    }

}
