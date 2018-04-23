<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Borrowed Books</title>
</head>
<body>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/test"
        user="root" password="root"/>
     
    <sql:query var="listBorrowed" dataSource="${myDS}">
        SELECT * FROM Library;
    </sql:query>
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
            List of books</caption>
            <tr>
                <th>Student ID</th>
                <th>Book ID</th>
                <th>Issued By</th>
                <th>Issue Date</th>
            </tr>
            <c:forEach var="library" items="${listBorrowed.rows}">
                <tr>
                    <td><c:out value="${library.studentid}" /></td>
                    <td><c:out value="${library.bookid}" /></td>
                    <td><c:out value="${library.issuedby}" /></td>
                    <td><c:out value="${library.issueddate}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>