package mtp.member.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class MemberCreateUICommand implements MemberCommand {

	public MemberCreateUICommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		return new CommandAction(false, "member_create.jsp");
	}

}
