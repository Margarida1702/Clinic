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
    <link href="/styles/signupStyles.css" rel="stylesheet">
    <title>Register Account</title>
</head>

<body>
    <div class="main">
        <div class="container">
            <div class="row ">
                <div class="col-4">
                    <div class="signup-content">
                        <form method="post" id="signup-form" class="signup-form" action="signupProcess">
                            <h2 class="form-title">Registe a sua conta!</h2>

                            <c:if test="${erro != null}">
                                <div class="alert alert-danger">
                                    ${erro}
                                </div>
                            </c:if>
                            <div class="form-group">

                                <div>
                                    <label for="username">Nome de Utilizador:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                                    <input type="text" class="form-control" name="username" id="username" placeholder="Nome de Utilizador">
                                </div>
                                <div>
                                    <label for="name">Nome Completo:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                                    <input type="text" class="form-control" name="name" id="name" placeholder="Nome Completo">
                                </div>

                                <div>
                                    <label for="NIF">Numero de Contribuinte:<p class="mandatory_filling" style="text-align: center">Preenchimento Obrigatório</p></label>
                                    <input type="text" class="form-control"  name="NIF" id="NIF" placeholder="NIF"/>
                                </div>

                                <div>
                                    <label for="birthdate">Data de nascimento:<p class="mandatory_filling">Preenchimento Obrigatório</p></label>
                                    <input type="date" class="form-control" name="birthdate" id="birthdate">
                                </div>

                                <div>
                                    <label for="address">Morada:</label>
                                    <input type="text" class="form-control" name="address" id="address" placeholder="Morada">
                                </div>

                                <div>
                                    <label for="email">Email:<p class="mandatory_filling">Preenchimento Obrigatório</p></label>
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                                </div>

                                <div>
                                    <label for="phoneNumber">Telemovel:</label>
                                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Telemovel">
                                </div>

                                <div>
                                    <label for="password">Password:<p class="mandatory_filling">Preenchimento Obrigatório</p></label>
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                                </div>

                                <div class="subbutton">
                                    <button class="btn btn-success">Submeter</button>
                                </div>
                            </div>
                        </form>
                        <p class="loginhere">Já tem uma conta? <a href="login">Faça login aqui</a> </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>