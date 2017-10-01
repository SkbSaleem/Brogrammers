package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class Search
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String credit = (String)request.getAttribute("credit");
		System.out.println("haha" + credit);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		Search searchReturn = new Search();
		List<UsersPojo> result = searchReturn.searchResult(search);
		
		if (!result.isEmpty()) {
			
			//SearchBean searchBean = new SearchBean(search);
			
			//String username = request.getParameter("username");
			
			UsersPojo credit = (UsersPojo) request.getSession().getAttribute("credit");
			String token = credit.getUrl();
			 request.setAttribute("result", result);
			 request.setAttribute("token", token);
			
			 request.getRequestDispatcher("loggedin/results.jsp").forward(request, response);
			
		}
		else {
			
			request.getRequestDispatcher("loggedin/fail.jsp").forward(request, response);
			
		}
	}

}
