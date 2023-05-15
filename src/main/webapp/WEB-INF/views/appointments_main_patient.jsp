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
    <link href="/styles/createScheduleStyles.css" rel="stylesheet">
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
                <div class="cust-title-main col-md px-3 m-2">Consultas</div>
            </div>

            <div class="row">
                <div class="col-md">

                    <div class=" cust-header-ctn d-flex flex-md-wrap">

                        <form method="get" class="cust-header-menu-ctn m-2">
                            <button formaction="appointments_choose_specialty" type="submit" class="btn btn-primary cust-btn-blu-Up">Marcar consulta</button>
                        </form>

                    </div>

                    <c:if test="${payResponse != null}">
                        <div class="alert alert-primary">
                                ${payResponse}
                        </div>
                    </c:if>

                    <c:if test="${error != null}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>

                    <c:if test="${waitingListSuccess != null}">
                        <div class="alert alert-success">
                                ${waitingListSuccess}
                        </div>
                    </c:if>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nº de consulta</th>
                            <th>Especialidade</th>
                            <th>Médico</th>
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
                                    <td>${appointment.getDoctor().getName()}</td>
                                    <td>${formatter.dateToPrettyNameString(appointment.getPlannedStartTime())}</td>
                                    <td>${appointment.status}</td>
                                    <td>${appointment.getPrice()}</td>
                                    <td>
                                        <form>
                                            <input value="${appointment.getId()}" name="appointment" type="hidden">
                                            <button formaction="view_appointment" class="btn btn-sm btn-success" type="submit">Ver Detalhes</button>

                                            <c:if test="${appointment.status.equals('Marcada') || appointment.getStatus().equals('Check-In efectuado')}">
                                                <button formaction="cancel_appointment" class="btn btn-sm btn-danger" formmethod="post" type="submit">Cancelar</button>
                                            </c:if>

                                            <c:if test="${appointment.getStatus().equals('Finalizada')}">
                                                <button formaction="seeInvoice" class="btn btn-sm btn-primary" formmethod="post" type="submit">Ver Fatura</button>
                                            </c:if>

                                            <%--<c:if test="${appointment.getStatus().equals('ENDED')}">
                                                <input value="${appointment}" name="appointment" type="hidden">
                                                <button onclick="window.open('${invoiceLinkPrefix.concat(appointment.getInvoiceIdentity())}','_blank')" class="btn btn-sm btn-primary">Ver Fatura</button>
                                            </c:if>--%>

                                            <c:if test="${appointment.getStatus().equals('Finalizada') && !appointment.isPaid()}">
                                                <input value="${appointment}" name="appointment" type="hidden">
                                                <button formaction="payInvoice" class="btn btn-sm btn-primary" formmethod="post" type="submit">Pagar</button>
                                            </c:if>


                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>


            <div class="cust-title1-ctn">
                <div class="cust-title-main col-md px-3 m-2 mt-3">Lista de Espera</div>
            </div>

            <div class="row">
                <div class="col-md">

                    <c:if test="${waitingListSuccess != null}">
                        <div class="alert alert-success">
                                ${waitingListSuccess}
                        </div>
                    </c:if>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nº da Lista de Espera</th>
                            <th>Especialidade</th>
                            <th>Médico</th>
                            <th>Registo</th>
                            <th>Estado</th>
                            <th>Acção</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${waitingListObj != null}">
                            <tr>
                                <td>${waitingListObj.getId()}</td>
                                <td>${waitingListObj.getSpecialty().getName()}</td>
                                <td>${waitingListObj.getDoctor().getName()}</td>
                                <td>${formatter.dateToPrettyNameString(waitingListObj.getRegisterTime())}</td>
                                <td>${waitingListObj.isActive()}</td>
                                <td>
                                    <form>
                                        <input value="${appointment.getId()}" name="appointment" type="hidden">
                                        <button formaction="view_appointment" class="btn btn-sm btn-danger" type="submit">Cancelar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

                </div>
            </div>


        </div> <!-- END Main Content Container -->

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>