package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

/**
 * Servlet implementation class profileController
 */
public class profileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List <String> wordsList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public profileController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() throws ServletException {
		   System.out.println("INIT servlet");
		   FileReaderClass file = new FileReaderClass("bullylist2.txt");
		   wordsList = (List<String>) file.getWordsList();
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		UsersPojo c = (UsersPojo) request.getSession().getAttribute("credit");
		List<PostPojo> p = null;
		String nextpage = "";
		// Create post
		if (ServletFileUpload.isMultipartContent(request)) {
			nextpage = "index.jsp";
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload sfu = new ServletFileUpload(factory);

				List fileitems = sfu.parseRequest(request);
				HashMap items = new HashMap();

				Iterator<FileItem> iter = fileitems.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (item.isFormField()) {
						items.put(item.getFieldName(), item.getString());
					} else {
						items.put(item.getFieldName(), item);
					}
				}
				new PostData().createPost(items, c.getUserName());
				//System.out.println(c.getUserName() + " posted: "+ items.get("textareapost"));
				boolean match = false;
				String JSON = new RestCurationAPI().
						ExtractFeaturesRest("http://d2dcrc.cse.unsw.edu.au:9091/ExtractionAPI-0.0.1-SNAPSHOT/rest/keyword", 
								"sentence", items.get("textareapost").toString());
				for(String s : wordsList) {					
				for (String keyword : new JSONObject(JSON).get("keyword").toString().split(",")) {
					if(s.equals(keyword)) {
						System.out.println("match:" + keyword);
						JavaMail.sendEmailToAdmin("christian.trinh@student.unsw.edu.au", 
								items.get("textareapost").toString(), c.getUserName().toString());
						match = true;
						break;
					}
				}
				if (match==true) {
					break;
				}
				}
				
			} catch (Exception e) {
			}
		}

		/*
		 * String content = request.getParameter("textareapost"); new
		 * PostData().createPost(content, c.getUserName()); nextpage = "index.jsp";
		 */
		// request.getSession().setAttribute("plist", p);

		if (request.getParameter("btn-deletepost") != null) {
			int pid = Integer.parseInt(request.getParameter("btn-deletepost"));
			// System.out.println("pid"+pid);
			new PostData().deletePost(pid);
			nextpage = "index.jsp";

		}
		if (request.getParameter("btn-editpost") != null) {
			int pid = Integer.parseInt(request.getParameter("btn-editpost"));
			//System.out.println(pid);
			request.getSession().setAttribute("content", request.getParameter("content"));
			// System.out.println(request.getParameter("editcontent"));
			// System.out.println(request.getParameter("bodyContent"));

			nextpage = "editpost.jsp?postID=" + pid;

		}

		if (request.getParameter("btn-submitchanges") != null) {
			System.out.println("Edit button clicked");
			String content = request.getParameter("editedpost");
			int pid = Integer.parseInt(request.getParameter("btn-submitchanges"));
			System.out.println(pid + ":" + content);
			new PostData().updatePost(pid, content);
			nextpage = "index.jsp";

		}

		p = new PostData().getProfilePost(c.getUserName());

		// request.getRequestDispatcher("/loggedin/index.jsp").forward(request,
		// response);
		request.getSession().setAttribute("plist", p);

		response.sendRedirect(request.getContextPath() + "/loggedin/" + nextpage);

	}

}
