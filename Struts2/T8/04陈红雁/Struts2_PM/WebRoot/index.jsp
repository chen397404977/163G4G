<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>系统登录 - 超市账单管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/style.css" />
</head>

<body class="blue-style">
	<c:if test="${param.loginMsg == 'failed'}">
		<script type="text/javascript">
			alert("用戶名或密碼錯誤");
		</script>
	</c:if>
	
	<div id="login">
		<div class="icon"></div>
		<div class="login-box">
			<s:form action="user!login" namespace="/" method="post">
			<s:textfield label="用户名" name="user.userName" required="true"cssClass="input-text"></s:textfield>
			<s:password label="密码" name="user.userPassword" required="true"cssClass="input-text"></s:password>
			<s:div cssClass="buttons">
			<s:submit value="登录" cssClass="input-button"/>
			<s:reset value="重置" cssClass="input-button"/>
			</s:div>
			</s:form>
		</div>
	</div>
</body>
</html>
