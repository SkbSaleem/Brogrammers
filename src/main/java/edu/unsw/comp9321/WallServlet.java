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
		if(request.getParameter("likes") != null) {
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			String username = ((UsersPojo) request.getSession().getAttribute("credit")).getUserName();
			
			new Like().addLike(username, post_id);
			Wall wallReturn = new Wall();
			List<PostPojo> result = wallReturn.wallPost(request);
			request.getSession().setAttribute("posts", result);

			System.out.println("RESULT" + result);
				
			//request.getRequestDispatcher("/WallServlet").forward(request, response);
		}
		
		Wall wallReturn = new Wall();
		List<PostPojo> result = wallReturn.wallPost(request);
		request.getSession().setAttribute("posts", result);
		
		
//		String test = (String) result.get(0)[1];
		
			
			//SearchBean searchBean = new SearchBean(search);
			
			
			 //request.getSession().setAttribute("posts", result);
			
			
			 response.sendRedirect(request.getContextPath()+ "/loggedin/wall.jsp");		
		
		
	}
	
	
	
	

}
