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
    <title>Centro Hospitalar Upskill :: Início</title>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/visualiseDoctorsStyles.css" rel="stylesheet">

</head>
<body>

<div class="container-fluid h-100">
    <div class="row h-100">
        <%@include file="templates/sidebar.jsp"%>
        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto justify-content-between">

            <div class="d-flex flex-column h-100 cust-order-board align-items-center">
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

                <div class="db-row-ctn-center w-100">
                    <div class="db-opt-title mt-5">Médicos</div>
                    <!-- Row Container for center options of dashboard START -->
                    <div class="cus-gen-dash-allopt-ctn">


                        <!-- Col Container for center option CHECKIN of dashboard START -->
                        <div class="cus-dash-opt-ctn justify-content-center">

                            <form action="appointments_choose_specialty">
                                <c:forEach items="${doctorList}" var="doctor">
                                    <div class="card d-flex flex-column justify-content-between align-items-center m-2">
<%--                                        <a href="/user_profile_page?user=${doctor.getId()}">--%>
                                            <img class= "portrait-photo" src="/images/user_profile_photos/doctor_profile_pic.png" alt="doctor_profile_pic.png">

                                            <div class="card-body text-right">
                                                <h5 class="card-title text-center">${doctor.getName()}</h5>
                                                <p class="cus-green-p-txt mt-1">Especialidades:</p>
                                                <div class="d-flex justify-content-around">
                                                    <c:forEach items="${doctor.getSpecialtyList()}" var="specialty">
                                                        <span class="card-text mx-1">${specialty.name}</span>
                                                    </c:forEach>
                                                </div>

                                            </div>
                                            <div class="text-center">
                                                <input class="btn btn-primary cust-btn-blu-Up mb-4" type="submit" value="Marcar Consulta">
                                            </div>
<%--                                        </a>--%>
                                    </div>
                                </c:forEach>
                            </form>


                        </div><!-- Col Container for center option CHECKIN of dashboard END -->

                    </div><!-- Row Container for center options of dashboard END -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>