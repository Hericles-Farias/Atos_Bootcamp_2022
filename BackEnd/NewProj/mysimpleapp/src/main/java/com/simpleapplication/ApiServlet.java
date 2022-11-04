package com.simpleapplication;
import com.br.database.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("./api works. Try <a href='./api/getMessage'>./api/getMessage</a>");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Servlet Home Page</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            String user=request.getParameter("user");
            out.println("<h2> Welcome User "+user+"</h2>");
            //then write it to database
            dbOp dbk = new dbOp("heckjpa");
            try{
                dbk.Insert(user);
            }catch(Exception e){
                System.out.println("Error: "+e);
            }

            // if(request.getParameter("user")==""){
            //     /* Dispatching the request to server.java servlet */
            //     RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            //     // forward the request and response
            //     rd.include(request, response);
            //     out.println("<h3> Enter a valid name </h3>");
            //     /* ServletContext interface application */
            //     ServletContext sc=getServletConfig().getServletContext();
            //     /* These are available to any servlet or JSPs that are part of the web app. */
            //     out.println("<p>Visit my <a href='"+sc.getInitParameter("MyWebsite")+"'> Webpage</a> to checkout more!😃✌</p>");
            //     } else if(request.getParameter("USER")!=null){
            //         out.println("<h1>Hello all from servlet</h1>");
            //         String user=request.getParameter("user");
            //         out.println("<h2> Welcome User "+user+"</h2>");
            //         /* ServletConfig interface methods application */
            //         ServletConfig sc=getServletConfig();
            //         out.println("<div><p><a href='"+sc.getInitParameter("GithubLink")+"'>Project Link</a></p>");
            //         out.println("<p>Servlet context <b>" + request.getServletContext() + "</b></p>");
            //         out.println("<p>Servlet name <b>" + sc.getServletName() + "</b></p>");
            //         out.println("<p>Initializing parameter name collection <b>" + sc.getInitParameterNames() + "</b></p></div>");
            //         out.println("</body>");
            //         }   
            }
            
            catch(Exception e){
                System.out.println(e);
            }
            finally{
                out.close();
            }
        }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    
}
