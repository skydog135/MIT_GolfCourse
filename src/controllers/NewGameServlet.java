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
import dbhelpers.ReadCourseRatingQuery;
import dbhelpers.ReadHoleQuery;
import dbhelpers.ReadHoleYardsQuery;


/**
 * @author jjewell_000
 * Servlet implementation class NewGameServlet
 */
@WebServlet(description = "This servlet loads the static data tables and calls the start-round.jsp", urlPatterns = { "/NewGame" })
public class NewGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewGameServlet() {
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
		System.out.println("I'm in the new game servlet start of doPost");
		session = request.getSession();	

		//******************************************************************************
		//This Section Loads the static Hole table
			// Create a ReadHoleQuery helper object
			ReadHoleQuery rhq = new ReadHoleQuery("tomcatdb","root","bu11fr0g");
		
			//Read the hole data for the UGA Golf Course (course ID = 1)
			rhq.doReadHole();
		
			//Call the method to load the Session Hole Array
			Hole[] holesArray = new Hole[18];
			System.out.println("I'm in the new game servlet going into rhq.loadHoleSessionQuery");
			holesArray = rhq.loadHoleSessionArray();
			
			System.out.println("I'm in the new game servlet coming out of rhq.loadHoleSessionQuery");
			session.setAttribute("holesArray", holesArray);
			
						
		// This ends the section that loads the static Hole table
		//******************************************************************************	
		
		//******************************************************************************			
		//This Section Loads the static Hole Yardage table
			// Create a ReadHoleYardQuery helper object
			ReadHoleYardsQuery rhyq = new ReadHoleYardsQuery("tomcatdb","root","bu11fr0g");
		
			//Read the hole data for the UGA Golf Course (course ID = 1)
			rhyq.doReadHoleYards();
			
			//Call the method to load the Session Hole Yard Array List
			ArrayList<HoleYards> holeYardsArrayList = new ArrayList<HoleYards>();
			holeYardsArrayList = rhyq.loadHoleSessionArray();
		
			session.setAttribute("holeYardsArraylist", holeYardsArrayList);
		// This ends the section that loads the static Hole Yardage table
		//******************************************************************************
		
		
		//******************************************************************************
			//This Section Loads the static course rating table
			// Create a ReadCourseRatingQuery helper object
			ReadCourseRatingQuery rcrq = new ReadCourseRatingQuery("tomcatdb","root","bu11fr0g");
				
			//Read the course rating data
			rcrq.doReadCourseRating();
					
			//Call the method to load the Gender Array List
			ArrayList<CourseRating> courseRatingArrayList = new ArrayList<CourseRating>();
			courseRatingArrayList = rcrq.loadCourseRatingSessionArray();
			
			System.out.println("just created the CourseRatingArryList Object" + courseRatingArrayList.size());
				
			session.setAttribute("courseRatingArrayList", courseRatingArrayList);
		// This ends the section that loads the static gender table
		//******************************************************************************				
							
			
		//Prepare to call the start round screen	
			url="start-round.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		
	}

	
	
}
