<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Centro Hospitalar Upskill :: Consultas</title>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/appointments.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid h-100">
    <div class="row h-100">
        <%@include file="templates/sidebar.jsp"%>
        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto justify-content-between">

            <div class="d-flex flex-column h-100 justify-content-between cust-order-board align-items-center">
                <!-- cust-t-ban START-->
                <div class="cust-t-ban text-center">

                    <div class="row d-flex align-items-center"> <!-- Sou obrigado a especificar altura? -->

                        <div class="cust-tban-logo-cont col-lg-2 mt-5 px-4 d-flex justify-content-between align-items-end h-100">
                            <img class="img-fluid " src="/images/common/Grupo 7.svg" alt="cust-tban-logo">
                        </div>
                        <div class="cust-tban-descr-container col-lg d-flex mt-5 px-4 align-content-between justify-content-start">
                            <div class="row">
                                <div class="cust-tban-desc-title">CUIDE DE SI E DOS SEUS.</div>
                                <div class="cust-tban-desc-txt mt-2">
                                    O Centro Hospitalar UPskill inspira-se nos seus
                                    clientes para estar na vanguarda na proteção das famílias. Estamos mais inovadores
                                    na prestação de cuidados de saúde, mais próximos e mais ágeis.
                                </div>
                            </div>
                        </div>
                    </div>

                </div><!-- cust-t-ban END-->
                <div class="mt-4">
                    <form class="dropdown text-center">
                        <input type="hidden" name="specialty" value="${param.specialty}">
                        <label for="doctor" class="text-center">Médico</label>
                        <select class="form-control cus-select p-3 cust-btn-blu-Up text-center mt-2" id="doctor" name="doctor" onchange="this.form.submit()">
                                <option disabled selected>Escolher Médico</option>
                            <c:forEach items="${doctorList}" var="doctor">
                                <option
                                        <c:if test="${param.doctor == doctor.id}">
                                            selected
                                        </c:if>
                                        value="${doctor.id}">${doctor.name}
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                </div>


                <section class="appointments">
                    <div class="d-flex justify-content-center">
                        <div class="">
                            <div class="container">
                                <div class="row" style="max-width: 800px">
                                    <form class="col-lg-1 col-6 text-center order-2 order-lg-0">
                                        <input type="hidden" name="weekOffSet" value="${weekOffSet - 1}">
                                        <input type="hidden" name="specialty" value="${param.specialty}">
                                        <input type="hidden" name="doctor" value="${param.doctor}">

                                        <button class="btn border-0">
                                            <img class="arrow" src="/images/common/left_arrow.png" alt="seta_p_esquerda">
                                        </button>
                                    </form>



                                    <div class="col-lg-10 cus-shed-table-cont">
                                        <table class="table table-striped" style="table-layout: fixed; text-align: center">
                                            <tr>
                                                <th>Seg</th>
                                                <th>Ter</th>
                                                <th>Qua</th>
                                                <th>Qui</th>
                                                <th>Sex</th>
                                                <th>Sáb</th>
                                                <th>Dom</th>
                                            </tr>
                                            <tr>
                                                <c:forEach var="day" begin="1" end="7">
                                                    <td>${weekDay.plusDays(day - weekDay.getDayOfWeek().getValue()).getDayOfMonth()}
                                                        /${weekDay.plusDays(day - weekDay.getDayOfWeek().getValue()).getMonth().getValue()}
                                                    </td>

                                                </c:forEach>
                                            </tr>
                                            <c:forEach var="hora" begin="0" end="23">
                                                <tr>
                                                    <c:forEach var="day" begin="1" end="7">
                                                        <c:set var="hour_key" value='${"".concat(hora).concat("_").concat(weekDay.plusDays(day - weekDay.getDayOfWeek().getValue()).getDayOfMonth()).concat("_").concat(weekDay.getMonth().getValue()).concat("_").concat(weekDay.getYear())}'/>
                                                        <td class="slot-hour p-0 ${slotMap.get(hour_key) != null ? 'cust-bg-gre-Up' : 'cus-inactive-slot'}"> <!--    TODO -->
                                                            <c:if test="${slotMap.get(hour_key) != null}">
                                                                <a class=""
                                                                        href="/confirm_appointment?specialty=${param.specialty}&doctor=${param.doctor}&hour=${hora}&day=${weekDay.plusDays(day - weekDay.getDayOfWeek().getValue()).getDayOfMonth()}&month=${weekDay.getMonth().getValue()}&year=${weekDay.getYear()}">
                                                                        ${(hora >= 10) ? hora : ("0".concat(hora))}:00
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${slotMap.get(hour_key) == null}">
                                                                <div class="cus-inactive-slot-link">
                                                                        ${(hora >= 10) ? hora : ("0".concat(hora))}:00
                                                                </div>

                                                            </c:if>
                                                        </td>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>


                                        </table>
                                    </div>
                                    <form class="col-lg-1 col-6 text-center order-2 order-lg-1">
                                        <input type="hidden" name="weekOffSet" value="${weekOffSet + 1}">
                                        <input type="hidden" name="specialty" value="${param.specialty}">
                                        <input type="hidden" name="doctor" value="${param.doctor}">
                                        <button class="btn border-0">
                                            <img class="arrow" src="/images/common/right_arrow.png" alt="seta_p_direita">
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>



                <div class="cust-bot-banner text-center">
                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="/images/dashboard/Grupo 30-2x.png" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="/images/dashboard/Grupo 30-2x.png" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="/images/dashboard/Grupo 30-2x.png" class="d-block w-100" alt="...">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                </div>


            </div>





        </div>

    </div>

</div>


</body>
</html>