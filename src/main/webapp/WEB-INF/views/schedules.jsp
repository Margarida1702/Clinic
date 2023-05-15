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
    <title>Centro Hospitalar Upskill :: Funcionários</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
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

            <div class="cust-title1-ctn">
                <div class="cust-title1-txt col-md px-3 m-2">Horário</div>
            </div>

            <div class="row">
                <div class="col-md">

                    <div class=" cust-header-ctn d-flex flex-md-wrap">

                        <div class="cust-header-menu-ctn m-2">
                            <button formaction="addScale" type="submit" class="btn btn-primary cust-btn-blu-Up">Adicionar Escala</button>
                        </div>

                    </div>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Dia da semana</th>
                            <th>Hora de Entrada</th>
                            <th>Hora de Saída</th>
                            <th>Acção</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="scale" items="${scalesList}">
                            <tr>
                                <td>${scale.getWorkDayWeek()}</td>
                                <td>${scale.getStartTime()}</td>
                                <td>${scale.getEndTime()}</td>
                                <td>
                                    <form method="post">
                                        <input value="${scale.getId()}" name="id" type="hidden">
                                        <button formaction="editScale" class="btn btn-sm btn-primary" type="submit">Editar</button>
                                        <button formaction="deleteScale" class="btn btn-sm btn-danger" type="submit">Apagar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>


        </div> <!-- END Main Content Container -->

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>