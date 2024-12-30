<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script>
        // JavaScript로 장바구니에 추가하는 함수 (예시)
        function addToCart(productId) {
            alert("Product " + productId + " has been added to the cart!");
            // 추가 로직: AJAX 요청 또는 로컬 저장소 처리
        }
    </script>
</head>
<body>
    <h1>Product List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Local</th>
                <th>Info</th>
                <th>Image</th>
                <th>Cart</th>
            </tr>
        </thead>
        <tbody>
            <!-- 각 제품 정보 출력 -->
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.prodId}</td> <!-- PROD_ID 출력 -->
                    <td>${product.prodCategory}</td> <!-- PROD_CATEGORY 출력 -->
                    <td>${product.prodName}</td> <!-- PROD_NAME 출력 -->
                    <td>${product.prodPrice}</td> <!-- PROD_PRICE 출력 -->
                    <td>${product.prodStock}</td> <!-- PROD_STOCK 출력 -->
                    <td>${product.prodLocal}</td> <!-- PROD_LOCAL 출력 -->
                    <td>${product.prodInfo}</td> <!-- PROD_INFO 출력 -->
                    <td><img src="${product.prodImg}" alt="Product Image" width="100" height="100" /></td> <!-- PROD_IMG 출력 -->
                    <td>
                        <!-- 장바구니 버튼 -->
                        <form method="post" action="/addToCart">
                            <input type="hidden" name="productId" value="${product.prodId}" />
                            <button type="submit">Add to Cart</button>
                        </form>
                        <!-- JavaScript로 처리하려면 아래 버튼 사용 -->
                        <button onclick="addToCart('${product.prodId}')">Add to Cart (JS)</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
