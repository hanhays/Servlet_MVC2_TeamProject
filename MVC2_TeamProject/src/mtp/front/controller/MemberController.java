package mtp.front.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.interfaces.Command;
import mtp.member.concrete.*;
import mtp.member.interfaces.MemberCommand;
import mtp.member.ui.*;
import mtp.member.ui.MemberUpdateUICommand;
import mtp.view.forward.CommandAction;

public class MemberController implements Command {

	public MemberController() {

	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		MemberCommand m_com = null;
		switch (what) {
		case "/read.do":
			m_com = new MemberReadCommand();
			break;
		case "/updateui.do":
			m_com = new MemberUpdateUICommand();
			break;
		case "/update.do":
			m_com = new MemberUpdateCommand();
			break;
		case "/update_admin.do":
			m_com = new MemberUpdateAdminCommand();
			break;
		case "/list.do":
			m_com = new MemberListUICommand();
			break;
		case "/listloading.do":
			m_com = new MemberListCommand();
			break;
		case "/createui.do":
			m_com = new MemberCreateUICommand();
			break;
		case "/create.do":
			m_com = new MemberCreateCommand();
			break;
		case "/delete.do":
			m_com = new MemberDeleteCommand();
			break;
		case "/deleteui.do":
			m_com = new MemberDeleteUICommand();
			break;
		case "/loginui.do":
			m_com = new MemberLoginUICommand();
			break;
		case "/login.do":
			m_com = new MemberLoginCommand();
			break;
		case "/logout.do":
			m_com = new MemberLogoutCommand();
			break;
		case "/check.do":
			break;
		case "/search.do":
			m_com = new MemberSearchCommand();
			break; 
		}
		return m_com.execute(request, response, what);
	}

}
