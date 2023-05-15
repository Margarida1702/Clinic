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
                    <div class="row" style="width: 85%">

                        <c:if test="${error != null}">
                            <div class="alert alert-danger">
                                    ${error}
                            </div>
                        </c:if>
                        <%--                        <form method="post">--%>
                        <%--                            <div class="form-group d-flex col-5">--%>
                        <%--                            <input type="number" class="form-control" id="pesquisarConsulta" placeholder="Pesquisar consulta por id">--%>
                        <%--                            <input value="${}" name="id" type="hidden">--%>
                        <%--                            <button formaction="pesquisarConsulta" class="btn btn-success" type="submit">Pesquisar consulta</button>--%>
                        <%--                            </div>--%>
                        <%--                        </form>--%>

                        <c:if test="${appointment == null && user.getRole().getName().equals('ROLE_DOCTOR')}"> <!-- apenas o médico vê mensagem a aguardar check in e botao para ver lista de consultas -->

                            <div class="d-flex align-items-center flex-column ">
                                <div class="text_t">A aguardar check in de utentes</div>
                                <form method="get" class="cust-header-menu-ctn m-2">
                                    <button formaction="appointments_main" type="submit" class="btn btn-primary btn-lg cust-btn-blu-Up">Ver lista de consultas</button>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${appointment != null}">
                            <table class="table table-lg table-striped mt-5">
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

                                <c:if test="${!user.getRole().getName().equals('ROLE_PATIENT') and appointment.getStatus().equals('Finalizada')}"> <!-- Todos os users podem ver a text area, excepto o utente -->
                                    <tr>
                                        <td>Observações</td>
                                            <td><div>
                                                <textarea class="form-control" name="observationsAlt" id="observationsAlt" placeholder="${appointment.getDescription()}" rows="10" onfocusout="postData(value)" disabled></textarea>
                                            </div><%--END count of description lenght real time--%>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>Preço</td>
                                        <td>
                                            <div class="input-group">
                                                <input type="text" class="form-control" name="price" id="priceaux" aria-label="Amount (to the nearest dollar)" placeholder="${appointment.getPrice()}" disabled>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">€</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                </c:if>

                                </tbody>
                            </table>

                            <form>
                                <button formaction="appointments_main" class="btn btn-primary cust-btn-blu-Up mt-4" formmethod="get" type="submit">Voltar atrás</button>
                            </form>

                        </c:if>
                        <c:if test="${appointment.getStatus().equals('Marcada')}}">
                            <div class="text_l" style="text-align: left">A aguardar check in do utente</div>
                        </c:if>
                        <c:if test="${user.getRole().getName().equals('ROLE_DOCTOR')}">
                            <c:if test="${appointment != null}">
                                <c:if test="${appointment.realStartTime == null}">
                                    <c:if test="${appointment.getStatus().equals('Check-In efectuado')}"> <!-- Botao de iniciar consulta so fica disponivel se estado de consulta for CHECKIN-->
                                        <form method="post">
                                            <input value="${appointment.id}" name="appointment" type="hidden">
                                            <button formaction="startAppointment" class="btn btn-success mt-2" type="submit">Iniciar consulta</button>
                                        </form>
                                    </c:if>
                                </c:if>
                                <c:if test="${appointment.getStatus().equals('Em curso')}"> <!-- Botao de terminar consulta so fica disponivel se estado de consulta for STARTED -->
                                    <form method="post">
                                        <input value="${appointment.id}" name="appointment" type="hidden">
                                        <button formaction="endAppointment" class="btn btn-primary mt-2" type="submit">Terminar consulta</button>
                                    </form>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${user.getRole().getName().equals('ROLE_DOCTOR') || user.getRole().getName().equals('ROLE_PATIENT')}">


                            <c:if test="${appointment.getStatus().equals('Marcada') || appointment.getStatus().equals('Check-In efectuado')}">
                                <form>
                                    <input value="${appointment.getId()}" name="appointment" type="hidden">
                                    <input value="${user}" name="appointment" type="hidden">
                                    <button formaction="cancel_appointment" class="btn btn-danger" formmethod="post" type="submit" style="margin-top: 10px">Cancelar</button>
                                </form>
                            </c:if>

                        </c:if>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>


</body>