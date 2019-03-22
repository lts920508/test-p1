<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
var contextPath=${ctx};
</script>
    
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
    
<!--Begin Header Begin-->

<!--End Header End--> 
<!--Begin Menu Begin-->
    	<!--Begin 商品分类详情 Begin-->    
    	
        <!--End 商品分类详情 End-->                                                     
    	
<!--End Menu End--> 
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="${ctx}/statics/images/img1.jpg" />        
    </div>
    
    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
          <tr>
            <td class="car_th" width="490">商品名称</td>
            <td class="car_th" width="140">单价</td>
            <td class="car_th" width="150">购买数量</td>
            <td class="car_th" width="130">小计</td>
            <td class="car_th" width="150">操作</td>
          </tr>
         <c:forEach items="${sessionScope.cart.items}" var="item">
          <tr class="car_tr">
            <td>
            	<div class="c_s_img"><img src="${ctx}/files/${item.product.fileName }" width="73" height="73" /></div>
                ${item.product.name }
            </td>
            <td align="center">￥${item.product.price }</td>
            <td align="center">
            	<div class="c_num">
                    <input type="button" value="" onclick="subQuantity(jq(this),'${item.product.id}');" class="car_btn_1" />
                	<input type="text" value="${item.quantity }" name="" class="car_ipt" />  
                    <input type="button" value="" onclick="addQuantity(jq(this),'${item.product.id}','${item.product.stock}');" class="car_btn_2" />
                </div>
            </td>
            <td align="center" style="color:#ff4e00;">￥${item.cost}</td>
            <td align="center"><a onclick="removeCart('${item.product.id}')">删除</a></td>
          </tr>
          </c:forEach>
          
          <tr height="70">
          	<td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
                <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥${sessionScope.cart.sum }</b></span>
            </td>
          </tr>
          <tr valign="top" height="150">
          	<td colspan="6" align="right">
            	<a href="${ctx}/Home?action=index"><img src="${ctx}/statics/images/buy1.gif" /></a>&nbsp; &nbsp; <a href="javascript:void(0)"><img src="${ctx}/statics/images/buy2.gif" onclick="settlement2();"/></a>
            </td>
          </tr>
        </table>
        
    </div>
	<!--End 第一步：查看购物车 End--> 
    </div>
    
    <!--Begin 弹出层-删除商品 Begin-->
    


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
