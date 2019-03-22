<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
        
  <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
        <script type="text/javascript" src="${ctx}/statics/js/login/login.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>    
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
    
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/iban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/fban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/f_ban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/mban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/bban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/hban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/tban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/num.js"></script>
    
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
    
<title>易买网订单</title>
</head>
<body>  
<!--Begin Header Begin-->
<%@include file="/common/pre/header.jsp" %>
<%@include file="/common/backend/searchBar.jsp" %>
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
<%@include file="/common/backend/leftBar.jsp" %>
		<div class="m_right">
            <p></p>
            <div class="mem_t">账号信息<c:if test="${sessionScope.loginUser.type==1}">（管理员）</c:if><c:if test="${sessionScope.loginUser.type==0}">（会员）</c:if></div>
           <div style="margin-left:50px;"> <img src="${ctx}/statics/images/user.jpg" /></div>
            <table border="0" class="mon_tab" style="width:870px; margin-bottom:20px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="40%">用户ID：<span style="color:#555555;">${sessionScope.loginUser.id}</span></td>
                <td width="60%">姓名：<span style="color:#555555;">${sessionScope.loginUser.userName}</span></td>
              </tr>
              <tr>
                <td>电&nbsp; &nbsp; 话：<span style="color:#555555;">${sessionScope.loginUser.mobile}</span></td>
                <td>邮&nbsp; &nbsp; 箱：<span style="color:#555555;">${sessionScope.loginUser.email}</span></td>
              </tr>
              <tr>
                <td>身份证号：<span style="color:#555555;">${sessionScope.loginUser.identityCode}</span></td>
                <td>性&nbsp; &nbsp; 别：<span style="color:#555555;"><c:if test="${sessionScope.loginUser.sex==1}">男</c:if><c:if test="${sessionScope.loginUser.sex==0}">女</c:if></span></td>
              </tr>
            </table>

    </div>
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
   <%@include file="/common/pre/footer.jsp" %> 
    <!--End Footer End -->    
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
