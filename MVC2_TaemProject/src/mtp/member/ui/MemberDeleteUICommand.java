package mtp.member.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class MemberDeleteUICommand implements MemberCommand {

	public MemberDeleteUICommand() {
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return null;
	}

}
