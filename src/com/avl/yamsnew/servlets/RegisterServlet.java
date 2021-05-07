package com.avl.yamsnew.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avl.yamsnew.forms.RegistrationForm;
import com.avl.yamsnew.beans.UserBean;
import com.avl.yamsnew.dao.DAOFactory;
import com.avl.yamsnew.dao.UserDao;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String VIEW             = "/WEB-INF/register.jsp";
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
		
			
		RegistrationForm form = new RegistrationForm(userDao);
		UserBean user = form.registerUser(request);
		
		request.setAttribute(ATT_USER, user);
		
		if (!form.getErrors().isEmpty()) {
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
		
		else {
			request.getSession().setAttribute(ATT_USER_SESSION, user);
			response.sendRedirect("MenuServlet");
		}
		
	}
}
