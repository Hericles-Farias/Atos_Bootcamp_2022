<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./styling/style.css" />
    <title>Boca Livre Buffet</title>
  </head>
  <body>
    <main>
      <h1>Boca Livre Buffet</h1>

      
      <div class="buttons">
        <form action="createNewOrder.jsp">
          <input type="submit" value="Registrar Nova Ordem" />
        </form>

        <form action="seeOrders.jsp">
          <input type="submit" value="Consultar Ordens" />
        </form>
      </div>

      <!-- <form action="./api" method="POST">
        <label>Your Name Goes Here: </label
        ><input type="text" name="user" autocomplete="off" /><br /><br />
        <input type="submit" value="Submit" /><br /><br />
      </form>

      <form action="./api" method="POST">
        <label>Your Name Goes Here: </label
        ><input type="text" name="user" autocomplete="off" /><br /><br />
        <input type="submit" value="Submit" /><br /><br />
      </form> -->
    </main>
  </body>
</html>
