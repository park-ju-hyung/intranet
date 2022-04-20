/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'paragraph', groups: [ 'align', 'bidi', 'list', 'indent', 'blocks', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];
	
	config.enterMode = CKEDITOR.ENTER_P;
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	config.fillEmptyBlocks = false;
	
	config.removeButtons = 'Save,NewPage,Preview,Print,Templates,Cut,Copy,Find,Replace,Paste,PasteText,PasteFromWord,SelectAll,Form,ImageButton,HiddenField,Checkbox,Radio,TextField,Textarea,Select,Button,RemoveFormat,Superscript,About,Styles,ShowBlocks,Iframe,Flash,Smiley,SpecialChar,Anchor,Language,BidiRtl,BidiLtr,CreateDiv';
	
	config.extraPlugins = "embed,autoembed,image2";
	config.filebrowserUploadUrl = "/file/upload/editor/image";
	config.allowedContent = true;
	config.extraAllowedContent = 'div(col-*,container,container-fluid,row)';
	config.language = 'ko';
	config.font_names = "맑은 고딕/'Malgun Gothic';굴림/Gulim;돋움/Dotum;바탕/Batang;궁서/Gungsuh;" + CKEDITOR.config.font_names;
	config.enterMode = CKEDITOR.ENTER_BR;
	//config.font_defaultLabel = '맑은 고딕';
	//config.fontSize_defaultLabel = '16';
	
	
	
};
