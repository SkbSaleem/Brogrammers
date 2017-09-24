package edu.unsw.comp9321;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoggedinFilter
 */
@WebFilter(filterName = "LoggedinFilter", urlPatterns= {"/loggedin/*"})
public class LoggedinFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoggedinFilter() {
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
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("test");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String nextPage = request.getRequestURI();
		boolean authenticated = Boolean.parseBoolean(request.getSession().getAttribute("authenticated").toString());
		System.out.println(authenticated);
		if(!authenticated) {
			request.getSession().setAttribute("previousPage", nextPage);
			nextPage = "/login.jsp";
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
