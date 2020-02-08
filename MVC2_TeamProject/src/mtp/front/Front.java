package mtp.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.front.controller.BoardController;
import mtp.front.controller.MemberController;
import mtp.interfaces.Command;
import mtp.view.forward.CommandAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class Front extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Front() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		String[] uris=setURI(uri,path);
		String root = uris[0];
		String what = uris[1];
		System.out.println(root);
		System.out.println(what);
		Command com = null;
		switch (root) {
		case "/member":
			com = new MemberController();
			break;
		case "/board":
			com = new BoardController();
			break;
		}
		CommandAction ca = com != null ? com.execute(request, response, what) : null;
		if (ca != null) {
			if (ca.isSend()) {
				response.sendRedirect(ca.getWhere()/* .contains("/")?ca.getWhere():"index.jsp" */);
				return;
			}
			request.getRequestDispatcher(ca.getWhere()).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */ 
	private String[] setURI(String uri,String path) {
		String[] uris = uri.split(path);
		String target = uris[uris.length-1].contains("/member")?"/member":"/board";
		System.out.println("target = "+target );
		uri = uris[uris.length-1].substring(target.length());
		System.out.println("uri ="+uri);
		uri = uri.contains(target)?uri.substring(target.length()):uri;
		System.out.println("sub -> uri ="+uri); 
		String root = uris[uris.length-1].split(uri)[0];
		System.out.println("root="+root);
		root = root.contains(target)?root.length()!=target.length()?
				root.substring(target.length()):root:root;
		System.out.println("sub -> root ="+root);
		return new String[] {root,uri}; 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
