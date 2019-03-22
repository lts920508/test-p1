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
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/Login" }, name = "Login")
public class LoginServlet extends AbstractServlet {
	// 注入用户业务类
	private UserService userService;

	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	public Class getServletClass() {
		return LoginServlet.class;
	}

	/*
	 * 跳转登录页面
	 */
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return "/pre/login";
	}

	public ReturnResult login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 参数获取
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		User user = userService.getUserByLoginName(loginName);
		if (EmptyUtils.isEmpty(user)) {
			result.returnFail("用户不存在");
		} else {
			if (user.getPassword().equals(SecurityUtils.mt5Hex(password))) {
				request.getSession().setAttribute("loginUser", user);
				result.returnSuccess("登录成功");
			} else {
				result.returnFail("密码错误");
			}
		}
		return result;
	}
	
	public String loginOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		request.getSession().removeAttribute("loginUser");
		result.returnSuccess("注销成功");
		return "/pre/login";
	}
}
