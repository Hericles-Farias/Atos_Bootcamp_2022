package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class seeOrders extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("./api works.");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        StringBuilder cssTag = new StringBuilder();
        
        cssTag.append("<style>.styled-table {border-collapse: collapse;margin: 25px 0;font-size: 0.9em;font-family: sans-serif;min-width: 400px;box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);}");
        cssTag.append("@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap');");
        cssTag.append("*{padding: 0px;margin: 0px;}");
        cssTag.append(".container{display: flex;flex-direction: column;padding: 60px;font-family: 'Abril Fatface', cursive;}");
        cssTag.append(".styled-table {font-family: 'Abril Fatface', cursive;}");
        cssTag.append(".styled-table thead tr {background-color: #009879;color: white;text-align: left;font-size:20px;}");
        cssTag.append(".styled-table th,.styled-table td {padding: 12px 15px;}");
        cssTag.append(".styled-table tbody tr {border-bottom: 1px solid black;background-color: #dddddd;}");
        cssTag.append(".styled-table thead tr:hover {background-color: rgb(120, 207, 134);}");
        cssTag.append(".styled-table tr:hover {background-color: rgb(120, 207, 134);}");
        cssTag.append("h1{font-size: 40px;border-radius: 5px;color:white;}");
        cssTag.append("main{text-align: center;justify-content: center;font-family: 'Abril Fatface', cursive;font-size: 20px;background:url(../images/bg.jpg);background-size: cover;background-repeat: no-repeat;width: 100vw;height: 100vh;}");
        cssTag.append(".btn{display: flex;justify-content: space-around;}");
        cssTag.append(".btn-act{padding:1rem 2rem;border-radius: 5px;border:none;background-color: white;color:black;text-decoration: none;}");
        cssTag.append(".btn-act:hover{background-color: black;color:white;transition: 0.5s;transform: scale(1.2);}");
        // cssTag.append(".fcc-btn {background-color: black;color: white; padding: 15px 25px;text-decoration: none;}");
        // cssTag.append(".fcc-btn:hover {background-color: white;color:black;}");
        cssTag.append("</style>");
        String cssTagString = cssTag.toString();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='pt-br'>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");  // escape the quote marks
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Orçamento</title>");
        out.println(cssTagString);
        out.println("</head>");
        out.println("<body>");
        out.println("<main>");
        out.println("<div class='container'>");
        try{
            String client=request.getParameter("client");
            
            
            //save it in the database
            dbOp dbk = new dbOp("buffetdb");
            List<budget> budgets = (List<budget>) dbk.Consultar(client);
            if (budgets.isEmpty()){
                out.println("<h1>Nenhum Orçamento Encontrado para este Cliente!</h1>");
            }
            else
            {
            out.println("<h1>Buffet - Seu Orçamento</h1>");
            out.println("<table class='styled-table'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nome</th>");
            out.println("<th>Convidados</th>");
            out.println("<th>Garçons</th>");
            out.println("<th>Sobremesa</th>");
            out.println("<th>Valor Por Convidado</th>");
            out.println("<th>Valor Total</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            //for(int i=0;i<=budgets.size();i++)
            for (budget k: budgets)
            {
                //budget k = budgets.get(i);
                out.println("<tr>");
                String getid = "<td>"+ k.getid() + "</td>";
                out.println(getid);
                getid = "<td>"+ k.getNome() + "</td>";
                out.println(getid);
                getid = "<td>"+ k.getConvidados() + "</td>";
                out.println(getid);
                getid = "<td>"+ k.getGarcons() + "</td>";
                out.println(getid);
                Boolean ds = k.getSobremesa();
                String dsx;
                if (ds){
                    dsx="Sim";
                }else{
                    dsx="Não";
                }
                getid = "<td>"+ dsx + "</td>";
                
                out.println(getid);
                getid = "<td>R$ "+ String.format("%.2f",k.getvalorPorConv()) + "</td>";
                out.println(getid);
                getid = "<td>R$ "+ String.format("%.2f",k.getValorTotal()) + "</td>";
                out.println(getid);
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            out.println("<div class= 'btn'>");
            out.println("<a class='btn-act'  href='../seeOrders.jsp'>Nova Busca</a>");
            out.println("<a class='btn-act' href='../'>Início</a>");
            out.println("</div>");

            out.println("</div>");
            out.println("</main>");
            out.println("</body></html>");            
            
        }
            //INVI FORM PRA CHAMAR O SERVLET DO BANCO DE DADOS 
        out.println("<div class= 'btn'>");
        out.println("<a class='btn-act'  href='../seeOrders.jsp'>Nova Busca</a>");
        out.println("<a class='btn-act' href='../'>Início</a>");
        out.println("</div>");

        out.println("</div>");
        out.println("</main>");
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
