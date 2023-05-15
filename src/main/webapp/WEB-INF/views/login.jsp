<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/loginStyles.css" rel="stylesheet">
    <title>Login Page</title>

</head>
<body>

    <div class="container">
        <section class="cust-log-container" >  <!-- não funciona no css não sei pq -->
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-4">
                    <div class="card">
                        <div class="card-header d-flex justify-content-center">
                            <h5 class="card-title">Login</h5>
                        </div>
                        <div class="card-body">
                            <c:if test="${param.ActivatedAccount != null}">
                                <div class="alert alert-success">
                                    Conta activada com sucesso!
                                    <p>Pode-se logar para usufruir dos serviços!</p>
                                </div>
                            </c:if>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                        Ooops!.. Email ou password incorrectos!
                                </div>
                            </c:if>
                           <c:if test="${param.resetSucessful != null}">
                                <div class="alert alert-success">
                                    Password mudada com sucesso!
                                </div>
                            </c:if>
                            <c:if test="${param.AccountCreated != null}">
                                <div class="alert alert-success">
                                    Conta criada com sucesso!
                                    <p>Verifique o email para activá-la!</p>
                                </div>
                            </c:if>
                            <form method="post" action="/authenticationProcess">
                                <label for="email">Email:</label>
                                <input class="form-control mb-3" id="email" type="email" name="email">
                                <label for="password">Password:</label>
                                <input class="form-control mb-3" id="password" type="password" name="password">
                                <button class="btn btn-success">Login</button>
                            </form>

                        </div>
                        <div class="footer">
                            <div class="row d-flex justify-content-around">
                                <!-- Simple link -->
                                <div class="mx-2 col-lg-4 text-center">
                                    <a href="/forgotPassword" class="card-link" >Esqueceu a sua password?</a>
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
