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
    
<title>易买新闻列表</title>
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
            <div class="mem_tit">资讯列表</div>
          <c:if test="${sessionScope.loginUser.type==1}"><p align="right"><a href="javascript:void(0)" onclick="toAddNewNews();" class="add_b"> 添加资讯 </a></p><br/>
            	<br/></c:if>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
              <tbody>
              <tr>                                                                                                                                                    
                <td width="20%">文章标题</td>
                <td width="25%">创建时间</td>
                <c:if test="${sessionScope.loginUser.type==1}"> <td width="10%">操作</td> </c:if>
              </tr>
              <c:forEach items="${newsList}" var="temp">
              <tr>
                <td><font color="#ff4e00"><a href="${ctx}/admin/news?action=newsDetail&id=${temp.id}">${temp.title}</a></font></td>
                <td>${temp.createTime}</td>
                <c:if test="${sessionScope.loginUser.type==1}">  <td> <a href="javascript:void(0);" onclick="deleteNews(${temp.id})">删除</a> </td>  </c:if>
              </tr>
              </c:forEach>
              </tbody>
            </table>
             <div class="pages">
                	<a href="${ctx}/admin/news?action=queryNewsList&currentPage=${pager.currentPage-1}" class="p_pre">上一页</a><c:forEach begin="1" end="${pager.pageCount}" var="i" step="1"><a href="${ctx}/admin/news?action=queryNewsList&currentPage=${i}"  <c:if test="${pager.currentPage==i}">class="cur"</c:if> >${i}</a></c:forEach><a href="${ctx}/admin/news?action=queryNewsList&currentPage=${pager.currentPage+1}" class="p_pre">下一页</a>
                </div>
                <div id="addNewNews"></div>
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
