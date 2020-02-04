package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class MemberReadCommand implements MemberCommand{

	public MemberReadCommand() {
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return null;
	}

}
