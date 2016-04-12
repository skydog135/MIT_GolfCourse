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
import model.Round;
import model.Shot;
import model.User;
import model.RoundHoleSummary;
import dbhelpers.AddRoundQuery;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author jjewell_000
 * Servlet implementation class StartRoundServlet
 */
@WebServlet(description = "This servlet initiates tables needed to start firt hole and calls new-hole.jsp", urlPatterns ={"/StartRound"})

public class StartRoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
	private int i;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartRoundServlet() {
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
		System.out.println("I'm in the start round servlet");
		
		HttpSession session = request.getSession();
		
		
		//******************************************************************************
		//This Section retrieves and stores the round choices the user entered on start-round.jsp
		
		//retrieve user inputted parameters
			String tee = request.getParameter("tee-box");
			session.setAttribute("tee", tee);
			System.out.println(tee);
			
			int numHoles = Integer.parseInt(request.getParameter("hole-amount"));
			session.setAttribute("numHoles", numHoles);
			System.out.println(numHoles);
			
			int startHoleNumber = Integer.parseInt(request.getParameter("starting-hole"));

			System.out.println("The starting hole is " + startHoleNumber);
			
			int currentHoleNumber = startHoleNumber;
			int currentHoleID=0;
			int currentShotNumber = 1;
			int cumulativeShots = 1;
			int endHoleNumber = 0;
			int currentShotPenaltyStroke = 0;
			int totalBogeys = 0;
			int totalBirdies=0;
			int totalFIR=0;
			int totalGIR=0;
			int totalPutts=0;
			int totalPars=0;
			int totalScoreF9=0;
			int totalScoreF9OverUnder=0;
			int totalParF9=0;
			int totalScoreB9=0;
			int totalScoreB9OverUnder=0;
			int totalParB9=0;
			int totalCurrent9=0;
			int totalCurrent9OverUnder=0;
			int totalCurrent9Par=0;
			String currentShotClub="";
			String currentShotLie = "";
			
			//initialize current shot session variables
			session.setAttribute("currentShotNumber", currentShotNumber);
			session.setAttribute("currentShotPenaltyStroke", currentShotPenaltyStroke);
			session.setAttribute("currentShotClub", currentShotClub);
			session.setAttribute("currentShotLie", currentShotLie);
			
			//initialize round related session variables (cumulative Par is set later in this code)
			session.setAttribute("cumulativeShots", cumulativeShots);
			session.setAttribute("totalBogeys", totalBogeys);
			session.setAttribute("totalBirdies", totalBirdies);
			session.setAttribute("totalFIR", totalFIR);
			session.setAttribute("totalGIR", totalGIR);
			session.setAttribute("totalPutts", totalPutts);
			session.setAttribute("totalPars", totalPars);
			session.setAttribute("totalScoreF9", totalScoreF9);
			session.setAttribute("totalScoreF9OverUnder", totalScoreF9OverUnder);
			session.setAttribute("totalParF9", totalParF9);
			session.setAttribute("totalScoreB9", totalScoreB9);
			session.setAttribute("totalScoreB9OverUnder", totalScoreB9OverUnder);
			session.setAttribute("totalParB9", totalParB9);
			session.setAttribute("totalCurrent9",totalCurrent9);
			session.setAttribute("totalCurrent9OverUnder",totalCurrent9OverUnder);
			session.setAttribute("totalCurrent9Par",totalCurrent9Par);	
			
			// TODO Auto-generated method stub
			
			//Determine the last hole based on user starting hole and number of holes to play
			if (startHoleNumber == 1) {
				if (numHoles == 9)
				    {endHoleNumber = 9;}
				else {endHoleNumber = 18;}
				
			} else if (startHoleNumber == 10) {
				if (numHoles == 9)
				   {endHoleNumber = 18;}
				else {endHoleNumber = 9;}
				
			} else if ((startHoleNumber > 1) && (startHoleNumber <10)){ //starting on a hole 2 - 9
				if (numHoles == 9)//and playing 9 holes
				    {endHoleNumber = startHoleNumber + 8;}
				else {endHoleNumber = startHoleNumber - 1;}//and playing 18 holes
				
			} else if (numHoles == 9) {//starting on a hole 11-18 and playing 9 holes
					   endHoleNumber = startHoleNumber -10;}//and playing 9 holes
						else {endHoleNumber = startHoleNumber - 1;} //playing 18 holes
			System.out.println("the endHole is " + endHoleNumber);
			session.setAttribute("endHoleNumber", endHoleNumber);
			
			// end of section to determine last hole
			
		//determine the starting hole id, par, Pin latitude, pin longitude by searching the HolesArray
			Hole[] holeArray = (Hole[]) session.getAttribute("holesArray");
			boolean match = false;
			i=0;
			do  {
				System.out.println("im in the startRoundServlet find hole id loop");
				int holeArrayHoleNumber = holeArray[i].getHoleNumber();
				System.out.println("The actual hole Number is " + startHoleNumber);
				System.out.println("The array hole Number is " + holeArrayHoleNumber);
				System.out.println("the counter int is " + i);
				
				if (holeArrayHoleNumber == startHoleNumber){
					System.out.println("in the if statement");
					System.out.println("the counter2 int is " + i);
					
					int startHoleID = holeArray[i].getHoleID();
					int currentHolePar = holeArray[i].getHolePar();
					double currentHolePinLatitude = holeArray[i].getHoleLatitude();
					double currentHolePinLongitude = holeArray[i].getHoleLongitude();
					System.out.println("just set the startHoleID "+ startHoleID);
					
					currentHoleID = startHoleID;
					System.out.println("just set the currentHoleID "+currentHoleID);
					
					session.setAttribute("startHoleID", startHoleID);
					session.setAttribute("startHoleNumber", startHoleNumber);
					session.setAttribute("currentHoleID", currentHoleID); //at game start will be equal to starting hole
					session.setAttribute("currentHoleNumber", currentHoleNumber);
					session.setAttribute("currentHolePar", currentHolePar);
					session.setAttribute("cumulativePar", currentHolePar);
					session.setAttribute("currentHolePinLatitude", currentHolePinLatitude);
					session.setAttribute("currentHolePinLongitude", currentHolePinLongitude);
					
					System.out.println("just set all the session attributes");
					match=true;
					}
				
				i=i+1;
			
				}while (match == false);
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	
		//calculate course handicap for golfer who has handicap on profile JAJ
			System.out.println("I'm in the start round servlet2 calculating golfer handicap");		
		float courseHandicap = 0;
		session.setAttribute("handicap",courseHandicap);
		
		User user = (User) session.getAttribute("user");
			float handicapIndex = (user.getGolferHandicapIndex());

		if (handicapIndex >0) {
			String gender = (user.getGender());
			System.out.println("Gender in Start Round =" + gender);		
			System.out.println("tee in Start Round =" + tee);
			
			//if golfer is female and they have chosen to play from either the Bulldog or Red Tees, we cannot
			//calculate a handicap as there is no course rating for female/bulldog or female/red combo.
			if (gender=="female" && ( tee.equalsIgnoreCase("Black")|| tee.equalsIgnoreCase("Bulldog")))
			
			{
			//Retrieve course rating information for tee, gender
			ArrayList<CourseRating> courseRatingArrayList = new ArrayList<CourseRating>();
			courseRatingArrayList = ((ArrayList<CourseRating>) session.getAttribute("courseRatingArrayList"));
	
			System.out.println("just created the CourseRatingArryList Object" + courseRatingArrayList.size());		
			
			//Read through array list until a match is found
			int counter = 0;
			int tempCourseSlope;
			boolean match2 = false;
			

			
			while (!match2) {
				System.out.println("Gender in Start Round =" + gender);		
				System.out.println("tee in Start Round =" + tee);	
				System.out.println("in the course rating while loop");	
				
				//CourseRating CR = new CourseRating();
				System.out.println("just created CR object");
				System.out.println("counter " + counter);
				//CR = courseRatingArrayList.get(counter);
				
				CourseRating CR = courseRatingArrayList.get(counter);
				
				
				System.out.println(CR.getCourseRatingGender());	
				System.out.println(CR.getCourseRatingTee());	
				
				
				System.out.println("just retreived courseratingArrayLIst index " + counter);	
				
				if ((CR.getCourseRatingGender().equalsIgnoreCase(gender))&& (CR.getCourseRatingTee().equalsIgnoreCase(tee))){
					match2 = true;
					System.out.println("gender");	
					System.out.println("just created the CourseRatingArryList Object");	
					//calculate course handicap based on 19, front 9 or back 9
					if (numHoles == 18){
						//calculate course rating based 18 holes
						tempCourseSlope = CR.getCourseRating18Slope();
						courseHandicap = (tempCourseSlope/113)*handicapIndex;
						session.setAttribute("handicap",courseHandicap);
				
					} else if (numHoles == 9 && startHoleNumber==1){
						tempCourseSlope = CR.getCourseRatingF9Slope();
						courseHandicap = (tempCourseSlope/113)*(handicapIndex/2);
						session.setAttribute("handicap",courseHandicap);
					}
					else {
						tempCourseSlope = CR.getCourseRatingB9Slope();
						courseHandicap = (tempCourseSlope/113)*(handicapIndex/2);
						session.setAttribute("handicap",courseHandicap);
						}
					}
				counter=counter+1;
			}
			}
		}
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	
		
		////////////////////////////////////////////////////////////////////////////
		//Add user round entry to round table  JAJ
		
		//Get the current date
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYY");
		   //get current date time with Date()
		   Date date = new Date();
		   String dateOut = dateFormat.format(date);
		   System.out.println(dateOut);	
		   session.setAttribute("roundDate",dateOut);
		   
		//First Set the initial round obj data with the golfer ID, tee, holes to play,etc
		Round ar = new Round();
			ar.setRoundID(0);
			int golferID = user.getId();
			ar.setRoundGolferID(golferID);
			ar.setRoundTee(tee);
			ar.setRoundDate(dateOut);
			ar.setRoundHolesToPlay(numHoles);
			ar.setRoundStartingHole(startHoleNumber);
			ar.setRoundCourseHandicap(courseHandicap);
			ar.setRoundTotalGross(0);
			ar.setRoundTotalNet(0);
			ar.setRoundComments("");
		
		// Create a AddRoundQuery helper object
		AddRoundQuery arq = new AddRoundQuery("tomcatdb","root","bu11fr0g");
			
		//call the doAddRound method to inset new round into database and return round id
		int currentRoundID = arq.doAddRound(ar);
		System.out.println("Current Round ID " + currentRoundID);	
		
		//set currentRounID session variable
		session.setAttribute("currentRoundID",currentRoundID);
		
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//Set up the arrays that will store the hole summary data and the shot by shot data
		
		//create the hole summary Array List.  This will be added to at
		//the end of each hole
		System.out.println("In StartRoundServlet just about to set holeSummaryArrayList ");	
		ArrayList<RoundHoleSummary> roundHoleSummaryArrayList = new ArrayList<RoundHoleSummary>();
		session.setAttribute("roundHoleSummaryArrayList", roundHoleSummaryArrayList);

		//create the shot summary array list.  This will be added to at the end of each shot
		System.out.println("In StartRoundServlet just about to set shotSummaryArrayList ");	
		ArrayList<Shot> shotSummaryArrayList = new ArrayList<Shot>();
		session.setAttribute("shotSummaryArrayList", shotSummaryArrayList);
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		
		
		//Prepare to call the start round screen	
		System.out.println("In StartRoundServlet just about to end and go to new-hole.jsp ");	
		url="new-hole.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
}
}
