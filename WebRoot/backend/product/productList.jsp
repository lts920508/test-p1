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
    
<title>易买网商品分类</title>
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
            <div class="mem_tit">商品列表</div><br/>
            	<br/>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
              <tr>   
                <td width="10%">商品名称</td>
                <td width="10%">商品图片</td>
                <td width="5%">库存</td>
                <td width="10%">价格</td>
                <td width="10%" colspan="2">操作</td>
              </tr>
              <c:forEach items="${productList}" var="temp">
              <tr>
                <td>${temp.name}</td>
                <td>
                <a href="${ctx}/Product?action=queryProductDetail&id=${temp.id}" target="_blank">
                <img src="${ctx}/files/${temp.fileName}" width="50" height="50" />
                </a>
                </td>
                <td>${temp.stock}</td>
                <td>${temp.price}</td>
                <td><a href="${ctx}/admin/product?action=toUpdateProduct&id=${temp.id}">修改</a></td>
                <td><a href="javascript:void(0);" onclick="deleteById(${temp.id});">删除</a></td>
              </tr>
              </c:forEach>
            </table>
             <div class="pages">
                	<a href="${ctx}/admin/product?action=index&currentPage=${pager.currentPage-1}" class="p_pre">上一页</a><c:forEach begin="1" end="${pager.pageCount}" var="i" step="1"><a href="${ctx}/admin/product?action=index&currentPage=${i}"  <c:if test="${pager.currentPage==i}">class="cur"</c:if> >${i}</a></c:forEach><a href="${ctx}/admin/product?action=index&currentPage=${pager.currentPage+1}" class="p_pre">下一页</a>
                </div>
                <div id="addProductCategory"></div>
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
