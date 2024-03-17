// 이메일 중복 확인
function checkEmail() {
    var email = document.getElementById("email").value;
    var resultSpan = document.getElementById("emailResult");
    fetch('/checkEmail?email=' + email)
        .then(response => response.json())
        .then(data => {
            if (data.toString() === "true" && (email.endsWith("@ewha.ac.kr") || email.endsWith("@ewhain.net"))) {
                resultSpan.textContent = "사용 가능한 이메일";
            } else if (data.toString() === "false") {
                resultSpan.textContent = "이미 존재하는 계정";
            } else {
                resultSpan.textContent = "이화인 계정만 가능";
            }
            validateSignup();
        });
}
// 회원가입 유효성 검사
function validateSignup() {
    var emailResultElement = document.getElementById("emailResult");
    var emailResult = emailResultElement ? emailResultElement.textContent : null;
    var signupBtn = document.getElementById("signupBtn");

    if (emailResult === "사용 가능한 이메일") {
        signupBtn.disabled = false;
    } else {
        signupBtn.disabled = true;
    }
}