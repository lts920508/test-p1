<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fs"%> 
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
    
<%@include file="/common/pre/header.jsp" %>
<title>易买网</title>
</head>
<body>  
<!--Begin Header Begin-->
<div id="searchBar">
<%@include file="/common/pre/searchBar.jsp" %>
</div>
<!--End Header End--> 
<!--Begin Menu Begin-->
<div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->  
         <%@include file="/common/pre/categoryBar.jsp" %>
       <!--End 商品分类详情 End-->
  </div>
</div>
<!--End Menu End--> 
<div class="i_bg">
    <div class="content mar_20">
       <div class="content mar_20">
    	<div class="l_history">
        	<div class="his_t">
            	<span class="fl">浏览历史</span>
                <span class="fr"><a href="#">清空</a></span>
            </div>
        	<ul>
        	<c:forEach items="${sessionScope.historyProductList}" begin="0" end="2" var="history" step="1">
        	<c:if test="${empty history}"></c:if>
        	<c:if test="${!empty history}">
            	<li>
                    <div class="img"><a href="${ctx}/Product?action=queryProductDetail&id=${history.id}"><img src="${ctx}/files/${history.fileName}" width="185" height="162" /></a></div>
                	<div class="name"><a href="${ctx}/Product?action=queryProductDetail&id=${history.id}">${history.name}</a></div>
                    <div class="price">
                    	<font>￥<span>${history.price}</span></font> &nbsp; 
                    </div>
                </li>
                </c:if>
               </c:forEach>
        	</ul>
        </div>
    <div id="favoriteList">
    </div>
        <div class="l_list">
        	<div class="list_t">
                <span class="fr">共发现${total}件</span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
            	<c:forEach items="${pList}" var="pList">
                	<li>
                    	<div class="img"><a href="${ctx}/Product?action=queryProductDetail&id=${pList.id}"><img src="${ctx}/files/${pList.fileName}" width="210" height="185" /></a></div>
                        <div class="price">
                            <font>￥<span>${pList.price}</span></font>
                        </div>
                        <div class="name"><a href="${ctx}/Product?action=queryProductDetail&id=${pList.id}">${pList.name}</a></div>
                        <div class="carbg">
                            <a href="javascript:void(0)" class="j_car" onclick="addCartByParam('${pList.id}',1)">加入购物车</a>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
               <div class="pages">
                	<a href="${ctx}/Product?action=queryProductList&currentPage=${pager.currentPage-1}&categoryId=${categoryId}&keyWord=${keyWord}" class="p_pre">上一页</a><c:forEach begin="1" end="${pager.pageCount}" var="i" step="1"><a href="${ctx}/Product?action=queryProductList&currentPage=${i}&categoryId=${categoryId}&keyWord=${keyWord}">${i}</a></c:forEach><a href="${ctx}/Product?action=queryProductList&currentPage=${pager.currentPage+1}&categoryId=${categoryId}&keyWord=${keyWord}" class="p_pre">下一页</a>
                </div>
            </div>
        </div>
    </div>
    
    <!--Begin Footer Begin -->
 <%@include file="/common/pre/footer.jsp" %>
    <!--End Footer End -->    
</div>
    <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
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
    <script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
    
</body>
</html>
