package cn.easybuy.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.easybuy.entity.Order;
import cn.easybuy.entity.User;
import cn.easybuy.params.OrderParams;
import cn.easybuy.params.UserParams;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.RegUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/admin/user"},name="user")
public class UserServlet extends AbstractServlet {
	private UserService userService;

	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}
	
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("menu", 2);
		HttpSession session=request.getSession();
		return "/backend/user/userDetail";
	}

	public String queryUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList=new ArrayList<User>();
		String currentPageStr=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		UserParams params=new UserParams();
		int total=userService.getUserRowCount(params);
		Pager pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/admin/user?action=queryUserList");
		userList=userService.getAllUser(pager);
		request.setAttribute("userList", userList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 8);
		return "/backend/user/userList";
	}
	
	public String toAddUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("menu", 8);
		return "/backend/user/toAddUser";
	}
	
	public ReturnResult deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		userService.deleteUser(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}
	
	public String toUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userService.getUserById(id);
		request.setAttribute("user", user);
		request.setAttribute("menu", 8);
		return "/backend/user/toUpdateUser";
	}
	
	public ReturnResult modifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result=new  ReturnResult();
		User user = new User();
		Integer id=Integer.parseInt(request.getParameter("id"));
		String loginName=request.getParameter("loginName");
		String userName=request.getParameter("userName");
		String identityCode=request.getParameter("identityCode");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String type=request.getParameter("type");
		
		user.setLoginName(loginName);
		user.setIdentityCode(identityCode);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setUserName(userName);
	    user.setType(Integer.parseInt(type));
	    user.setId(id);
		result=checkUser(user);
		if (result.getStatus()==Constants.ReturnResult.SUCCESS) {
			boolean flag=userService.modifyUser(user);
			if (!flag) {
				return result.returnFail("修改失败");
			}else{
				return result.returnSuccess("修改成功");
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
	
	@Override
	public Class getServletClass() {
		return UserServlet.class;
	}

}
