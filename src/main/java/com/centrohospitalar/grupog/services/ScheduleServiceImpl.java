package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.*;

import com.centrohospitalar.grupog.exceptions.addDateScalePeriodToEmployeeException;
import com.centrohospitalar.grupog.models.Slot;
import com.centrohospitalar.grupog.repositories.AppointmentRepository;
import com.centrohospitalar.grupog.repositories.DateScalePeriodRepository;
import com.centrohospitalar.grupog.repositories.EmployeeRepository;
import com.centrohospitalar.grupog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    DateScalePeriodRepository dateScalePeriodRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<DateScalePeriod> getDateScalePeriods() {
        List<DateScalePeriod> dateScalePeriodForAllSpecialties = dateScalePeriodRepository.findAll();
        return dateScalePeriodForAllSpecialties;
    }

    public DateScalePeriod getDateScalePeriodById(Long id) {
        DateScalePeriod dateScalePeriod = dateScalePeriodRepository.getReferenceById(id);
        return dateScalePeriod;
    }



    public List<DateScalePeriod> createDateScalePeriodsFromSchedule(Employee employee, String [] weekdays, LocalDate startDay, LocalDate endDay, LocalTime startTime, LocalTime endTime, List<Specialty> specialties){

        List<DateScalePeriod> dateScalePeriodList=new ArrayList<>();
        List<String> weekdaysStringList = new ArrayList<>();

        for (int i=0; i< weekdays.length; i++){
             weekdaysStringList.add(weekdays[i]);
        }


        long startDayMilis= startDay.toEpochDay();
        long endDayMilis= endDay.toEpochDay();


        for (long l= startDayMilis; l<=endDayMilis; l++){

            if (weekdaysStringList.contains(LocalDate.ofEpochDay(l).getDayOfWeek().toString())) {
                if (specialties.size()>0){

                    for (int b=0; b<specialties.size(); b++){
                        DateScalePeriod dateScalePeriod = new DateScalePeriod(LocalDateTime.of(LocalDate.ofEpochDay(l),startTime),
                                LocalDateTime.of(LocalDate.ofEpochDay(l),endTime),specialties.get(b),employee);
                        dateScalePeriodList.add(dateScalePeriod);
                    }
                } else {
                    DateScalePeriod dateScalePeriod = new DateScalePeriod(LocalDateTime.of(LocalDate.ofEpochDay(l),startTime),
                            LocalDateTime.of(LocalDate.ofEpochDay(l),endTime),null,employee);
                    dateScalePeriodList.add(dateScalePeriod);
                }
            }
        }//end of DateScalePeriod added to list



/*        for(DateScalePeriod d: dateScalePeriodList){ //debug
            System.out.println("inic - " + d.getStartTime().toString());
            System.out.println("fim - " + d.getEndTime().toString());
        }*/
        return dateScalePeriodList;
    }


    @Override
    public boolean addDateScalePeriodToEmployee(DateScalePeriod dateScalePeriod, Employee employee) throws addDateScalePeriodToEmployeeException { //TODO IT'S WRONG IT CAN JOIN DIFFERENT DAYS

        if(!dateScalePeriod.getStartTime().toLocalDate().equals(dateScalePeriod.getEndTime().toLocalDate())){
            throw new addDateScalePeriodToEmployeeException("Não pode adicionar escalas diárias com começo e fim em dias diferentes");
        }

        if(!dateScalePeriod.getEmployee().equals(employee)){
            throw new addDateScalePeriodToEmployeeException("Não pode adicionar escalas diárias a um funcionário de outro funcionário");
        }

        List <DateScalePeriod> eDateScalePeriodList = employee.getDateScalePeriodList();
        boolean inserted = false;

        if(eDateScalePeriodList.isEmpty()){
            eDateScalePeriodList.add(dateScalePeriod);
            inserted = true;
        }else {
            for (DateScalePeriod old : eDateScalePeriodList) {
                if(dateScalePeriod.getSpecialty()!=null && old.getSpecialty()!=null){
                    if ((dateScalePeriod.getSpecialty().getName().equals(old.getSpecialty().getName()))) { // if from same specialty
                        if (!((dateScalePeriod.getEndTime().compareTo(old.getStartTime()) <= 0) ||
                                (dateScalePeriod.getStartTime().compareTo(old.getEndTime()) >= 0))) {// intersection cases (1)
                            throw new addDateScalePeriodToEmployeeException("Existem conflitos de horário já existentes, detectado pelo menos 1 a começar em "+ old.getStartTime().toString(),old.getStartTime().toLocalDate());
                        }
                    }
                }else {
                    if(dateScalePeriod.getSpecialty()==null && old.getSpecialty()==null){
                        if (!((dateScalePeriod.getEndTime().compareTo(old.getStartTime()) <= 0) ||
                                (dateScalePeriod.getStartTime().compareTo(old.getEndTime()) >= 0))) {// intersection cases (1)
                            throw new addDateScalePeriodToEmployeeException("Existem conflitos de horário já existentes, detectado pelo menos 1 a começar em "+ old.getStartTime().toString(),old.getStartTime().toLocalDate());
                        }
                        // same generic service without Specialty
                    }
                }

            }
            eDateScalePeriodList.add(dateScalePeriod);
            inserted = true;
        }
        dateScalePeriodRepository.save(dateScalePeriod);
        employeeRepository.save(employee);



        return inserted;


        //IT WAS WRONG IT COULD JOIN DIFFERENT DAYS
        /*List <DateScalePeriod> dateScalePeriodListFinal = new ArrayList<>();
        List <DateScalePeriod> eDateScalePeriodList = employee.getDateScalePeriodList();

        if(eDateScalePeriodList.isEmpty()){
            dateScalePeriodListFinal.add(dateScalePeriod);
            employee.setDateScalePeriodList(dateScalePeriodListFinal);
        }else{
            for(DateScalePeriod old: eDateScalePeriodList){
                if(!(dateScalePeriod.getSpecialty().getName().equals(old.getSpecialty().getName()))){ // if from different specialty add
                    dateScalePeriodListFinal.add(old);
                }else { //if from same specialty
                    if((dateScalePeriod.getEndTime().isBefore(old.getStartTime())) ||
                            (dateScalePeriod.getStartTime().isAfter(old.getEndTime()))){//No intersection cases (1)
                        dateScalePeriodListFinal.add(old);
                    } else if ((dateScalePeriod.getStartTime().compareTo(old.getStartTime())<=0) && //new dateScalePeriod starts before or equal to old and ends in interval (2)
                            (dateScalePeriod.getEndTime().compareTo(old.getStartTime()) >=0) &&
                            (dateScalePeriod.getEndTime().compareTo(old.getEndTime()) <=0)){
                        int endYear = old.getEndTime().getYear();
                        Month endMonth = old.getEndTime().getMonth();
                        int endDay = old.getEndTime().getDayOfMonth();
                        int endHour = old.getEndTime().getHour();
                        int endMinute = old.getEndTime().getMinute();
                        int endSecond = old.getEndTime().getSecond();

                        LocalDateTime dspNewEndTime = LocalDateTime.of(endYear,endMonth,endDay,endHour,endMinute,endSecond);
                        dateScalePeriod.setEndTime(dspNewEndTime);

                    }else if((dateScalePeriod.getStartTime().isAfter(old.getStartTime())) &&
                            (dateScalePeriod.getStartTime().compareTo(old.getEndTime())<=0) &&
                            (dateScalePeriod.getEndTime().compareTo(old.getEndTime())<=0)){ // new is containned in old(4)

                        int startYear = old.getStartTime().getYear();
                        Month startMonth = old.getStartTime().getMonth();
                        int startDay = old.getStartTime().getDayOfMonth();
                        int startHour = old.getStartTime().getHour();
                        int startMinute = old.getStartTime().getMinute();
                        int startSecond = old.getStartTime().getSecond();

                        LocalDateTime dspNewStartTime = LocalDateTime.of(startYear,startMonth,startDay,startHour,startMinute,startSecond);
                        dateScalePeriod.setStartTime(dspNewStartTime);

                        int endYear = old.getEndTime().getYear();
                        Month endMonth = old.getEndTime().getMonth();
                        int endDay = old.getEndTime().getDayOfMonth();
                        int endHour = old.getEndTime().getHour();
                        int endMinute = old.getEndTime().getMinute();
                        int endSecond = old.getEndTime().getSecond();

                        LocalDateTime dspNewEndTime = LocalDateTime.of(endYear,endMonth,endDay,endHour,endMinute,endSecond);
                        dateScalePeriod.setEndTime(dspNewEndTime);


                    } else if ((dateScalePeriod.getStartTime().compareTo(old.getStartTime())>0) &&
                            (dateScalePeriod.getStartTime().compareTo(old.getEndTime())<=0) &&
                            (dateScalePeriod.getEndTime().compareTo(old.getEndTime())>0)){ // new starts after start in interval and ends after interval (5)

                        int startYear = old.getStartTime().getYear();
                        Month startMonth = old.getStartTime().getMonth();
                        int startDay = old.getStartTime().getDayOfMonth();
                        int startHour = old.getStartTime().getHour();
                        int startMinute = old.getStartTime().getMinute();
                        int startSecond = old.getStartTime().getSecond();

                        LocalDateTime dspNewStartTime = LocalDateTime.of(startYear,startMonth,startDay,startHour,startMinute,startSecond);
                        dateScalePeriod.setStartTime(dspNewStartTime);
                    }
                }




            }

            dateScalePeriodListFinal.add(dateScalePeriod);
        }
        employee.setDateScalePeriodList(dateScalePeriodListFinal);
        userRepository.save(employee);*/
    }

    @Override
    public Map<DateScalePeriod, String> addDateScalePeriodListToEmployee(List<DateScalePeriod> dateScalePeriodList, Employee employee) {

        Map reportScheduleCreationStatus = new HashMap<>();
        for(DateScalePeriod d: dateScalePeriodList) {

            try {
                this.addDateScalePeriodToEmployee(d,employee);
                reportScheduleCreationStatus.put(d,"Sucesso - Criada Escala");
            } catch (addDateScalePeriodToEmployeeException e) {
                System.out.println(e.getMessage().toString());
                reportScheduleCreationStatus.put(d,"Falhou - " + e.getMessage());
            }
        }

        return reportScheduleCreationStatus;
    }

    @Override
    public List<DateScalePeriod> getAllDateScalePeriodBetweenDatesByEmployeeAndBySpecialtyInclusive(LocalDate startDate, LocalDate endDate, Specialty specialty, Employee employee) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate.getYear(),startDate.getMonth(),startDate.getDayOfMonth(),0,0,0);
        LocalDateTime endDateTime = LocalDateTime.of(endDate.getYear(),endDate.getMonth(),endDate.getDayOfMonth(),23,59,59); //para ser inclusivé

        List<DateScalePeriod> finalList = new ArrayList<>();
        List<DateScalePeriod> employeeAllDateScalePeriod = employee.getDateScalePeriodList();

        if(employeeAllDateScalePeriod == null ){
            employeeAllDateScalePeriod = new ArrayList<>(); //garantir que não é null
        }
        if(!employeeAllDateScalePeriod.isEmpty()){
            for (DateScalePeriod d: employeeAllDateScalePeriod) {
                if(d.getSpecialty().getName().equals(specialty.getName())){
                    if(d.getStartTime().compareTo(startDateTime)>=0 && d.getEndTime().compareTo(endDateTime)<=0){ //DateScalePeriod dentro de intervalo pretendido
                        finalList.add(d);
                    }
                }

            }
        }


        return finalList;
    }



    @Override
    public List<Slot> createSlotsBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int slotMinuteDuration) {
        List<Slot> slots = new ArrayList<>();

        LocalDateTime current = LocalDateTime.of(startDateTime.toLocalDate(),startDateTime.toLocalTime());

        while(current.plusMinutes(slotMinuteDuration).compareTo(endDateTime)<=0){
            slots.add(new Slot(current,current.plusMinutes(slotMinuteDuration)));
            current = LocalDateTime.of((current.plusMinutes(slotMinuteDuration)).toLocalDate(),(current.plusMinutes(slotMinuteDuration)).toLocalTime());
        }

        return slots;
    }

    public List<Slot> createSlotsListFromDateScalePeriod(DateScalePeriod dateScalePeriod,int slotMinuteDuration){
        return this.createSlotsBetweenDateTimes(dateScalePeriod.getStartTime(),dateScalePeriod.getEndTime(),slotMinuteDuration);

    }

    public List<Slot> createSlotsListFromDateScalePeriods(List<DateScalePeriod> dateScalePeriods,int slotMinuteDuration){
        List<Slot> auxListSlots = new ArrayList<>();
        List<Slot> finalListSlots = new ArrayList<>();

        if(dateScalePeriods != null || (!dateScalePeriods.isEmpty())){

            for(DateScalePeriod d: dateScalePeriods){
                auxListSlots = createSlotsBetweenDateTimes(d.getStartTime(),d.getEndTime(),slotMinuteDuration);

                for(Slot slot: auxListSlots){
                    if(!finalListSlots.contains(slot)){
                        finalListSlots.add(slot);
                    }
                }
            }


        }

        return finalListSlots;

    }

    public boolean isSlotInsideIntervalExclusive(Slot slot, LocalDateTime startDateTime, LocalDateTime endDateTime){
        boolean contained=true;

        if((slot.getStartTime().compareTo(startDateTime)<=0 && slot.getEndTime().compareTo(startDateTime)<=0) ||
        (slot.getStartTime().compareTo(endDateTime)>=0 && slot.getEndTime().compareTo(endDateTime)>=0)){
            contained=false;
        }
        return contained;
    }

    @Override
    public List<Slot> getSpecialtyFreeVacanciesByDoctorAndTimeInterval(Specialty specialty, Doctor doctor, LocalDateTime startDateTime, LocalDateTime endDateTime, int slotMinuteDuration) {
        List<Slot> vacantList = new ArrayList<>();


        List<DateScalePeriod> doctorDayShedulesBySpe =
                this.getAllDateScalePeriodBetweenDatesByEmployeeAndBySpecialtyInclusive(startDateTime.toLocalDate(),endDateTime.toLocalDate(),specialty,doctor);
        List<Slot> doctorDaySheduleBySpeSlots = createSlotsListFromDateScalePeriods(doctorDayShedulesBySpe,slotMinuteDuration);

        //remember this is Date slots and not DateTime so it can be out of interval and has to be evaluated next

        LocalDateTime endLocalDateTimeForAppointmentSearch = LocalDateTime.of(endDateTime.toLocalDate(), LocalTime.of(23,59,59));

        List<String> appointmentStatusList = new ArrayList<>();
        appointmentStatusList.add(Appointment.AppointmentStatus.SCHEDULED.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.DOCTOR_ABSENCE.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.PATIENT_ABSENCE.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.STARTED.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.RESERVED_24H.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.CHECKIN.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.LATE_CHECKIN.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.PATIENT_CALLED_ABSENCE.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.AUTHORIZED.name());
        appointmentStatusList.add(Appointment.AppointmentStatus.ENDED.name());


        List <Appointment> activeAppoitments = appointmentRepository.getAppointmentsByDoctorIdAndPlannedStartTimeGreaterThanEqualAndPlannedEndTimeLessThanEqualAndAndStatusIn(
                doctor.getId(),startDateTime,endLocalDateTimeForAppointmentSearch,appointmentStatusList);
        boolean contained = false;
        for(Slot slot:doctorDaySheduleBySpeSlots){
            for(Appointment app: activeAppoitments){
                this.isSlotInsideIntervalExclusive(slot, app.getPlannedStartTime(),app.getPlannedEndTime());
                contained=true;
            }
            if(!contained){
                vacantList.add(slot);
                contained=false;
            }
        }


        return vacantList;
    }


}