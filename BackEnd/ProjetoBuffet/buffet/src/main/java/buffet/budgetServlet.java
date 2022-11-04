package buffet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class budgetServlet extends HttpServlet{
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
        out.println("<title>Orçamento</title>");
        out.println(cssTagString);
        out.println("</head>");
        out.println("<body>");
        try{
            String client=request.getParameter("client");
            Integer guests = Integer.parseInt(request.getParameter("guests"));
            Boolean dessert = Boolean.parseBoolean(request.getParameter("dessert"));
            String dessertConverted;
            if (dessert){
                dessertConverted="Sim";
            }else{
                dessertConverted="Não";
            }
            out.println("<main>");
            out.println("<div class='container'>");
            out.println("<div class='topo'>");
            out.println("<div><img src='../images/logo.png' alt='MeuLogo'></div>");
            out.println("<div>Budget Info</div>");
            out.println("</div>");            
            calcPrice bud = new calcPrice(client, guests, dessert);
            bud.doCalc();
            out.println("<div class='to_from'>");
            out.println("<div>Para: "+client+"</div>");
            out.println("<div>De: Boca Livre Buffet</div>");
            out.println("</div>");
            out.println("<div class='info'>");
            out.println("<div>Convidados: <br>"+guests+"</div>");
            out.println("<div>Sobremesa: <br>"+dessertConverted+"</div>");
            out.println("<div>Garçons: <br>"+bud.getWaiters()+"</div>");
            out.println("<div>Valor por pessoa:<br>R$ "+String.format("%.2f",bud.getValorPorConv())+"</div>");
            out.println("</div>");
            out.println("<div class='total'>");
            out.println("<div>Valor Total:</div>");
            out.println("<div>R$ "+String.format("%.2f",bud.getValorTotal())+"</div>");
            out.println("</div>");
            out.println("<div class='btn'>");
            out.println("<form action=\"./insertNewOrder\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"client\" value= '"+bud.getName() +"'/>");
            out.println("<input type=\"hidden\" name=\"guests\" value= '"+bud.getGuests() +"'/>");
            out.println("<input type=\"hidden\" name=\"dessert\" value= '"+bud.getDessert() +"'/>");
            out.println("<input type=\"hidden\" name=\"waiters\" value= '"+bud.getWaiters() +"'/>");
            out.println("<input type=\"hidden\" name=\"valorPorConv\" value= '"+bud.getValorPorConv() +"'/>");
            out.println("<input type=\"hidden\" name=\"valorTotal\" value= '"+bud.getValorTotal() +"'/>");
            //out.println("<input type=\"submit\" value=\"submit\">");
            out.println("<input type='submit' class='btn-act' value='Confirmar'/>");
            out.println("</form>");
            out.println("</form>");
            out.println("<a class='btn-act fcc-btn'  href='../createNewOrder.jsp'>Novo Orçamento</a>");
            out.println("<a class='btn-act fcc-btn' href='../'>Início</a>");
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
