document.addEventListener("DOMContentLoaded", function() {
    // 팝업을 표시하는 요소를 찾습니다.
    const searchBox = document.getElementById("hz");
    // 삭제 버튼
    const deleteButton = document.getElementById("deleteButton");
    // 닫기 버튼
    const closeButton = document.getElementById("toggleFadeButton2");

    // 'deleteButton' 클릭 시 팝업 표시
    deleteButton.addEventListener("click", function(event) {
        event.preventDefault(); // 폼 제출 방지
        searchBox.style.display = "block"; // 팝업을 보여줍니다.
    });

    // 'closeButton' 클릭 시 팝업 숨김
    closeButton.addEventListener("click", function() {
        searchBox.style.display = "none"; // 팝업을 숨깁니다.
    });
});