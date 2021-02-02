<header>
    <div class="header-logo">
        <span>&#9776;</span>
        <img src="https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_dark_1x_r2.png" alt="Logo">
    </div>

    <form action="/messages/search" method="get" class="search">
        <input name="search" id="search" type="search" placeholder="Search in the mail..."
               value="<#if search??>${search}</#if>" required />
        <button type="submit">Search</button>
    </form>

    <div class="header-options">
        <form method="post" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" value="&#128682;" class="logout">
        </form>
    </div>
</header>