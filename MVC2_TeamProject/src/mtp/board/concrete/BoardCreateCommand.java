package mtp.board.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class BoardCreateCommand implements MemberCommand {

	public BoardCreateCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDTO dto = null;
		try {
			new BoardDAO().create(new BoardDTO(0,
											 ((MemberDTO)request.getSession(false).getAttribute("dto")).getM_id(),
											   title,content,null,0,0,0,0));
		} catch (Exception e) {
			return new CommandAction(false , "createui.do") ;
		}
	
		return new CommandAction(true ,"list.do?flag=true");
	}

}
