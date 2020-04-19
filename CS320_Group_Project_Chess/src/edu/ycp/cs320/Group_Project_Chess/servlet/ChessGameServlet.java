package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.awt.Point;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
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
	
	GameController controller = new GameController(game);
	
	//TEMP MOVE DATA
	Boolean pos1Recieved = false;
	int sourceX;
	int sourceY;
	int destX;
	int destY;

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("model", game);
		System.out.println("ChessGame Servlet: doGet");
		
//-- structure taken from the library example
		String name = (String) req.getSession().getAttribute("name");
		if (name == null) {
			System.out.println("   User: <" + name + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("   User: <" + name + "> logged in");
//-- structure taken from the library example
		
		req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ChessGame Servlet: doPost");		
		
		if (req.getParameter("home") != null) {
			System.out.println("ChessGame Servlet: forwarding to hHome");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("ChessGame Servlet: forwarding to chessHome");
			resp.sendRedirect(req.getContextPath() + "/chessHome");
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessGame Servlet: forwarding to profile");
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("ChessGame Servlet: forwarding to friends");
			resp.sendRedirect(req.getContextPath() + "/friends");
		}
		if(req.getParameter("x1") != null && pos1Recieved == false) { //if position 1 is received
			pos1Recieved = true; //sets position 1 received flag to true
			System.out.println("Recieved Source");
			
			sourceX = Integer.parseInt(req.getParameter("x1"));
			sourceY = Integer.parseInt(req.getParameter("y1"));
			
			System.err.println("TURN: " + game.getTurn()%2);

			
			if(game.getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				if(game.getTurn()%2 != game.getBoard().getSpace(sourceX, sourceY).getPiece().getColor()) { //if not player's turn
					pos1Recieved = false;
				}
			} else {
				pos1Recieved = false;
			}
			
			
			req.setAttribute("model", game);
			
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} 
		
		else if(req.getParameter("x1") != null && pos1Recieved == true) { //if position 2 is received
			pos1Recieved = false; //sets position 1 received flag to false
			System.out.println("Recieved Destination");
			
			destX = Integer.parseInt(req.getParameter("x1"));
			destY = Integer.parseInt(req.getParameter("y1"));
			
			if(game.getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				if(sourceX == destX && sourceY == destY) { //if source is destination
					System.out.println("NOT VALID");
				}
				if(game.getBoard().getSpace(sourceX, sourceY).getPiece().validMove(new Point(destX, destY), game.getBoard()) == true) {	//if move is valid			
					controller.movePiece(game.getBoard().getSpace(sourceX, sourceY), game.getBoard().getSpace(destX, destY)); //moves piece
					System.out.println("VALID");
					game.setTurn(game.getTurn()+1); //increments turn counter
				} else {
					System.out.println("NOT VALID ");
				}
			}
			
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