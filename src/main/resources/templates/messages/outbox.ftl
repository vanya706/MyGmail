<!doctype html>
<html lang="en">
<head>
    <#include "../include/meta.ftl">
    <link rel="stylesheet" href="/css/messages.css">
    <title>outbox</title>
</head>
<body id="outbox">

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
                    <span class="receiver">To: <#list message.receiverUsernames as receiverUsername>
                            ${receiverUsername + receiverUsername_has_next?then(", ", "")}</#list></span>
                </div>

                <span class="content">
                    ${message.title + " - " + message.body}
                </span>
                <span class="date">
                    ${(message.date.hour?string?length<2) ?
                    then('0'+message.date.hour, message.date.hour+'')}:${(message.date.minute?string?length<2) ?
                    then('0'+message.date.minute, message.date.minute+'')}
                </span>
            </li>
        </#list>
    </ul>
</div>

</body>
</html>