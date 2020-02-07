package mtp.board.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardCreateUICommand implements MemberCommand {

	public BoardCreateUICommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		if(request.getSession(false)!=null) {
			return new CommandAction(false,"board_create.jsp");
		}
		return new CommandAction(true , "../member/loginui.do");
	}

}
