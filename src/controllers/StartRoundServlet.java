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
import model.Shot;
import model.User;
import model.RoundHoleSummary;

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
			System.out.println(numHoles);
			
			int startHoleNumber = Integer.parseInt(request.getParameter("starting-hole"));
			System.out.println("The starting hole is " + startHoleNumber);
			
			int currentHoleNumber = startHoleNumber;
			int currentHoleID=0;
			
			// TODO Auto-generated method stub
			
		//determine the starting hole id by searching the HolesArray
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
					System.out.println("just set the startHoleID "+ startHoleID);
					
					currentHoleID = startHoleID;
					System.out.println("just set the currentHoleID "+currentHoleID);
					
					session.setAttribute("startHoleID", startHoleID);
					session.setAttribute("startHoleNumber", startHoleNumber);
					session.setAttribute("currentHoleID", currentHoleID); //at game start will be equal to starting hole
					session.setAttribute("currentHoleNumber", currentHoleNumber);
					System.out.println("just set all the session attributes");
					match=true;
					}
				
				i=i+1;
			
				}while (match == false);
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	
		//calculate course handicap for golfer who has handicap on profile 
			System.out.println("I'm in the start round servlet2 calculating golfer handicap");		
		float courseHandicap = 0;
		session.setAttribute("handicap",courseHandicap);
		
		User user = (User) session.getAttribute("user");
			float handicapIndex = (user.getGolferHandicapIndex());

		if (handicapIndex >0) {
			String gender = (user.getGender());
			System.out.println("Gender in Start Round =" + gender);		
			System.out.println("tee in Start Round =" + tee);	
			
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
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//Set up the arrays that will store the hole summary data and the shot by shot data
		
		//create the hole summary Array List.  This will be added to at
		//the end of each hole
		ArrayList<RoundHoleSummary> holeSummaryArrayList = new ArrayList<RoundHoleSummary>();
		session.setAttribute("holeSummaryArraylist", holeSummaryArrayList);

		//create the shot summary array list.  This will be added to at the end of each shot
		ArrayList<Shot> shotSummaryArrayList = new ArrayList<Shot>();
		session.setAttribute("shotSummaryArraylist", shotSummaryArrayList);
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		
		
		//Prepare to call the start round screen	
		url="new-hole.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
}
}
