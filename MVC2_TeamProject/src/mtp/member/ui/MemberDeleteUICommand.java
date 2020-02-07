package mtp.member.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class MemberDeleteUICommand implements MemberCommand {

	public MemberDeleteUICommand() {
	}


	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		HttpSession sess = request.getSession(false);
		if(sess!=null) {
			return new CommandAction(false , "member_delete.jsp" );
		}System.out.println("세션만료");
		return new CommandAction(true,"loginui.do");
	}

}
