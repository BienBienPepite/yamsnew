package com.avl.yamsnew.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6713087287747640491L;
	
	public static final String VIEW = "/WEB-INF/menu.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

	}
	
}
