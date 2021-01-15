<html lang="uk">
<head>
    <#include "../include/coreDependencies.ftl">
    <title>Create user</title>
</head>
<body>

<div class="w-100 h-100 d-flex align-items-center justify-content-center">
    <div class="col-md-8">
        <form method="post" action="/messages/new">
            <h2 class="d-flex justify-content-center">Написати</h2>
            <div class="form-group">
                <input name="receiverUsername" type="text" class="form-control m-3" placeholder="Кому?"
                       autofocus="true" required>
                <input name="title" type="text" class="form-control m-3" placeholder="Заголовок листа" required>
                <textarea name="body" class="form-control m-3" rows="3" placeholder="Тіло листа"></textarea>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-row justify-content-center">
                    <button class="btn btn-primary p-3 w-50" type="submit">Надіслати</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>