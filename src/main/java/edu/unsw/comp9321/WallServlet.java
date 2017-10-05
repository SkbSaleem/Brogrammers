package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WallServlet
 */
public class WallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WallServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Wall wallReturn = new Wall();
		List<PostPojo> result = wallReturn.wallPost(request);
		System.out.println("RESULT" + result);
		
		
//		String test = (String) result.get(0)[1];
		
		if (!result.isEmpty()) {
			
			//SearchBean searchBean = new SearchBean(search);
			
	
			
			System.out.println("PRINT ING G" + result);
			
			
			 request.setAttribute("posts", result);
			
			
			 request.getRequestDispatcher("loggedin/wall.jsp").forward(request, response);
			
		}
		else {
			
			request.getRequestDispatcher("loggedin/fail.jsp").forward(request, response);
			
		}
		
		
		
		
		
	}
	
	
	
	

}