package mtp.front.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.concrete.BoardCreateCommand;
import mtp.board.concrete.BoardDeleteCommand;
import mtp.board.concrete.BoardListCommand;
import mtp.board.concrete.BoardReadCommand;
import mtp.board.concrete.BoardSearchCommand;
import mtp.board.concrete.BoardUpdateCommand;
import mtp.board.interfaces.BoardCommand;
import mtp.board.ui.BoardCreateUICommand;
import mtp.board.ui.BoardDeleteUICommand;
import mtp.board.ui.BoardListUICommand;
import mtp.board.ui.BoardUpdateUICommand;
import mtp.member.concrete.MemberCreateCommand;
import mtp.member.concrete.MemberDeleteCommand;
import mtp.member.concrete.MemberListCommand;
import mtp.member.concrete.MemberLoginCommand;
import mtp.member.concrete.MemberLogoutCommand;
import mtp.member.concrete.MemberReadCommand;
import mtp.member.concrete.MemberSearchCommand;
import mtp.member.concrete.MemberUpdateCommand;
import mtp.member.interfaces.MemberCommand;
import mtp.member.ui.MemberCreateUICommand;
import mtp.member.ui.MemberDeleteUICommand;
import mtp.member.ui.MemberListUICommand;
import mtp.member.ui.MemberLoginUICommand;
import mtp.member.ui.MemberUpdateUICommand;
import mtp.view.forward.CommandAction;

public class BoardController implements BoardCommand {

	public BoardController() {
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		
		MemberCommand b_com = null;
		switch (what) {
		case "/read.do":
			b_com = new BoardReadCommand();
			break;
		case "/update.do":
			b_com = new BoardUpdateCommand();
			break;
		case "/list.do":
			b_com = new BoardListUICommand();
			break;
		case "/listloading.do":
			b_com = new BoardListCommand();
			break;
		case "/create.do":
			b_com = new BoardCreateCommand();
			break;
		case "/delete.do":
			b_com = new BoardDeleteCommand();
			break;
		case "/updateui.do":
			b_com = new BoardUpdateUICommand();
			break;
		case "/createui.do":
			b_com = new BoardCreateUICommand();
			break;
		case "/search.do":
			b_com = new BoardSearchCommand();
			break;
		}
		return b_com.execute(request, response, what);
		
	}

}
