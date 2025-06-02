let isIdChecked = false;
let isPasswordChecked = false;

function checkDuplicate() {
    event.preventDefault();
    const userid = document.getElementById("userid").value;
    const result = document.getElementById("checkResult");

    if (!userid.trim()) {
        result.textContent = "아이디를 입력해주세요.";
        result.style.color = "orange";
        return;
    }
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

    if(password.length == "" || confirmPassword == ""){
        checkConfirmPassword.textContent = "";
        isPasswordChecked = false;
    } else {
        if(password != confirmPassword){
            checkConfirmPassword.textContent = "비밀번호가 일치하지 않습니다.";
            checkConfirmPassword.style.color = "red";
            isPasswordChecked = false;
        } else {
            checkConfirmPassword.textContent = "비밀번호가 일치합니다.";
            checkConfirmPassword.style.color = "lightgreen";
            isPasswordChecked = true;
        }
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
//if (!validateBirthdate(birth)) {
//    alert("생년월일은 yyyyMMdd 형식으로 입력해주세요.");
//    return;
//}

function validateBirthdate(birth) {
    const birthRegex = /^(19[0-9]{2}|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;
    return birthRegex.test(birth);
}


document.getElementById("userid").addEventListener("input", function () {
    const useridInput = document.getElementById("userid");
    useridInput.addEventListener("input", function () {
        isIdChecked = false;
        document.getElementById("checkResult").textContent = "";
    });
});

// 비밀번호 동일한지 체크 로직
document.getElementById("password").addEventListener("input", checkPassword);
document.getElementById("confirmPassword").addEventListener("input", checkPassword);

// submit 이전 체크
document.querySelector("form").addEventListener("submit", function (e) {
    const pw = document.getElementById("password").value;
    const birth = document.getElementById("birthdate").value;

    if (!isIdChecked) {
        alert("아이디 확인 및 중복확인 요청드립니다.");
        e.preventDefault();
        document.getElementById("userid").focus();
        return;
    }

    if (!isPasswordChecked) {
        alert("비밀번호가 동일하지 않습니다.");
        e.preventDefault();
        document.getElementById("password").focus();
        return;
    }

    if (!validatePassword(pw)) {
        alert("비밀번호는 대소문자, 숫자, 특수문자를 포함한 8~16자여야 합니다.");
        e.preventDefault();
        document.getElementById("birthdate").focus();
        return;
    }

    if (!validateBirthdate(birth)) {
        alert("생년월일은 yyyyMMdd 형식으로 입력해주세요.");
        e.preventDefault();
        document.getElementById("birthdate").focus();
        return;
    }
});