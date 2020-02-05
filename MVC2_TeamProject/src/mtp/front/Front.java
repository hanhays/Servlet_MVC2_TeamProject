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
		String member = "/member";
		String board = "/board";
		String getPath = uri.substring(path.length());
		String what = getPath.substring(uri.contains(member) ? member.length() : board.length()).toLowerCase();
		String[] root = getPath.split(what);
		Command com = null;
		switch (root[0].toLowerCase()) {

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
