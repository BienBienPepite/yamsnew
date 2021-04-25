package com.avl.yamsnew.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avl.yamsnew.beans.GameBean;
import com.avl.yamsnew.forms.GameForm;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW     = "/WEB-INF/game.jsp";
	private static final String ATT_GAME = "game";
	private static final String ATT_FORM = "form";
	
       
    public GameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GameBean gameBean = new GameBean();
		
		request.getSession().setAttribute(ATT_GAME, gameBean);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GameBean gameBean = (GameBean) request.getSession().getAttribute(ATT_GAME);
		
		GameForm gameForm = new GameForm();
		
		gameForm.updateGameBean(request, gameBean);
		
		if (!gameForm.getErrors().isEmpty()) {
			
			request.setAttribute(ATT_FORM, gameForm);
			
		}
		
		else {
			
			gameBean = gameForm.getGameBean();
			
		}
		
		request.getSession().setAttribute(ATT_GAME, gameBean);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
