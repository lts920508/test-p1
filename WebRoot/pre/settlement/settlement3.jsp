<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
var contextPath=${ctx};
</script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
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

<!--End Menu End--> 
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="images/img3.jpg" />        
    </div>
    
    <!--Begin 第三步：提交订单 Begin -->
    <div class="content mar_20">
    	
        <!--Begin 银行卡支付 Begin -->
    	<div class="warning">        	
            <table border="0" style="width:1000px; text-align:center;" cellspacing="0" cellpadding="0">
              <tr height="35">
                <td style="font-size:18px;">
                	感谢您在本店购物！您的订单已提交成功，请记住您的订单号: <font color="#ff4e00">${order.serialNumber}</font>
                </td>
              </tr>
              <tr>
                <td style="font-size:14px; font-family:'宋体'; padding:10px 0 20px 0; border-bottom:1px solid #b6b6b6;">
                	您选定的配送方式为: <font color="#ff4e00">申通快递</font>； &nbsp; &nbsp;您选定的支付方式为: <font color="#ff4e00">支付宝</font>； &nbsp; &nbsp;您的应付款金额为: <font color="#ff4e00">￥ ${order.cost}</font>
                </td>
              </tr>
              <tr>
                <td style="padding:20px 0 30px 0; font-family:'宋体';">
                	 收货人姓名： ${sessionScope.loginUser.userName} ；地址： ${order.userAddress} ； <br />
                    注意事项：办理电汇时，请在电汇单“汇款用途”一栏处注明您的订单号。
                </td>
              </tr>
              <tr>
                <td>
                	<a href="${ctx}/Home?action=index">首页</a> &nbsp; &nbsp; <a href="${ctx}/admin/user?action=index">用户中心</a>
                </td>
              </tr>
            </table>        	
        </div>
        <!--Begin 银行卡支付 Begin -->
        
        <!--Begin 支付宝支付 Begin -->
    	
        <!--Begin 支付宝支付 Begin -->
        
        <!--Begin 余额不足 Begin -->
    
        <!--Begin 余额不足 Begin -->
        
        
    </div>
	<!--End 第三步：提交订单 End--> 
    
    
    <!--Begin Footer Begin -->
    
    <!--End Footer End -->    
</div>



<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
