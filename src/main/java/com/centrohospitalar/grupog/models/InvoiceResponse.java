package com.centrohospitalar.grupog.models;

public class InvoiceResponse {
    public String status;
    public Invoice invoice;

    public InvoiceResponse() {
    }

    public InvoiceResponse(String status, Invoice invoice) {
        this.status = status;
        this.invoice = invoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
