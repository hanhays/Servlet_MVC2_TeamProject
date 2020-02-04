package mtp.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.interfaces.Command;
import mtp.member.concrete.MemberReadCommand;
import mtp.view.forward.CommandAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		String member = "/member";
		String board = "/board";
		String getPath = uri.substring(path.length());
		
		String parameter = getPath.substring(uri.contains(member)?member.length():board.length());
		String[] root = getPath.split(parameter);
		Command com = null;
		switch (root[0]) {
		case "/member":
			
			break;
		case "/board":
		
			break;
		default:
			break;
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
