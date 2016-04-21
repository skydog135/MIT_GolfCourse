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
	private String roundSummaryDisp="";
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
		Integer cumulativeShots = (Integer) session.getAttribute("cumulativeShots");
		Integer currentHoleID = (Integer) session.getAttribute("currentHoleID");
		Integer endHoleNumber = (Integer) session.getAttribute("endHoleNumber");
		Integer currentHoleNumber = (Integer) session.getAttribute("currentHoleNumber");
		Integer currentShotNumber = (Integer) session.getAttribute("currentShotNumber");
		Integer numHoles = (Integer) session.getAttribute("numHoles");
		Integer startHoleNumber = (Integer) session.getAttribute("startHoleNumber");
		String currentShotClub = (String) session.getAttribute("currentShotClub");
		String currentShotLie = (String) session.getAttribute("currentShotLie");
		
		//Display session variables
		System.out.println("The currentHoleID is " + currentHoleID);
		System.out.println("The currentShotNumber is " + currentShotNumber);
		System.out.println("The currentShotClub is " + currentShotClub);
		System.out.println("The currentShotLie is " + currentShotLie);
		
		
		//if golfer added penalty strokes on the last screen, increment the current shot number
		//by the user entered penalty strokes.
		int holePenaltyStrokes = 0;
		if (!stringHolePenaltyStrokes.equalsIgnoreCase("0")) {
			holePenaltyStrokes = Integer.parseInt(stringHolePenaltyStrokes);
			currentShotNumber = currentShotNumber + holePenaltyStrokes;
			cumulativeShots = ((Integer) session.getAttribute("cumulativeShots") + holePenaltyStrokes);
			session.setAttribute("cumulativeShots", cumulativeShots);

			System.out.println("The cumulative shot for the hole is " + cumulativeShots);				
			System.out.println("The PenaltyStroke for the hole is " + holePenaltyStrokes);	
			
			///MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
			//code to add another entry to the ShotSummaryArrayList to account for penalty shots added on at the end of the hole
			//the shotID is set to zero as it is not known at this point.
			//the ShotRoundHoleSummaryID is temp being used to store the hole id for the shot
		
			//create a Shot Object and load it with current shot information
				
				Shot currentShot = new Shot();
				currentShot.setShotID(0);
				currentShot.setShotRoundHoleSummaryID(currentHoleID); //using this to temp store the holeID for this shot
				currentShot.setShotClub(currentShotClub);
				currentShot.setShotLocation(currentShotLie);
				currentShot.setShotNumber(currentShotNumber);
				currentShot.setShotPenaltyStroke(holePenaltyStrokes);
				
			//Create an ArrayList that stores Shot object types
			//load the ShotSummaryArray List into that new ArrayList
				
				ArrayList<Shot> shotSummaryArrayList = new ArrayList<Shot>();
				shotSummaryArrayList = ((ArrayList<Shot>) session.getAttribute("shotSummaryArrayList"));
				System.out.println("Size of ShotSummaryArrayList before additions: " + shotSummaryArrayList.size());	
				shotSummaryArrayList.add(currentShot);
				session.setAttribute("shotSummaryArrayList", shotSummaryArrayList);
								
				System.out.println("Size of ShotSummaryArrayList after additions: " + shotSummaryArrayList.size());				
		
			///MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM			
			};
			
		//******************************************************************************
		//This Section adds another entry to the RoundHoleSummaryArrayList

		//First create a RoundHoleSummary Object and load it with current shot information
			
			RoundHoleSummary rhs = new RoundHoleSummary();
			rhs.setRoundHoleSummaryRoundID(0);//set to zero but will be AutoIncremented when added to db
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
			
		//*************************************************************************************************	

		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//This section updates all of the session variables used at the end the round for the round-stats.jsp screen
		//Update Birdie and Bogey session variables
			int currentHolePar = (Integer)session.getAttribute("currentHolePar");
			if (currentShotNumber < currentHolePar){//increment birdie count
				int totalBirdies = ((Integer)session.getAttribute("totalBirdies") + 1);
				session.setAttribute("totalBirdies",totalBirdies);	
			} else if (currentShotNumber > currentHolePar){//increment total Bogeys
				int totalBogeys = ((Integer)session.getAttribute("totalBogeys") + 1);
				session.setAttribute("totalBogeys",totalBogeys);	
			}else {//increment total Pars
				int totalPars = ((Integer)session.getAttribute("totalPars") + 1);
				session.setAttribute("totalPars",totalPars);	
			}
			
			//increment current 9 over under, running total shots, and running total par values
			int holeOverUnder = currentShotNumber-currentHolePar;
			int totalCurrent9OverUnder = (((Integer)session.getAttribute("totalCurrent9OverUnder")) + holeOverUnder);
			int totalCurrent9 = ((Integer)session.getAttribute("totalCurrent9") + currentShotNumber );
			int totalCurrent9Par = (((Integer)session.getAttribute("totalCurrent9Par")) + currentHolePar );

			
			if ((currentHoleNumber == 9)|| (currentHoleNumber==18)) {//need to set front or back 9 session variables
				if (currentHoleNumber == 9){
					session.setAttribute("totalScoreF9",totalCurrent9);
					session.setAttribute("totalScoreF9OverUnder",totalCurrent9OverUnder);
					session.setAttribute("totalParF9",totalCurrent9Par);
				} else {
					session.setAttribute("totalScoreB9",totalCurrent9);
					session.setAttribute("totalScoreB9OverUnder",totalCurrent9OverUnder);
					session.setAttribute("totalParB9",totalCurrent9Par);					
				}
				
			//reset current9 session variables to be ready for next 9 holes cumulatives
				totalCurrent9=0;
				totalCurrent9OverUnder = 0;
				totalCurrent9Par = 0;
			};
			
			session.setAttribute("totalCurrent9",totalCurrent9);
			session.setAttribute("totalCurrent9OverUnder",totalCurrent9OverUnder);
			session.setAttribute("totalCurrent9Par",totalCurrent9Par);
			
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//This section gets program set up for next hole processing, if it is not end of round
			
			//If this is not the last hole, then wrap up hole and prepare for next hole
			System.out.println("I'm in the WrapUpHoleServlet and testing to see if last hole");
			if (currentHoleNumber != endHoleNumber){//increment hole number
				if (currentHoleNumber <18){//increment hole number by 1
					currentHoleNumber = currentHoleNumber +1;
				}else {//golfer is playing 18 and started on back9 and next hole will be hole 1
					currentHoleNumber = 1;
				}	
			//setting currentShotNumber to 1 and incrementing cumulativeShots in preparation for first shot of next hole 
			cumulativeShots = cumulativeShots +1;
			currentShotNumber = 1;
			System.out.println("I'm in the Hole2Servlet of doPost & currentShotNumber =" + currentShotNumber);
			int currentShotPenaltyStroke = 0;
			String currentShotClub2 = "";
			String currentShotLie2 = "";					
			
			//Reset the these session variables
			session.setAttribute("currentShotNumber", currentShotNumber);
			session.setAttribute("currentShotPenaltyStroke", currentShotPenaltyStroke);
			session.setAttribute("currentShotClub", currentShotClub2);
			session.setAttribute("currentShotLie", currentShotLie2);
			session.setAttribute ("currentHoleNumber", currentHoleNumber);
			session.setAttribute ("cumulativeShots", cumulativeShots);			
			//
			//determine the next hole id, par, Pin latitude, pin longitude by searching the HolesArray
			Hole[] holeArray = (Hole[]) session.getAttribute("holesArray");
			boolean match = false;
			i=0;
			do  {
				System.out.println("im in the WrapUpHoleServlet find hole id loop");
				int holeArrayHoleNumber = holeArray[i].getHoleNumber();
				System.out.println("The actual hole Number is " + currentHoleNumber);
				System.out.println("The array hole Number is " + holeArrayHoleNumber);
				System.out.println("the counter int is " + i);
				
				if (holeArrayHoleNumber == currentHoleNumber){
					System.out.println("in the if statement");
					
					currentHoleID = holeArray[i].getHoleID();
					int currentHolePar2 = holeArray[i].getHolePar();
					double currentHolePinLatitude = holeArray[i].getHoleLatitude();
					double currentHolePinLongitude = holeArray[i].getHoleLongitude();
					System.out.println("just set the currentHoleID "+ currentHoleID);
					
			
					session.setAttribute("currentHoleID", currentHoleID); //at game start will be equal to starting hole
					session.setAttribute("currentHoleNumber", currentHoleNumber);
					session.setAttribute("currentHolePar", currentHolePar2);
					int cumulativePar = (Integer)session.getAttribute("cumulativePar");
					cumulativePar = cumulativePar + currentHolePar2;
					session.setAttribute("cumulativePar", cumulativePar);
					session.setAttribute("currentHolePinLatitude", currentHolePinLatitude);
					session.setAttribute("currentHolePinLongitude", currentHolePinLongitude);
					
					System.out.println("just set all the session attributes");
					match=true;
					}
				
				i=i+1;
			
				}while (match == false);
			
			//Prepare to call the start new hole screen	
			url="new-hole.jsp";	
			} 	
			//end of round time to write shots and round array information to database tables
			else {//end of round
				url = "RoundWrapUp";
				if (startHoleNumber == 1){//started on hole 1	
				//determine what needs to be displayed in next screen
					if (numHoles == 18 ){roundSummaryDisp="18";}
						else {roundSummaryDisp = "F9";}
				} else {//golfer played back 9
					roundSummaryDisp = "B9";	
						}
			session.setAttribute ("roundSummaryDisp", roundSummaryDisp);
			}
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			//if not end of round, prgram goes to new-hole.jsp
			//if end of round, program goes to round wrap up servlet to write to RoundHoleSummary and Shot Tables

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
