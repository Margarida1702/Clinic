package com.centrohospitalar.grupog.models;

public class InvoiceItem {
    private String description;
    private String value;

    public InvoiceItem() {
    }

    public InvoiceItem(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}