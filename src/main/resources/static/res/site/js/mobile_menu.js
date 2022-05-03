<<<<<<< HEAD
$(document).ready(function(){
	var windowWidth = $(window).width();
	if(windowWidth < 1000){
		$(".standard_left").slideUp();

		$(".menu > li > a").attr("href","javscript:;");
		$(".menu > li > div").hide();
		$(".menu > li > a").click(function(){
			var subcont = $(this).next("div");

			if( subcont.is(":visible") ){ //닫을 때
				subcont.slideUp();
			}else{
				subcont.slideDown(); // 열릴 때						 
				$(".menu > li > div").not(subcont).slideUp();
			}         
		});
	} 
=======
$(document).ready(function(){
	var windowWidth = $(window).width();
	if(windowWidth < 1000){
		$(".standard_left").slideUp();

		$(".menu > li > a").attr("href","javscript:;");
		$(".menu > li > div").hide();
		$(".menu > li > a").click(function(){
			var subcont = $(this).next("div");

			if( subcont.is(":visible") ){ //닫을 때
				subcont.slideUp();
			}else{
				subcont.slideDown(); // 열릴 때						 
				$(".menu > li > div").not(subcont).slideUp();
			}         
		});
	} 
>>>>>>> branch 'master' of https://github.com/inikorea/ini-home2022.git
});