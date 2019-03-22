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
	<script type="text/javascript" src="${ctx}/statics/js/backend/backend.js"></script>
    
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
            <div class="mem_t">会员列表<c:if test="${sessionScope.loginUser.type==1}">（管理员）</c:if><c:if test="${sessionScope.loginUser.type==0}">（会员）</c:if></div>
            <p align="right"><a href="${ctx}/admin/user?action=toAddUser" class="add_b"> 新增用户 </a></p>
            <table border="1" class="mem_tab" style="width:870px; text-align:center; margin-top:20px;" cellspacing="0" cellpadding="0">
              <tr>
                <td  class="td_bg">id</td>
                <td>登录名</td>
                <td  class="td_bg">姓名</td>
                <td>性别</td>
                <td  class="td_bg">邮箱</td>
                <td>电话</td>
                <td  class="td_bg">类型</td>
                <td colspan="2">操作</td>
              </tr>
              <c:forEach items="${userList}" var="user">
              <tr>
                <td  class="td_bg">${user.id}</td>
                <td >${user.loginName}</td>
                <td  class="td_bg">${user.userName}</td>
                <td ><c:if test="${user.sex==1}">男</c:if><c:if test="${user.sex==0}">女</c:if></td>
                <td  class="td_bg">${user.email}</td>
                <td >${user.mobile}</td>
                <td   class="td_bg"><c:if test="${user.type==0}">会员</c:if><c:if test="${user.type==1}">管理员</c:if></td>
                <td ><a href="${ctx}/admin/user?action=toUpdateUser&id=${user.id}">修改</a></td>
                <td ><c:if test="${user.id!=sessionScope.loginUser.id}"><a href="javascript:void(0);" onclick="deleteUser(${user.id});">删除</a></c:if></td>
                
              </tr>
              </c:forEach>
            </table>
	 <div class="pages">
                	<a href="${ctx}/admin/user?action=queryUserList&currentPage=${pager.currentPage-1}" class="p_pre">上一页</a><c:forEach begin="1" end="${pager.pageCount}" var="i" step="1"><a href="${ctx}/admin/user?action=queryUserList&currentPage=${i}" <c:if test="${pager.currentPage==i}">class="cur"</c:if> >${i}</a></c:forEach><a href="${ctx}/admin/user?action=queryUserList&currentPage=${pager.currentPage+1}" class="p_pre">下一页</a>
                </div>

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
