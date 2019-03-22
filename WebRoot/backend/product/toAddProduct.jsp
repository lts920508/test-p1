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
            <div class="mem_tit">
				<c:choose>
				<c:when test="${empty product.id || product.id==0 }">添加商品</c:when>
				<c:otherwise>修改商品</c:otherwise>
				</c:choose>
				</div>
				
			<form action="${ctx}/admin/product?action=addProduct" method="post" enctype="multipart/form-data" id="productAdd" onsubmit="return checkProduct()">
            <table border="0" class="order_tab" style="width:930px; margin-bottom:30px;" cellspacing="0" cellpadding="0">
              <tr>   
                <td width="135" align="right">一级分类</td>
                <td colspan="3">
					<select name="categoryLevel1Id" id="productCategoryLevel1" onchange="querProductCategory(this,'productCategoryLevel2');">
					<option value="" selected="selected">请选择</option>
					<c:forEach items="${productCategoryList1}" var="temp">
						<option value="${temp.id}" <c:if test="${product.categoryLevel1Id==temp.id }">selected="selected"</c:if>	>${temp.name} </option>
					</c:forEach>
					</select>
				</td>
                
              </tr>
              <tr>   
                <td width="135" align="right">二级分类</td>
                <td>
					<select name="categoryLevel2Id" id="productCategoryLevel2" onchange="querProductCategory(this,'productCategoryLevel3');">
					<option value="0" selected="selected">请选择</option>
					<c:forEach items="${productCategoryList2}" var="temp">
						<option value="${temp.id}" <c:if test="${product.categoryLevel2Id==temp.id }">selected="selected"</c:if>	>		
						</option>
						</c:forEach>
					</select>
				</td>
                
              </tr>
              <tr>   
                <td width="135" align="right">三级分类</td>
                <td>
					<select name="categoryLevel3Id" id="productCategoryLevel3">
					<option value="0" selected="selected">请选择</option>
					<c:forEach items="${productCategoryList3}" var="temp">
						<option value="${temp.id}" <c:if test="${product.categoryLevel3Id==temp.id }">selected="selected"</c:if>>		
						</option>
					  </c:forEach>
					</select>
				</td>
              </tr>
            
            <tr>
            	<td width="135" align="right">商品名称</td>
            	<td>
            			<input type="text" value="${product.name}" class="add_ipt" name="name" id="name"/>
            		<input type="hidden" name="id" id="id" value="${product.id}"/>
            	</td>
            </tr>
            <tr>
            	<td width="135" align="right">商品图片</td>
            	<td>
            			<c:if test="${product.fileName!=null && product.fileName!=''}">
            			<img src="${ctx}/files/${product.fileName}" width="50" height="50"/>
            			</c:if>
            			<input type="file" class="test" name="photoFile"/>
            			<input type="hidden" class="text" name="fileName" id="photo" value="${product.fileName }"/>
            			<span></span>
            	</td>
            </tr>
            <tr>
            	<td width="135" align="right">单价</td>
            	<td>
            	<input type="text" value="${product.price }" class="add_ipt" name="price" id="price"/>
            	</td>
            </tr>
            <tr>
            	<td width="135" align="right">库存</td>
            	<td>
            	<input type="text" value="${product.stock }" class="add_ipt" name="stock" id="stock"/>
            	</td>
            </tr>
            <tr>
            	<td width="135" align="right">描述</td>
            	<td>
            	<textarea name="description">${product.description }</textarea>
            	</td>
            </tr>
            <tr>
            <td></td>
            	<td colspan="2" align="right">
            	<c:choose>
            	<c:when test="${empty product.id }">
            		<input type="submit" value="商品上架" class="s_btn"/>
            	</c:when>
            	<c:otherwise>
            		<input type="submit" value="确定修改" class="s_btn"/>
            	</c:otherwise>
            	</c:choose>
            	</td>
            </tr>
            </table>
            </form>
           
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
