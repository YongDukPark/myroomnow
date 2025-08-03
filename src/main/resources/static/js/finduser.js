let isPasswordChecked = false;

// 페이지 로딩 시 처음 상태 반영
window.addEventListener('DOMContentLoaded', () => {
    const radios = document.getElementsByName("findusertype");

    // 1. 리스너 등록
    radios.forEach(radio => {
        radio.addEventListener("change", function () {
            const selectedValue = this.value;
            document.getElementById("findClear").innerText = selectedValue === "id" ? "아이디 메일로 전송" : "비밀번호 재설정";
            document.getElementById("idarea").style.display = selectedValue === "id" ? "none" : "block";
        });
    });

    // 2. 리스너 등록된 후에 초기 상태 반영
    const checkedRadio = document.querySelector('input[name="type"]:checked');
    if (checkedRadio) {
        checkedRadio.dispatchEvent(new Event('change')); // 선택된 라디오에 대한 변경 이벤트 트리거
    }
});

document.getElementById("phonenumber").addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, '');
});

//인증번호 발송 버튼 클릭
function sendAuthCode() {
    const selectedValue = document.querySelector('input[name="findusertype"]:checked')?.value;

    const username = document.querySelector('#username').value;
    const userid = document.querySelector('#userid').value;
    const phonenumber = document.querySelector('#phonenumber').value;

    const result = document.getElementById("checkResult");

    if (selectedValue === "password" || !username.trim()) {
        result.textContent = "성함을 입력해주세요.";
        result.style.color = "orange";
        return;
    }

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
        .then(res => {
            if (res.ok) {
                document.getElementById("sendauthcodearea").style.display = "none";
                document.getElementById("codearea").style.display = "block";
                startTimer(300);
            } else {
                // 에러 응답일 경우
                alert("서버에 문제가 발생하였습니다.");
            }
        })
        .catch(err => {
            result.textContent = "오류가 발생했습니다.";
            result.style.color = "yellow";
            isIdChecked = false;
            console.error(err);
        });
}

// 인증버튼 클릭
function submitCode() {
    const selectedValue = document.querySelector('input[name="findusertype"]:checked')?.value;
    const code = document.querySelector('#code').value;

    if (!code.trim()) {
        alert("인증코드를 입력해주세요.");
        return;
    }

    fetch("/users/check-id", {
        method : "POST",
        headers : {
             "Content-Type": "application/x-www-form-urlencoded",
        },
        body : `userid=${encodeURIComponent(userid)}`
        })
        .then(res => {
            if (res.ok) {
                document.getElementById("codearea").style.display = "none";
                document.getElementById("sendauthcodearea").style.display = "none";
                document.getElementById("cleararea").style.display = "block";
                if (selectedValue === "password") {
                    document.getElementById("resetpasswordarea").style.display = "block";
                }
            } else {
                // 에러 응답일 경우
                alert("서버에 문제가 발생하였습니다.");
            }
        })
        .catch(err => {
            result.textContent = "오류가 발생했습니다.";
            result.style.color = "yellow";
            isIdChecked = false;
            console.error(err);
        });
}

// 비밀번호 동일한지 체크 로직
document.getElementById("password").addEventListener("input", checkPassword);
document.getElementById("confirmPassword").addEventListener("input", checkPassword);

// 마지막 버튼 클릭
function findClear() {
    const selectedValue = document.querySelector('input[name="findusertype"]:checked')?.value;
    const code = document.querySelector('#code').value;

    if (!code.trim()) {
        alert("인증코드를 입력해주세요.");
        return;
    }

    fetch("/users/check-id", {
        method : "POST",
        headers : {
             "Content-Type": "application/x-www-form-urlencoded",
        },
        body : `userid=${encodeURIComponent(userid)}`
        })
        .then(res => {
            if (res.ok) {
                document.getElementById("codearea").style.display = "none";
                document.getElementById("sendauthcodearea").style.display = "none";
                document.getElementById("cleararea").style.display = "block";
                if (selectedValue === "password") {
                    document.getElementById("resetpasswordarea").style.display = "block";
                }
            } else {
                // 에러 응답일 경우
                alert("서버에 문제가 발생하였습니다.");
            }
        })
        .catch(err => {
            result.textContent = "오류가 발생했습니다.";
            result.style.color = "yellow";
            isIdChecked = false;
            console.error(err);
        });
}

document.getElementById("phonenumber").addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, '');
});

document.getElementById("code").addEventListener("input", function () {
    this.value = this.value.replace(/[^0-9]/g, '');
});

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

//인증코드 타이머
function startTimer(durationInSeconds) {
    const timerElement = document.getElementById("timer");
    let remaining = durationInSeconds;

    // 최초 표시
    updateTimerDisplay(remaining);

    const intervalId = setInterval(() => {
        remaining--;

        if (remaining <= 0) {
            clearInterval(intervalId);
            timerElement.textContent = "시간 초과";
            timerElement.style.color = "red";
            return;
        }

        updateTimerDisplay(remaining);
    }, 1000);
}

// 시간 포맷 업데이트 함수
function updateTimerDisplay(seconds) {
    const min = String(Math.floor(seconds / 60)).padStart(2, '0');
    const sec = String(seconds % 60).padStart(2, '0');
    document.getElementById("timer").textContent = `${min}:${sec}`;
}

// 비밀번호 비교
function checkPassword(){
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const checkConfirmPassword = document.getElementById("checkConfirmPassword");

    if(password === "" || confirmPassword === ""){
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

// 비밀번호
function validatePassword(password) {
    const lengthCheck = /^.{8,16}$/;
    const letter = /[a-zA-Z]/;
    const number = /[0-9]/;
    const specialChar = /[!@#$%^&*(),.?":{}|<>]/;

    return lengthCheck.test(password) &&
           letter.test(password) &&
           number.test(password) &&
           specialChar.test(password);
}