package com.centrohospitalar.grupog.exceptions;

import java.time.LocalDate;

public class addDateScalePeriodToEmployeeException extends Exception {

    LocalDate localDate;
    public addDateScalePeriodToEmployeeException(String message) {
        super(message);
    }

    public addDateScalePeriodToEmployeeException(String message, LocalDate localDate ) {
        super(message);
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
