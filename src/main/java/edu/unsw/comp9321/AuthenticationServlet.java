package edu.unsw.comp9321;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xml.internal.security.utils.Base64;

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
		if(request.getParameter("param")!=null) {
			if(request.getParameter("param").equals("logout"))
				request.getSession().invalidate();
				nextPage = "login.jsp";
		}
		
		if(request.getParameter("token")!=null) {
			
			UsersPojo credit = new UsersData().confirm(request.getParameter("token"));
			if(credit!=null) {
				request.getSession().setAttribute("credit", credit);
				request.getSession().setAttribute("convertedProfilepic", Base64.encode(credit.getProfilePic()).toString());
				request.getSession().setAttribute("plist", new PostData().getProfilePost(credit.getUserName()));
				nextPage = "loggedin/index.jsp";
			}
			else {
				nextPage = "login.jsp?confirmed=false";
			}
		}
		response.sendRedirect(request.getContextPath()+"/"+nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage="";
		String param = request.getParameter("param");
		if(param.equals("create")) {
			nextPage = "confirm.jsp";
			try {
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
			UsersPojo authenticated = new UsersData().authenticate(username, password);
			if(authenticated!=null) {
				if(authenticated.isBanned()==true) {
					nextPage = "login.jsp?unauthorized=true";
				}
				else {
					request.getSession().setAttribute("credit", authenticated);
					request.getSession().setAttribute("convertedProfilepic", Base64.encode(authenticated.getProfilePic()).toString());
					request.getSession().setAttribute("plist", new PostData().getProfilePost(authenticated.getUserName()));
					nextPage = "loggedin/index.jsp";
				}
				}
			else {
				nextPage = "login.jsp?loginfailed";
			}
		}
		
		if(param.equals("admin")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminPojo authenticated = new UsersData().authenticateAdmin(username,password);
			List<UsersPojo> users = new UsersData().getAllUsers();
			if(authenticated!=null) {
				nextPage = "admin/admin.jsp";
				request.getSession().setAttribute("admin", authenticated);
				request.getSession().setAttribute("users", users);
			}
			else {
				nextPage = "adminlogin.jsp?loginfailed";
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/"+nextPage);
	}
}
