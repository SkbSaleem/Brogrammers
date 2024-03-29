package edu.unsw.comp9321;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AcceptFriendServlet
 */
public class AcceptFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptFriendServlet() {
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
		//doGet(request, response);
		
		String request_user = request.getParameter("request_user");
		
		Friend friendReturn = new Friend();
		String result = friendReturn.acceptRequest(request_user, request);
		
		if (result == "success"){
			
			
			 request.getRequestDispatcher("loggedin/index.jsp").forward(request, response);
		}
		else if (result == "fail"){
			
			 request.getRequestDispatcher("loggedin/DuplicateEntry.jsp").forward(request, response);
			
			
			
		}
		
	}

}
