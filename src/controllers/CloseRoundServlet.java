package controllers;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import dbhelpers.ReadHoleSummaryQuery;
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
		int netScore = cumulativeShots - Math.round(courseHandicap);
		

		System.out.println("In CloseRoundServlet of doPost");
		System.out.println("Course Handicap = " + courseHandicap);
		
		//Call UpdateRoundQuery db helper to update round record
		// Create an UpdateRoundQuery helper object
		UpdateRoundQuery urq = new UpdateRoundQuery("tomcatdb","root","bu11fr0g");
		
		//Update the round record in the round table
		urq.doUpdateRound(golferID, roundID, cumulativeShots, netScore, comments);
		
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
			avgNetScore = netScore;
		}else {
			avgGrossScore = (avgGrossScore + cumulativeShots)/2;
			avgNetScore = ((avgNetScore + netScore)/2);
		};
		
		// Update golfer record with new average scores
		UpdateUserHelper uq = new UpdateUserHelper("tomcatdb","root","bu11fr0g");
		uq.doUserRoundUpdate(golferID, avgGrossScore, avgNetScore);
		
		System.out.println("In the CloseRoundServlet of doPost just updated the round record");
		
		//*************************************************************************
		
		//######################################################################
		//Load the table to display the round hole by hole stats
		// Create a ReadHoleSummaryQuery helper object
		System.out.println("In the CloseRoundServlet of doPost getting ready to build 9 hole summary tables");
		
		ReadHoleSummaryQuery rhs = new ReadHoleSummaryQuery("tomcatdb","root","bu11fr0g");
		
		// Get the html table from the ReadHoleSummaryQuery object
		String tee = (String) session.getAttribute("tee");
		int numHoles = (Integer) session.getAttribute("numHoles");
		
		rhs.doReadHoleSummary(tee, roundID);
		
		//If golfer played 9 holes, then we only need to load one table with 9 records
		//for display
		if (numHoles == 9){//golfer played 9 holes
			String table = rhs.getHTMLTable();
			System.out.println(table);
			session.setAttribute("9OnlyTable", table);
			url="round-summary-front-9-only.jsp";

			
		} else {//golfer played 18 and we need to format two tables
			String[] F9B9TableArray = new String[2];
			F9B9TableArray = rhs.getHTML18Table();
			session.setAttribute("F9Table",F9B9TableArray[0]);
			session.setAttribute("B9Table",F9B9TableArray[1]);	
			url="round-summary-front-9.jsp";
			
		}

		System.out.println("In the CloseRoundServlet of doPost just created the display 9 hole tables");

		

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
