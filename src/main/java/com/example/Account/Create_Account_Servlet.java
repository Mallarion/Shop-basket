package com.example.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Account-create")
public class Create_Account_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie=new Cookie("order_id", "");
        cookie.setMaxAge(30*24*60*60);
        PrintWriter printWriter= response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<title> Registration page </title>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<h1> Registration page </h1>");
        printWriter.println("<div>\n" +
                "  <form name=\"publish\">\n" +
                "    <input type=\"text\" name=\"message\" placeholder=\"enter your order id\" size=\"40\"  />\n" +
                "    <input type=\"submit\" value=\"send\"/>\n" +
                "  </form>\n" +
                "</div>");

        String cooki= request.getParameter("message");
        cooki= cooki.trim();
        if(cooki.length()==36){
            cooki= cooki.replaceAll("<", "&lt").replaceAll(">", "&gt");
            cookie.setValue(cooki);
            response.addCookie(cookie);
        }else if(!cooki.equals("")){
            printWriter.println("<p> Please, enter correct data or click" +
                    " \"next\" to continue with your last data <p>");
        }
        printWriter.println("<form action=\"/Account-registration\" target=\"_self\">\n" +
                "   <button>Next step</button>\n" +
                "  </form>");
        printWriter.println("</body>");
        printWriter.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
