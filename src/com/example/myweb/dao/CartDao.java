package com.example.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.example.myweb.model.CartDto;
import com.example.myweb.model.ProductDto;

public class CartDao {
    private DataSource ds;

    public CartDao() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // 장바구니 목록 조회
    /*
    public List<CartDto> listCart(int custId) {
    	
        List<CartDto> cartList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT cart_id, cart_count, cust_id, prod_id FROM cart WHERE cust_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, custId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CartDto cart = new CartDto(
	        		rs.getLong("cart_id"),
	    		    rs.getLong("cart_count"),
	    		    rs.getLong("cust_id"),
	    		    rs.getLong("prod_id")
                );
                cartList.add(cart);
            }
        } catch (Exception e) {
            throw new RuntimeException("CartDao listCart error=" + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cartList;
    }
    */

    // 장바구니에 항목 추가
    public void addCart(int prodId, int cartCount, int custId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            String sql = "INSERT INTO cart (cartid, cartcount, custid, prodid) VALUES (cart_seq.NEXTVAL, ?, ?, ?)";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cartCount);
            pstmt.setInt(2, custId);
            pstmt.setInt(3, prodId);
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            throw new RuntimeException("CartDao addCart error=" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 장바구니 항목 제거
    public void removeCart(int cartId, int custId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
        	
            con = ds.getConnection();
            String sql = "DELETE FROM cart WHERE cartid = ? AND custid = ?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cartId);
            pstmt.setInt(2, custId);
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            throw new RuntimeException("CartDao removeCart error=" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    


    public void updateCart(Long long1, int cartCount) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            String sql = "UPDATE cart SET cart_count = ? WHERE cart_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cartCount);
            pstmt.setLong(2, long1);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("CartDao updateCart error=" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 선택된 항목 삭제
    public void removeSelectedItems(List<Long> cartIds) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            String sql = "DELETE FROM cart WHERE cart_id = ?";
            pstmt = con.prepareStatement(sql);

            for (Long cartId : cartIds) {
                pstmt.setLong(1, cartId);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
        } catch (Exception e) {
            throw new RuntimeException("CartDao removeSelectedItems error=" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<CartDto> listCartWithProductInfo(int custId) {
        List<CartDto> cartList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT cart_id, cart_count, cust_id, prod_id FROM cart WHERE cust_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, custId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CartDto cart = new CartDto();
                cart.setCartId(rs.getLong("cart_id"));
                cart.setCartCount(rs.getLong("cart_count"));
                cart.setCustId(rs.getLong("cust_id"));
                cart.setProdId(rs.getLong("prod_id"));

                // 해당 prodId에 해당하는 상품 정보 가져오기
                ProductDto product = getProductById(rs.getLong("prod_id"));
                cart.setProduct(product);

                cartList.add(cart);
            }
        } catch (Exception e) {
            throw new RuntimeException("CartDao listCartWithProductInfo error=" + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;
    }

    private ProductDto getProductById(long prodId) {
        // ProductDao에서 상품 정보를 조회하는 로직
        ProductDAO productDao = new ProductDAO();
        return productDao.getProductById(prodId);
    }


    
    

    
    
}
