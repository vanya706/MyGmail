<!doctype html>
<html lang="en">
<head>
    <#include "../include/meta.ftl">
    <link rel="stylesheet" href="/css/messages.css">
    <title>inbox</title>
</head>
<body id="inbox">

<#include "../include/header.ftl">
<#include "../include/sidebar.ftl">
<#include "../include/message-controls.ftl">

<div class="messages_box">
    <ul>
        <#list messagePage.content as message>
            <li class="message_item">
                <div class="message_item__marks">
                    <span class="checkbox">&#x2610;</span>
                    <span class="mark">${(message.marked) ? then("&#x2605;","&#x2606;")}</span>
                    <span class="sender">${message.senderUsername}</span>
                </div>

                <span class="content ${message.read ? then("read","unread")}">
                    ${message.title + " - " + message.body}
                </span>
                <span class="date">
                    ${(message.date.hour?string?length<2) ? then('0'+message.date.hour, message.date.hour+'')}
                    <span>:</span>
                    ${(message.date.minute?string?length<2) ? then('0'+message.date.minute, message.date.minute+'')}
                </span>
            </li>
        </#list>
    </ul>
</div>

</body>
</html>