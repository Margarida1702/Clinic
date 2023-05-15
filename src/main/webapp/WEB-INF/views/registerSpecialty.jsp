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
    <link href="/styles/registerSpecialtyStyles.css" rel="stylesheet">
    <link href="/styles/doctorManagementStyles.css" rel="stylesheet">
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
                <div class="cust-title1-txt col-md px-3">Registar Especialidade</div>
            </div>
            <!-- cust-title1-ctn End-->

            <!-- cust-registerDoctor-content -->
            <div class="cust-registerSpecialty-content px-5 pt-5">

                <c:if test="${erro != null}">
                    <div class="alert alert-danger">
                            ${erro}
                    </div>
                </c:if>

                <!-- cust-cust-doctor-form-content -->
                <form method="post" id="specialty-form" class="cust-specialty-form" action="registerSpecialtyProcess" enctype="multipart/form-data">

                    <div class="form-group">

                        <div>
                            <label for="name">Nome:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="name" id="name" placeholder="Nome">
                        </div>

                        <div>
                            <label for="description">Descrição:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <textarea class="form-control" name="description" id="description" placeholder="Descrição" rows="10"></textarea>
                            <span class=count id="count">Limite de 500 caracteres: </span>
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
                            <label for="imageName">Nome da imagem (case-sensitive, sem extenção):<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                            <input type="text" class="form-control" name="imageName" id="imageName" placeholder="Nome da imagem">
                        </div>


<%--                        <form method="POST" action="upload" enctype="multipart/form-data">--%>
<%--
                            Ícone:<p class="mandatory_filling" style="text-align: center"> Inserção Obrigatória</p><br>
                            <input type="file" name="file" id="file" /> <br/>--%>
                        <!-- buttons -->
                        <div class="row">

                            <div class="subbutton" id="submit" name="submit">
                                <button class="btn btn-success">Submeter</button>
                            </div>

                            <div class="subbutton d-inline" id="cancel" name="cancel">
                                <button class="btn btn-danger">Cancelar</button>
                            </div>

                        </div>
                        <!-- buttons END-->



                </form> <!-- cust-cust-doctor-form-content End -->
                <%--                </form> <!-- END FORM FOR IMAGE UPLOAD -->--%>
            </div> <!-- cust-registerDoctor-content End-->


        </div> <!-- Main Content Container End-->

    </div> <!-- cust-row-sb-and-maincont-ctn End -->

</div> <!-- cust-sub-body-cont End -->

</body>
</html>