package cn.easybuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.easybuy.entity.User;
import cn.easybuy.utils.EmptyUtils;

@WebFilter(urlPatterns = { "/*" }, filterName = "UserFilter", initParams = { @WebInitParam(name = "encode", value = "utf-8") })
public class UserFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.contains("/admin/")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("loginUser");
			if (uri.contains("queryAllOrder") || uri.contains("queryUserList")
					|| uri.contains("/admin/productCategory")
					|| uri.contains("/admin/product")) {
				if (EmptyUtils.isNotEmpty(user)) {
					if (user.getType() == 1) {
						chain.doFilter(req, res);
					}
				} else {
					res.sendRedirect("/EasyBuy/Login?action=toLogin");
				}
			} else {
				if (EmptyUtils.isNotEmpty(user)) {
					chain.doFilter(req, res);
				} else {
					res.sendRedirect("/EasyBuy/Login?action=toLogin");
				}
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
