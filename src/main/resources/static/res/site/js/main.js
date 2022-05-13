$(document).ready(function() {
	const header = $('meta[name="_csrf_header"]').attr('content');
	const token = $('meta[name="_csrf"]').attr('content');
	//ajaxUtil.setup(header, token);
	
	//slickController();
	menuController();
});

const menuController = function() {
	var gnbHtml = $("#header .gnb").html();
	$("#all_menu .gnb").html(gnbHtml);
	$("#m_menu .gnb_box").html(gnbHtml);
	
	$("#header .all_menu_btn").on('click', function(){
		$("#all_menu").fadeIn(200);
	});
	$("#all_menu .all_menu_btn").on('click', function(){
		$("#all_menu").fadeOut(200);
	});

	$(".top_btn").on("click",function(){
		$("body,html").animate({scrollTop:0});
	});	
};

$(window).on("load",function(){
	// 모바일 클릭
	$("#header .m_btn").on("click",function(){
		$("#m_menu").addClass("on");
		$(".m_menu_bg").fadeIn();
	});
	$(".m_menu_bg, #m_menu .close_btn").on("click",function(){
		$("#m_menu").removeClass("on");
		$(".m_menu_bg").fadeOut();
	});
	// 모바일 메뉴
	$("#m_menu .gnb_box > ul > li > a").on("click",function(){
		if(!$(this).parent().hasClass("depth01")){
			// 2depth가 있을 경우
			$(this).parent("li").toggleClass("on").siblings().removeClass("on");
			$(this).next(".depth02").stop(true,true).slideToggle().parent("li").siblings().find(".depth02").slideUp();
			return false;
		}
	});
	$('#header').on('mouseenter', function(){
		$(this).addClass("on")
	});
	$('#header').on('mouseleave', function(){
		$(this).removeClass("on")
	});

});


/* 풀페이지
$('#fullpage').fullpage({
	licenseKey: 'OPEN-SOURCE-GPLV3-LICENSE',
	anchors : ['01', '02', '03', '04'],
	navigation: true,
	responsiveWidth: 1000,
	navigationPosition: 'right',
});
*/
/*
var mc3 = new Swiper(".main_con03 .img_inner_slide_box",{
	speed:4000,
	autoplay:{
		delay:2000,
		pauseOnMouseEnter:true,
	}
});	

const slickController = function() {
	$slick = $('.slider');
	
	$slick.slick({
		draggable: true,
		adaptiveHeight: false,
		dots: false,
		arrows: false,
		mobileFirst: true,
		pauseOnDotsHover: true,
		fade:true,
		autoplay: true,
		autoplaySpeed: 5000,
	});
}; */