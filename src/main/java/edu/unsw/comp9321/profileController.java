package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class profileController
 */
public class profileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("jupp");
		Credit c = (Credit) request.getSession().getAttribute("credit");
		List<Object[]> p = null;
		if (request.getParameter("btn-post").equals("post")) {
			String content = request.getParameter("textareapost");
			new PostData().createPost(content, c.getUsername());

			//request.getSession().setAttribute("plist", p);
		}
		p = new PostData().getProfilePost(c.getUsername());
		request.getSession().setAttribute("plist", p);


	//request.getRequestDispatcher("/loggedin/index.jsp").forward(request, response);

	response.sendRedirect(request.getContextPath()+"/loggedin/index.jsp");

	}

}
