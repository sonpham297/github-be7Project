package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import utility.EmailUtility;
import utility.PasswordUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao = new UserDAO();
	private String message;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailRegister = request.getParameter("emailRegister");
		System.out.println("we are in register servlet");
		
		if (userDao.findUser(emailRegister) != null) {
			userExist(request, response);
		}
		else {
			insertNewUser(request, response, emailRegister);
		}
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
	
	private void insertNewUser (HttpServletRequest request, HttpServletResponse response, String emailRegister)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String hashedPassword = request.getParameter("passReg"); // this password already hashed by SHA256
		System.out.println("password hashed by SHA: " + hashedPassword);
		String finalPassword = PasswordUtil.hashPassword(hashedPassword);
		System.out.println("password hashed by BCrypt: " + finalPassword);

		String token = UUID.randomUUID().toString();
        Timestamp expiryTime = Timestamp.from(Instant.now().plus(48, ChronoUnit.HOURS)); // 48-hour expiry

		User user = new User(firstname, lastname, emailRegister, finalPassword, token, expiryTime, "N");

		if (userDao.insert(user)) {
			String activationLink = "http://localhost:8080/ChemistSale/ActivateAccount?token=" + token;

			try {
				EmailUtility.sendEmail(emailRegister, "From ChemistSale: Account Activation",
						"Click here to activate your account: " + activationLink);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			message = "Please check your registered email to confirm the registration.";
			dispatchView(request, response);
		} else {
			message = "Something went wrong. Your registration was fail. Please try again!";
			dispatchView(request, response);
		}
	}
	
	private void userExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		message = "The email already registered. Please login with this email!";
		dispatchView(request, response);		
	}

	private void dispatchView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("register-authen.jsp");
		rd.forward(request, response);
	}

}
