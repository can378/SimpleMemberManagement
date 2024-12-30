package com.example.myweb.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myweb.dao.CartDao;
import com.example.myweb.model.CartDto;


@WebServlet("/Cart.do")
public class CartServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 public CartServlet() {
     super();
 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     CartDao cartDao = new CartDao();
     List<CartDto> cartList = cartDao.listCartWithProductInfo(1);//이거 custID로 변경해야함

     request.setAttribute("cartList", cartList);
     request.getRequestDispatcher("/cartform.jsp").forward(request, response);
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     // 수량 업데이트 처리
     if ("update".equals(request.getParameter("action"))) {
         CartDao cartDao = new CartDao();
         for (CartDto cart : (List<CartDto>) request.getAttribute("cartList")) {
             String quantityStr = request.getParameter("quantity_" + cart.getCartId());
             if (quantityStr != null) {
                 int quantity = Integer.parseInt(quantityStr);
                 cartDao.updateCart(cart.getCartId(), quantity);
             }
         }
         response.sendRedirect("Cart.do");
     }
 }

 // 선택된 항목 삭제 처리
 protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     String[] cartIds = request.getParameterValues("cartIds");
     if (cartIds != null) {
         CartDao cartDao = new CartDao();
         List<Long> ids = new ArrayList<>();
         for (String id : cartIds) {
             ids.add(Long.parseLong(id));
         }
         cartDao.removeSelectedItems(ids);
         response.sendRedirect("Cart.do");
     }
 }
}
