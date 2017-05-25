<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<h1>查询结果：<h1>
题目：${select.title} <br>
内容：${select.content} <br>
<ol type="A">
<c:forEach items="${select.choices}" var="c">
       <b>${select.content}</b>
      <input type="radio" name="answer" value="${c.id}"/>
          
              <li>${c.content}</li>
              
     </c:forEach>
</ol>
</body>
</html>