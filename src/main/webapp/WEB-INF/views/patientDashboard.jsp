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
    <link href="/styles/dashboardStyles.css" rel="stylesheet">

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


                <c:if test="${appointmentMessageError != null}">
                    <div class="alert alert-danger">
                            ${appointmentMessageError}
                    </div>
                </c:if>

                <c:if test="${appointmentSucessMessage != null}">
                    <div class="alert alert-success">
                            ${appointmentSucessMessage}
                    </div>
                </c:if>


                <div class="db-row-ctn-center w-100">
                    <div class="db-opt-title mt-5">Vista Geral</div>
                    <!-- Row Container for center options of dashboard START -->
                    <div class="cus-gen-dash-allopt-ctn">


                        <!-- Col Container for center option CHECKIN of dashboard START -->
                        <div class="cus-dash-opt-ctn justify-content-center">

                            <form action="appointmentCheckInProcess">
                                <div class="card d-flex flex-column justify-content-between m-2">
                                    <div class="card-body text-center">
                                        <h5 class="card-title text-center">CHECKIN</h5>
                                        <p class="cus-green-p-txt mt-5">Introduza o número de consulta.</p>
                                        <input type="text" id="appointmentIdString" name="appointmentIdString">

                                    </div>
                                    <div class="text-center">
                                        <input class="btn btn-primary cust-btn-blu-Up mb-4" type="submit" value="EFETUAR CHECKIN >">
                                    </div>
                                </div>
                            </form>


                        </div><!-- Col Container for center option CHECKIN of dashboard END -->

                    </div><!-- Row Container for center options of dashboard END -->
                </div>



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