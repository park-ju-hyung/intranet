const ajaxUtil = {
	_csrf_header : null,
	_csrf : null,
	ajaxLoadingHtml : '<div class="loading"><img src="/res/mngr/img/loading.gif" alt="loading"></div>',
	setup : function (csrf_header, csrf) {
		if(csrf_header && csrf) { 
			ajaxUtil._csrf_header = csrf_header;
			ajaxUtil._csrf = csrf;
		}

		jQuery.ajaxSetup({
	        beforeSend: ajaxUtil.beforeSendAjax
	        , success: ajaxUtil.successAjax
	        , error: ajaxUtil.errorAjax
	        , complete: ajaxUtil.completeAjax
	    });
	},
	showAjaxLoading : function () {
	    if (jQuery("body").find(".loading").length == 0) {
	        jQuery("body").prepend(ajaxUtil.ajaxLoadingHtml);
	    }

	    jQuery(".loading").fadeIn(10);
    },
    hideAjaxLoading : function () {
	    jQuery(".loading").fadeOut(100);
	},
	beforeSendAjax : function (xhr) {
	    //header ajax 설정
	    if(ajaxUtil._csrf !== null && ajaxUtil._csrf_header !== null) {
			xhr.setRequestHeader(ajaxUtil._csrf_header, ajaxUtil._csrf);
		}
	    ajaxUtil.showAjaxLoading();
	},
	successAjax : function (result, status, xhr) {
	    ajaxUtil.hideAjaxLoading();
	},
	errorAjax : function (xhr, status, error) {
	    console.log(error);
		if(xhr.status === 403 || xhr.status === 405) {
			alert("세션이 만료되었습니다");
			location.replace("/mngr/logout");
		} else {
			//alert(error);
		}

	    ajaxUtil.hideAjaxLoading();
	},
	completeAjax : function (xhr, status) {
	    ajaxUtil.hideAjaxLoading();
	}
};
