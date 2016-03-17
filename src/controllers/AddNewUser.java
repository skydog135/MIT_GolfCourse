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
import dbhelpers.AddUser;
import dbhelpers.UserDBHelper;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(description = "Add's a user and their information to the database", urlPatterns = { "/AddUser" })
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewUser() {
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
		
		System.out.println("I'm in the AddNewUser servlet doPost beginning");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		System.out.println("I'm in the AddNewUser servlet and the user first name is "+ firstName);
		System.out.println("I'm in the AddNewUser servlet and the user last name is "+ lastName);
		System.out.println("I'm in the AddNewUser servlet and the user gender is "+ gender);
		System.out.println("I'm in the AddNewUser servlet and the user email is "+ email);

		
		// JAJ update next three lines for new fields
		float handicapIndex = Float.valueOf(request.getParameter("handicap"));
		int avgScoreGross = 0;
		int avgScoreNet = 0;
		
		System.out.println("I'm in the AddNewUser servlet and the user handicap is "+ handicapIndex);
		System.out.println("I'm in the AddNewUser servlet and the user avg gross score is "+ avgScoreGross);
		System.out.println("I'm in the AddNewUser servlet and the user avg net score is "+ avgScoreNet);
		
		System.out.println("I'm in the AddNewUser servlet just about to encrypt password");
		PasswordService pws = new PasswordService();
		String encryptedPass = pws.encrypt(password);

		//Updated next line to use new default constructor to create object
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
		
		System.out.println("I'm in the AddNewUser servlet just about to call AddUser ");
		AddUser uq = new AddUser("tomcatdb","root","bu11fr0g");
		System.out.println("I'm in the AddNewUser servlet just about to call uq.doAdd(User) ");
		uq.doAdd(user);
		
		System.out.println("Successfully added new user and will take golfer to login screen");
		String url = "login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
		
	}
	}
