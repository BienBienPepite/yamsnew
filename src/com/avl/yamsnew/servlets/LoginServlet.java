package com.avl.yamsnew.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOFactory;
import com.avl.yamsnew.dao.UserDao;
import com.avl.yamsnew.forms.LoginForm;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6391556064492609482L;
	
	public static final String VIEW             = "/WEB-INF/login.jsp";
	public static final String ATT_USER         = "user";
	public static final String ATT_FORM         = "form";
	public static final String ATT_USER_SESSION = "user";
	public static final String CONF_DAO_FACTORY = "daofactory";
	
	private UserDao userDao;
	
	
	public void init() throws ServletException {
		
        this.userDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			
		LoginForm loginForm = new LoginForm(userDao);
		UserBean user = loginForm.loginUser(request);
		
		HttpSession session = request.getSession();
		
		
		if (loginForm.getErrors().isEmpty()) {
			session.setAttribute(ATT_USER_SESSION, user);
			
			response.sendRedirect("GameServlet");
		}
		else {
			session.setAttribute(ATT_USER_SESSION, null);
			
			request.setAttribute(ATT_USER, user);
		    request.setAttribute(ATT_FORM, loginForm);
		    
		    this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}
}
