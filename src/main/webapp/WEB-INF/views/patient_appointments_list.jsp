<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Centro Hospitalar Upskill :: </title>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/appointments.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid h-100">
    <div class="row h-100">
        <%@include file="templates/sidebar.jsp"%>
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto p-0">
            <section class="appointments">
                <div class="d-flex justify-content-center">
                    <div class="row">
                        <c:forEach items="${appointmentsList}" var="appointment">--%>
<%--                            <p>${appointment.id}</p>--%>
<%--                            <p>${appointment.doctor.name}</p>--%>
<%--                        </c:forEach>--%>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>


</body>