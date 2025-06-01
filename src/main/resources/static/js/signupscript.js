let isIdChecked = false;

function checkDuplicate() {
    //event.preventDefault();
    const userid = document.getElementById("userid").value;
    const result = document.getElementById("checkResult");

    if (!userid.trim()) {
        result.textContent = "아이디를 입력해주세요.";
        result.style.color = "orange";
        return;
    }
    fetch("/users/check-id", {
        method : "POST",
        headers : {
             "Content-Type": "application/x-www-form-urlencoded",
        },
        body : `userid=${encodeURIComponent(userid)}`
        })
        .then(res => res.json())
        .then(data => {
            if (data.available) {
                result.textContent = "사용 가능한 아이디입니다.";
                result.style.color = "lightgreen";
                isIdChecked = true;
            } else {
                result.textContent = "이미 사용 중인 아이디입니다.";
                result.style.color = "red";
                isIdChecked = false;
            }
        })
        .catch(err => {
            result.textContent = "오류가 발생했습니다.";
            result.style.color = "yellow";
            isIdChecked = false;
            console.error(err);
        });
}

function checkPassword(){
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const checkConfirmPassword = document.getElementById("checkConfirmPassword");

    if(password != confirmPassword){
        checkConfirmPassword.textContent = "비밀번호가 일치하지 않습니다.";
        checkConfirmPassword.style.color = "red";
    } else {
        checkConfirmPassword.textContent = "비밀번호가 일치합니다.";
        checkConfirmPassword.style.color = "lightgreen";
    }
}

// 한글 체크
function validateUserId(userid) {
    const koreanRegex = /[ㄱ-ㅎ가-힣]/;
    return !koreanRegex.test(userid); // 한글이 있으면 false
}
// 이메일 형식 체크
function validateEmailFormat(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
}


// 비밀번호 형식 체크
function validatePassword(password) {
    const lengthCheck = /^.{8,16}$/;
    const lowerCase = /[a-z]/;
    const upperCase = /[A-Z]/;
    const number = /[0-9]/;
    const specialChar = /[!@#$%^&*(),.?":{}|<>]/;

    return lengthCheck.test(password) &&
           lowerCase.test(password) &&
           upperCase.test(password) &&
           number.test(password) &&
           specialChar.test(password);
}
//생년월일 형식 체크
if (!validateBirthdate(birth)) {
    alert("생년월일은 yyyyMMdd 형식으로 입력해주세요.");
    return;
}

// submit 이전 체크
document.querySelector("form").addEventListener("submit", function (e) {
    const userid = document.getElementById("userid").value;
    const pw = document.getElementById("password").value;
    const birth = document.getElementById("birthdate").value;

    if (!validateUserId(userid)) {
        alert("아이디에 한글은 사용할 수 없습니다.");
        e.preventDefault();
        return;
    }

    if (!validateEmailFormat(userid)) {
        alert("이메일 형식 아이디를 부탁드립니다.");
        e.preventDefault();
        return;
    }

    if (!validatePassword(pw)) {
        alert("비밀번호는 대소문자, 숫자, 특수문자를 포함한 8~16자여야 합니다.");
        e.preventDefault();
        return;
    }

    if (!validateBirthdate(birth)) {
        alert("생년월일은 yyyyMMdd 형식으로 입력해주세요.");
        e.preventDefault();
        return;
    }
});

// 아이디가 바뀌면 다시 중복확인 필요하게
document.addEventListener("DOMContentLoaded", function () {
    const useridInput = document.getElementById("userid");
    useridInput.addEventListener("input", function () {
        isIdChecked = false;
        document.getElementById("checkResult").textContent = "";
    });

    const form = document.querySelector("form");
    form.addEventListener("submit", function (e) {
        if (!isIdChecked) {
            e.preventDefault();
            alert("아이디 중복확인을 먼저 해주세요.");
        }
    });
});