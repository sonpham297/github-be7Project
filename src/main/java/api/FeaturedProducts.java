package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Product;
import utility.DBUtils;

/**
 * Servlet implementation class FeaturedProducts
 */
@WebServlet("/FeaturedProducts")
public class FeaturedProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeaturedProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

    
   	@Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           response.setContentType("application/json");
           response.setCharacterEncoding("UTF-8");
           
           List<Product> products = new ArrayList<>();
           try (Connection conn = DBUtils.makeConnection();
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE rating >= 97")) {

               while (resultSet.next()) {
                   products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
   						resultSet.getString("gender"), resultSet.getFloat("price"),
   						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
   						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
   						resultSet.getInt("avail_qty"))
                   );
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }

           Gson gson = new Gson();
           String json = gson.toJson(products);
           PrintWriter out = response.getWriter();
           out.print(json);
           out.flush();
       }


}
