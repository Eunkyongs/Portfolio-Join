<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html; charset=UTF-8");
request.setCharacterEncoding("utf-8");

String msg=(String)request.getAttribute("msg");

if(msg.equals("ok")){
	out.print("<script>alert('회원가입이 완료되었습니다.'); location.href='../mall/member_login.jsp';</script>");
}else{
	out.print("<script>alert('회원가입에 실패했습니다. 다른 아이디를 사용해주세요.'); history.go(-1);</script>");
}

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
</body>
</html>