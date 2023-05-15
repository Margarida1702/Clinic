<!-- template para qualquer página que inclui sidebar. Só copiar e começar a preencher -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Centro Hospitalar Upskill :: Confirmação Consulta</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/appointments.css" rel="stylesheet">
</head>
<body>

<!-- cust-sub-body-cont -->
<div class="container-fluid h-100 cust-sub-body-cont">
    <!-- cust-main-sb-and-maincont-body-ctn -->
    <div class="row h-100 cust-row-sb-and-maincont-ctn">
        <%@include file="templates/sidebar.jsp"%>

        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto">

            <div class="row">
                <div class="col-md mt-5">

                    <c:if test="${sucess != null}">
                        <div class="alert alert-success">
                                ${sucess}
                        </div>
                    </c:if>

                    <c:if test="${error != null}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>

                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <td>Nº de Consulta</td>
                            <td>${appointment.getId()}</td>
                        </tr>
                        <tr>
                            <td>Especialidade</td>
                            <td>${appointment.getSpecialty().getName()}</td>
                        </tr>
                        <tr>
                            <td>Médico</td>
                            <td>${appointment.getDoctor().getName()}</td>
                        </tr>
                        <tr>
                            <td>Hora Marcada</td>
                            <td>${dateFormatter.dateToPrettyNameString(appointment.getPlannedStartTime())}</td>
                        </tr>
                        <tr>
                            <td>Estado</td>
                            <td>${appointment.getStatus()}</td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="alert alert-warning">
                        <h6>Pedimos que faça o checkIn antes da hora marcada! Até lá!</h6>
                    </div>

                    <form>
                        <button formaction="appointments_main" class="btn btn-primary cust-btn-blu-Up mt-4" formmethod="get" type="submit">Ver Consultas</button>
                    </form>

                </div>
            </div>


        </div> <!-- END Main Content Container -->

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>