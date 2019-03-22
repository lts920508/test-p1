function addCartByParam(entityId,quantity){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"add",
			entityId:entityId,
			quantity:quantity,
		},
		success:function(jsonStr){
			var result=eval("("+jsonStr+")");
			if(result.status==1){
				showMessage("操作成功");
				refreshCart();
			}else{
				showMessage(result.message);
			}
		}
	})
}
function refreshCart(){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"refreshCart",
		},
		success:function(jsonStr){
			$("#searchBar").html(jsonStr);
		}
	})
}


function settlement1(){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"toSettlement1",
		},
		success:function(jsonStr){
			$("#settlement").html(jsonStr);
		}
	})
}

function subQuantity(obj,entityId){
	var quantity=Number(getPCount(obj))-1;
	if(quantity==0)quantity=1;
	modifyCart(entityId,quantity,obj);
}

function addQuantity(obj,entityId,stock){
	var quantity=Number(getPCount(obj))+1;
	if(quantity>stock){
		showMessage("商品数量不足");
		return;
	}modifyCart(entityId,quantity,obj);
}

function modifyCart(entityId,quantity,obj){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"modifyCart",
			entityId:entityId,
			quantity:quantity
		},
		dataType:"json",
		success:function(jsonStr){
			if(jsonStr.status==1){
				obj.parent().find(".car_ipt").val(quantity);
				settlement1();
			}else{
				showMessage(jsonStr.message);
			}
		}
	})
}


function removeCart(entityId){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"modifyCart",
			entityId:entityId,
			quantity:0
		},
		dataType:"json",
		success:function(jsonStr){
			if(jsonStr.status==1){
				settlement1();
			}else{
				showMessage(jsonStr.message);
			}
		}
	})
}

function settlement2(){
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"settlement2",
		},
		success:function(jsonStr){
			$("#settlement").html(jsonStr);
		}
	})
}

function settlement3(){
	var addressId=$("input[name='selectAddress']:checked").val();
	var newAddress=$("input[name='address']").val();
	var newRemark=$("input[name='remark']").val();
	if(addressId==null || addressId==""){
		showMessage("请选择收货地址");
		return;
	}else if(addressId=='-1'){
		if(newAddress==null || newAddress==""){
			showMessage("请填写收货地址");
			return;
		}else if(newAddress<=2 || newAddress>=50){
			showMessage("收货地址为2-50个字符");
			return;
		}
	}
	$.ajax({
		url:contextPath+"/Cart",
		method:"post",
		data:{
			action:"settlement3",
			addressId:addressId,
			newAddress:newAddress,
			newRemark:newRemark
		},
		success:function(jsonStr){
			$("#settlement").html(jsonStr);
		}
	})
}

function addCart(){
	var entityId=$("input[name='entityId']").val();
	var quantity=$("input[name='quantity']").val();
	addCartByParam(entityId,quantity);
}




