<?
	include_once $_SERVER['DOCUMENT_ROOT']."/lib/function.php";

	$uploadFullPath = $_SERVER['DOCUMENT_ROOT'].'/upload/editor/';
	$filename = 'files0';

	$files = $_FILES[$filename];

	$tmp_name = $files['tmp_name'];
	$org_name = $files['name'];

	$file_name = getRandFileName($org_name);
	move_uploaded_file($tmp_name, $uploadFullPath.$file_name);

	print '/upload/editor/'.$file_name;
?>