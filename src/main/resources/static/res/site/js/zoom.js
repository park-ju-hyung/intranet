
$(window).on("load resize", function(){
	if( $(window).width() < 1200 && $(window).width() > 1000 ){
		$('#body').css("zoom", 1- ( (1200 - $(window).width()) * 0.001));
	}else if( $(window).width() > 1200 ){
		$('#body').css("zoom", 1 );
	}
});
//가장 바깥 박스를 "#body" 부분에 넣어 사용하세요 