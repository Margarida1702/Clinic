package com.centrohospitalar.grupog.utilities;

import com.centrohospitalar.grupog.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Format;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormater {

    public DateTimeFormater() {
    }

    public String dateToPrettyNameString(LocalDateTime localDateTime){
        int day= localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String formatDateTime = localDateTime.format(formatter);
        DayOfWeek weekDay = LocalDate.of(year,month,day).getDayOfWeek();
        String weekDayFinal="";
        String monthFinal="";

        switch (month){
            case 1: monthFinal = "Janeiro"; break;
            case 2: monthFinal = "Fevereiro"; break;
            case 3: monthFinal = "Março"; break;
            case 4: monthFinal = "Abril"; break;
            case 5: monthFinal = "Maio"; break;
            case 6: monthFinal = "Junho"; break;
            case 7: monthFinal = "Julho"; break;
            case 8: monthFinal = "Agosto"; break;
            case 9: monthFinal = "Setembro"; break;
            case 10: monthFinal = "Outubro"; break;
            case 11: monthFinal = "Novembro"; break;
            default: monthFinal = "Dezembro"; break;
        }

        switch (weekDay){
            case MONDAY: weekDayFinal = "Segunda-feira"; break;
            case TUESDAY: weekDayFinal = "Terça-feira"; break;
            case WEDNESDAY: weekDayFinal = "Quarta-feira"; break;
            case THURSDAY: weekDayFinal = "Quinta-feira"; break;
            case FRIDAY: weekDayFinal = "Sexta-feira"; break;
            case SATURDAY: weekDayFinal = "Sábado"; break;
            case SUNDAY: weekDayFinal = "Domingo"; break;
        }

        String finalDate = formatDateTime + " (" + weekDayFinal + ")";

        return finalDate;
    }
}
