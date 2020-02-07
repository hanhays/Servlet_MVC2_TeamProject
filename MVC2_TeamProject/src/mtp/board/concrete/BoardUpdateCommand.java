package mtp.board.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardUpdateCommand implements MemberCommand {

	public BoardUpdateCommand() {}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		int num =Integer.valueOf(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");		
		new BoardDAO().update(new BoardDTO(num, ((BoardDTO)request.getSession(false).getAttribute("dto")).getM_id(),title, content, null, 0, 0, 0, 0));
		
		
		return new CommandAction(true, "read.do?num="+num);
	}

}
