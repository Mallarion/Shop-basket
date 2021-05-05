package com.example.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Workspace")
public class Basket_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter= response.getWriter();
        Cookie [] cookies= request.getCookies();
        for (Cookie cookie: cookies) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Products_From_Task",
                    "postgres", "postgres");
            Statement statement= connection.createStatement();
            Statement statement2= connection.createStatement();
            Statement statement3= connection.createStatement();
            int count=1;

            String SQL= "SELECT NAME FROM ORDER_ITEMS, PRODUCTS WHERE PRODUCT_ID= PRODUCTS.ID " +
                    "AND ORDER_ID = '"+ cookie.getValue() +"' ORDER BY NAME;";
            ResultSet resultSet1= statement.executeQuery(SQL);

            String SQL4= "SELECT   (PRODUCTS.PRICE_PER_UNIT*ORDER_ITEMS.QUANTITY) AS TOTAL_PRICE" +
                    " FROM ORDER_ITEMS, PRODUCTS\n" +
                    "WHERE ORDER_ID= '"+ cookie.getValue() +"' AND PRODUCT_ID= PRODUCTS.ID\n" +
                    "GROUP BY  ORDER_ITEMS.QUANTITY, PRODUCT_ID, PRODUCTS.PRICE_PER_UNIT, products.name" +
                    " ORDER BY NAME;";
            ResultSet resultSet3= statement3.executeQuery(SQL4);

            String SQL3= "SELECT NAME, QUANTITY FROM ORDER_ITEMS, PRODUCTS" +
                    " WHERE PRODUCT_ID= PRODUCTS.ID AND ORDER_ID = '"+ cookie.getValue() +"'" +
                    " ORDER BY NAME";
            ResultSet resultSet2= statement2.executeQuery(SQL3);

            printWriter.println("<html>");
            printWriter.println("<body>");
            printWriter.println("<h2> Your basket:</h2>");

            printWriter.println("<table border=\"2\">");
            printWriter.println("<tr>");
            printWriter.println("<th> &#8470 </th>");
            printWriter.println("<th> Products </th>");
            printWriter.println("<th> Quantity </th>");
            printWriter.println("<th> Total Price </th>");
            printWriter.println("</tr>");

            while(resultSet1.next()) {
                printWriter.println("<tr>");
                printWriter.println("<td>");
                printWriter.println(count);
                count++;
                printWriter.println("</td>");
                printWriter.println("<td>");
                printWriter.println(resultSet1.getString("name"));
                printWriter.println("</td>");
                while (resultSet2.next()){
                    printWriter.println("<td>");
                    printWriter.println(resultSet2.getString("quantity"));
                    printWriter.println("</td>");

                    while (resultSet3.next()){
                        printWriter.println("<td>");
                        printWriter.println(resultSet3.getString("TOTAL_PRICE"));
                        printWriter.println("</td>");
                        break;
                    }
                    break;
                }
                printWriter.println("</tr>");
            }

            printWriter.println("</table>");

            printWriter.println("<p>");
            printWriter.println("<form action=\"/Account-registration\" target=\"_self\">\n" +
                    "   <button> back </button>\n" +
                    "  </form>");
            printWriter.println("</p>");
            printWriter.println("</body>");
            printWriter.println("</html>");

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
