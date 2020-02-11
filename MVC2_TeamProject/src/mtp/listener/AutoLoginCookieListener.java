package mtp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;

import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;

/**
 * Application Lifecycle Listener implementation class AutoLoginCookieListener
 *
 */
@WebListener
public class AutoLoginCookieListener implements ServletRequestListener , SessionListener {

	/**
	 * Default constructor.
	 */
	public AutoLoginCookieListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		Cookie[] cookie = request.getCookies();
		Cookie c = null;
		MemberDTO dto = null;
		HttpSession sess = null;
		if(cookie!=null) {
			for(Cookie s:cookie) {
			if(s.getName().equals("id")) {
				c = s;
				if(c.getMaxAge()==-1) {
				c.setMaxAge(60*24*24);
				}
			}
			}
		} 
		if(c!=null) {
			if(c.getMaxAge()>0) {
			dto = new MemberDAO().login(c.getValue(),null,false);
			sess = request.getSession(false);
			if(sess==null) {
				sess = request.getSession();
			} 
			sess.setAttribute("dto",dto);
			}
		}
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
	}

	@Override
	public void sessionEvent(SessionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
