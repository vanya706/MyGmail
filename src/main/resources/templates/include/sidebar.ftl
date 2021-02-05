<aside>
    <#if isMessageSentSuccessfully?? && isMessageSentSuccessfully>
        <p id="message_sent__text">Message was sent successfully!</p>
        <script>
            let message_sent = document.querySelector("#message_sent__text")
            setTimeout(function() {
                message_sent.remove()
            }, 5000);
        </script>
    </#if>

    <a href="/messages/new" class="new_message_btn">Create</a>

    <ul class="menu">
        <li class="menu__inbox"><a href="/messages/inbox">Inbox</a><spam>?</spam></li>
        <li class="menu__unread"><a href="/messages/unread">Unread</a><spam></spam></li>
        <li class="menu__outbox"><a href="/messages/outbox">Outbox</a><spam></spam></li>
        <li class="menu__special"><a href="/messages/special">Special</a><spam></spam></li>
        <li class="menu__drafted"><a href="/messages/drafted">Drafted</a><spam></spam></li>
        <li class="menu__spam"><a href="/messages/spam">Spam</a><spam></spam></li>
    </ul>
</aside>
