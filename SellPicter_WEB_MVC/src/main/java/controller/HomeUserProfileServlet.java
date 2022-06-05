package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import repository.UsersBO;

/**
 * Servlet implementation class HomeUserProfileServlet
 */
public class HomeUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeUserProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// long id = Long.parseLong(request.getParameter("id"));

		// UsersBO us = new UsersBO();
		// Users user = new Users();
		// user = us.getUsersById(id);
		// request.setAttribute("info", user);

		RequestDispatcher rd = request.getRequestDispatcher("user-profile.jsp");
		rd.forward(request, response);
	}

	// Kiá»ƒm tra lá»—i
	private String Validator(String pass) {
		
		Pattern pattern;
		final String PASSWORD_PATTERN = "^[A-Za-z0-9]{3,30}$";
		pattern = Pattern.compile(PASSWORD_PATTERN);
	
		if (pattern.matcher(pass).matches()) {
			pass = "";
		} else {
			pass = "Máº­t kháº©u tá»« 3 Ä‘áº¿n 30 kÃ­ tá»± bao gá»“m chá»¯ vÃ  sá»‘";
		}
		return pass;
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

		HttpSession session = request.getSession();
		UsersBO dao = new UsersBO();
		String user_name = request.getParameter("user-name");
		String user_view = request.getParameter("view-name");
		String user_pass = request.getParameter("password");
		// String user_email= request.getParameter("email");
		long id = Long.parseLong(request.getParameter("id"));
		String mes = "";
		String pass = Validator(user_pass);
		if (pass.equals("")) {
		
			user_pass = dao.encryption(user_pass);

			Users user = new Users();
			user.setTenHienThi(user_view);
			user.setMatKhau(user_pass);
			user.setTenTaiKhoan(user_name);
			System.out.println(user_view);
			try {
				if (dao.editAccount(user)) {
					
					Users us = dao.getUsersByName(user_name);
					System.out.println(us.getTenHienThi() + "vs" + us.getMatKhau());
					mes = "Cập nhật thông tin tài khoản thành công.";
					session.setAttribute("uslogin", us);
					session.setAttribute("SussecfulEditAcc", mes);
					response.sendRedirect(request.getHeader("referer"));
				} else {
					mes = "Cập nhật thông tin tài khoản thất bại.";
					session.setAttribute("SingupError", mes);
					response.sendRedirect(request.getHeader("referer"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			mes = "";
			session.setAttribute("SingupError", mes);
			response.sendRedirect(request.getHeader("referer"));
		}
	}

}
