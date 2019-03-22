function register(s){
	var loginName=$("input[name='loginName']").val();
	var userName=$("input[name='userName']").val();
	var password=$("input[name='password']").val();
	var confirmPassword=$("input[name='confirmPassword']").val();
	var email=$("input[name='email']").val();
	var mobile=$("input[name='mobile']").val();
	var identityCode=$("input[name='identityCode']").val();
	var address=$("input[name='address']").val();
	var sex=$("input[name='sex']:checked").val();
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
	if(password!=confirmPassword){
		showMessage("两次输入的密码不一致。");
		return;
	}
	if(password==""){
		showMessage("密码不能为空。");
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
		url:contextPath+"/Register",
		method:"post",
		data:{
			action:"login",
			loginName:loginName,
			userName:userName,
			password:password,
			sex:sex,
			email:email,
			mobile:mobile,
			action:'doRegister',
			identityCode:identityCode,
			address:address,
			type:type
		},
		success:function(jsonStr){
			var result=eval("("+jsonStr+")");
			if(result.status==1){
				showMessage(result.message);
				if(s=="xinzeng"){
					window.location.href=contextPath+"/admin/user?action=queryUserList";
				}else{
					window.location.href=contextPath+"/Login?action=toLogin";
				}
			}else{
				showMessage(result.message);
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

function searchUserKuang(){
	var keyWord=$("#search").val();
	$.ajax({
		url:contextPath+"/Product",
		method:"post",
		data:{keyWord:keyWord,action:"search"},
		dataType:"json",
		success:function(jsonStr){
			if(jsonStr.status == 1){
				window.location.href=contextPath+"/Product?action=queryProductList&keyWord="+keyWord;
			}else{
				showMessage(jsonStr.message);
			}
		}
	})
}