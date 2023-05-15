<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/loginStyles.css" rel="stylesheet">
    <title>Forgot Password</title>
</head>
<body>
    <div class="container">
        <section class="cust-log-container" >  <!-- não funciona no css não sei pq -->
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-4">
                    <div class="card">
                        <div class="card-header d-flex justify-content-center">
                            <h5 class="card-title">Esqueceu a Pass...</h5>
                        </div>
                        <div class="card-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    Não encontramos conta associada ao email indicado...
                                </div>
                            </c:if>
                            <c:if test="${param.emailError != null}">
                                <div class="alert alert-danger">
                                    Ooops!.. Erro ao enviar o email :(
                                </div>
                            </c:if>
                            <c:if test="${param.emailSent != null}">
                                <div class="alert alert-success">
                                    Enviamos um link para o email indicado. Por favor verifique!
                                </div>
                            </c:if>
                            <form action="forgotPasswordProcess" method="post">
                                <div>
                                    <p>Iremos enviar um e-mail para a conta indicada</p>
                                </div>
                                <label for="email">Email:</label>
                                <input class="form-control mb-3" id="email" type="email" name="email" required autofocus>
                                <button class="btn btn-primary">Pedir</button>
                            </form>
                        </div>
                        <div class="footer">
                            <div class="row d-flex justify-content-around">
                                <!-- Simple link -->
                                <div class="mx-2 col-lg-4 text-center">
                                    <a href="/" class="card-link" >Home</a>
                                </div>
                                <div class="mx-2 col-lg-4 text-center">
                                    <a href="/signup" class="card-link">Criar novo registo</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>
