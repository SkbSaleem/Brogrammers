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
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String nextPage = request.getRequestURI();
		Credit authenticated = (Credit) request.getSession().getAttribute("credit");
		if(authenticated == null || !authenticated.isAuthorized()) {
			nextPage="/login.jsp?unauthorized=true";
			//request.setAttribute("filtererror", "You are not authorized to view this page. Please log in or create a user.");
			String contextPath = request.getContextPath();
			response.sendRedirect(response.encodeRedirectURL(contextPath + nextPage)); 
		}
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
