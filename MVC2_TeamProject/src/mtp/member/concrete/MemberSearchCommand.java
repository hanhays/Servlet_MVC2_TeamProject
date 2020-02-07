package mtp.member.concrete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;
import mtp.view.forward.CommandAction;

public class MemberSearchCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {

		int target = Integer.parseInt(request.getParameter("category"));
		String value = request.getParameter("content").toLowerCase();
		PrintWriter out = response.getWriter();
		String msg = "";
		PageVO pv= new MemberDAO().listSearch(1, target, value);
		
		for (MemberDTO x : pv.getM_list()) {
			msg += "{ ";
			msg += "\"m_id\" :\"";
			msg += x.getM_id() + "\"";
			msg += ", ";
			msg += "\"m_name\" :\"";
			msg += x.getM_name() + "\"";
			msg += ", ";
			msg += "\"m_birth\" :\"";
			msg += x.getM_birth() + "\"";
			msg += ", ";
			msg += "\"m_age\" :";
			msg += x.getM_age();
			msg += ", ";
			msg += "\"m_phone\" :\"";
			msg += x.getM_phone() + "\"";
			msg += ", ";
			msg += "\"m_email\" :\"";
			msg += x.getM_email() + "\"";
			msg += ", ";
			msg += "\"m_nickname\" :\"";
			msg += x.getM_nickname() + "\"";
			msg += ", ";
			msg += "\"m_grade\" :\"";
			msg += x.getM_grade() + "\"";
			msg += " } ";
			msg += ", ";
		}
//		StringBuffer msg = new StringBuffer();
//		for(MemberDTO x : new MemberDAO().listSearch(1, target, value).getM_list()) {
//			msg.append("{ ");
//			msg.append("\"m_id\" :\"");
//			msg.append(x.getM_id()+"\"");
//			msg.append(", ");
//			msg.append("\"m_name\" :\""));
//			msg.append(x.getM_name()+"\"");
//			msg.append(", ");
//			msg.append("\"m_birth\" :\"");
//			msg.append(x.getM_birth()+"\"");
//			msg.append(", ");
//			msg.append("\"m_age\" :");
//			msg.append(x.getM_age());
//			msg.append(", ");
//			msg.append("\"m_phone\" :\"");
//			msg.append(x.getM_phone()+"\"");
//			msg.append(", ");
//			msg.append("\"m_email\" :\"");
//			msg.append(x.getM_email()+"\"");
//			msg.append(", ");
//			msg.append("\"m_nickname\" :\"");
//			msg.append(x.getM_nickname()+"\"");
//			msg.append(", ");
//			msg.append("\"m_grade\" :\"");
//			msg.append(x.getM_grade()+"\"");
//			msg.append(" } ");
//			msg.append(", ");
//		}
//		
//		msg.toString().substring(0,msg.toString().length()-2);
		if(pv.getM_list().size()>0) {	
			msg = msg.substring(0, msg.length() - 2);	
		}
		out.print("[");
		out.print(msg);
		out.print("]");
		
		request.setAttribute("s_pv", pv);
		
		return null;
	}
}
