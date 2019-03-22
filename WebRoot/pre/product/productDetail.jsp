<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	  <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
        <script type="text/javascript" src="${ctx}/statics/js/login/login.js"></script> 
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>    
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/ShopShow.js"></script>
     <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>   
	<script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>
    <link rel="stylesheet" type="text/css" href="css/ShopShow.css" />
    <link rel="stylesheet" type="text/css" href="css/MagicZoom.css" />
    <script type="text/javascript" src="${ctx}/statics/js/common/MagicZoom.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/num.js">
    	var jq = jQuery.noConflict();
    </script>
    <script type="text/javascript" src="${ctx}/statics/js/common/p_tab.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/iban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/fban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/f_ban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/mban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/bban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/hban.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/tban.js"></script>
    
<title>易买网</title>
</head>
<body>  
<!--Begin Header Begin-->
<%@include file="/common/pre/header.jsp" %>
<!--End Header End--> 
<div id="searchBar">
<%@include file="/common/pre/searchBar.jsp" %>
</div>
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
	<div class="postion">
    	<span class="fl">全部 > 美妆个护 > 香水 > 迪奥 > 迪奥真我香水</span>
    </div>    
    <div class="content">
    	                    
        <div id="tsShopContainer">
            <div id="tsImgS">
            <a href="${ctx}/files/${product.fileName}" title="Images" class="MagicZoom" id="MagicZoom">
            <img src="${ctx}/files/${product.fileName}" width="390" height="390" />
            </a>
            </div>
        </div>
        <div class="pro_des">
        	<div class="des_name">
        	<input type="hidden" value="${product.id}" name="entityId" class="n_ipt"/>
            	<p>${product.name}</p>
                “开业巨惠，北京专柜直供”，不光低价，“真”才靠谱！
            </div>
            <div class="des_price">
            	本店价格：<b>￥${product.price}</b><br/>
            </div>
            <div class="des_price">
            	库存：<b>${product.stock}</b><br/>
            </div>
            <div class="des_join">
            	<div class="j_nums">
                	<input type="text" value="1" name="quantity" class="car_ipt" />
                    <input type="button" value="" onclick="addQuantity(jq(this),'${product.id}','${product.stock}');" class="n_btn_1" />
                    <input type="button" value="" onclick="subQuantity(jq(this),'${product.id}');" class="n_btn_2" />   
                    <input type="hidden" name="productStock" value="${product.stock}"/>
                </div>
                <span class="fl">
                <img src="${ctx}/statics/images/j_car.png" onclick="addCart();"/>
                </span>
            </div>            
        </div>    
    </div>
    <div class="content mar_20">
        <div class="l_list">        	
            <div class="des_border">
                <div class="des_tit">
                    			商品属性
                </div>
                <div class="des_con" id="p_attribute">
                	
                	<table border="0" align="center" style="width:100%; font-family:'宋体'; margin:10px auto;" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>商品名称：${product.name}</td>
                        <td>商品编号：${product.id}</td>
                      </tr>
                      <tr>
                        <td>商品毛重：160.00g</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>容量：1ml-15ml </td>
                        <td>&nbsp;</td>
                      </tr>
                    </table>                                               
                </div>
          	</div>  
        </div>
    </div>
    
    
    <!--Begin 弹出层-收藏成功 Begin-->
   
    <!--End 弹出层-收藏成功 End-->
    
    
    <!--Begin 弹出层-加入购物车 Begin-->
    
    <!--End 弹出层-加入购物车 End-->
    
    
    
    <!--Begin Footer Begin -->
  <%@include file="/common/pre/footer.jsp" %>
    <!--End Footer End -->    
</div>

</body>
</html>
