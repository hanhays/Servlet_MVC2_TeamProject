package mtp.member.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;
import mtp.util.JsonParsing;
import mtp.view.forward.CommandAction;

public class MemberListUICommand implements MemberCommand {
	public MemberListUICommand() {
	}
	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		return new CommandAction(false, "member_list.jsp"); 
		
//		int target = Integer.parseInt(request.getParameter("category"));
//		String value = request.getParameter("content");
//		System.out.println(target + value);
//		response.getWriter().write(getJSON(target, value));
//		System.out.println(getJSON(target, value));
//		
//		return null;
		
	}
	
//	private String getJSON(int target, String value) {
//		StringBuffer result = new StringBuffer();
//		result.append("{\"result\":[");
//		List<MemberDTO> list = new MemberDAO().listSearch(target, value);
//		for (int i = 0; i < list.size(); i++) {
//			result.append("[{\"value\":\""+list.get(i).getM_grade()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_id()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_name()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_nickname()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_birth()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_age()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_phone()+"\"}, ");
//			result.append("{\"value\":\""+list.get(i).getM_email()+"\"}], ");
//		}
//		result.append("]}");
//		System.out.println(result.toString());
//		return result.toString();
//	}

	
	
}
