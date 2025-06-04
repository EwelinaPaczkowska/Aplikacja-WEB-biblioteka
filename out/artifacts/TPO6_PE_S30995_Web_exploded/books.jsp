<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Szukaj książek</title>
</head>
<body>

<%@ page import="java.util.List" %>
<%@ page import="pl.utp.bookapp.model.Book" %>
<%
  List<Book> books = (List<Book>) request.getAttribute("books");
%>
<html>
  <body>
    <h2>Lista książek:</h2>
    <ul>
    <% for (Book b : books) { %>
      <li><b><%= b.getTitle() %></b> - <%= b.getAuthor() %></li>
    <% } %>
    </ul>
    <a href="index.jsp">Powrót</a>
  </body>
</html>
