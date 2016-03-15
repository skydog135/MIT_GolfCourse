package controllers;

import dbhelpers.UserDBHelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import utilities.PasswordService;

/**
 * Servlet implementation class LoginController
 * A controller for handling user authentication and login
 */
@WebServlet(description = "A controller for handling user logins", urlPatterns = { "/login.jsp" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
	private int loginAttempts;

	/**
	 * Servlet constructor
	 */
	public LoginController() {
		super();
	}

	/**
	 * Process GET requests/responses (logout)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		//session = request.getSession();

		//if(request.getParameter("logout") !=null){

		//	logout();
	//		url="index.jsp";
	//	}

		
		//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
	}

	/**
	 * Process POST requests/responses (login)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("I'm in the doPost LoginController servlet");
		
		session = request.getSession();

		
		if(session.getAttribute("loginAttempts") == null){
			loginAttempts = 0;
		}
		
		
		if(loginAttempts > 2){
			String errorMessage = "Error: Number of Login Attempts Exceeded";
			request.setAttribute("errorMessage", errorMessage);
			url = "index.jsp";
		}else{	
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			//encrypt the password to check against what's stored in DB
			PasswordService pws = new PasswordService();
			String encryptedPass = pws.encrypt(password);
			
			
			UserDBHelper udbh = new UserDBHelper("tomcatdb","root","bu11fr0g");
			User user = udbh.authenticateUser(email, encryptedPass);

			
			if(user != null){
				
				session.invalidate();
				session=request.getSession(true);
				session.setAttribute("user", user);
				url="new-round.jsp";
			}
			
			else{
				String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
				request.setAttribute("errorMessage", errorMessage);

				
				session.setAttribute("loginAttempts", loginAttempts++);
				url = "index.jsp";
			}
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * Logs the user out
	 */
	public void logout() {
		session.invalidate();
	}
}
