package mtp.member.concrete;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.view.forward.CommandAction;

public class MemberDuplicateCommand implements MemberCommand {

	public MemberDuplicateCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String id = request.getParameter("id");
		String nickName = URLDecoder.decode(request.getParameter("nickName"),"UTF-8");
		String[] nickNames = nickName.split("=");
		System.out.println(id);
		System.out.println(nickNames.length); 
		String target = id!=""&&nickNames.length==1?id:nickNames[1];
		
		boolean flag = id!= null?true : false;
		flag =new MemberDAO().duplicate(target,flag);
		String msg ="";
		msg+= flag?"중복입니다.":"사용가능합니다.";
		msg+=",";
		String selecter = id!=""&&nickNames.length==1?"#id_check_msg":"#nickName_check_msg";
		msg+=selecter;
		response.getWriter().print(msg);
		return null;
	}

}
