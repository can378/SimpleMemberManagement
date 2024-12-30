<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
        input[type="number"] {
            width: 60px;
        }
    </style>
    
    <script>
        // 전체 선택/해제
        function toggleAllCheckboxes(source) {
            const checkboxes = document.querySelectorAll('input[type="checkbox"].item-checkbox');
            checkboxes.forEach(checkbox => checkbox.checked = source.checked);
        }

        // 선택된 항목 삭제
        function deleteSelectedItems() {
            const selectedItems = Array.from(document.querySelectorAll('input[type="checkbox"].item-checkbox:checked'))
                .map(checkbox => checkbox.value);
            
            if (selectedItems.length === 0) {
                alert("선택된 항목이 없습니다.");
                return;
            }

            // 선택된 항목 삭제 요청
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'deleteSelectedItems.do';
            
            selectedItems.forEach(item => {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'cartIds';
                input.value = item;
                form.appendChild(input);
            });

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
    <h1>장바구니</h1>
    <form action="updateCart.do" method="post">
        <table>
            <thead>
                <tr>
                    <th><input type="checkbox" onclick="toggleAllCheckboxes(this)" /></th>
                    <th>상품id</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartList}">
                    <tr>
                        <td><input type="checkbox" class="item-checkbox" value="${item.cartId}" /></td>
                        <td>${item.product.prodId}</td> <!-- 상품ID 출력 -->
                        <td>${item.product.prodName}</td> <!-- 상품명 출력 -->
                        <td>${item.product.prodPrice}</td> <!-- 상품 가격 출력 -->
                        <td><input type="number" name="quantity_${item.cartId}" value="${item.cartCount}" min="0" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        <button type="button" onclick="deleteSelectedItems()">선택 항목 삭제</button>
        <button type="submit">수량 업데이트</button>
    </form>
</body>
</html>
