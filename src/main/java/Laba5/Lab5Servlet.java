/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laba5;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mykola
 */
public class Lab5Servlet extends HttpServlet {
    
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String find = request.getParameter("find");
        String result = Laba5_Part1.FindWord(find);
        request.setAttribute("result", result);
        request.getRequestDispatcher("Laba5.jsp").forward(request, response);
    }
}
