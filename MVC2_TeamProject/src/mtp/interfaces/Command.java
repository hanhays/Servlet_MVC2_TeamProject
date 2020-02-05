package mtp.interfaces;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.view.forward.CommandAction;

public interface Command {
	public abstract CommandAction execute(HttpServletRequest request,HttpServletResponse response, String url)throws IOException,ServletException; 
}
