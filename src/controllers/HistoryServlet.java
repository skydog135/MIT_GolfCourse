package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.ReadRoundQuery;
import model.User;

/**
 * Servlet implementation class HistoryServlet
 * @author jjewell_000
 */
@WebServlet(description = "Servlet that controls the display of golfer rounds", urlPatterns = { "/History" })
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
		
		//Get Session Data
		HttpSession session = request.getSession();
		
		//create user object and get golferID
		User golfer = (User) session.getAttribute("user");
		int golferID = (Integer) golfer.getId();
		
		//call db help to connect to database
		ReadRoundQuery rrq = new ReadRoundQuery("tomcatdb","root","bu11fr0g");
		
		//call db helper to execute query on round table for specific golfer
		rrq.doReadRound(golferID);
		
		//call db helper to format round history table
		String roundTable = rrq.getRoundHTMLTable();
		
		//set session variable
		session.setAttribute("RoundTable", roundTable);
		
		//set url and forward to dispatcher
		url = "round-history.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
