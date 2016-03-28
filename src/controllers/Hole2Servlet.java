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





import model.CourseRating;
import model.Hole;
import model.HoleYards;
import model.RoundHoleSummary;
import model.Shot;
import dbhelpers.ReadCourseRatingQuery;
import dbhelpers.ReadHoleQuery;
import dbhelpers.ReadHoleYardsQuery;


/**
 * @author jjewell_000
 * Servlet implementation class NewGameServlet
 */
@WebServlet(description = "This servlet does processing after the golfer hit the second shot", urlPatterns = { "/Hole2" })
public class Hole2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hole2Servlet() {
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
		System.out.println("I'm in the Hole2Servlet of doPost");
	
		HttpSession session = request.getSession();
		
		//******************************************************************************
		//This section retrieves session variables
		Integer currentHoleID = (Integer) session.getAttribute("currentHoleID");
		Integer currentShotNumber = (Integer) session.getAttribute("currentShotNumber");
		Integer currentShotPenaltyStroke = (Integer) session.getAttribute("currentShotPenaltyStroke");
		String currentShotClub = (String) session.getAttribute("currentShotClub");
		String currentShotLie = (String) session.getAttribute("currentShotLie");
		
		
		
		//******************************************************************************
		//This Section adds another entry to the ShotArrayList JAJ
		//the shotID is set to zero as it is not known at this point.
		//the ShotRoundHoleSummaryID is temp being used to store the hole id for the shot
	
		//create a Shot Object and load it with current shot information
			
			Shot currentShot = new Shot();
			currentShot.setShotID(0);
			currentShot.setShotRoundHoleSummaryID(currentHoleID); //using this to temp store the holeID for this shot
			currentShot.setShotClub(currentShotClub);
			currentShot.setShotLocation(currentShotLie);
			currentShot.setShotNumber(currentShotNumber);
			currentShot.setShotPenaltyStroke(currentShotPenaltyStroke);
			
		//Create an ArrayList that stores Shot object types
		//load the ShotSummaryArray List into that new ArrayList
			
			ArrayList<Shot> shotSummaryArrayList = new ArrayList<Shot>();
			shotSummaryArrayList = ((ArrayList<Shot>) session.getAttribute("shotSummaryArrayList"));
			System.out.println("Size of ShotSummaryArrayList after additions: " + shotSummaryArrayList.size());	
			
		//Load the ShotSummaryArrayList session variable and add a new entry to the end of it with the
		// shot object just created then restet the session variable
			shotSummaryArrayList.add(currentShot);
			session.setAttribute("shotSummaryArrayList", shotSummaryArrayList);

			//testing shot storage...will delete once tested

			shotSummaryArrayList = ((ArrayList<Shot>) session.getAttribute("shotSummaryArrayList"));
			int arraySize = shotSummaryArrayList.size();
			for (int i = 0;i<arraySize; i++) {
				System.out.println("The shotSummaryArrayList is " + shotSummaryArrayList.size());		
			}
		
			
		//Increment currentShot Number and reset the session variable
			System.out.println("I'm in the Hole2Servlet of doPost & currentShotNumber =" + currentShotNumber);
			currentShotNumber = currentShotNumber+1;
			System.out.println("I'm in the Hole2Servlet of doPost & currentShotNumber =" + currentShotNumber);
			currentShotPenaltyStroke = 0;
			currentShotClub = "";
			currentShotLie = "";
			
		//Reset the these session variables
			session.setAttribute("currentShotNumber", currentShotNumber);
			session.setAttribute("currentShotPenaltyStroke", currentShotPenaltyStroke);
			session.setAttribute("currentShotClub", currentShotClub);
			session.setAttribute("currentShotLie", currentShotLie);
			
			
		//Prepare to call the start round screen	
			url="hole-2.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
