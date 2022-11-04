package com.br.mybuffet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myservlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset-UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out = response.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Hello all from Servlet</h1>");
            out.println("<h1>Servlet NewServlet at "+request.getContextPath()+"</h1>");
            String user = request.getParameter("user");
            out.println("<h2>Welcome "+user+"</h2>");
            out.println("<body>");
            out.println("</html>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            out.close();
        }

    }

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException{
            processRequest(request, response);
        }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException{
            processRequest(request, response);
        }
    
}
