package mtp.board.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class BoardReadCommand implements MemberCommand {

	public BoardReadCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		BoardDTO dto = null;
		try {
		//int num = Integer.parseInt(request.getParameter("num"));
			//dto = new BoardDAO().read(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dto", dto);
		 
		return new CommandAction(false, "board_read.jsp");
	}

}
