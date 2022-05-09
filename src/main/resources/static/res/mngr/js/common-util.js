var elFocus = function (obj) {
    var offset = obj.offset();
    offsetTop = offset.top;
    offsetTop -= 56;
    $("html, body").animate({scrollTop: offsetTop}, 400, function () {
        obj.focus();
    });
}

var sleep = function (delay) {
    var start = new Date().getTime();
    while (new Date().getTime() < start + delay) ;
}

var fileFormDataFlag = false;

var getFormDataEmpty = function (formDataObj) {
    /*
    var i = 0;
    for (var formData of formDataObj.entries()) {
        i++;
    }
    return i == 0 ? false : true;
    */

    // ie11 호환성 문제로 전역변수를 이용한 체크로 변경
    return fileFormDataFlag;
};



var getFileFormData = function (fileObj) {
    var fileFormData = new FormData();
    $.each(fileObj, function (idx, el) {
        if (el.files[0] != undefined) {
            fileFormData.append(fileObj.attr("name") + "_" + idx, el.files[0]);
            fileFormDataFlag = true;
        }
    });
    return fileFormData;
};

function escapeHtml(s) {
	s = s+"";
    return s ? s.replace(
        /[&<>'"]/g,
        function (c, offset, str) {
            if (c === "&") {
                var substr = str.substring(offset, offset + 6);
                if (/&(amp|lt|gt|apos|quot);/.test(substr)) {
                    // already escaped, do not re-escape
                    return c;
                }
            }
            return "&" + {
                "&": "amp",
                "<": "lt",
                ">": "gt",
                "'": "apos",
                '"': "quot"
            }[c] + ";";
        }
    ) : "";
}

function unescapeHtml(s) {
	s = s+"";
    return s
        .replace(/&amp;/g, '&')
        .replace(/&gt;/g, '>')
        .replace(/&lt;/g, '<')
        .replace(/&apos;/g, "'")
        .replace(/&quot;/g, '"')
        ;

}

function trimToEmpty(s) {
    if (s == undefined || s == null) {
        return "";
    } else {
        return s;
    }
}

function sharpTag(s) {
    s = trimToEmpty(s);
    var sArr = s.split(",");
    rs = '';
    for (var i = 0; i<sArr.length; i++) {
        rs += '<span class="border border-light-blue-600 text-light-blue-600 btn-xs">'+sArr[i]+'</span>';
    }
    return rs;
}

function encodeXSS(s) {
    if (s == undefined || s == null) {
        return "";
    } else {
        return escapeHtml(s);
    }
}

function decodeXSS(s) {
    if (s == undefined || s == null) {
        return "";
    } else {
        return unescapeHtml(s);
    }
}

function dateFormat(d, f) {
    if (d == undefined || d == null) {
        return "";
    } else {
        return moment(d).format(f);
    }
}

function numberFormat(n) {
    if (n == undefined || n == null) {
        return 0;
    } else {
        return $.number(n);
    }
}

//전체 체크박스 함수
function elAllCheckboxFunc(allCheckboxObj, itemCheckboxObj) {
    allCheckboxObj.off("click").on("click", function () {
        elCheckFunc(itemCheckboxObj, $(this).is(":checked"));
    });

    itemCheckboxObj.off("click").on("click", function () {
        if (itemCheckboxObj.length == elCheckedCnt(itemCheckboxObj)) {
            allCheckboxObj.prop("checked", true);
        } else {
            allCheckboxObj.prop("checked", false);
        }
    });
}

//전체 체크함수
function elCheckFunc(obj, checked) {
    try {
        obj.prop("checked", checked);
    } catch (e) {
        console.log("elCheckFunc", e);
    }
}

//체크 개수
function elCheckedCnt(obj) {
    let rs = 0;
    try {
        rs = obj.filter(":checked").length;
    } catch (e) {
        console.log("elCheckedCnt", e);
    }
    return rs;
}
/*김태영
$.fn.formData = function () {
    return new FormData($(this)[0]);
}
*/

//체크박스 체크된 값 배열
function elCheckboxVal(obj) {
    var rsArr = [];

    try {
        var tagName = obj[0].tagName.toLowerCase();
        var type = obj[0].type.toLowerCase();

        if (tagName == "input" && type == "checkbox") {
            $.each(obj, function (index, el) {
                if ($(this).is(":checked") == true) {
                    rsArr.push($(this).val());
                }
            });
        }
    } catch (e) {
        // console.log("elCheckboxVal", e);
    }

    return rsArr;
}

//라디오 선택 값
function elRdoVal(obj) {
    var rs = "";

    try {
        if (obj.length > 0) {
            var tagName = obj[0].tagName.toLowerCase();
            var type = obj[0].type.toLowerCase();

            if (tagName == "input" && type == "radio") {
                rs = obj.filter(":checked").val();
            }
        }
    } catch (e) {
        // console.log("elRdoVal", e);
    }

    return rs;
}

/*function getWriterInfo(obj) {
    var rs = '';
    var writerNm = getWriterNm(obj);
    var writerAcnt = getWriterAcnt(obj);
    rs += writerNm;
    if (writerAcnt != '') {
        rs += '(' + writerAcnt + ')';
    }
    return rs;
}*/


/*
$(function () {
    attachFileDownloadFunc();
});
//첨부파일 다운로드 함수
function attachFileDownloadFunc(){
	$(".attachFileDownloadBtn").off("click").on("click", function(e){
		e.preventDefault();
		var atchmnflId = $(this).attr("data-atchmnflId");

		var formData = {};
		formData["atchmnflId"] = atchmnflId;

		//파일존재 체크
        $.ajax({
            data: JSON.stringify(formData),
            url: "/file/download/check",
            type: "post",
            dataType: "json",
            contentType: "application/json"
        }).done(function (result, status, xhr) {
            //파일다운로드 실행
            var fileFormHtml = '';
            fileFormHtml += '<form name="frm" method="post" action="/file/download/attach">';
            fileFormHtml += '   <input type="hidden" name="atchmnflId" value="'+atchmnflId+'"/>';
            fileFormHtml += '</form>';
            $(fileFormHtml).appendTo("body").submit().remove();
        }).fail(function (xhr, status, error) {
            alert("데이터를 불러오는데 실패했습니다.");
        });
	});
}
*/
