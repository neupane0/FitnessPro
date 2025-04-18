package Controller;


import Model.User;
import Model.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String adminCode = request.getParameter("adminCode"); // Hidden field or specific form field.

        boolean isAdmin = false;
        if ("ADMIN123".equals(adminCode)) {  // Simple  code check.
            isAdmin = true;
        }

        User user = new User(fullName, email, password, isAdmin);
        DAO.UserDAO dao = new DAO.UserDAO();

        if (dao.registerUser(user)) {
            response.sendRedirect("LoginPage.jsp");
        } else {
            response.sendRedirect("SignUpPage.jsp?error=RegistrationFailed");
        }
    }
}