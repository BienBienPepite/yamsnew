package com.avl.yamsnew.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avl.yamsnew.beans.GameBean;
import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOFactory;
import com.avl.yamsnew.dao.GameDao;
import com.avl.yamsnew.forms.GameForm;
import com.avl.yamsnew.util.JsonUtil;
import com.google.gson.Gson;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	
	
	/*
	 * controller part of the application
	 * guide the request of the user from the view (game.jsp) to the model (GameForm.java)
	 */
	
	private static final long serialVersionUID = -1505256822220364573L;
	
	private static final String VIEW     = "/game.html";
	private static final String ATT_USER = "user";
	public static final String CONF_DAO_FACTORY = "daofactory";
	
	private GameDao gameDao;
	
	public void init() throws ServletException {
		
        this.gameDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGameDao();
    }
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		UserBean userBean = new UserBean();
		userBean.setUsername("Boris");
		
		request.getSession().setAttribute("user", userBean);
		
		GameForm gameForm = new GameForm(gameDao);
		GameBean gameBean = gameForm.getNewGameBean(request);
		
		String jsonResponse = "";
		
		String gameJson = gameBean.getGamestate();
		
		jsonResponse = gameJson;
		
		if (!gameForm.getErrors().isEmpty()) {
			
			Gson gson = new Gson();
			String errorJson = gson.toJson(gameForm.getErrors());
			jsonResponse = JsonUtil.mergeJson(gameJson, errorJson);
		}
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * control the request of the user during a game :
		 * - if there isn't any error in the request, update the game
		 * - otherwise, send the report to the user in the view (game.jsp)
		 */
		
		processRequest(request, response);
		
		//this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * control the request of the user during a game :
		 * - if there isn't any error in the request, update the game
		 * - otherwise, send the report to the user in the view (game.jsp)
		 */
		  
		  
		  processRequest(request, response);
	}

}
