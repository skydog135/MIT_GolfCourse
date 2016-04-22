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
@WebServlet(description = "This servlet closes out the round", urlPatterns = { "/CloseRound" })
public class CloseRoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloseRoundServlet() {
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
		//This section updates the round record with cumulative and net scores, and comments

		User user = (User) session.getAttribute("user");
		int golferID = (user.getId());
		int roundID = (Integer) session.getAttribute("currentRoundID");
		int cumulativeShots = (Integer) session.getAttribute("cumulativeShots");
		float courseHandicap = (Float) session.getAttribute("handicap");
		String comments = request.getParameter("round-comments");
		

		System.out.println("In CloseRoundServlet of doPost");
	
		
		//Call UpdateRoundQuery db helper to update round record
		// Create an UpdateRoundQuery helper object
		UpdateRoundQuery urq = new UpdateRoundQuery("tomcatdb","root","bu11fr0g");
		
		//Update the round record in the round table
		urq.doUpdateRound(golferID, roundID, cumulativeShots, courseHandicap, comments);
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		//*************************************************************************
		//This section updates the golfer record with new average scores
		
		//first retrieve the session variable where we stored previous averages
		int avgGrossScore = (Integer) session.getAttribute("avgGross");
		int avgNetScore = (Integer) session.getAttribute("avgNet");
		
		//Calculate new average scores
		//if  avg scores are 0, then this is golfer's first round
		//and no need to calculate an updated average
		if (avgGrossScore == 0) {
			avgGrossScore = cumulativeShots;
			avgNetScore = (cumulativeShots - Math.round(courseHandicap));
		}else {
			avgGrossScore = (avgGrossScore + cumulativeShots)/2;
			avgNetScore = ((avgNetScore + (cumulativeShots - Math.round(courseHandicap)))/2);
		};
		
		// Update golfer record with new average scores
		UpdateUserHelper uq = new UpdateUserHelper("tomcatdb","root","bu11fr0g");
		uq.doUserRoundUpdate(golferID, avgGrossScore, avgNetScore);
		
		//*************************************************************************
		
		//######################################################################
		//Call the servlet that will determine which screen to display next and do table retrieval prep
		

		
		System.out.println("In the CloseRoundServlet of doPost just updated the round record");
		url= "round-summary-front-9.jsp";
		

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
