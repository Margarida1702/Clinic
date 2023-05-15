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
    <title>Centro Hospitalar Upskill :: Utilizadores</title>
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
                <div class="cust-title1-txt col-md px-3 m-2">Lista de Utilizadores</div>
            </div>

            <div class="row">
                <div class="col-md">

                    <div class=" cust-header-ctn d-flex flex-md-wrap">

                        <form method="get" class="cust-header-menu-ctn m-2">
                            <button formaction="registerDoctor" type="submit" class="btn btn-primary cust-btn-blu-Up">Novo Médico</button>
                        </form>

                        <form method="get" class="cust-header-menu-ctn m-2">
                            <button formaction="registerDeskReceptionist" type="submit" class="btn btn-primary cust-btn-blu-Up">Novo Recepcionista</button>
                        </form>

                        <form method="get" class="cust-header-menu-ctn m-2">
                            <button formaction="registerAdmin" type="submit" class="btn btn-primary cust-btn-blu-Up">Novo Administrador</button>
                        </form>

                    </div>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Descrição</th>
                            <th>Acção</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="profileUser" items="${allUsers}">
                            <tr>
                                <td>${profileUser.name}</td>
                                <td>${profileUser.email}</td>
                                <td>${profileUser.getUserSimpleTypeToString()}</td>
                                <td>
                                    <form method="post">
                                        <input value="${profileUser.getId()}" name="profileUserId" type="hidden">
                                        <button formaction="userProfilePage" class="btn btn-sm btn-success" type="submit">Ver Perfil</button>

                                        <c:if test="${profileUser.isActiveAccount()}">
                                            <button formaction="disableUser" class="btn btn-sm btn-secondary" type="submit">Desactivar</button>
                                        </c:if>

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