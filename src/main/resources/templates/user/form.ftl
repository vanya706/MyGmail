<html>
<head>
    <#include "../include/coreDependencies.ftl">
    <title>Create user</title>
</head>
<body>

<div class="w-100 h-100 d-flex align-items-center justify-content-center">
    <div class="col-md-8">
        <form method="post" action="/user/new">
            <h2 class="d-flex justify-content-center">Зареєструватися</h2>
            <div class="form-group">
                <#if userIsExistsMessage??>
                    <div class="form-row form-control m-3 alert alert-danger" role="alert">${userIsExistsMessage}</div>
                </#if>
                <input name="username" type="text" class="form-control m-3" placeholder="Ім`я користувача" autofocus="true" required>
                <input name="password" type="password" class="form-control m-3" placeholder="Пароль" required>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-row justify-content-center">
                    <button class="btn btn-primary p-3 w-50" type="submit">Зареєструвати</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>