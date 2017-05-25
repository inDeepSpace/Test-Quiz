<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="main.css">
<title>修改测试页面</title>
</head>
<body>
<div class="head">
          <ul>
              <li class="logo"><a href="update.jsp">D B M S</a></li>
              <li><a href="#">帮助Help</a></li>
              <li><a href="../link_oth/us.html">关于我们</a></li>
              <li><a href="../login/login.html">退出登录</a></li>
          </ul>
</div>
<div class="dark-matter">
	<form action="quizupdate" method="post" class="STYLE-NAME">
<h1>修改测试题
</h1>
<label>
<span>测试题题目 :</span>
<textarea id="message" name="title" >${Quiz.title}</textarea><br>
</label>

<label>
<span>修改内容 :</span>
<textarea id="message" name="content" >${Quiz.content}</textarea><br>
</label>

<label>
<ol type="1">
<c:forEach items="${li}" var="c">
              <li>选项<textarea id="message" name="content" >${c.content}</textarea><br></li>
              <li>答案<textarea id="message" name="answer" >${c.answer}</textarea><br></li>
     </c:forEach>
</ol>
</label>


<span>&nbsp;</span>
<input type="submit" class="button" value="存入数据库" />
</label>
</form>
</div>

<div class="pen">
  <div class="jian"></div>
</div>
</body>
</html>