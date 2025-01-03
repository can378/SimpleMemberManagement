package com.example.myweb.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myweb.dao.ProductDAO;
import com.example.myweb.model.ProductDto;


@WebServlet("/Product.do")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProductServlet() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        ProductDAO productDAO = new ProductDAO();
        List<ProductDto> productList = productDAO.listProducts();

        // 제품 목록을 request 객체에 저장
        request.setAttribute("productList", productList);

        // forwarding
        request.getRequestDispatcher("/listproduct.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
