<%@ page language="java" contentType="text/html; charset=gb18030"
    pageEncoding="gb18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>心里测试管理系统</title>
	<link rel="stylesheet" type="text/css" href="admin/index2/css/main.css">
  <link rel="stylesheet" type="text/css" href="admin/index2/css/style.css"  >
</head>
<body id="banner">
<div class="head">
          <ul>
              <li class="logo"><a href="#">D B M S</a></li>
              <li><a href="#">帮助Help</a></li>
              <li><a href="#">关于我们</a></li>
              <li><a href="#">管理员设置</a></li>
              <li><a href="#">退出登录</a></li>
          </ul>
</div>
         
<div class="container">
<div class="menu-wrap">	
  <ul id="accordion" class="accordion">
    <li>
      <div class="link"><i class="fa fa-paint-brush"></i>业务功能<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">栏目管理</a></li>
        <li><a href="#">心里测试管理</a></li>
      </ul>
    </li>
    <li>
      <div class="link"><i class="fa fa-code"></i>管理功能<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="admin/select/select.html">查询测试题</a></li>
        <li><a href="admin/delete/delete.html">删除测试题</a></li>
        <li><a href="admin/add/quizadd.html">新增测试题</a></li>
      </ul>
    </li>
    <li>
      <div class="link"><i class="fa fa-mobile"></i>数据库访问量<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">天访问量统计</a></li>
        <li><a href="#">周访问量统计</a></li>
        <li><a href="#">月访问量统计</a></li>
        <li><a href="#">地区访问量</a></li>
        <li><a href="#">历史访问量</a></li>
      </ul>
    </li>
    <li>
      <div class="link"><i class="fa fa-mobile"></i>后台代码管理<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">Tablets</a></li>
        <li><a href="#">Dispositivos mobiles</a></li>
        <li><a href="#">Medios de escritorio</a></li>
        <li><a href="#">Otros dispositivos</a></li>
      </ul>
    </li>
    <li>
      <div class="link"><i class="fa fa-mobile"></i>数据库维护<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">Tablets</a></li>
        <li><a href="#">Dispositivos mobiles</a></li>
        <li><a href="#">Medios de escritorio</a></li>
        <li><a href="#">Otros dispositivos</a></li>
      </ul>
    </li>
    <li>
      <div class="link"><i class="fa fa-mobile"></i>关于我们<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">Tablets</a></li>
        <li><a href="#">Dispositivos mobiles</a></li>
        <li><a href="#">Medios de escritorio</a></li>
        <li><a href="#">Otros dispositivos</a></li>
      </ul>
    </li>
    <li><div class="link"><i class="fa fa-globe"></i>管理员登录<i class="fa fa-chevron-down"></i></div>
      <ul class="submenu">
        <li><a href="#">Google</a></li>
        <li><a href="#">Bing</a></li>
        <li><a href="#">Yahoo</a></li>
        <li><a href="#">Otros buscadores</a></li>
      </ul>
    </li>
  </ul>

<button class="close-button" id="close-button">Close Menu</button>
</div>

<button class="menu-button" id="open-button">Open Menu</button>
  
<div class="right">
	 <table class="table1">
              <thead>
                <tr>
                  <th>心里测试</th>
                  <th>标题</th>
                  <th>发布日期</th>
                  <th>点击数</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${li}" var="c">
                   <tr>
                      <td>${c.id}</td>
                      <td>${c.title}</td>
                      <td>2015-6-8</td>
                      <td>150</td>
                      <td>
                        <a href="quizupdate?id=${c.id}">修改</a>
                        <a href="quizdelete?id=${c.id}">删除</a>
                        <a href="quizselect?id=${c.id}">查询</a>
                      </td>  
                      </tr>
                  </c:forEach>      
            </table>                         
</div>

		<script src="js/classie.js"></script>
		<script src="js/main.js"></script>
		<script src='js/jquery.js'></script>
		<script src="js/index.js"></script>

</div>
<div class="foot">
          <ul>
              <li><a href="#">关于数据库</a></li>
              <li><a href="#">常见问题</a></li>
              <li><a href="#">意见反馈</a></li>
              <li><a href="#">版权所有</a></li>
          </ul>
          <img src="admin/index2/foot.png"/>
</div>

</body>
</html>