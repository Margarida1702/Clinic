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
    <title>Centro Hospitalar Upskill :: Registar Especialidade</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/dashboardStyles.css" rel="stylesheet">
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


            <!-- cust-title1-ctn  -->
            <div class="cust-title-main">
                <div class="cust-title-main col-md px-3 mt-5">Consulta</div>
                <div class="cust-subtitle-main col-md px-3 mt-2"> a ${patient.getName()}</div>
            </div>
            <!-- cust-title1-ctn End-->

            <!-- cust-createSchedule-content -->
            <div class="cust-createSchedule-content px-5 pt-2">

                <c:if test="${erro != null}">
                    <div class="alert alert-danger">
                            ${erro}
                    </div>
                </c:if>

                <div class="container mt-1">

                    <table class="table table-lg table-striped">
                        <thead>
                        <tr>
                            <td>Nº da consuta</td>
                            <td>${appointment.id}</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${!user.getRole().getName().equals('ROLE_PATIENT')}"> <!-- não faz sentido o utente ver o seu proprio nome na consulta -->
                        <tr>
                            <td>Nome do Utente</td>
                            <td>
                                <form>
                                    <input value="${appointment.patient.id}" name="patient" type="hidden">
                                    <button formaction="appointments_main_bypatient" class="btn btn-md border-0" style="padding: 0px" type="submit">${appointment.patient.name}</button>
                                </form>

                            </td>
                        </tr>
                        </c:if>
                        <tr>
                            <td>Nome do Médico</td>
                            <c:if test="${!user.getRole().getName().equals('ROLE_PATIENT')}"> <!-- todos menos o utente e podem ver as consultas dos medicos -->
                                <td>
                                    <form>
                                        <input value="${appointment.doctor.id}" name="doctor" type="hidden">
                                        <button formaction="appointments_main_bydoctor" class="btn btn-md border-0" style="padding: 0px" type="submit">${appointment.doctor.name}</button>
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${user.getRole().getName().equals('ROLE_PATIENT')}"> <!-- se for utente, vê nome do medico mas sem hiperligaçao para as consultas desse medico -->
                            <td>${appointment.doctor.name}
                                </c:if>
                        </tr>
                        <tr>
                            <td>Especialidade</td>
                            <td>${appointment.specialty.name}</td>
                        </tr>
                        <tr>
                            <td>Hora de início programada</td>
                            <td>
                                <c:set var="hour" value="${appointment.plannedStartTime.getHour()}"/>
                                <c:set var="minute" value="${appointment.plannedStartTime.getMinute()}"/>
                                ${(hour >= 10) ? hour : ("0".concat(hour))}:${(minute >= 10) ? minute : ("0".concat(minute))}
                            </td>
                        </tr>
                        <tr>
                            <td>Hora de fim programada</td>
                            <td>
                                <c:set var="hour" value="${appointment.plannedEndTime.getHour()}"/>
                                <c:set var="minute" value="${appointment.plannedEndTime.getMinute()}"/>
                                ${(hour >= 10) ? hour : ("0".concat(hour))}:${(minute >= 10) ? minute : ("0".concat(minute))}
                            </td>
                        </tr>
                        <tr>
                            <td>Hora de início real</td>
                            <td>
                                <c:if test="${appointment.realStartTime == null}">Por iniciar
                                </c:if>
                                <c:if test="${appointment.realStartTime != null}">
                                    <c:set var="hour" value="${appointment.realStartTime.getHour()}"/>
                                    <c:set var="minute" value="${appointment.realStartTime.getMinute()}"/>
                                    ${(hour >= 10) ? hour : ("0".concat(hour))}:${(minute >= 10) ? minute : ("0".concat(minute))}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Hora de fim real</td>
                            <td>
                                <c:if test="${appointment.realEndTime == null}">--
                                </c:if>
                                <c:if test="${appointment.realEndTime != null}">
                                    <c:set var="hour" value="${appointment.realEndTime.getHour()}"/>
                                    <c:set var="minute" value="${appointment.realEndTime.getMinute()}"/>
                                    ${(hour >= 10) ? hour : ("0".concat(hour))}:${(minute >= 10) ? minute : ("0".concat(minute))}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Estado</td>
                            <td>${appointment.status}</td>
                        </tr>

                        <form action="endAppointment" method="post">
                        <input value="${appointment.getId()}" name="appointment" type="hidden">
                        <tr>
                            <td>Observações<p class="mandatory_filling" style="text-align: right"> Preenchimento Obrigatório</p></td>
                            <td><div>
                                <textarea class="form-control" name="description" id="description" placeholder="Descrição" rows="10"></textarea>
                                <span class=count id="count">Limite de 1500 caracteres: </span>
                            </div>
                                <%--count of description lenght real time--%>
                                <script type="text/javascript">
                                    var a = document.getElementById("description");
                                    a.addEventListener("keyup",function(){
                                        document.getElementById("count").innerHTML = "Limite de 1500 caracteres:" + " "+ a.value.length;
                                    })
                                </script>
                                <%--END count of description lenght real time--%>
                            </td>
                        </tr>

                        <tr>
                            <td>Preço<p class="mandatory_filling" style="text-align: right"> Preenchimento Obrigatório</p></td>
                            <td>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="price" id="price" aria-label="Amount (to the nearest dollar)">
                                    <div class="input-group-append">
                                        <span class="input-group-text">€</span>
                                    </div>
                                </div>
                            </td>
                        </tr>

                            <tr>
                                <button type="submit" class="cust-btn-blu-Up btn btn-primary mt-3">Finalizar Consulta</button>
                            </tr>

                    </form>

                        </tbody>
                    </table>
                </div>

            </div> <!-- cust-registerDoctor-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>