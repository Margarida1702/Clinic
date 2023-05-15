<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Centro Hospitalar Upskill :: Consultas</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/doctorManagementStyles.css" rel="stylesheet">
</head>

<body>

<!-- cust-sub-body-cont -->
<div class="container-fluid h-100 cust-sub-body-cont">
    <!-- cust-main-sb-and-maincont-body-ctn -->
    <div class="row h-100 cust-row-sb-and-maincont-ctn">
        <%@include file="templates/sidebar.jsp"%>

        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto">

            <div class="cust-title1-ctn">
                <div class="cust-title1-txt col-md px-3 m-2">Consultas do Médico ${doctor.getName()}</div>
            </div>

            <div class="row">
                <div class="col-md">

                    <div class=" cust-header-ctn d-flex flex-md-wrap">

                    </div>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nº de consulta</th>
                            <th>Especialidade</th>
                            <th>Utente</th>
                            <th>Data/Hora</th>
                            <th>Estado</th>
                            <th>Valor</th>
                            <th>Acção</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="appointment" items="${appointmentList}">
                            <tr>
                                <td>${appointment.getId()}</td>
                                <td>${appointment.getSpecialty().getName()}</td>
                                <td>${appointment.getPatient().getName()}</td>
                                <td>${formatter.dateToPrettyNameString(appointment.getPlannedStartTime())}</td>
                                <td>${appointment.status}</td>
                                <td>${appointment.getPrice()}</td>
                                <td>
                                    <form>
                                        <input value="${appointment.getId()}" name="appointment" type="hidden">
                                        <button formaction="view_appointment" class="btn btn-sm btn-success" type="submit">Ver Detalhes</button>
                                        <c:if test="${appointment.getStatus().equals('SCHEDULED') || appointment.getStatus().equals('CHECKIN')}">
                                            <button formaction="deleteAppointment" class="btn btn-sm btn-danger" formmethod="post" type="submit">Cancelar</button>
                                        </c:if>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>


        </div> <!-- END Main Content Container -->

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>