(jQ = function(jQ) {
	jQ.__proto__.enterSubmit = function(obj) {
		if ( event.keyCode === 13 ) {
			medi25.var.$(obj).closest('form').submit();
		}
	};

	jQ.__proto__.getParameter = function(param_name, param_cnt) {
		var param_name = typeof param_name !== 'undefined' ? param_name : '';
		var param_cnt = typeof param_cnt !== 'undefined' ? param_cnt : 1;
		if (param_name) {
			/*
				param_name : 찾을 param_name
				param_cnt : path_name에서의 찾을 param_name의 갯수, 해당 번째의 parameter 값을 가져오게 됨
			*/
			var param_value;
			param_name += '=';
			var path_name = location.search;
			var param_index = path_name.indexOf(param_name); /* path_name에서의 param_name의 index */
			if (param_index >= 0) {
				for (var i=1; i<param_cnt; i++) {
					param_index = path_name.indexOf(param_name, param_index + param_name.length);
				}
				var cutting_path_name = path_name.substring(param_index, path_name.length); /* param_index부터 path_name 끝까지 자름 */
				var has_more_param = cutting_path_name.indexOf('&') >= 0; /* cutting_path_name에 &가 포함되어 있는지를 확인, parameter가 더 존재하는지 여부를 파악하기 위함 */

				if (has_more_param) { /* 포함 하면 & 기준으로 뒤를 더 잘라내고 */
					param_value = path_name.substring(param_index+param_name.length, path_name.indexOf('&', param_index));
				}
				else { /* 포함하지 않으면 param_name이 가장 끝쪽에 위치해있다는것으로 판단 */
					param_value = path_name.substring(param_index+param_name.length, path_name.length);
				}
			}
			else return '';

			return param_value;
		}
		else return '';
	};

	jQ.__proto__.transUniText = function(text) {
		var text = typeof text !== 'undefined' ? text : '';
		var uni_text = '';
		for (var i=0; i<text.length; i++) {
			if ( text[i].trim() == '' ) {
				uni_text += ' ';
			}
			else {
				uni_text += escape(text[i]).replace('%', '\\');
			}
		}
		return uni_text;
	};

	jQ.__proto__.transHexCode = function(text, isScript, transLoop) {
		isScript = typeof isScript !== 'undefined' ? isScript : false;
		text = typeof text !== 'undefined' ? text : '';
		transLoop = typeof transLoop !== 'undefined' ? transLoop : 1;
		
		function getHexCode(str) {
			var result = '';
			for (var i=0; i<str.length; i++) {
				if(i!=0)result+=',';
				result+='0x'+((str.substring(i,(i+1))).charCodeAt()).toString(16);
			}
			return 'String.fromCharCode('+result+')';
		}

		for (var i=0; i<transLoop; i++) {
			if (isScript) {
				text = 'eval('+getHexCode(text)+')';
			}
			else {
				if (i!=0)
				text = 'eval('+getHexCode(text)+')';
				else
				text = getHexCode(text);
			}
		}

		return text;
	};

	$.__proto__.jQ = jQ;
})(jQ);