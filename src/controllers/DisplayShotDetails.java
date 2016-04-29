package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.ReadShotQuery;

/**
 * Servlet implementation class DisplayShotDetails
 */
@WebServlet(description = "Servlet that controls display of shot details", urlPatterns = { "/DisplayShotDetails" })
public class DisplayShotDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayShotDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Session Data
		HttpSession session = request.getSession();
		
		int roundHoleSummaryID=Integer.parseInt(request.getParameter("roundHoleSummaryID"));
		int holeNum=Integer.parseInt(request.getParameter("holeNum"));
		System.out.println("In Display Shot Detail: Hole Number = " + holeNum);
		String holeCom = request.getParameter("holeCom");
		System.out.println("In Display Shot Detail: Hole Comments = " + holeCom);
		session.setAttribute("holeCom", holeCom);
		session.setAttribute("holeNum", holeNum);
		
		//prepare to read the Shot table
		//create a ReadShotQuery helper object
		ReadShotQuery rsq = new ReadShotQuery("tomcatdb","root","bu11fr0g");
		
		//Execute Select * for shot table where 
		System.out.println("I'm in DisplayShotDetails of doPost & roundHoleSummaryID = "+roundHoleSummaryID);
		rsq.doReadShot(roundHoleSummaryID);
		
		//format the shot detail table
		String shotTable = rsq.getShotHTMLTable();
		session.setAttribute("shotDetailTable", shotTable);
		
		System.out.println("I'm in DisplayShotDetails of doPost & return table value = "+shotTable);
		url= "shot-detail.jsp";
		

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);	
		
	}

}
