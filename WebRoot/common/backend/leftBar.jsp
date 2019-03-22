<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
        <script type="text/javascript" src="${ctx}/statics/js/login/login.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>    
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/register/register.js"></script>
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
   		<div class="m_left">
        	<div class="left_n">管理中心</div>
            <div class="left_m">
            	<div class="left_m_t t_bg1">订单中心</div>
                <ul>
                	<li><a href="${ctx}/admin/order?action=index&userId=${sessionScope.loginUser.id}" <c:if test="${menu==1}">class="now"</c:if> >我的订单</a></li>
                	<c:if test="${sessionScope.loginUser.type==1}">
                	<li><a href="${ctx}/admin/order?action=queryAllOrder" <c:if test="${menu==9}">class="now"</c:if> >全部订单</a></li>
                	</c:if>
                </ul>
            </div>
            <div class="left_m">
            	<div class="left_m_t t_bg2">会员中心</div>
                <ul>
                	<li><a href="${ctx}/admin/user?action=index"<c:if test="${menu==2}">class="now"</c:if>>用户信息</a></li>
                	<c:if test="${sessionScope.loginUser.type==1}">
                	<li><a href="${ctx}/admin/user?action=queryUserList" <c:if test="${menu==8}">class="now"</c:if> >用户管理</a></li>
                	</c:if>
                </ul>
            </div>
            <c:if test="${sessionScope.loginUser.type==1}">
            <div class="left_m">
            	<div class="left_m_t t_bg3">商品管理</div>
                <ul>
                	<li><a href="${ctx}/admin/productCategory?action=index"<c:if test="${menu==4}">class="now"</c:if>>分类管理</a></li>
                    <li><a href="${ctx}/admin/product?action=index"<c:if test="${menu==5}">class="now"</c:if>>商品管理</a></li>
                    <li><a href="${ctx}/admin/product?action=toAddProduct"<c:if test="${menu==6}">class="now"</c:if>>商品上架</a></li>
                </ul>
            </div>
            </c:if>
            <div class="left_m">
            	<div class="left_m_t t_bg4">资讯中心</div>
                <ul>
                	<li><a href="${ctx}/admin/news?action=queryNewsList"<c:if test="${menu==7}">class="now"</c:if>>资讯列表</a></li>
                </ul>
            </div>
        </div>
