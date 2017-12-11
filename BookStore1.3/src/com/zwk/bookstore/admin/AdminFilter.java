package com.zwk.bookstore.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zwk.bookstore.user.domain.User;

import cn.itcast.commons.CommonUtils;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(
		urlPatterns = { "/adminjsps/admin/*" }, 
		servletNames = { 
				"AdminAddBookServlet", 
				"AdminBookServlet", 
				"AdminCategaryServlet", 
				"AdminOrderServlet", 
				"AdminUserServlet"
		})
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
		User admin = (User)hrequest.getSession().getAttribute("admin");
		if(admin!=null){
			chain.doFilter(request, response);
		} else{
			request.setAttribute("msg", "过滤器:您不是管理员,请登录后操作!");
			request.getRequestDispatcher("/adminjsps/login.jsp").forward(request,response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
