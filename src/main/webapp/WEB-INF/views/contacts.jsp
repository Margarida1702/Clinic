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
    <link href="/styles/contactsStyles.css" rel="stylesheet">


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

                <div class="db-row-ctn-center w-100">
                    <!-- Row Container for center options of dashboard START -->
                        <div class="cus-gen-dash-allopt-ctn">


                        <!-- Col Container for center option CHECKIN of dashboard START -->
                        <div class="cus-dash-opt-ctn justify-content-center">

                            <!-- ======= Contact Section ======= -->
                            <section id="contact" class="contact">
                                <div class="container">

                                    <div class="section-title mt-5 cust-color-gre-Up">
                                        <p>Como chegar às nossas instalações</p>
                                    </div>
                                </div>

                                <div>
                                    <iframe style="border:0; width: 100%; height: 350px;" src="https://maps.google.com/maps?q=upskil&t=&z=17&ie=UTF8&iwloc=&output=embed" frameborder="0" allowfullscreen></iframe>
                                </div>

                                <div class="container">
                                    <div class="row mt-5">

                                        <div class="col-lg-4">
                                            <div class="info">
                                                <div class="address">
                                                    <i class="bi bi-geo-alt"></i>
                                                    <h4>Localização:</h4>
                                                    <p>Largo Casal Vistoso, 1900-142 Lisboa</p>
                                                </div>

                                                <div class="email">
                                                    <i class="bi bi-envelope"></i>
                                                    <h4>Email:</h4>
                                                    <p>centro.hospitalar.upskill.grupog@gmail.com</p>
                                                </div>

                                                <div class="phone">
                                                    <i class="bi bi-phone"></i>
                                                    <h4>Contato:</h4>
                                                    <p>+351 925 451 609</p>
                                                </div>

                                            </div>

                                        </div>

                                        <div class="col-lg-8 mt-5 mt-lg-0">

                                            <form action="#" method="post" role="form" class="php-email-form">
                                                <div class="row">
                                                    <div class="col-md-6 form-group">
                                                        <input type="text" name="name" class="form-control" id="name" placeholder="Nome" required>
                                                    </div>
                                                    <div class="col-md-6 form-group mt-3 mt-md-0">
                                                        <input type="email" class="form-control" name="email" id="email" placeholder="Email" required>
                                                    </div>
                                                </div>
                                                <div class="form-group mt-3">
                                                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Assunto" required>
                                                </div>
                                                <div class="form-group mt-3">
                                                    <textarea class="form-control" name="message" rows="5" placeholder="Mensagem" required></textarea>
                                                </div>
                                                <%--<div class="my-3">
                                                    <div class="loading">Processando</div>
                                                    <div class="error-message"></div>
                                                    <div class="sent-message">A sua mensagem foi enviada. Obrigado!</div>
                                                </div>--%>
                                                <div class="text-center"><button class="btn btn-primary cust-btn-blu-Up mt-2" type="submit">Enviar Messagem</button></div>
                                            </form>

                                        </div>

                                    </div>

                                </div>
                            </section><!-- End Contact Section -->

                            </div>

                        </div><!-- Col Container for center option CHECKIN of dashboard END -->

                         </div><!-- Row Container for center options of dashboard END -->
                        <div class="cust-bot-banner d-flex justify-content-center">
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

</div>


</body>
</html>