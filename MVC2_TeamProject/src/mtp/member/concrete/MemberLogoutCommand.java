package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.member.interfaces.MemberCommand;
import mtp.view.forward.CommandAction;

public class MemberLogoutCommand implements MemberCommand {

	public MemberLogoutCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		HttpSession sess=request.getSession(false);
		if(sess != null) {
			sess.invalidate();
		}
		Cookie[] autoLogin=request.getCookies();
		if(autoLogin!=null) {
			for(Cookie c:autoLogin) {
				if(c.getName().equals("id")) {
					c.setPath(request.getContextPath());
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
		return new CommandAction(true,"/MVC2_TeamProject/");
	}

}
