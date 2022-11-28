<!DOCTYPE html>
<html lang="pt-BR" xmlns:th="http://www.thymeleaf.org"
xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
    
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapsed"
                data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

                </button>
                <a class="navbar-brand" href="#" th:href="@{/}">Registration and Login Module</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <!-- Sucess Message -->
                <div th:if="${param.success}">
                    <div class="alert alert-info">You have successfully registered</div>
                </div>
                <h1>Registration</h1>
                <form th:action="@{/register}" method="post" th:object="${user}">
                    <div class="form-group">
                        <label class="control-label" for="nickname">
                            Nickname
                        </label>
                        <input id="nickname" class="form-control" th:field="*{nickname}" required autofocus="autofocus"/>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="username">
                            Username
                        </label>
                        <input id="username" class="form-control" th:field="*{username}" required autofocus="autofocus"/>                        
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="password">
                            Password
                        </label>
                        <input id="password" class="form-control" th:field="*{password}" required autofocus="autofocus"/>                        
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Register</button>
                        <span>Already Registered?<a href="/" th:href="@{/login}">Login Here</a></span>
                    </div>

                </form>
            </div>
        </div>
    </div>


</body>
</html>