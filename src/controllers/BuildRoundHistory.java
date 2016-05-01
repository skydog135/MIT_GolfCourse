package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.ReadHoleSummaryQuery;

/**
 * Servlet implementation class BuildRoundHistory
 * @author jjewell_000
 */
@WebServlet(description = "controls the building of the round history f9/b9 tables", urlPatterns = { "/BuildRoundHistory" })
public class BuildRoundHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildRoundHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Session/request Data
		HttpSession session = request.getSession();
		int golferID=Integer.parseInt(request.getParameter("golferID"));
		int roundID=Integer.parseInt(request.getParameter("roundID"));
		int holesPlayed=Integer.parseInt(request.getParameter("holesPlayed"));
		String tee=(request.getParameter("tee"));
		session.setAttribute("RoundComments", (request.getParameter("comments")));
		session.setAttribute("RoundDate", (request.getParameter("date")));
		session.setAttribute("RoundHolesPlayed", holesPlayed);
		session.setAttribute("RoundID", roundID);
		
		//call db help to connect to database
		ReadHoleSummaryQuery rhs = new ReadHoleSummaryQuery("tomcatdb","root","bu11fr0g");
		
		//call db helper to execute query on round table for specific golfer
		rhs.doReadHoleSummary(tee, roundID);

		//Call dbhelper to format display tables
		if (holesPlayed==9){//If round was 9 holes, call getHTMLTable
			String table = rhs.getHTMLTable();
			session.setAttribute("9OnlyTable", table);
			
			//get hole summary data and table set up for nine holes only
			int[] nineHoleSummaryArray = rhs.getNineHoleTotals();
			String nineHoleSummaryTable = rhs.getRoundHoleSummaryHTML(nineHoleSummaryArray, tee);
			session.setAttribute("HistoryRoundSummary",nineHoleSummaryTable);
			
		}else {//18 holes played, call getHTML18Table
			String[] F9B9TableArray = new String[2];
			F9B9TableArray = rhs.getHTML18Table();
			session.setAttribute("F9Table",F9B9TableArray[0]);
			session.setAttribute("B9Table",F9B9TableArray[1]);
			
			//get hole summary data and table set up for 18 holes only
			int[] eighteenHoleSummaryArray = rhs.getEighteenHoleTotals();
			String eighteenHoleSummaryTable = rhs.getRoundHoleSummaryHTML(eighteenHoleSummaryArray, tee);
			session.setAttribute("HistoryRoundSummary",eighteenHoleSummaryTable);
		}
		
		System.out.println("In the BuildRoundHistoryServlet of doPost just created the display hole history tables");

		
		url="historic-round-summary.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	
	}
	

}
