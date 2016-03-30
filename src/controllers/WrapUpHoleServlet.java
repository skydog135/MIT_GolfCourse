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
@WebServlet(description = "This servlet does processing after the golfer hit the second shot", urlPatterns = { "/HoleWrapUp" })
public class WrapUpHoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
	private int i;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrapUpHoleServlet() {
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
		System.out.println("I'm in the WrapUpHoleServlet of doPost");
	
		HttpSession session = request.getSession();
		String currentHoleComments = request.getParameter("comments");
		String stringHolePenaltyStrokes = request.getParameter("hole-penalty-strokes");

		//******************************************************************************
		//This section retrieves session variables needed later in the code
		Integer currentHoleID = (Integer) session.getAttribute("currentHoleID");
		Integer endHoleNumber = (Integer) session.getAttribute("endHoleNumber");
		Integer currentHoleNumber = (Integer) session.getAttribute("currentHoleNumber");
		Integer currentShotNumber = (Integer) session.getAttribute("currentShotNumber");
		Integer numHoles = (Integer) session.getAttribute("numHoles");
		Integer startHoleNumber = (Integer) session.getAttribute("startHoleNumber");
		Integer currentRoundScore = (Integer) session.getAttribute("currentRoundScore");	
		
		//if golfer added penalty strokes on the last screen, increment the current shot number
		//by the user entered penalty strokes.
		int holePenaltyStrokes = 0;
		if (!stringHolePenaltyStrokes.equalsIgnoreCase("0")) {
			holePenaltyStrokes = Integer.parseInt(stringHolePenaltyStrokes);
			currentShotNumber = currentShotNumber + holePenaltyStrokes;
			};
			
		//Update currentRoundScore by adding the total number of shots to the previous
		//currentRound score and reset session variable
			currentRoundScore = currentRoundScore + currentShotNumber;
			session.setAttribute("currentRoundScore", currentRoundScore);
	
		
		//******************************************************************************
		//This Section adds another entry to the HoleSummaryArrayList

		//First create a RoundHoleSummary Object and load it with current shot information
			
			RoundHoleSummary rhs = new RoundHoleSummary();
			rhs.setRoundHoleSummaryRoundID(0);//set to zero but will be AI when added to db
			int currentRoundID = (Integer) session.getAttribute("currentRoundID");
			rhs.setRoundHoleSummaryRoundID(currentRoundID);
			rhs.setRoundHoleSummaryHoleID(currentHoleID);
			rhs.setRoundHoleSummaryGross(currentShotNumber);
			rhs.setRoundHoleSummaryComments(currentHoleComments);
			
		//Create an ArrayList that stores RoundHoleSummary object types
			ArrayList<RoundHoleSummary> roundHoleSummaryArrayList = new ArrayList<RoundHoleSummary>();
			roundHoleSummaryArrayList = ((ArrayList<RoundHoleSummary>) session.getAttribute("roundHoleSummaryArrayList"));

			//append the rhs object which contains the latest hole summary info into the arraylist
			roundHoleSummaryArrayList.add(rhs);
			session.setAttribute("roundHoleSummaryArrayList", roundHoleSummaryArrayList);
			System.out.println("The RoundHoleSummaryArrayList has " + roundHoleSummaryArrayList.size() + " entries");

		
			
		//If this is not the last hole, then wrap up hole and prepare for next hole
			System.out.println("I'm in the WrapUpHoleServlet and testing to see if last hole");
			if (currentHoleNumber != endHoleNumber){//increment hole number
				if (currentHoleNumber <18){//increment hole number by 1
					currentHoleNumber = currentHoleNumber +1;
				}else {//golfer is playing 18 and started on back9 and next hole will be hole 1
					currentHoleNumber = 1;
				}	
			
			currentShotNumber = 1;
			System.out.println("I'm in the Hole2Servlet of doPost & currentShotNumber =" + currentShotNumber);
			int currentShotPenaltyStroke = 0;
			String currentShotClub = "";
			String currentShotLie = "";
					
			
			//Reset the these session variables
			session.setAttribute("currentShotNumber", currentShotNumber);
			session.setAttribute("currentShotPenaltyStroke", currentShotPenaltyStroke);
			session.setAttribute("currentShotClub", currentShotClub);
			session.setAttribute("currentShotLie", currentShotLie);
			session.setAttribute ("currentHoleNumber", currentHoleNumber);
			
			//
			//determine the next hole id, par, Pin latitude, pin longitude by searching the HolesArray
			Hole[] holeArray = (Hole[]) session.getAttribute("holesArray");
			boolean match = false;
			i=0;
			do  {
				System.out.println("im in the WrapUpRoundServlet find hole id loop");
				int holeArrayHoleNumber = holeArray[i].getHoleNumber();
				System.out.println("The actual hole Number is " + currentHoleNumber);
				System.out.println("The array hole Number is " + holeArrayHoleNumber);
				System.out.println("the counter int is " + i);
				
				if (holeArrayHoleNumber == currentHoleNumber){
					System.out.println("in the if statement");
					
					currentHoleID = holeArray[i].getHoleID();
					int currentHolePar = holeArray[i].getHolePar();
					double currentHolePinLatitude = holeArray[i].getHoleLatitude();
					double currentHolePinLongitude = holeArray[i].getHoleLongitude();
					System.out.println("just set the currentHoleID "+ currentHoleID);
					
			
					session.setAttribute("currentHoleID", currentHoleID); //at game start will be equal to starting hole
					session.setAttribute("currentHoleNumber", currentHoleNumber);
					session.setAttribute("currentHolePar", currentHolePar);
					session.setAttribute("currentHolePinLatitude", currentHolePinLatitude);
					session.setAttribute("currentHolePinLongitude", currentHolePinLongitude);
					
					System.out.println("just set all the session attributes");
					match=true;
					}
				
				i=i+1;
			
				}while (match == false);
			
			//Prepare to call the start round screen	
			url="new-hole.jsp";	
			} 	

			else if (startHoleNumber == 1) {//end of round 
				url="round-summary-front-9.jsp";
				} else {
						url="round-summary-back-9.jsp";
						}		

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
