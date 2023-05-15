package com.centrohospitalar.grupog.services;


import com.centrohospitalar.grupog.entities.DateScalePeriod;
import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Employee;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.exceptions.addDateScalePeriodToEmployeeException;
import com.centrohospitalar.grupog.models.Slot;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    public List<DateScalePeriod> getDateScalePeriods();

    public DateScalePeriod getDateScalePeriodById(Long id);

    public boolean addDateScalePeriodToEmployee(DateScalePeriod dateScalePeriod, Employee employee) throws addDateScalePeriodToEmployeeException;

    public Map<DateScalePeriod,String> addDateScalePeriodListToEmployee(List<DateScalePeriod> dateScalePeriodList, Employee employee);

    public List<DateScalePeriod> getAllDateScalePeriodBetweenDatesByEmployeeAndBySpecialtyInclusive(LocalDate startDate, LocalDate endDate, Specialty specialty, Employee employee);

    public List<Slot> createSlotsBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int slotMinuteDuration);
    public List<Slot> createSlotsListFromDateScalePeriod(DateScalePeriod dateScalePeriod,int slotMinuteDuration);

    public boolean isSlotInsideIntervalExclusive(Slot slot, LocalDateTime startDateTime, LocalDateTime endDateTime);

    public List<Slot> getSpecialtyFreeVacanciesByDoctorAndTimeInterval(Specialty specialty, Doctor doctor, LocalDateTime startDateTime,LocalDateTime endDateTime,int slotMinuteDuration);//TODO

    public List<DateScalePeriod> createDateScalePeriodsFromSchedule(Employee employee, String [] weekdays, LocalDate begginingDay, LocalDate finishDay, LocalTime startTime, LocalTime endTime, List <Specialty> specialties);
}
