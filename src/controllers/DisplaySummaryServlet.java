package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RoundHoleSummary;
import model.Shot;
import model.User;
import dbhelpers.UpdateRoundQuery;
import dbhelpers.UpdateUserHelper;


/**
 * @author jjewell_000
 * Servlet implementation class NewGameServlet
 */
@WebServlet(description = "This servlet displays the F9 and B9 summary screens", urlPatterns = { "/DisplaySummayr" })
public class DisplaySummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplaySummaryServlet() {
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
		
		// Get session data
		HttpSession session = request.getSession();
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//Retrieve session variables

		User user = (User) session.getAttribute("user");
		int golferID = (user.getId());
		int roundID = (Integer) session.getAttribute("currentRoundID");
		String roundType = (String) session.getAttribute("roundType");

		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//Need to enter code to get round data for display
		
		
		
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		
		//######################################################################
		//Call the servlet that will determine which screen to display next
		if ((roundType.equals("18H1S")) || (roundType.equals("18H10S"))){//Display jsp with both F9 and B9 buttons
			url = "round-summary-front-9.jsp";
		}else if (roundType.equals("9H1S")){//display screen with only F9 scores visible
			url = "round-summary-front-9.jsp"; //need to change to round-summary-front-9-only once created.
		}else {
			url = "round-summary-back-9.jsp"; //need to change to round-summary-back-9-only once created.
		}
		
	
		System.out.println("In the DisplaySummaryServlet");
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
