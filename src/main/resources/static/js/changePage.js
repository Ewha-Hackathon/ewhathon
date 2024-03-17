function changePage(url){
    window.location.href = url;
}

function changePage(element){
    let id = element.id;
    window.location.href = "event/detail?event_id=" + id;
}