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
                <div class="cust-title1-txt col-md px-3 mt-5">Editar Especialidade ${specialty.getName()}</div>
            </div>
            <!-- cust-title1-ctn End-->

            <div class="row">
                <div class="col">

                </div>
            </div>
            <!-- cust-editAccountDetails-content -->
            <div class="cust-editAccountDetails-content px-5 pt-5">

                <!-- cust-cust-editAccountDetails-form-content -->
                <form method="post" id="editSpecialtyProcess-form" class="cust-editAccountDetails-form" action="editSpecialtyProcess">

                    <c:if test="${error != null}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>

                    <div class="form-group">

                        <div>
                            <input type="text" class="form-control" name="id" id="id" value=${specialty.getId()}  style="display:none">
                        </div>

                        <div>
                            <label for="name">Nome:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="name" id="name" value=${specialty.getName()}>
                        </div>


                        <div>
                            <label for="description">Descrição:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <textarea class="form-control" name="description" id="description" rows="15">${specialty.getDescription()}</textarea>
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

                        <div>
                            <label for="imageName">Nome da Imagem:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="imageName" id="imageName" value="${specialty.getImageName()}">
                        </div>

                        <div class="subbutton" id="change" name="change">
                            <button class="btn btn-primary cust-btn-blu-Up">Atualizar</button>
                        </div>

                    </div>

                </form> <!-- cust-cust-editAccountDetails-form-content End -->
            </div> <!-- cust-editAccountDetails-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>