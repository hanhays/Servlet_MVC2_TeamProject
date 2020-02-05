package mtp.front.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.board.interfaces.BoardCommand;
import mtp.view.forward.CommandAction;

public class BoardController implements BoardCommand {

	public BoardController() {
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		return null;
	}

}
