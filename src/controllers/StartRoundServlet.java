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
import model.Shot;
import model.User;
import model.RoundHoleSummary;
import dbhelpers.ReadHoleQuery;

/**
 * @author jjewell_000
 * Servlet implementation class StartRoundServlet
 */
@WebServlet(description = "This servlet initiates tables needed to start firt hole and calls new-hole.jsp", urlPatterns ={"/StartRound"})

public class StartRoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
       
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
		
		session = request.getSession();
		
		
		//******************************************************************************
		//This Section retrieves and stores the round choices the user entered on start-round.jsp
		
		//retrieve user inputted parameters
			String tee = request.getParameter("tee-box");
			int numHoles = Integer.parseInt(request.getParameter("hole-amount"));
			int startHoleNumber = Integer.parseInt(request.getParameter("starting-hole"));
			int currentHoleID=0;
		
		//determine the starting hole id by searching the HolesArray
			Hole[] holeArray = (Hole[]) session.getAttribute("holesArray");
			boolean match = false;
			int i=0;
			while (!match){
				int holeArrayHoleNumber = holeArray[i].getHoleNumber();
				if (holeArrayHoleNumber == startHoleNumber){
					int startHoleID = holeArray[i].getHoleID();
					currentHoleID = startHoleID;
					session.setAttribute("startHoleID", startHoleID);
					session.setAttribute("startHoleNumber", startHoleNumber);
					session.setAttribute("currentHoleID", currentHoleID); //at game start will be equal to starting hole
					match = true;
				}else {
					i=i++;
					}
				}
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&	
		//calculate course handicap for golfer who has handicap on profile 
		long courseHandicap = 0;
		session.setAttribute("handicap",courseHandicap);
		
		User user = (User) session.getAttribute("user");
			long handicapIndex = (user.getGolferHandicapIndex());

		if (handicapIndex >0) {
			String gender = (user.getGender());
			
			//Retrieve course rating information for tee, gender
			ArrayList<CourseRating> courseRatingArrayList = (ArrayList<CourseRating>) session.getAttribute("courseRatingArrayList");
			
			//Read through array list until a match is found
			int counter = 0;
			int tempCourseSlope;
			boolean match2 = false;
			while (!match2) {
				CourseRating CR = new CourseRating();
				CR = courseRatingArrayList.get(counter);
				if ((CR.getCourseRatingGender()== gender)&& (CR.getCourseRatingTee() == tee)){
					match2 = true;
										
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
				counter=counter++;
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
