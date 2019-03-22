package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.RegUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns="/Register",name="Register")
public class RegisterServlet extends AbstractServlet {

	private UserService userService;

	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}


	@Override
	public Class getServletClass() {
		return RegisterServlet.class;
	}

	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/pre/register";
	}

	public ReturnResult doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		User user = new User();
		String loginName=request.getParameter("loginName");
		User oldUser=userService.getUserByLoginName(loginName);
		if (EmptyUtils.isNotEmpty(oldUser)) {
			result.returnFail("用户已经存在");
			return result;
		}
		String sex=request.getParameter("sex");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		String identityCode=request.getParameter("identityCode");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String type=request.getParameter("type");
		user.setLoginName(loginName);
		user.setPassword(SecurityUtils.mt5Hex(password));
		user.setSex(EmptyUtils.isEmpty(sex)?1:Integer.parseInt(sex));
		user.setIdentityCode(identityCode);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setUserName(userName);
		if (EmptyUtils.isEmpty(type)) {
			user.setType(Constants.UserType.PRE);
		}else{
			user.setType(Integer.parseInt(type));
		}
		result=checkUser(user);
		if (result.getStatus()==Constants.ReturnResult.SUCCESS) {
			boolean flag=userService.save(user);
			if (!flag) {
				return result.returnFail("注册失败");
			}else{
				return result.returnSuccess("注册成功");
			}
		}else{
			return result;
		}
	}
	
	private ReturnResult checkUser(User user) {
		ReturnResult result=new ReturnResult();
		if(EmptyUtils.isNotEmpty(user.getMobile())){
			if (!RegUtils.checkMobile(user.getMobile())) {
				return result.returnFail("手机格式不正确");
			}
		}
		if(EmptyUtils.isNotEmpty(user.getIdentityCode())){
			if (!RegUtils.checkIdentityCode(user.getIdentityCode())) {
				return result.returnFail("身份证格式不正确");
			}
		}
		if(EmptyUtils.isNotEmpty(user.getEmail())){
			if (!RegUtils.checkEmail(user.getEmail())) {
				return result.returnFail("邮箱格式不正确");
			}
		}
		return result.returnSuccess();
	}
}
