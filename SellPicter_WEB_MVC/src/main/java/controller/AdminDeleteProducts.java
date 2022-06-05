package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Products;
import repository.ProductsBO;

/**
 * Servlet implementation class AdminDeleteProducts
 */
public class AdminDeleteProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ProductsBO productControl = new ProductsBO();
		Products product = productControl.getProductsByID(id);

		
		String appPath = request.getServletContext().getRealPath("");
		appPath = appPath.replace('\\', '/');

	
		String fullSavePath = null;
		if (appPath.endsWith("/")) {
			fullSavePath = appPath + "assets/img/shop/product/";
		} else {
			fullSavePath = appPath + "/" + "assets/img/shop/product/";
		}

		String fileName = product.getAnhChinh();
System.out.println("sddddddđ");
		File file = new File(fullSavePath, fileName);
		System.out.println(file.getPath()+"1");

		boolean check = productControl.deleteProduct(id);
		if (check) {
			HttpSession session = request.getSession();
			session.setAttribute("Delete", "Success");
			session.setMaxInactiveInterval(15);
			if (file.delete()) {
				System.out.println("file: " + file.getName());
			} else {
				System.out.println("");
			}
			response.sendRedirect("AdminListProducts");
		} else {

		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
