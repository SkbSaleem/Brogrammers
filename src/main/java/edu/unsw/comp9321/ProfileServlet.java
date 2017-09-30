package edu.unsw.comp9321;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Servlet implementation class EditUserServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
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

		String param = request.getParameter("param");
		String nextPage="loggedin/edit.jsp";
		if(param.equals("newprofilepic")) {
			try {
				if (! ServletFileUpload.isMultipartContent(request)) {
					System.out.println("sorry. No file uploaded");
					return;
				}

				// Apache Commons-Fileupload library classes
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload sfu  = new ServletFileUpload(factory);

				List fileitems = sfu.parseRequest(request);
				
				FileItem fi = (FileItem) fileitems.get(0);
				
				new UsersData().updateProfilePicture(fi, request);
				UsersPojo user = (UsersPojo) request.getSession().getAttribute("credit");
				request.getSession().setAttribute("convertedProfilepic",  Base64.encode(user.getProfilePic()).toString());
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		if(param.equals("edit")) {
			Map<String, String[]> attributes = request.getParameterMap();
			Map<String, String> fields = new HashMap<String, String>();
			for(Entry<String, String[]> entry : attributes.entrySet()) {
				if(entry.getValue()!=null) {
					for(String s: entry.getValue()) {
						if(!s.isEmpty()) {
							fields.put(entry.getKey(), s);
						}
					}
				}
			}
			fields.remove("edit");
			new UsersData().updateUserInfo(fields, request);
		}
		response.sendRedirect(request.getContextPath()+"/"+nextPage);
	}
}
