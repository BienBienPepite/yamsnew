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
	
	/*
	 * controller part of the application
	 * guide the request of the user from the view (game.jsp) to the model (GameForm.java)
	 */
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW     = "/WEB-INF/game.jsp";
	private static final String ATT_GAME = "game";
	private static final String ATT_FORM = "form";
	
       
    public GameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * check the first connection to the page
		 */
		
		GameBean gameBean = new GameBean();
		
		request.getSession().setAttribute(ATT_GAME, gameBean);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * control the request of the user during a game :
		 * - if there isn't any error in the request, update the game
		 * - otherwise, send the report to the user in the view (game.jsp)
		 */
		
		GameBean gameBean = (GameBean) request.getSession().getAttribute(ATT_GAME);
		
		
		// if the session is still open, i.e. the GameBean still exists, keep on playing
		
		if (gameBean != null) {
			GameForm gameForm = new GameForm();
			
			gameForm.updateGameBean(request, gameBean);
			
			if (!gameForm.getErrors().isEmpty()) {
				
				// if there is an error, it becomes an attribute of the request
				
				request.setAttribute(ATT_FORM, gameForm);
				
			}
			
			else {
				
				// if there isn't any error, load the new version of the game
				
				gameBean = gameForm.getGameBean();
				
			}
			
			request.getSession().setAttribute(ATT_GAME, gameBean);
			
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
		
		
		// if the gameBean session doesn't exist anymore, start a new game
		
		else {
			
			doGet(request, response);
			
		}
		
	}

}
