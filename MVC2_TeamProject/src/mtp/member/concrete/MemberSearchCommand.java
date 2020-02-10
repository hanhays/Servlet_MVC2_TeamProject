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
import mtp.util.JsonParsing;
import mtp.view.forward.CommandAction;

public class MemberSearchCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int target = Integer.parseInt(request.getParameter("category"));
		String content =  URLDecoder.decode(request.getParameter("content"),"UTF-8");
		content = URLDecoder.decode(content,"UTF-8");
		content = content.split("content=")[1];

		PageVO pv = new MemberDAO().listSearch(currentPage, target, content);
		PrintWriter out = response.getWriter(); 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JsonParsing jp = new JsonParsing(pv, pv.getM_list());
		String[] msg = jp.getMsg();
		if(msg!=null) {
			out.print("[");
			out.print("[");
			out.print(msg[0]);
			out.print("]");
			out.print(",[");
			out.print(msg[1]);
			out.print("]");
			out.print("]");
		}
		return null;
//		response.getWriter().write(getJSON(target, content));
	}
//	
//	private String getJSON(int target, String value) {
//		StringBuffer result = new StringBuffer();
//		result.append("{\"result\":[");
//		List<MemberDTO> list = new MemberDAO().listSearch(target, value);
//		for (int i = 0; i < list.size(); i++) {
//			result.append("[{\"value\":\"" + list.get(i).getM_grade() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_id() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_name() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_nickname() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_birth() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_age() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_phone() + "\"}, ");
//			result.append("{\"value\":\"" + list.get(i).getM_email() + "\"}], ");
//		}
//		result.append("]}");
//		return result.toString();
//	}

}
