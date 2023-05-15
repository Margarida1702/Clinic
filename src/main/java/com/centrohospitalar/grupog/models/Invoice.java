package com.centrohospitalar.grupog.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoice {
    private String id;
    private String name;
    private String email;
    private String nif;
    private String dueDate;
    private String issuedDate;
    private String paidDate;
    private String value;
    private List<InvoiceItem> items;
    private String url;

    public Invoice() {
    }

    public Invoice(String id, String name, String email, String nif, String dueDate, String issuedDate, String paidDate, String value, List<InvoiceItem> items, String url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.dueDate = dueDate;
        this.issuedDate = issuedDate;
        this.paidDate = paidDate;
        this.value = value;
        this.items = items;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}