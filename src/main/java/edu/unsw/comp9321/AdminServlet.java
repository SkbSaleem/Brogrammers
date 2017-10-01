package edu.unsw.comp9321;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		String nextPage = "";
		if(request.getParameter("ban")!=null) {
			String username = request.getParameter("ban");
			new UsersData().banUser(username);
			nextPage = "/admin/admin.jsp";
			request.getSession().setAttribute("users", new UsersData().getAllUsers());
		}
		if(request.getParameter("logout")!=null) {
			request.getSession().invalidate();
			nextPage="/adminlogin.jsp";
		}
		response.sendRedirect(request.getContextPath()+ nextPage);
	}
}
