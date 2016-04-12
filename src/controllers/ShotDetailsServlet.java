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
@WebServlet(description = "This servlet loads saves the club and lie choices for the first shot on a hole", urlPatterns = { "/ShotDetail" })
public class ShotDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShotDetailsServlet() {
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
		System.out.println("I'm in the ShotDetailsServlet of doPost");
	
		HttpSession session = request.getSession();
		
		
		//******************************************************************************
		//This Section retrieves and stores the club and lie choices made in new-hole-details.jsp JAJ
		
		//retrieve user inputted parameters
			String club = request.getParameter("club");
			String lie = request.getParameter("lie");
			
		System.out.println("in ShotDetailServlet:  club= " + club + "lie = " + lie);	
			
		//Store Shot Details in session variables
			session.setAttribute("currentShotClub", club);
			session.setAttribute("currentShotLie", lie);
			
		//Prepare to call the start round screen	
			url="new-hole.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		
	}

	
	
}
