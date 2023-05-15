package com.centrohospitalar.grupog.models;

import java.time.LocalDateTime;

public class Slot {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Slot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Slot other = (Slot) obj;
        if (!this.startTime.equals(other.getStartTime()))
            return false;
        if (!this.endTime.equals(other.getEndTime()))
            return false;
        return true;
    }
}
