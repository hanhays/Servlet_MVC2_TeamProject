package mtp.board.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.mms.BoardDAO;
import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardDeleteCommand implements MemberCommand {

	public BoardDeleteCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String num = request.getParameter("num");
		
		if (num!=null) {
			new BoardDAO().delete(Integer.parseInt(num));
			return new CommandAction(true, "list.do");
		}
		return new CommandAction();
	}

}
