<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${error_number!"error"}</title>
</head>
<body>

<#if error_number??>
    <h1>Error: ${error_number}</h1>
</#if>
<#if exception??>
    <h2>Excaption message: ${exception}</h2>
</#if>

<h1><a href="/message/inbox">Go to my messages</a></h1>

<#include "../include/footer.ftl">