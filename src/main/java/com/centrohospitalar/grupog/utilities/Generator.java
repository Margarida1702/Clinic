package com.centrohospitalar.grupog.utilities;

import com.centrohospitalar.grupog.entities.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    static Random random = new Random();
    static String firstNamesArray[] = {"Adriana","Adriano","Alexandra","Alexandre","Alice","Ana","Anabela","André",
            "Ãngela","Ãngelo","Angélica","Angélico","Andreia","António",
            "Assunção","Artur","Augusto","Beatriz","Bia","Bruna","Bruno","Carla","Carlos","Carolina","Cláudia","Cláudio","Cristina",
            "Daniel","Daniela","Dário","David","Diogo","Diana","Dina",
            "Eduardo","Francisca","Francisco","Filipe","Gabriel","Gabriela","Gaby","Gonçalo","Henrique","Ivan","Ivo",
            "Joana","João","Jorge","José","Kyara","Lara","Leonor","Lucas","Luís","Marco","Marcus","Mafalda","Maria",
            "Mariana","Mário","Marisa","Margarida","Marta","Miguel","Miriam","Neuza","Norberto","Nuno","Paula",
            "Paulo","Pedro","Rafael","Rafaela", "Ricardo","Rita","Rodrigo","Rui","Santiago","Sara","Sérgio","Sónia","Susana"
            ,"Tânia","Tomás","Vanda","Valter","Vasco","Vera","Vitor","Xavier"};
    static String lastNamesArray[] = {"Adriano","Afonso","Alexandra","Alexandre","Amaro","Ãngelo","Angélico",
            "António","Antunes","Aparício","Assunção","Augusto","Belo","Bruno","Campos","Carrilho","Carvalho","Coelho","Cruz","Daniel","David",
            "Diogo","Eduardo","Faria","Félix", "Fernandes","Ferrão","Ferreira","Fonseca","Fortuna",
            "Francisca","Francisco","Filipe","Firmino","Gabriel","Gamito","Gil","Gonçalo","Grifo","Henriques","Ivan","Jesus","Jorge","José",
            "Leal","Leonor","Lona","Lucas","Luís","Machado","Marcus","Maria","Mário",
            "Marta","Mateus","Mendonça","Miguel","Monteiro","Montenegro","Nunes","Paixão","Papi","Pedro","Pereira",
            "Pinto","Rafael","Ramalho","Ramos","Reis","Ricardo",
            "Rita","Rodrigo","Rui","Sá","Salvador","Santiago","Santos","Silva","Sousa","Tomás","Valente",
            "Vasco","Venâncio","Ventura","Vera","Viana","Vicente","Vitória","Xavier"};
    static String addressStartArray[] ={"Avenida ","Avenida de ","Rua ","Rua de ","Praceta ","Praceta de ","Largo ", "Travessa "};
    static String addressSeparatorArray[] ={" "," "," "," "," "," "," de ", " e "," de ", " e "," de ", " e "," de ", " e ",
            " Capitão "," General ", " Soldado ", " Libertador ", " " + Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)] + " "
            , " " + Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)] + " ",
            " " + Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)] + " ", " " + Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)] + " "};
    static String addressDoorSide[]= {"Esq","Dto","Frt","Tras","A","B","C","D","E","F"};
    static String city[]= {"Almada","Lisboa","Porto","Setúbal","Viana do Castelo","Faro","Seixal","Braga","Guimarães","Cacém",
            "Sintra","Barreiro","Rio de Mouro","Amadora","Benfica","Damaia","Queluz","Santarém","Amora"};

    static String conditionNamesArray[] ={"Apneia do sono","Problemas de Coração",
            "Arritmia","Falta de visão","Falta de audição","Asma","Prisão de Ventre","Síndrome de Gilbert","Síndrome de Brugada",
            "Artroses", "Fumador","Operado à Apendicite","Sem amígdalas","Ansiedade","Acne","Artrite","Dores de cabeça",
            "Cansaço extremo","Dores corporais","Diabetes","Teve Covid","Problemas de Rins","Problemas de Fígado","Alergias"
            ,"Má circulação","Dificuldades de Locomoção"};

    public static String generateRandomNameOfUser() {
        String name = Generator.firstNamesArray[random.nextInt(firstNamesArray.length-1)] + " " + Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)];
        return name;
    }

    public static LocalDate generateBirthDay() {

        LocalDate start = LocalDate.of(1950,1,1);
        LocalDate end = LocalDate.of(2012,1,1);// it's exclusive so it's 2011-12-31

        long startMilis = start.toEpochDay();
        long endMilis = end.toEpochDay();
        long randomDayMilis = random.nextLong(startMilis, endMilis);
        return LocalDate.ofEpochDay(randomDayMilis);

    }

    public static String generateAddress() {

        String addressAux = Generator.addressStartArray[random.nextInt(addressStartArray.length-1)]+
                Generator.firstNamesArray[random.nextInt(firstNamesArray.length-1)] +
                Generator.addressSeparatorArray[random.nextInt(addressSeparatorArray.length-1)] +
                Generator.lastNamesArray[random.nextInt(lastNamesArray.length-1)] + " " +
                random.nextInt(1,501) + " " +random.nextInt(1,10) +
                Generator.addressDoorSide[random.nextInt(addressDoorSide.length-1)] + " " +
                random.nextInt(1000,4901) + "-" + random.nextInt(10) +""
                +random.nextInt(10)+""+random.nextInt(10) + " " +
                Generator.city[random.nextInt(city.length-1)];

        return addressAux;
    }

    public static String generateCondition() {
        String condition;

        if(random.nextInt(2)==0){
            return "";
        }

        return Generator.conditionNamesArray[random.nextInt(conditionNamesArray.length-1)];
    }

    public static long generatePhoneNumber() {
        return random.nextLong(900000000,999999999);
    }

    public static String generateNIF() {
        return String.valueOf(random.nextLong(200000000,500000001));
    }

    public static long generateProfessionalCertificateNumber() {
        return random.nextLong(100000000,999999999);
    }
}
