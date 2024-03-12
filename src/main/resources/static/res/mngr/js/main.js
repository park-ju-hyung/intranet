document.addEventListener("DOMContentLoaded", function() {
    // 팝업을 표시하는 요소를 찾습니다.
    const searchBox = document.getElementById("hz");
    // 팝업을 토글하는 버튼
    const toggleFadeButton = document.getElementById("toggleFadeButton");
    // 닫기 버튼
    const closeButton = document.getElementById("toggleFadeButton2");

    // 'toggleFadeButton' 클릭 시 팝업 표시
    toggleFadeButton.addEventListener("click", function() {
        searchBox.style.display = "block"; // 팝업을 보여줍니다.
    });

    // 'closeButton' 클릭 시 팝업 숨김
    closeButton.addEventListener("click", function() {
        searchBox.style.display = "none"; // 팝업을 숨깁니다.
    });
});