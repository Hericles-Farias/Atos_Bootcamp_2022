<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styling/styleSO.css">
    <title>Ver Ordens</title>
  </head>
  <body>
    <main>
      <form action="./api/seeOrders" method="post">
        <div>
          <div><label><span>Cliente</span></label></div>
          <input type="text" name="client" class="caixa"/>
        </div>
        <div class="btn">
          <input type="submit" class="btn-act" value="Buscar" />
          <input type="reset" class="btn-act"  value="Resetar" />
          <a class='btn-act fcc-btn' href='./'>Início</a>
        </div>

      </form>
    </main>
  </body>
</html>