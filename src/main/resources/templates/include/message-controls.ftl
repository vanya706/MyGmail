<div class="message_controls">
    <div class="message_control">
        <span class="checkbox">&#x2610;</span>
    </div>

    <div class="pagination">

        <#assign p = messagePage>

        <div class="pagination_info">
            ${p.getNumber() * p.getSize()}-${p.hasNext()?
            then((p.getNumber() + 1) * p.getSize(), p.getTotalElements())}
            from ${p.getTotalElements()}
        </div>

        <div class="pagination__change_page">
            <a href="?page=${p.hasPrevious()?
            then(p.getNumber() - 1, p.getNumber())}" class="${p.hasPrevious()?string("", "disable")}">&laquo;</a>
            <a href="?page=${p.hasNext()?
            then(p.getNumber() + 1, p.getNumber())}" class="${p.hasNext()?string("", "disable")}">&raquo;</a>
        </div>
    </div>
</div>