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
    <title>Centro Hospitalar Upskill :: Editar Utilizador</title>
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
                <div class="cust-title1-txt col-md px-3">A Minha Conta</div>
            </div>
            <!-- cust-title1-ctn End-->

            <div class="row">
                <div class="col">

                </div>
            </div>
            <!-- cust-editAccountDetails-content -->
            <div class="cust-editAccountDetails-content px-5 pt-5">

                <c:if test="${erro != null}">
                    <div class="alert alert-danger">
                            ${erro}
                    </div>
                </c:if>
                <c:if test="${accountUpdated != null}">
                    <div class="alert alert-success">
                            ${accountUpdated}
                    </div>
                </c:if>

                <!-- cust-cust-editAccountDetails-form-content -->
                <form method="post" id="editAccountDetails-form" class="cust-editAccountDetails-form" action="#">

                    <div class="form-group">

                        <div>
                            <input type="text" class="form-control" name="id" id="id" value=${profileUser.getId()}  style="display:none">
                        </div>

                        <div>
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" id="email" value=${profileUser.getEmail()} disabled>
                        </div>

                        <div>
                            <label for="name">Nome Completo:</label>
                            <input type="text" class="form-control" name="name" id="name" value="${profileUser.getName()}">
                        </div>

                        <div>
                            <label for="username">Nome de Utilizador:</label>
                            <input type="text" class="form-control" name="username" id="username" value="${profileUser.getUsername()}">
                        </div>

                        <div>
                            <label for="NIF">Numero de Contribuinte:</label>
                            <input type="text" class="form-control"  name="NIF" id="NIF" value="${profileUser.getNIF()}"/>
                        </div>

                        <div>
                            <label for="birthdate">Data de nascimento:</label>
                            <input type="date" class="form-control"  name="birthdate" id="birthdate" value="${profileUser.getBirthdate()}"/>
                        </div>

                        <div>
                            <label for="address">Morada:</label>
                            <input type="text" class="form-control" name="address" id="address" value="${profileUser.getAddress()}">
                        </div>

                        <div>
                            <label for="phoneNumber">Telemovel:</label>
                            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" value="${profileUser.getPhoneNumber()}">
                        </div>

                        <c:if test="${profileUser.getRole().getName().equals('ROLE_DOCTOR')}">
                            <div>
                                <label for="professionalCertificateNumber">Cédula profissional:</label>
                                <input type="text" class="form-control" name="professionalCertificateNumber" id="professionalCertificateNumber" value="${profileUser.getProfessionalCertificateNumber()}">
                            </div>
                        </c:if>

                        <div class="subbutton" id="change" name="change">
                            <button class="btn btn-success">Atualizar Dados</button>
                        </div>

                    </div>

                </form> <!-- cust-cust-editAccountDetails-form-content End -->




            </div> <!-- cust-editAccountDetails-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>


