package edu.unsw.comp9321;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		if(request.getParameter("token")!=null) {
			
			Credit credit = new UsersData().confirmUser(request.getParameter("token"));
			if(credit.isAuthorized()) {
				nextPage = "/loggedin/index.jsp";
			}
			else {
				request.setAttribute("loginfailed", true);
				nextPage = "login.jsp";
			}
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String nextPage="";
		String param = request.getParameter("param");
		if(param.equals("create")) {
			nextPage = "confirm.jsp";
			try {
				if (! ServletFileUpload.isMultipartContent(request)) {
					System.out.println("sorry. No file uploaded");
					return;
				}

				// Apache Commons-Fileupload library classes
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload sfu  = new ServletFileUpload(factory);

				List fileitems = sfu.parseRequest(request);
				HashMap items = new HashMap();
				
				Iterator<FileItem> iter = fileitems.iterator();
				while(iter.hasNext()) {
					FileItem item = iter.next();
					if(item.isFormField()) {
						items.put(item.getFieldName(), item.getString());
					}
					else {
						items.put(item.getFieldName(), item);
					}
				}
				new UsersData().createUser(items, request);
				if(request.getAttribute("exists")!=null) {
					nextPage = "register.jsp?exists=true";
				}
			}catch(Exception e){
				nextPage="register.jsp";
				e.printStackTrace();
			}
		}
		if(param.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Credit isAuthenticated = new UsersData().authenticateUser(username, password);
			if(isAuthenticated.isAuthorized()) {
				request.getSession().setAttribute("credit", isAuthenticated);
				nextPage = "loggedin/index.jsp";
			}
			else {
				request.setAttribute("loginfailed", true);
				nextPage = "login.jsp";
			}
		}
		if(param.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("/login.jsp");
		}
			RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
			 rd.forward(request, response);	
	}
}
