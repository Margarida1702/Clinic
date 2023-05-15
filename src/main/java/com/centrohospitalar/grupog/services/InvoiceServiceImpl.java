package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Appointment;
import com.centrohospitalar.grupog.models.Invoice;
import com.centrohospitalar.grupog.models.InvoiceItem;
import com.centrohospitalar.grupog.models.InvoiceResponse;
import com.centrohospitalar.grupog.repositories.AppointmentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.Inet4Address;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    AppointmentRepository appointmentRepository;

    private static String COMPANY_NIF = "588260745";

    public String createInvoice(Appointment appointment) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject invoiceRequest = new JSONObject();
        invoiceRequest.put("name", appointment.getPatient().getName());
        invoiceRequest.put("email", appointment.getPatient().getEmail());
        invoiceRequest.put("nif", appointment.getPatient().getNIF());
        invoiceRequest.put("duedate", df.format(new Date()));
        invoiceRequest.put("issuedDate", df.format(new Date()));
        InvoiceItem invoiceItem = new InvoiceItem(appointment.getDescription(), appointment.getPrice().toString());
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);
        invoiceRequest.put("items", invoiceItemList);
        invoiceRequest.put("value", appointment.getPrice().toString());

        String requestUrl = "https://serro.pt/invoices/" + COMPANY_NIF + "/create";
        InvoiceResponse response = restTemplate.postForObject(requestUrl, new HttpEntity<>(invoiceRequest.toString(), headers), InvoiceResponse.class);
        if (response == null || response.status.equals("error")) {
            return "Erro a processar fatura";
        }

        Invoice invoice = response.getInvoice();

        //TODO fazer qualquer coisa com o invoice
        appointment.setInvoiceIdentity(invoice.getId());
        appointmentRepository.save(appointment);
        invoiceRequest.put("url", seeInvoice(appointment.getInvoiceIdentity()));

        return "Fatura " + invoice.getId() + " criada com sucesso!";
    }


    public String payInvoice(Appointment appointment) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject invoiceRequest = new JSONObject();

        String requestUrl = "https://serro.pt/invoices/" + COMPANY_NIF + "/pay/" + appointment.getInvoiceIdentity();

        InvoiceResponse response = restTemplate.postForObject(requestUrl, new HttpEntity<>(invoiceRequest.toString(), headers), InvoiceResponse.class);

        appointment.setPaid(true);
        appointmentRepository.save(appointment);

        if (response == null || response.status.equals("error")) {
            return "Erro no pagamento"; //DIFERENCIAR ERROS
        }

        return "Fatura " + appointment.getInvoiceIdentity() + " paga com sucesso";
    }

    public String seeInvoice(String invoiceId) {

        String uri = "https://serro.pt/invoices/" + COMPANY_NIF + "/get/" + invoiceId;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        if (result == "error")
            return "error";
        else
            return result;
    }

    public String seeInfo(String invoiceId) {

        String uri = "https://serro.pt/invoices/" + COMPANY_NIF + "/info/" + invoiceId;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        if (result == "error")
            return "error";
        else
            return result;
    }

    public String listInvoices() {

        String uri = "https://serro.pt/invoices/" + COMPANY_NIF + "/list";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        if (result == "error")
            return "error";
        else
            return result;
    }




}
