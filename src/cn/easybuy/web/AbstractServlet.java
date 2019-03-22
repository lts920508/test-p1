package cn.easybuy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ReturnResult;

public abstract class AbstractServlet extends HttpServlet {

	public abstract Class getServletClass();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Method method = null;
		Object result = null;
		if (EmptyUtils.isEmpty(action)) {
			request.getRequestDispatcher("/pre/index.jsp").forward(request,
					response);
		} else {
			try {
				method = getServletClass().getDeclaredMethod(action,
						HttpServletRequest.class, HttpServletResponse.class);
				result = method.invoke(this, request, response);
				toView(request, response, result);
			} catch (NoSuchMethodException e) {
				String viewName="404.jsp";
				request.getRequestDispatcher(viewName).forward(request,
						response);
				e.printStackTrace();
			} catch (Exception e) {
				if (!EmptyUtils.isEmpty(result)) {
					if (result instanceof String) {
						String viewName="500.jsp";
						request.getRequestDispatcher(viewName).forward(request,
								response);
					}else{
						ReturnResult returnR=new ReturnResult();
						returnR.returnFail("系统错误");
					}
				}else{
					String viewName="500.jsp";
					request.getRequestDispatcher(viewName).forward(request,
							response);
				}
				e.printStackTrace();
			}
		}
	}

	public void toView(HttpServletRequest request,
			HttpServletResponse response, Object result) throws Exception {
		if (!EmptyUtils.isEmpty(result)) {
			if (result instanceof String) {
				String viewName = result.toString() + ".jsp";
				request.getRequestDispatcher(viewName).forward(request,
						response);
			}else{
				write(result, response);
			}
		}
	}

	public void write(Object obj, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String json = JSONObject.toJSONString(obj);
		PrintWriter writer = null;
		if (null != response) {
			try {
				writer = response.getWriter();
				writer.print(json);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				writer.close();
			}
		}
	}
}
