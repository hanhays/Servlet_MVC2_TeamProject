package mtp.board.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardUpdateUICommand implements MemberCommand {

	public BoardUpdateUICommand() {}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String num_ = request.getParameter("num");
		int num = -1;
		if (num_ != null) num = Integer.parseInt(num_);
		
		BoardDTO dto = new BoardDAO().updateui(num);
		
		request.setAttribute("dto", dto);
		
		return new CommandAction(false, "board_update.jsp");
	}

}
