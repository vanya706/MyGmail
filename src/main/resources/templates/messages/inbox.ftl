<html>
<head>
    <#include "../include/coreDependencies.ftl">
    <title>inbox</title>
</head>
<body>

<header>
    <div class="container-fluid">
        <div class="row">
            <form method="get" action="/messages/new" class=" col-12">
                <input type="submit" class="btn btn-primary col-12" value="+ Написати"/>
            </form>
        </div>
    </div>
</header>

<main>
    <div class="rows">
        <div class="row">
            <aside class="col-3">
                <div class="list-group">
                    <a href="#"
                       class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">messages
                        which mark special<span class="badge bg-primary rounded-pill">14</span></a>
                    <a href="#"
                       class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">unread
                        messages<span class="badge bg-primary rounded-pill">367</span></a>
                    <a href="#"
                       class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">sent
                        messages<span class="badge bg-primary rounded-pill">32</span></a>
                    <a href="#"
                       class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">bin<span
                                class="badge bg-primary rounded-pill">3</span></a>
                </div>
            </aside>
            <article class="col-9">
                <div class="rows">
                    <#list inboxMessagesList as message>
                        <div class="row border-top">
                            <#if message.marked>
                                <img src="/icons/baseline_bookmark_black_48dp.png" alt="didn't mark">
                            <#else>
                                <img src="/icons/baseline_bookmark_border_black_48dp.png" alt="marked">
                            </#if>
                            <#assign s = message.title + " - " + message.body>
                            <#if message.read>
                                ${s}
                            <#else >
                                <b>${s}</b>
                            </#if>
                        </div>
                    <#else>
                        <div class="row">У вас немає повідомлень, спробуйте пізніше!</div>
                    </#list>
                </div>
            </article>
        </div>
    </div>

</main>

<footer class="footer">
    <div class="container">
        <form method="post" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" value="logout">
        </form>
    </div>
</footer>
</body>
</html>