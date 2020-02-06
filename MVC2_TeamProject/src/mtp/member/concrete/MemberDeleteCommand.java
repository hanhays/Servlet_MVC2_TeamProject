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
		boolean flag = false;
		String password = request.getParameter("password");
		HttpSession sess = request.getSession(false);
		Object obj = sess.getAttribute("dto");
		MemberDTO dto = null;
		if (obj instanceof MemberDTO) {
			dto = (MemberDTO) obj;
		}
		String id = dto.getM_id();
		MemberDAO dao = new MemberDAO();
		 flag = dao.delete(id, password);
		
		if(flag) {
			sess.invalidate();
			return new CommandAction(true,"/MVC2_TeamProject/");
		}
		request.setAttribute("msg","비밀번호를 확인해 주세요.");
		return new CommandAction(false, "deleteui.do");
	}

}

