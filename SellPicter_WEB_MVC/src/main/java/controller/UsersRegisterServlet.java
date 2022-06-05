package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import repository.UsersBO;

/**
 * Servlet implementation class UsersRegisterServlet
 */
public class UsersRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// khai bÄ‚Â¡o session
		HttpSession session = request.getSession();
		UsersBO s = new UsersBO();
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		if (!s.checkAccount(userName, email)) {
			Users users = new Users();
			users.setTenTaiKhoan(request.getParameter("name"));
			users.setEmail(request.getParameter("email"));
			users.setAnhChinh("avatar.png");
			// users.setMatKhau(encryption(request.getParameter("user-password")));
			users.setMatKhau(request.getParameter("pass"));
			users.setPhanQuyen("O");
			users.setTenHienThi(request.getParameter("name"));
			String mes = "";
			try {
				if (s.addAccount(users)) {

					mes = "";
					session.setAttribute("SingupSuccess", mes);
					response.sendRedirect("login.jsp");
				} else {
					mes = "Lá»—i!";
					session.setAttribute("SingupError", mes);
					response.sendRedirect("register.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else

		{

			String mes = "";
			session.setAttribute("AcccExists", mes);
			response.sendRedirect("register.jsp");
		}

	}

}
