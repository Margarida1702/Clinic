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
    <link href="/styles/visualiseSpecialtiesStyles.css" rel="stylesheet">

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
                    <div class="db-opt-title mt-5">Novidades</div>
                    <!-- Row Container for center options of dashboard START -->
                    <div class="cus-gen-dash-allopt-ctn">

                        <div data-bs-spy="scroll" data-bs-target=".navbar" data-bs-offset="50">

                            <c:if test="${generalInfo != null}">
                                <div class="alert-success">${generalInfo}</div>
                            </c:if>

                            <c:if test="${user.getRole().getName()=='ROLE_ADMIN'}">
                                <form method="get">
                                    <button formaction="createGeneralInformation" class="btn btn-primary cust-btn-blu-Up mx-3" type="submit">Criar Novo Post</button>
                                </form>
                            </c:if>

                            <nav class="navbar navbar-expand-sm bg-light navbar-light">
                                <div class="container-fluid">
                                    <ul class="navbar-nav">
                                        <c:forEach var="post" begin="1" end="${generalInformationList.size()}">

                                            <li class="nav-item">
                                                <a class="nav-link" href="#section${post}">${generalInformationList.get(post-1).getTitle()}</a>
                                            </li>

                                        </c:forEach>
                                    </ul>
                                </div>
                            </nav>

                            <c:forEach var="post" begin="1" end="${generalInformationList.size()}">

                                <div id="section${post}" class="container-fluid bg-transparent" style="padding:100px 20px;">
                                    <h1 class="cust-color-gre-Up">${generalInformationList.get(post-1).getTitle()}</h1>
                                    <p>${formatter.dateToPrettyNameString(generalInformationList.get(post-1).getRegisterDate())}</p>
                                    <img class= "photo" src="/images/generalInformations/${generalInformationList.get(post-1).getImageName()}" alt="${generalInformationList.get(post-1).getImageName()}">
                                    <p>${generalInformationList.get(post-1).getBody()}</p>
                                </div>

                            </c:forEach>

                        </div>


                    </div><!-- Row Container for center options of dashboard END -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>