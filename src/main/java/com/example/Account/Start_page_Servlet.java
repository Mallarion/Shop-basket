package com.example.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Account-start")
public class Start_page_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookies= request.getCookies();
        if(cookies.length==0){
            RequestDispatcher dispatcher= request.getRequestDispatcher("/Account-create");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher= request.getRequestDispatcher("/Account-registration");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
