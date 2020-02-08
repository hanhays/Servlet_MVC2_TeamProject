package mtp.member.concrete;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

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
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String content = request.getParameter("content");
		System.out.println(content);
		content = URLDecoder.decode(content,"UTF-8"); 
		System.out.println(content); 
		content = URLDecoder.decode(content,"UTF-8");
		System.out.println(content);
		content = content.split("content=")[1];
		PrintWriter out = response.getWriter();
		String msg ="";
		PageVO pv = new MemberDAO().listSearch(currentPage, target, content);

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
		if(msg.length()>10) {
		msg = msg.substring(0, msg.length() - 2);
		}
		
		out.print("[");
		out.print(msg);
		out.print("]");

		request.setAttribute("s_pv", pv);

		return null;
//		response.getWriter().write(getJSON(target, content));
	}

	private String getJSON(int target, String value) {
		StringBuffer result = new StringBuffer();
		result.append("{\"result\":[");
		List<MemberDTO> list = new MemberDAO().listSearch(target, value);
		for (int i = 0; i < list.size(); i++) {
			result.append("[{\"value\":\"" + list.get(i).getM_grade() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_id() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_name() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_nickname() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_birth() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_age() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_phone() + "\"}, ");
			result.append("{\"value\":\"" + list.get(i).getM_email() + "\"}], ");
		}
		result.append("]}");
		return result.toString();
	}

}
