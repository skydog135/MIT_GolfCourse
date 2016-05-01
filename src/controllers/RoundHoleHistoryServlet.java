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
@WebServlet(description = "Servlet that controls the display of golfer rounds", urlPatterns = { "/RoundHoleHistory" })
public class RoundHoleHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoundHoleHistoryServlet() {
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
		int roundHolesPlayed = (Integer)session.getAttribute("RoundHolesPlayed");
		
		if (roundHolesPlayed == 9){//display 9 holes only jsp
			url="round-history-front-9-only.jsp";
			
		}else {//display 18 hole jsp
			url="round-history-front-9.jsp";
		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
