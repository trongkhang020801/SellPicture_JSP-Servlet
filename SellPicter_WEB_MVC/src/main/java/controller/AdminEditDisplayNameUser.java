package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import repository.UsersBO;

/**
 * Servlet implementation class AdminEditDisplayNameUser
 */
@WebServlet(description = "AdminEditDisplayNameUser", urlPatterns = { "/admin/pages/profile/profile" })
public class AdminEditDisplayNameUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditDisplayNameUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String displayName = request.getParameter("displayName");
		//System.out.println(displayName);
		long id = Long.parseLong(request.getParameter("id"));
		//System.out.println("id");
		try {
			if (new UsersBO().editDisplayNameAccount(displayName, id)) {
				HttpSession session = request.getSession();
				Users user = (Users) session.getAttribute("uslogin");
				user.setTenHienThi(displayName);
				session.removeAttribute("uslogin");
				session.setAttribute("uslogin", user);
			} else {
				System.out.println("that bai");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
