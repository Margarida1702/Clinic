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
    <title>Centro Hospitalar Upskill :: Registar Médico</title>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/doctorManagementStyles.css" rel="stylesheet">
    <link href="/styles/registerDoctorStyles.css" rel="stylesheet">

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
            <div class="cust-title1-ctn">

                <div class="cust-title1-txt col-md px-3 mt-5">Registar Médico</div>

            </div>
            <!-- cust-title1-ctn End-->

            <div class="row">
                <div class="col">

                </div>
            </div>
            <!-- cust-registerDoctor-content -->
            <div class="cust-registerDoctor-content px-5 pt-5">

                <c:if test="${erro != null}">
                    <div class="alert alert-danger">
                            ${erro}
                    </div>
                </c:if>

                <!-- cust-cust-doctor-form-content -->
                <form method="post" id="doctor-form" class="cust-doctor-form" action="doctorRegisterProcess">

                    <div class="form-group">

                        <div>
                            <label for="name">Nome Completo:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="name" id="name" placeholder="Nome Completo">
                        </div>

                        <div>
                            <label for="username">Nome de Utilizador:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="Nome de Utilizador">
                        </div>


                        <div>
                            <label for="NIF">Numero de contribuinte:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="NIF" id="NIF" placeholder="NIF">
                        </div>

                        <div>
                            <label for="birthdate">Data de nascimento:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="date" class="form-control" name="birthdate" id="birthdate">
                        </div>

                        <div>
                            <label for="address">Morada:</label>
                            <input type="text" class="form-control" name="address" id="address" placeholder="Morada">
                        </div>

                        <div>
                            <label for="email">Email:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                        </div>

                        <div>
                            <label for="phoneNumber">Telemovel:</label>
                            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Telemovel">
                        </div>

                        <div>
                            <label for="professionalCertificateNumber">Nº de cédula profissional:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="professionalCertificateNumber" id="professionalCertificateNumber" placeholder="Nº de cédula profissional">
                        </div>

                        <div>


                                <label for="specialtiesIds" class="form-label">Especialidades: (Pressionar ctrl para selecionar mais do que uma):</label>
                                <select multiple class="form-select" id="specialtiesIds" name="specialtiesIds">

                                    <c:forEach var="specialty" items="${specialties}">
                                        <option value="${specialty.getId()}">${specialty.getName()}</option>
                                    </c:forEach>

                                </select>

                        </div>

                        <div>
                            <label for="password">Password:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        </div>

                        <div class="subbutton" id="submit" name="submit">
                            <button class="btn btn-success">Submeter</button>
                        </div>

                    </div>



                </form> <!-- cust-cust-doctor-form-content End -->
            </div> <!-- cust-registerDoctor-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>