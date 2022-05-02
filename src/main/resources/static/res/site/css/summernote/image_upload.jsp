<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Enumeration" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.io.*" %>
<%@ page import="com.vizensoft.util.*" %>
<%@ page import="com.vizensoft.config.*" %>
<%
	String uploadPath = "/upload/editor/";
	MultipartRequest multi = new MultipartRequest(request, request.getRealPath(uploadPath), SiteProperty.EDITOR_MAXSIZE, "utf-8",  new MyFileRenamePolicy());
	ArrayList saveFiles = new ArrayList(); // 파일 이름을 저장할 ArrayList를 선언
	Enumeration files = multi.getFileNames(); // getFileNames로 업로드된 file들의 이름을 Enumeration 객체에 담는다.
    
    while(files.hasMoreElements()){
        String name = (String)files.nextElement();
		if(!"".equals(Function.checkNull(multi.getFilesystemName(name)))){
	saveFiles.add(multi.getFilesystemName(name));	
		}
		        
    } 
		
	String filename = saveFiles.get(0).toString();	// 실제이름
// 	out.println("uploadPath = ["+uploadPath+"]<br/>");
// 	out.println("filename = ["+filename+"]<br/>");
//	out.println(SiteProperty.getCompanyUrl()+uploadPath+filename);
	out.println(uploadPath+filename);
%>
