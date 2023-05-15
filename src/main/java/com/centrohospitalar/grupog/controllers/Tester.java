package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.Appointment;
import com.centrohospitalar.grupog.utilities.Generator;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

public class Tester {


    public static void main(String[] args) {


        /*Random random = new Random();

        LocalDate start = LocalDate.of(2022,10,20);
        LocalDate end = LocalDate.of(2022,10,21+1);// because it's exclusive

        Long startMilis = start.toEpochDay();
        Long endMilis = end.toEpochDay();
        long randomDayMilis = random.nextLong(startMilis, endMilis);
        LocalDate dateAux = LocalDate.ofEpochDay(randomDayMilis);
        System.out.println(dateAux);

        System.out.println(Generator.generateAddress());*/

        System.out.println(Appointment.AppointmentStatus.CHECKIN);
        String a = Appointment.AppointmentStatus.CHECKIN.name();







    }
}
