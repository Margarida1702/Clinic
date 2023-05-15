package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Appointment;

public interface InvoiceService {

    String createInvoice(Appointment appointment);
    String seeInvoice(String invoiceId);
    String payInvoice(Appointment appointment);
    String seeInfo(String invoiceId);
    String listInvoices();
}
