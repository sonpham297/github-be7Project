package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductDAO;
import entity.Product;

/**
 * Servlet implementation class SearchBar
 */
@WebServlet("/SearchModule")
public class SearchModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ArrayList<Product> products = null;
		Product product = null;
		String query = request.getParameter("searchTerm");
		
		if (query != null) {
			products = searchEngine(query);			
		} else {
			String productName = request.getParameter("product");
			ProductDAO dao = new ProductDAO();
			product = dao.getProductDetail(productName);			
		}
		
		try (PrintWriter out = response.getWriter()) {
			Gson gson = new Gson();
			if (query != null) 
				out.write(gson.toJson(products));
			else
				out.write(gson.toJson(product));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while generating JSON: " + e.getMessage());
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

	private ArrayList<Product> searchEngine(String query) {
		ProductDAO dao = new ProductDAO();
		if (query == null) 
			return dao.getFeaturedProduct();
		if (query.contains("BetterThanHalf"))
			return dao.getMoreThanHalfProducts();
		if (query.contains("Fragrances")) {
			if (query.contains("women"))
				return dao.getWomenFragrances();
			else if (query.contains("men"))
				return dao.getMenFragrances();
			else 
				return dao.getProductByCat("Fragrances");
		}
		if (query.contains("Vitamins"))
			return dao.getProductByCat("Vitamins & Supplements");
		if (query.contains("Beauty"))
			return dao.getProductByCat("Beauty");
		if (query.contains("Baby"))
			return dao.getProductByCat("Baby Care");
		if (query.contains("Cosmetics"))
			return dao.getProductByCat("Cosmetics");
		if (query.contains("Clearance"))
			return dao.getProductByCat("Clearance");

		//will implement get favourite products later
		if (query.contains("favourite"))
			return dao.getFavPro("User's name will be here");
		
		return dao.getProductByName(query);
	}

}
