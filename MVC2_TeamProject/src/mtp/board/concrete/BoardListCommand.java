package mtp.board.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mtp.board.mms.BoardDAO;
import mtp.member.interfaces.MemberCommand;
import mtp.paging.vo.PageVO;
import mtp.view.forward.CommandAction;

public class BoardListCommand implements MemberCommand {

	public BoardListCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String currentPage_ = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentPage_ != null) currentPage = Integer.parseInt(currentPage_);
		
//		List<BoardDTO> list = new BoardDAO().list();
		
		PageVO pv = new BoardDAO().list(currentPage);
	
		request.setAttribute("list", pv.getB_list());
		request.setAttribute("pv", pv);
		
		return new CommandAction(false, "board_list.jsp");
	}

}
