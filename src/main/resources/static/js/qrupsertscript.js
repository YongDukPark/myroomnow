// 페이지 로딩 시 처음 상태 반영
window.addEventListener('DOMContentLoaded', () => {
    const radios = document.getElementsByName("type");

    // 1. 리스너 등록
    radios.forEach(radio => {
        radio.addEventListener("change", function () {
            const selectedValue = this.value;
            document.getElementById("imgarea").style.display = selectedValue === "img" ? "block" : "none";
            document.getElementById("contentarea").style.display = selectedValue === "content" ? "block" : "none";
        });
    });

    // 2. 리스너 등록된 후에 초기 상태 반영
    const checkedRadio = document.querySelector('input[name="type"]:checked');
    if (checkedRadio) {
        checkedRadio.dispatchEvent(new Event('change')); // 선택된 라디오에 대한 변경 이벤트 트리거
    }
});

// radio 버튼 전체 선택
const radios = document.getElementsByName("type");

// 이벤트 리스너 추가
radios.forEach(radio => {
    radio.addEventListener("change", function () {
        const selectedValue = this.value;

        // 섹션 가져오기
        const imgarea = document.getElementById("imgarea");
        const contentarea = document.getElementById("contentarea");

        // 값에 따라 display 설정
        if (selectedValue == "img") {
            imgarea.style.display = "block";
            contentarea.style.display = "none";
        } else if (selectedValue == "content") {
            imgarea.style.display = "none";
            contentarea.style.display = "block";
        }
    });
});

function showSection(){
    const radios = document.getElementByName("type").value;

    let selectedValue;
    for (const radio of radios) {
        if (radio.checked) {
            selectedValue = radio.value;
            break;
        }
    }

    if (selectedValue = "img") {

    } else if (selectedValue = "content") {

    }
}