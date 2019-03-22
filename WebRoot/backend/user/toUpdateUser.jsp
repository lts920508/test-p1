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
        
  
    
<title>易买网订单</title>
</head>
<body>  
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
    
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>   
    <script type="text/javascript" src="${ctx}/statics/js/login/login.js"></script>       
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/register/register.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/backend/backend.js"></script>
    
    <script type="text/javascript" src="${ctx}/statics/js/common/iban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/fban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/f_ban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/mban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/bban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/hban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/tban.js"></script>
    
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
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
            <form>
            <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
              	<td width="95">&nbsp;</td>
                <td>
                	<span class="fl" style="font-size:24px;">修改用户</span>
                </td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
                <td><input type="text" name="loginName" value="${user.loginName}" class="l_user" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;真实姓名 &nbsp;</td>
                <td><input type="text" name="userName" value="${user.userName}" class="l_user" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;身份证号 &nbsp;</td>
                <td><input type="text" name="identityCode" value="${user.identityCode}" class="l_user" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;邮箱 &nbsp;</td>
                <td><input type="text" name="email" value="${user.email}" class="l_email" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;手机 &nbsp;</td>
                <td><input type="text" value="${user.mobile}" name="mobile" class="l_tel" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;用户类型 &nbsp;</td>
                <td>
                <select id="type" class="l_tel">
                <option value="0" <c:if test="${user.type==0}">selected="selected"</c:if> >会员</option>
                <option value="1" <c:if test="${user.type==1}">selected="selected"</c:if> >管理员</option>
                </select>
                </td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input type="button" value="修改" class="log_btn" onclick="updateUser(${user.id});"/></td>
              </tr>
            </table>
            </form>
    </div>
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
   <%@include file="/common/pre/footer.jsp" %> 
    <!--End Footer End -->    
</div>
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
