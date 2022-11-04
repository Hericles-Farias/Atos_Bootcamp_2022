package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertOrderServlet extends HttpServlet{
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
        StringBuilder cssTag = new StringBuilder();
        cssTag.append("<style>");
        cssTag.append("@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap');");
        cssTag.append("*{padding: 0px;margin: 0px;}");
        cssTag.append("main{background: url(../images/bg.jpg);font-family: 'Abril Fatface', cursive;background-size: cover;display:flex;flex-direction: column;justify-content: center;align-items: center; width: 100vw;height: 100vh;color:white;}");
        cssTag.append(".container { display: flex;flex-direction: column;backdrop-filter: blur(20px);padding: 30px;border-radius: 20px;width: 500px;align-items: center;}");
        cssTag.append(".topo{width: 300px;}");
        cssTag.append(".topo > div {font-size: 25px;display: flex;align-items: center;justify-content: space-around;}");
        cssTag.append(".to_from{width: 350px;}");
        cssTag.append(".to_from > div{width: 250px;}");
        cssTag.append(".topo img {height: 80px;width: 80px;border-radius: 50%;}");
        cssTag.append(".info{width: 100%;background-color: black;padding: 5px;display: flex;align-items: center;justify-content: center;text-align: center;}");
        cssTag.append(".total  {width: 100%;display: flex;justify-content: right;}");
        cssTag.append(".total >  div{background-color: #2db83d;padding: 5px;}");
        cssTag.append(".fcc-btn{text-decoration: none;}");
        cssTag.append(".just{width: 100%;}");
        cssTag.append("label{font-size: 25px;color: white;}");
        cssTag.append("div{display:flex;margin-bottom: 10px;}");
        cssTag.append("div > div {width: 150px;}");
        cssTag.append(".caixa{border: none;border-radius: 5px;width: 350px;background-color: white;}");
        cssTag.append(".btn{width: 100%;display: flex;justify-content: space-around;}");
        cssTag.append(".btn-act{padding:1rem 2rem;border-radius: 5px;border:none;background-color: white;color:black;font-family: 'Abril Fatface', cursive;font-size: 16px;}");
        cssTag.append(".btn-act:hover{background-color: black;color:white;transition: 0.5s;transform: scale(1.2);}");
        cssTag.append("</style>");
        String cssTagString = cssTag.toString();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='pt-br'>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");  // escape the quote marks
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Status do Pedido</title>");
        out.println(cssTagString);
        out.println("</head>");
        out.println("<body>");
        try{
            String client=request.getParameter("client");
            Integer guests = Integer.parseInt(request.getParameter("guests"));
            Boolean dessert = Boolean.parseBoolean(request.getParameter("dessert"));
            Integer waiters = Integer.parseInt(request.getParameter("waiters"));
            Double vpc = Double.parseDouble(request.getParameter("valorPorConv"));
            Double vt = Double.parseDouble(request.getParameter("valorTotal"));
            BigDecimal valorPorConv = new BigDecimal(vpc, MathContext.DECIMAL64);
            BigDecimal valorTotal = new BigDecimal(vt, MathContext.DECIMAL64);
            //save it in the database
            dbOp dbk = new dbOp("buffetdb");
            dbk.Insert(client, guests, dessert, waiters, valorPorConv,valorTotal);

            out.println("<main>");
            out.println("<div class='container'>");
            out.println("<div class='info'>");
            out.println("<p>Pedido Confirmado!</p>");
            out.println("</div>");
            out.println("<div class='just'>");
            out.println("<div class='btn'>");
            out.println("<a class='btn-act fcc-btn'  href='../createNewOrder.jsp'>Novo Orçamento</a>");
            out.println("<a class='btn-act fcc-btn'  href='../'>Início</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");
            //INVI FORM PRA CHAMAR O SERVLET DO BANCO DE DADOS 

            out.println("</body></html>");
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
