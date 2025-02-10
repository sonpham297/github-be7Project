package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

/**
 * Servlet implementation class ActivateServlet
 */
@WebServlet("/ActivateAccount")
public class ActivateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		UserDAO userDao = new UserDAO();
		User user = userDao.findUserByToken(token);
		
		RequestDispatcher rd = request.getRequestDispatcher("register-authen.jsp");
		String message = null;
		if (user == null) {
			message = "Invalid token. Please register again";
		} else {
			Timestamp expiryTime = user.getTokenExpiry();
			if (expiryTime != null && expiryTime.toInstant().isAfter(Instant.now())) {
				// Token is still valid, activate account
				if (userDao.updateActivation(token)) {
		            // Create a session and store user details
	                HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	                session.setMaxInactiveInterval(30 * 60); // Session expires after 30 minutes
	                message = "Congratulations! You've successfully registered. Thank you for joining us. You can now explore our products and start shopping. Happy browsing!";			
				} else {
					message = "Something went wrong. Your registration was fail. Please try again!";
				}			
			} else {
				message = "Activation link has expired. Please register again!";
			}			
		}
		request.setAttribute("message", message);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
