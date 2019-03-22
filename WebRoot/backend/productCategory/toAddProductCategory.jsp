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
    
</head>
<body>  
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
              <tr>   
                <td width="25%">分类级别</td>
                <td width="30%">
				<select id="type" onchange="selectProductCategoryLevel(this);" <c:if test="${pc.id!=null }">disabled="disabled"</c:if> >
					<option value="">请选择</option>
					<option value="1" <c:if test="${pc.type==1 }">selected="selected"</c:if> >一级分类</option>
					<option value="2" <c:if test="${pc.type==2 }">selected="selected"</c:if> >二级分类</option>
					<option value="3" <c:if test="${pc.type==3 }">selected="selected"</c:if> >三级分类</option>
				</select>
				</td>
              </tr>
              <tr <c:if test="${pc.type==1 || empty pc.type}">style="display:none;"</c:if>>   
                <td width="25%">一级分类</td>
                <td width="30%">
				<select id="productCategoryLevel1" onchange="queryProductCategory(this);">
					<option value="" selected="selected">请选择</option>
					<c:forEach items="${productCategoryList1}" var="temp" >
					<option value="${temp.id}"
					<c:if test="${pc.parentId==temp.id || parentProductCategory.id==temp.id }">selected="selected"</c:if>
					>${temp.name}</option>
					</c:forEach>
				</select>
				</td>
              </tr>
              <tr <c:if test="${pc.type !=3 || empty pc.type}">style="display:none;"</c:if>>   
                <td width="25%">二级分类</td>
                <td width="30%">
				<select id="productCategoryLevel2">
				<c:forEach items="${productCategoryList2}" var="temp" >
					<option value="${temp.id}"
					<c:if test="${pc.parentId==temp.id}">selected="selected"</c:if>
					>${temp.name}</option>
					</c:forEach>
				</select>
				</td>
              </tr>
              <tr>
                <td>分类名称</td>
                <td>
                <input type="text" name="name" id="name" value="${pc.name }"/>(必填)
                <input type="hidden" name="id" id="id" value="${pc.id }"/>
                </td>
              </tr>
            </table>
<p align="right"><a href="javascript:void(0)" onclick="saveOrUpdate();" class="add_b"> <c:if test="${pc.id==null }">确认添加</c:if><c:if test="${pc.id!=null }">确认修改</c:if> </a></p>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
