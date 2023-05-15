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
                <div class="cust-title-main col-md px-3 mt-5">Atribuir Horário</div>
                <div class="cust-subtitle-main col-md px-3 mt-2"> a ${employee.getName()}</div>
            </div>
            <!-- cust-title1-ctn End-->

            <!-- cust-createSchedule-content -->
            <div class="cust-createSchedule-content px-5 pt-5">

                <c:if test="${erro != null}">
                    <div class="alert alert-danger">
                            ${erro}
                    </div>
                </c:if>

                <div class="container mt-3">
                    <form action="createScheduleProcess" method="post"> <%--  PUT POST --%>
                        <input value="${employee.getId()}" name="employeeId" type="hidden">
<%--                        cust-weekDayChoice CHOOSE WEEKDAYS--%>
                        <div class="cust-weekDayChoice mb-4">
                            <p class="cust-label"> Dias de semana:</p>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="monday" name="weekDays" value="MONDAY" checked>
                                <label class="form-check-label" for="monday">Segunda-feira</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="tuesday" name="weekDays" value="TUESDAY">
                                <label class="form-check-label" for="tuesday">Terça-feira</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="wednesday" name="weekDays" value="WEDNESDAY">
                                <label class="form-check-label" for="wednesday">Quarta-feira</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="thursday" name="weekDays" value="THURSDAY">
                                <label class="form-check-label" for="thursday">Quinta-feira</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="friday" name="weekDays" value="FRIDAY">
                                <label class="form-check-label" for="friday">Sexta-feira</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="saturday" name="weekDays" value="SATURDAY">
                                <label class="form-check-label" for="saturday">Sábado</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="sunday" name="weekDays" value="SUNDAY">
                                <label class="form-check-label" for="sunday">Domingo</label>
                            </div>
                        </div> <%--  END cust-weekDayChoice CHOOSE WEEKDAYS--%>

                        <div class="cust-start-day mb-4">

                            <label for="startDay" class="cust-label form-label"> Dia de Inicio:</label>
                            <input type="date" class="form-control" name="startDay" id="startDay">

                        </div> <%--  END cust-start-hour--%>

                        <div class="cust-finish-hour mb-4">

                            <label for="finishDay" class="cust-label form-label"> Dia de Fim:</label>
                            <input type="date" class="form-control" name="finishDay" id="finishDay">

                        </div> <%--  END cust-finish-day--%>

                        <div class="cust-start-hour mb-4">

                            <label for="startHour" class="cust-label form-label"> Hora de Inicio:</label>
                            <input type="time" class="form-control" name="startHour" id="startHour">

                        </div> <%--  END cust-start-hour--%>

                        <div class="cust-finish-hour mb-4">

                            <label for="finishHour" class="cust-label form-label"> Hora de Fim:</label>
                            <input type="time" class="form-control" name="finishHour" id="finishHour">

                        </div> <%--  END cust-finish-hour--%>

                        <c:if test="${employee.getRole().getName() == 'ROLE_DOCTOR'}">

                            <div class="cust-specialty mb-4"> <%--  BEGIN specialty select--%>

                                <label for="specialties" class="cust-label form-label">Especialidades: (Pressionar ctrl para selecionar mais do que uma):</label>
                                <select multiple class="form-select" id="specialties" name="specialties">

                                    <c:forEach var="specialty" items="${doctor.getSpecialtyList()}">
                                        <option value="${specialty.getName()}">${specialty.getName()}</option>
                                    </c:forEach>

                                </select>

                            </div> <%--  END specialty select--%>

                        </c:if>

                        <button type="submit" class="cust-btn-blu-Up btn btn-primary mt-3">Submeter</button>
                    </form>
                </div>

            </div> <!-- cust-registerDoctor-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>