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
@WebServlet(description = "This servlet loads saves the club and lie choices for the shots 2 through x on a hole", urlPatterns = { "/Hole2ShotDetail" })
public class Hole2ShotDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hole2ShotDetailsServlet() {
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
		System.out.println("I'm in the Hole2ShotDetailsServlet of doPost");
	
		HttpSession session = request.getSession();
		
		
		//******************************************************************************
		//This Section retrieves and stores the club and lie choices made in new-hole-details.jsp JAJ
		
		//retrieve user inputted parameters
			String club = request.getParameter("club");
			String lie = request.getParameter("lie");
			String stringPenaltyStrokes = request.getParameter("penalty-strokes");
			int currentShotNumber = (Integer) session.getAttribute("currentShotNumber");
			
			int penaltyStrokes = 0;
			if (!stringPenaltyStrokes.equalsIgnoreCase("0")) {
				penaltyStrokes = Integer.parseInt(stringPenaltyStrokes);
				currentShotNumber = currentShotNumber + penaltyStrokes;
				session.setAttribute("currentShotNumber", currentShotNumber);
				int cumulativeShots = ((Integer) session.getAttribute("cumulativeShots")+ penaltyStrokes);
				session.setAttribute("cumulativeShots", cumulativeShots);
			};
			

			
		//********************************************************************************
			
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//This section increments game stat variables, if needed
			
			//checking to see if we need to increment Fairway in Regulation Counter
			if (lie.equalsIgnoreCase("Fairway") &&  currentShotNumber==2 ){//then FIR
				int fir = ((Integer) session.getAttribute("totalFIR"))+1;
				session.setAttribute("totalFIR",fir);
			}
			
			//checking to see if we need to increment Green in Regulation Counter
			//If par 5, GIR is on in 3
			//If Par 4, GIR is on in 2
			//If Par 5, GIR is on in 1
			
			if (lie.equalsIgnoreCase("Green")) {//then determine GIR Stroke value
				int currentHolePar = (Integer)session.getAttribute("currentHolePar");
				int girStrokes = 0;
				switch (currentHolePar){
					case 5: girStrokes = 3;
							break;
					case 4: girStrokes = 2;
						break;
					case 3: girStrokes = 1;
						break;
				}//end switch
			
				if (currentShotNumber == girStrokes){//increment GIR by 1
					int gir = ((Integer)session.getAttribute("totalGIR")+1);
					session.setAttribute("totalGIR",gir);
				}
			}//end girStrokes
			
			//check to see club used is a putter to increment total put count
			if (club.equalsIgnoreCase("putter")){
				int putts = (((Integer)session.getAttribute("totalPutts")) + 1);
				session.setAttribute("totalPutts", putts);
			}
			
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		System.out.println("in ShotDetailServlet:  club= " + club + "lie = " + lie);	
			
		//Store Shot Details in session variables
			session.setAttribute("currentShotClub", club);
			session.setAttribute("currentShotLie", lie);
			session.setAttribute("currentShotPenaltyStroke", penaltyStrokes);
			
		//Prepare to call the start round screen	
			url="hole-2.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		
	}

	
	
}
