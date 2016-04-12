package controllers;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.PasswordService;
import model.User;
import model.NewEmail;
import dbhelpers.AddUser;
import dbhelpers.UserDBHelper;
import dbhelpers.UpdateUserHelper;


/**
 * Servlet implementation class AddUser
 */
@WebServlet(description = "Update's a user and their information to the database", urlPatterns = { "/UpdateUser" })
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String newEmail = request.getParameter("newEmail");
		String email = request.getParameter("email");

		float handicapIndex = Float.valueOf(request.getParameter("handicap"));
		int avgScoreGross = 0;
		int avgScoreNet = 0;

		PasswordService pws = new PasswordService();
		String encryptedPass = pws.encrypt(password);

		//Updated next line to use new default constructor to create object
		
		NewEmail update = new NewEmail();
		update.setNewEmail(newEmail);
		
		User user = new User();
		user.setGender(gender);
		user.setPassword(encryptedPass);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
	
		//added three lines to update three new fields
		user.setGolferHandicapIndex(handicapIndex);
		user.setGolferAvgScoreGross(avgScoreGross);
		user.setGolferAvgScoreNet(avgScoreNet);	
		
		
		UpdateUserHelper uq = new UpdateUserHelper("tomcatdb","root","bu11fr0g");
		uq.doUpdate(user, update);
		
		String url = "login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
		
	}
	}
