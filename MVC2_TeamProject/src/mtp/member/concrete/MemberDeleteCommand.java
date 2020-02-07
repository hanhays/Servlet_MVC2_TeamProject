package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberDeleteCommand implements MemberCommand {

	public MemberDeleteCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		try {
		String password = request.getParameter("password");  //pw를 받아옴(jsp) 
		HttpSession sess=request.getSession(false);
		String id =((MemberDTO)sess.getAttribute("dto")).getM_id();
			if(new MemberDAO().delete(id, password)) {
				sess.invalidate();
				return new CommandAction(true,"/MVC2_TeamProject/");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//이미 return해서 메서드가 끝났기 때문에 else가 없음
		
		request.setAttribute("msg","비밀번호를 확인해 주세요."); //false이면 메세지를 바인딩해서 deleteui.do로 넘겨줌
		return new CommandAction(false, "deleteui.do");
	}

}

