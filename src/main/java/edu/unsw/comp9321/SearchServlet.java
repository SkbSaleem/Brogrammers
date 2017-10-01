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
		doGet(request, response);
		
		
		
		String search = request.getParameter("search");
		
		
		
		Search searchReturn = new Search();
		List<Object[]> result = searchReturn.searchResult(search);
		
		ArrayList<String> test = new ArrayList<String>();  
		
		for (int i = 0; i<result.size(); i++) {
			
			test.add((String) result.get(i)[4]);
			test.add((String) result.get(i)[5]);
			
			
			
		}
//		String test = (String) result.get(0)[1];
		System.out.println("PRINT ING G" + test);
		
		if (!result.isEmpty()) {
			
			//SearchBean searchBean = new SearchBean(search);
			
			//String username = request.getParameter("username");
			
			UsersPojo credit = (UsersPojo) request.getSession().getAttribute("credit");
			String token = credit.getUrl();
			System.out.println(token);
			 request.setAttribute("test", test);
			 request.setAttribute("token", token);
			
			 request.getRequestDispatcher("loggedin/results.jsp").forward(request, response);
			
		}
		else {
			
			request.getRequestDispatcher("loggedin/fail.jsp").forward(request, response);
			
		}
		
	
		
		
		
	}

}
