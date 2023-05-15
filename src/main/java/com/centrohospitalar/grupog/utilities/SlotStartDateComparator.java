package com.centrohospitalar.grupog.utilities;

import com.centrohospitalar.grupog.models.Slot;

import java.util.Comparator;

public class SlotStartDateComparator implements Comparator<Slot> {

    @Override
    public int compare(Slot o1, Slot o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
