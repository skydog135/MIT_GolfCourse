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
import dbhelpers.AddRoundHoleSummaryQuery;
import dbhelpers.AddShotQuery;

/**
 * @author jjewell_000
 * Servlet implementation class NewGameServlet
 */
@WebServlet(description = "This servlet does processing at the end of a roun", urlPatterns = { "/RoundWrapUp" })
public class WrapUpRoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrapUpRoundServlet() {
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
		
		int i=0;
		int j = 0;
		int currentRoundHoleSummaryID = 0;
		int currentHoleID = 0;
		boolean readRoundArray = true;

		
		// Get session data
		System.out.println("I'm in the WrapUpHoleServlet of doPost");
	
		HttpSession session = request.getSession();
		
		//Calculate round stats for display
		int cumulativeShots = (Integer) session.getAttribute("cumulativeShots");
		int cumulativePar = (Integer)session.getAttribute("cumulativePar");
		double avgStrokesAbovePar = cumulativeShots/cumulativePar;
		session.setAttribute("avgStrokesAbovePar",avgStrokesAbovePar);
		
			
		//Create an ArrayList that stores RoundHoleSummary object types
		ArrayList<RoundHoleSummary> roundHoleSummaryArrayList = new ArrayList<RoundHoleSummary>();
		roundHoleSummaryArrayList = ((ArrayList<RoundHoleSummary>) session.getAttribute("roundHoleSummaryArrayList"));
		
		//Create an ArrayList that stores Shot object types			
		ArrayList<Shot> shotSummaryArrayList = new ArrayList<Shot>();
		shotSummaryArrayList = ((ArrayList<Shot>) session.getAttribute("shotSummaryArrayList"));
		
		
		//Loop through RoundHoleSummaryArrayList
		while  (readRoundArray) {
			
			//create temporary object store retrieved arrayList roundHoleSummary object
			RoundHoleSummary rhsArrayObject = roundHoleSummaryArrayList.get(i);
			
			//retrieve the holeID for the hole we are currently processing shots for (from roundHoleSummaryArrayList)
			currentHoleID = rhsArrayObject.getRoundHoleSummaryHoleID();
			
			//Display values pulled from roundHoleSummaryArrayList
			System.out.println("RoundHoleSummaryID = " + rhsArrayObject.getRoundHoleSummaryID());
			System.out.println("RoundHoleSummaryRoundID = " + rhsArrayObject.getRoundHoleSummaryRoundID());
			System.out.println("RoundHoleSummaryHoleID = " + rhsArrayObject.getRoundHoleSummaryHoleID());
			System.out.println("RoundHoleSummaryGross = " + rhsArrayObject.getRoundHoleSummaryGross());
			System.out.println("RoundHoleSummaryComments = " + rhsArrayObject.getRoundHoleSummaryComments());
			
			
			//Prepare to write RoundHoleSummary entry to RoundHoleSummary table
			// Create a AddRoundHoleSummaryQuery helper object
			AddRoundHoleSummaryQuery arhsq = new AddRoundHoleSummaryQuery("tomcatdb","root","bu11fr0g");
			
			//Write the record to the RoundHoleSummary table and store the RoundHoleSummaryID created for this hole
			currentRoundHoleSummaryID = arhsq.doAddRoundHoleSummary(rhsArrayObject);
		
			System.out.println("I'm in the WrapUpHoleServlet and just wrote a roundHoleSummary record");
     		System.out.println("I'm in the WrapUpHoleServlet: retrieved currentRoundHoleSummaryID = " + currentRoundHoleSummaryID);
			
             //Process through the shots for this hole if readShotArray = true.
			//each hole will have at least one shot.
			 boolean readShotArray = true;
			
			 while (readShotArray){
			  
				//create temporary object to store retrieved shotSummaryArray object
				Shot shotArrayObject = shotSummaryArrayList.get(j);
				
				///display shotArrayObject attributes
	     		System.out.println("In WrapUpHoleServlet: shotArrayObject.shotRoundHoleSummaryID = " + shotArrayObject.getShotRoundHoleSummaryID());
	     		System.out.println("In WrapUpHoleServlet: shotArrayObject.shotClub = " + shotArrayObject.getShotClub());
	     		System.out.println("In WrapUpHoleServlet: shotArrayObject.shotLocation = " + shotArrayObject.getShotLocation());
	     		System.out.println("In WrapUpHoleServlet: shotArrayObject.shotNumber = " + shotArrayObject.getShotNumber());
	     		System.out.println("In WrapUpHoleServlet: shotArrayObject.shotPenaltyStroke = " + shotArrayObject.getShotPenaltyStroke());
				
					//when array list was loaded, the shot hole id was stored in the shotRoundSummaryID field temporarily
					int shotHoleID = shotArrayObject.getShotRoundHoleSummaryID();
					
					//process this shot record if the shot hole ID = current hole ID and not at the end of the array
					if (shotHoleID == currentHoleID) {//the shot is for the roundHoleSummary we previously wrote to the table
						
						//prepare to write Shot entry to the shot table
						//create a AddShotQuery helper object
						AddShotQuery asq = new AddShotQuery("tomcatdb","root","bu11fr0g");
						
						//Prepare the record for writing to shot table
						//need to first set the shotRoundHoleSummaryID to the key id retrieved when we last wrote to the RoundHoleSummary table.
						shotArrayObject.setShotRoundHoleSummaryID(currentRoundHoleSummaryID);
						asq.doAddShot(shotArrayObject);
						
						System.out.println("I'm in the WrapUpHoleServlet of doPost and just wrote a Shot record");
						
						//increment shot array list counter
						j= j+1;
			     		System.out.println("In WrapUpHoleServlet: j = " + j);
						
						//check to see if you are at the end of the shotArrayList to ensure we don't error out 
						//trying to read beyond the size of the array list
						if (j >= shotSummaryArrayList.size()) {
							readShotArray = false;
						}
						
					} else {readShotArray = false;}//end of shotHoleID == currentHoldID if loop..hole ID changed
	
			 }// end of readShotArray = true loop
		
		i = i+1;
 		System.out.println("In WrapUpHoleServlet: i = " + i);
		if (i >= roundHoleSummaryArrayList.size()){//the roundArrayList has hit the end
			readRoundArray = false;
		}
			
			
		}//end of while readRoundArray = true loop
		
		//Retrieve Cumulative Round stats
			int totalF9Score = (Integer)session.getAttribute("totalScoreF9");
			int totalF9OverUnder = (Integer) session.getAttribute("totalScoreF9OverUnder");
			int totalF9Par = (Integer) session.getAttribute("totalParF9");
			int totalB9Score = (Integer)session.getAttribute("totalScoreB9");
			int totalB9OverUnder = (Integer) session.getAttribute("totalScoreB9OverUnder");
			int totalB9Par = (Integer) session.getAttribute("totalParB9");
			
			int totalScore = totalF9Score + totalB9Score;
			int totalPar = totalF9Par + totalB9Par;
			int totalOverUnder = totalF9OverUnder + totalB9OverUnder;
			session.setAttribute("totalRoundScore",totalScore);
			session.setAttribute("totalRoundOverUnder",totalOverUnder);
			session.setAttribute("totalRoundPar",totalPar);
	
		
		System.out.println("I'm in the WrapUpHoleServlet of doPost and just processed through both the roundHole and shot arrays");
		url= "round-stats.jsp";
		

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		

	}	
	
}
