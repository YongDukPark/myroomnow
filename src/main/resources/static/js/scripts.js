//document.addEventListener("DOMContentLoaded", () => {
//    const form = document.getElementById("login-form");
//    //const resultDiv = document.getElementById("result");
//
//    form.addEventListener("submit", function (event) {
//        // 기본 form 제출 막기
//        event.preventDefault();
//
//        const username = form.username.value;
//        const password = form.password.value;
//
//        // 콘솔에 출력 (서버 요청 없이 확인할 때)
//        console.log("ID:", username);
//        console.log("PW:", password);
//
//        // 예: Ajax로 서버에 전송 (fetch 사용)
//        fetch("/api/login", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify({ username, password })
//        })
//        .then(response => {
//            if (!response.ok) throw new Error("로그인 실패");
//            return response.json();
//        })
////        .then(data => {
////            resultDiv.textContent = `환영합니다, ${data.name}님!`;
////        })
//        .catch(err => {
//            console.error(err);
//            alert("아이디/비밀번호를 확인해 주세요.")
//            //resultDiv.textContent = "로그인 실패";
//        });
//    });
//});