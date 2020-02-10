package mtp.board.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardListUICommand implements MemberCommand {

	public BoardListUICommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		System.out.println(request.getParameter("flag"));
		request.setAttribute("flag",request.getParameter("flag")); 
		return new CommandAction(false,"board_list.jsp");
	}

}
