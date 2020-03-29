package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.model.*;


public class ChessGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//TEMPORARY PERSISTANT MEMORY
  	Profile pr1 = new Profile();
  	FriendsList f1 = new FriendsList();
  	Stats s1 = new Stats();
  	Credentials c1 = new Credentials("a", "b", "c");
  	Credentials c2 = new Credentials("d", "e", "f");
	User u1 = new User(c1, s1, f1, pr1);
	User u2 = new User(c2, s1, f1, pr1);
	Player p1 = new Player(u1, 0);
	Player p2 = new Player(u2, 1);
	Game game = new Game(p1, p2);
	Boolean pos1Recieved = false;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("model", game);
		System.out.println("ChessGame Servlet: doGet");
		
		req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ChessGame Servlet: doPost");
		
		
		if (req.getParameter("home") != null) {
			System.out.println("ChessGame Servlet: forwarding to hHome");
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("ChessGame Servlet: forwarding to chessHome");
			req.getRequestDispatcher("/_view/chessHome.jsp").forward(req, resp);
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessGame Servlet: forwarding to profile");
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("ChessGame Servlet: forwarding to friends");
			req.getRequestDispatcher("/_view/friends.jsp").forward(req, resp);
		}
		if(req.getParameter("x1") != null && pos1Recieved == false) {
			pos1Recieved = true;
			System.out.println("Recieved Position 1");
			System.out.println(req.getParameter("x1"));
			System.out.println(req.getParameter("y1"));
			
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} 
		
		else if(req.getParameter("x1") != null && pos1Recieved == true) {
			pos1Recieved = false;
			System.out.println("Recieved Position 2");
			System.out.println(req.getParameter("x1"));
			System.out.println(req.getParameter("y1"));
			
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		}
	}
}














//My attempt at passing model into the view, Use to reference later

/*
package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.model.*;


public class ChessGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  protected void processRequest(HttpServletRequest request, 
              HttpServletResponse response) 
throws ServletException, IOException 
{ 
		  	Profile pr1 = new Profile();
		  	FriendsList f1 = new FriendsList();
		  	Stats s1 = new Stats();
		  	Credentials c1 = new Credentials("a", "b", "c");
		  	Credentials c2 = new Credentials("d", "e", "f");
			User u1 = new User(c1, s1, f1, pr1);
			User u2 = new User(c2, s1, f1, pr1);
			Player p1 = new Player(u1, 0);
			Player p2 = new Player(u2, 1);
			Game game = new Game(p1, p2);
//			model.getBoard().getSpace(0, 0).getPiece().getRank().toString();
			
			request.setAttribute("model", game);
	        RequestDispatcher rd = request.getRequestDispatcher("/_view/chessGame.jsp"); 
	        rd.forward(request, response); 
				  
}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("ChessGame Servlet: doGet");
		
		request.getRequestDispatcher("/_view/chessGame.jsp").forward(request, response);
		processRequest(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ChessGame Servlet: doPost");
		
		
				
		if (request.getParameter("home") != null) {
			System.out.println("ChessGame Servlet: forwarding to hHome");
			request.getRequestDispatcher("/_view/home.jsp").forward(request, resp);
		}
		
		if (request.getParameter("chessHome") != null) {
			System.out.println("ChessGame Servlet: forwarding to chessHome");
			request.getRequestDispatcher("/_view/chessHome.jsp").forward(request, resp);
		}
		
		if (request.getParameter("profile") != null) {
			System.out.println("ChessGame Servlet: forwarding to profile");
			request.getRequestDispatcher("/_view/profile.jsp").forward(request, resp);
		}
		
		if (request.getParameter("friends") != null) {
			System.out.println("ChessGame Servlet: forwarding to friends");
			request.getRequestDispatcher("/_view/friends.jsp").forward(request, resp);
		}
		processRequest(request, resp);


	}
}

*/