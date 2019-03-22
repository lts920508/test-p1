function deleteProductCategory(id) {
	var bool = window.confirm("确定删除次分类？");
	if (bool) {
		$.ajax({
			url : contextPath + "/admin/productCategory",
			method : "post",
			data : {
				id : id,
				action : "deleteProductCategory"
			},
			success : function(jsonStr) {
				var result = eval("(" + jsonStr + ")");
				if (result.status == 1) {
					window.location.reload();
				} else {
					showMessage(result.message);
				}
			}
		})
	}
}

function deleteById(id) {
	var bool = window.confirm("确定删除此商品信息吗");
	if (bool) {
		$.ajax({
			url : contextPath + "/admin/product",
			method : "post",
			data : {
				id : id,
				action : "deleteById"
			},
			success : function(jsonStr) {
				var result = eval("(" + jsonStr + ")");
				if (result.status == 1) {
					window.location.reload();
				}
			}
		})
	}
}
function deleteNews(id) {
	var bool = window.confirm("确定删除这条资讯吗");
	if (bool) {
		$.ajax({
			url : contextPath + "/admin/news",
			method : "post",
			data : {
				id : id,
				action : "deleteNews"
			},
			success : function(jsonStr) {
				var result = eval("(" + jsonStr + ")");
				if (result.status == 1) {
					window.location.reload();
				}
			}
		})
	}
}

function toAddProductCategory() {
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "toAddProductCategory"
		},
		success : function(jsonStr) {
			$("#addProductCategory").html(jsonStr);
		}
	})
}


function toAddNewNews() {
	$.ajax({
		url : contextPath + "/admin/news",
		method : "post",
		data : {
			action : "toAddNewNews"
		},
		success : function(jsonStr) {
			$("#addNewNews").html(jsonStr);
		}
	})
}



function selectProductCategoryLevel(obj) {
	var level = $(obj).val();
	if (level == 1) {
		$("#productCategoryLevel1").parent().parent().hide();
		$("#productCategoryLevel2").parent().parent().hide();
	} else if (level == 2) {
		$("#productCategoryLevel1").parent().parent().show();
	} else {
		$("#productCategoryLevel1").parent().parent().show();
		$("#productCategoryLevel2").parent().parent().show();
	}
}

function querProductCategory(obj, s) {
	var parentId = $(obj).val();
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "queryProductCategoryList",
			parentId : parentId
		},
		dataType : "json",
		success : function(jsonStr) {
			if (jsonStr.status == 1) {
				var options = "<option value=''>" + "请选择</option>";
				for ( var i = 0; i < jsonStr.data.length; i++) {
					var option = "<option value=" + jsonStr.data[i].id + ">"
							+ jsonStr.data[i].name + "</option>";
					options += option;
				}
				if (s == "productCategoryLevel2") {
					$("#productCategoryLevel2").html(options);
				} else {
					$("#productCategoryLevel3").html(options);
				}
			}
		}
	})
}
function queryProductCategory(obj) {
	var parentId = $(obj).val();
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "queryProductCategoryList",
			parentId : parentId
		},
		dataType : "json",
		success : function(jsonStr) {
			if (jsonStr.status == 1) {
				var options = "<option value=''>" + "请选择</option>";
				for ( var i = 0; i < jsonStr.data.length; i++) {
					var option = "<option value=" + jsonStr.data[i].id + ">"
							+ jsonStr.data[i].name + "</option>";
					options += option;
				}
				$("#productCategoryLevel2").html(options);
			}
		}

	})
}

function addProductCategory() {
	var productCategoryLevel1 = $("#productCategoryLevel1").val();
	var productCategoryLevel2 = $("#productCategoryLevel2").val();
	var name = $("#name").val();
	var type = $("#type").val();
	$.ajax({
				url : contextPath + "/admin/productCategory",
				method : "post",
				data : {
					action : "addProductCategory",
					name : name,
					type : type,
					productCategoryLevel1 : (productCategoryLevel1 == null || productCategoryLevel1 == "") ? 0
							: productCategoryLevel1,
					productCategoryLevel2 : (productCategoryLevel2 == null || productCategoryLevel2 == "") ? 0
							: productCategoryLevel2,
				},
				dataType : "json",
				success : function(jsonStr) {
					if (jsonStr.status == 1) {
						window.location.reload();
					}
				}
			})
}



function saveNewNews() {
	var title = $("#title").val();
	var newsContent = $("#content").val();
	$.ajax({
		url : contextPath + "/admin/news",
		method : "post",
		data : {
			action : "addNews",
			title : title,
			newsContent : newsContent,
		},
		dataType : "json",
		success : function(jsonStr) {
			if (jsonStr.status == 1) {
				window.location.reload();
			}
		}
	})
}

function toUpdateProductCategory(obj) {
	var id = $(obj).val();
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "toUpdateProductCategory",
			id : id
		},
		success : function(jsonStr) {
			$("#addProductCategory").html(jsonStr);
		}
	})
}

function saveOrUpdate() {
	var id = $("#id").val();
	if (id == null || id == "") {
		addProductCategory();
	} else {
		modifyProductCategory();
	}
}

function modifyProductCategory() {
	var id = $("#id").val();
	var productCategoryLevel1 = $("#productCategoryLevel1").val();
	var productCategoryLevel2 = $("#productCategoryLevel2").val();
	var name = $("#name").val();
	var type = $("#type").val();
	$
			.ajax({
				url : contextPath + "/admin/productCategory",
				method : "post",
				data : {
					action : "modifyProductCategory",
					id : id,
					name : name,
					type : type,
					productCategoryLevel1 : (productCategoryLevel1 == null || productCategoryLevel1 == "") ? 0
							: productCategoryLevel1,
					productCategoryLevel2 : (productCategoryLevel2 == null || productCategoryLevel2 == "") ? 0
							: productCategoryLevel2,
				},
				dataType : "json",
				success : function(jsonStr) {
					if (jsonStr.status == 1) {
						window.location.reload();
					}
				}
			})
}

function checkProduct() {
	var productCategoryLevel1 = $("#productCategoryLevel1").val();
	var productCategoryLevel2 = $("#productCategoryLevel2").val();
	var productCategoryLevel3 = $("#productCategoryLevel3").val();
	var name = $("#name").val();
	var price = $("#price").val();
	var stock = $("#stock").val();
	if (productCategoryLevel1 == null || productCategoryLevel1 == "") {
		showMessage("请选择商品分类");
		return false;
	}
	if (name == null || name == "") {
		showMessage("请填写商品名称");
		return false;
	}
	if (name.length < 2 || name.length > 16) {
		showMessage("商品名称2到16字符");
		return false;
	}
	if (price == null || price == "") {
		showMessage("请填写商品价格");
		return false;
	}
	if (stock == null || stock == "") {
		showMessage("请填写商品库存");
		return false;
	}
}

function deleteUser(id) {
	var bool = window.confirm("确定删除此用户吗");
	if (bool) {
		$.ajax({
			url : contextPath + "/admin/user",
			method : "post",
			data : {
				id : id,
				action : "deleteUser"
			},
			success : function(jsonStr) {
				var result = eval("(" + jsonStr + ")");
				if (result.status == 1) {
					window.location.reload();
				}
			}
		})
	}
}


function updateUser(id){
	var loginName=$("input[name='loginName']").val();
	var userName=$("input[name='userName']").val();
	var email=$("input[name='email']").val();
	var mobile=$("input[name='mobile']").val();
	var identityCode=$("input[name='identityCode']").val();
	var type=$("#type").val();
	if(loginName==null || loginName==""){
		showMessage("用户名不能为空。");
		return;
	}
	if(loginName.length<2 || loginName.length>10){
		showMessage("用户名不能小于两个字符或大于十个字符。");
		return;
	}
	if(userName==null || userName==""){
		showMessage("真实姓名不能为空。");
		return;
	}
	if(userName.length<2 || userName.length>10){
		showMessage("真实姓名不能小于两个字符或大于十个字符。");
		return;
	}
	if(identityCode!=null && identityCode!="" && !checkIdentityCode(identityCode)){
		showMessage("身份证号格式不正确。");
		return;
	}
	if(email!=null && email!="" && !checkEmail(email)){
		showMessage("邮箱格式不正确。");
		return;
	}
	if(mobile!=null && mobile!="" && !checkMobile(mobile)){
		showMessage("手机格式不正确。");
		return;
	}
	
	$.ajax({
		url:contextPath+"/admin/user",
		method:"post",
		data:{
			action:"modifyUser",
			loginName:loginName,
			userName:userName,
			email:email,
			mobile:mobile,
			identityCode:identityCode,
			type:type,
			id:id
		},
		success:function(jsonStr){
			var result=eval("("+jsonStr+")");
			if(result.status==1){
				showMessage(result.message);
					window.location.href=contextPath+"/admin/user?action=queryUserList";
		}
			}
	})
	
}


function checkEmail(email){
	var filter=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(filter.test(email)){
		return true;
	}else{
		return false;
	}
}

function checkMobile(mobile){
	var filter=/^\d{5,11}$/;
	if(filter.test(mobile)){
		return true;
	}else{
		return false;
	}
}

function checkIdentityCode(identityCode){
	var filter=/^\w{18}$/;
	if(filter.test(identityCode)){
		return true;
	}else{
		return false;
	}
}









