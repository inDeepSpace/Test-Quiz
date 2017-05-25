<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>²âÊÔ½çÃæ</title>

<link rel="stylesheet" type="text/css" href="main.css"/>
</head>
<body>
<div class="head">
          <ul>
          <li class="wlogo">
             <div class="c1">
             <div class="cx1"></div>
             </div>
             <div class="c2">
             <div class="cx2"></div>
             </div>
      		</li>
              <li class="wlogo"><a href="index.html">ikoodly</a></li>
              <li><a href="#">联系我们</a></li>
      			<li><a href="#">加入收藏</a></li>
      		<li><a href="#">设为首页</a></li>
      		<li><a href="../login/login.html">管理登陆</a></li>
          </ul>
</div>
<div class="context">
${Quiz.id}
±êÌâ£º<h1>${Quiz.title}</h1>
ÄÚÈÝ£º<h2>${Quiz.content}</h2>
<form action="test" method="post">
  <ol type="A">
     <c:forEach items="${li}" var="c">
        <input type="radio" name="id" value="${c.id}"/>
          
              <li>${c.content}</li>
              
     </c:forEach>
</ol> 
   <input type="Submit" value="²âÊÔ">
 </form>
 </div>
</body>
</html>