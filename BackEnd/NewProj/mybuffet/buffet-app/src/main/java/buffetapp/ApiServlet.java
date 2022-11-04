package buffetapp;

import com.br.database.*;
import java.io.IOException;
import java.io.PrintWriter;
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
        // String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        // out.println("<head><title>Servlet Home Page</title>"+cssTag+"</head>");
        out.println("<head><title>Servlet Home Page</title></head>");
        out.println("<body>");
        try{
            String user=request.getParameter("user");
            out.println("<h2> Welcome User "+user+"</h2>");
            //then write it to database
            
            //dbOp dbk = new dbOp("heckjpa");
            //dbk.Insert(user);
            db db = new db();
            db.put_data(user);
            System.out.println("\nInseriu com sucesso\n");
            out.println("<p>Inseriu com sucesso</p>");

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

