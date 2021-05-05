package com.example.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Account-registration")
public class Registration_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<html>");
            printWriter.println("<h1> Account verification </h1>");
            printWriter.println("<div>");
            printWriter.println("If you want to check " + cookie.getName()+ " with number: "+cookie.getValue());
            printWriter.println("<form action=\"/Workspace\" target=\"_self\">\n" +
                    "   <button> Click here! </button>\n" +
                    "  </form>");
            printWriter.println("</div>");

            printWriter.println("<div>");
            printWriter.println("If you want to change your order_id, ");
            printWriter.println("<form action=\"/Account-create\" target=\"_self\">\n" +
                    "   <button> Click here! </button>\n" +
                    "  </form>");
            printWriter.println("</div>");

            printWriter.println("</html>");
            printWriter.println("</html>");
        }
            }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
