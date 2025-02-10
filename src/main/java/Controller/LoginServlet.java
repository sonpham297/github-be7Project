package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;
import utility.PasswordUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("we are in loginServlet");
		
		UserDAO userDao = new UserDAO();
		User user = userDao.findUser(email);
		RequestDispatcher rd = null;
		String message = null;
		if (user == null) {
			message = "This email is not register yet. Please register!!!";
		} else {
			if (PasswordUtil.checkPassword(password, user.getPassword())) {
				if (user.getIsActivate().equals("Y")) {
	                // Create a session and store user details
	                HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	                session.setMaxInactiveInterval(30 * 60); // Session expires after 30 minutes
	                response.sendRedirect("home.jsp"); // Redirect to the  HomePage
	                return;				
				} else {
					message = "Please check your email and confirm your registration by click to the link!";
				}
			} else {
				message = "Wrong password, please try again!";
			}
		}
		request.setAttribute("loginMessage", message);
		rd = request.getRequestDispatcher("login.jsp");
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
