function like_on() {
    fetch('/heart/' + id, {
        method: 'POST',
        credentials: 'same-origin'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        console.log('Like on successful');
        location.reload();  //바로 반영이 되지 않는 문제가 있어서
    })
    .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
    });
}


function like_off() {
    fetch('/heart/' + id, {
        method: 'DELETE',
        credentials: 'same-origin' // 이 부분은 세션이 유지되는 경우에만 필요합니다
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        console.log('Like off successful');
        location.reload();
    })
    .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
    });
}

