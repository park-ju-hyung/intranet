
  $(function(){
    var $firstMenu = $('.t-menu>li'),
      $header = $('header');
  
  $firstMenu.mouseover(function() {
    $header.stop().animate({height: '270px'},300);
  })
  .mouseout(function() {
    $header.stop().animate({height: '130px'},300);
  });
  });
    
  
  $(function(){
    $('#c3-tab>li>a').click(function(){
      $(this).parent().addClass("tab-on").siblings().removeClass("tab-on");
      return false;
    });
    $('.l-depth2>li>a').click(function(){
      if($(this).attr('href') != '')
        return true;
      $(this).parent().removeClass('close').addClass('open').siblings().removeClass('open').addClass('close');
        return false;
    });
  });


/* TAB */  
  $(document).ready(function(){
	
    $('.tab-list li span').click(function(){
      var tab_id = $(this).attr('data-tab');
  
      $('.tab-list li span').removeClass('current');
      $('.tab-content').removeClass('current');
  
      $(this).addClass('current');
      $("#"+tab_id).addClass('current');
    })
  
  })


  $(document).ready(function(){
	
    $('ul.tabs li').click(function(){
      var tab_id = $(this).attr('data-tab');
  
      $('ul.tabs li').removeClass('current');
      $('.tab-content').removeClass('current');
  
      $(this).addClass('current');
      $("#"+tab_id).addClass('current');
    })
  
  })


/* 레이어팝업 */

