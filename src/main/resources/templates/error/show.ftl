<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${errorCode!"error"}</title>
</head>
<body>

<#if errorCode??>
    <h1>Error: ${errorCode}</h1>
</#if>
<#if errorMessage??>
    <h2>Error message: ${errorMessage}</h2>
</#if>

<h1><a href="/message/inbox">Go to my messages</a></h1>

</body>
</html>