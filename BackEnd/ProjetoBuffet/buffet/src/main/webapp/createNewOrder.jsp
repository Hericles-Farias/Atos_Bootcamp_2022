<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styling/stylecno.css">
    <title>Nova Ordem</title>
  </head>
  <body>
    <main>
      <form action="./api/Budget" method="post">
        <div>
          <div><label><span>Cliente</span></label></div>
          <input type="text" name="client" class="caixa"/>
        </div>

        <div>
          <div><label>Numero de Convidados</label></div>
          <input type="text" name="guests" class="caixa" />
        </div>

        <div class="radios">
          
          <input type="radio" id="no_dessert" name="dessert" value="false" />
          <label for="no_dessert">Sem Sobremesa.</label>

          <input type="radio" id="with_dessert" name="dessert" value="true" checked />
          <label for="with_dessert">Com Sobremesa.</label>

        </div>

        <div class="btn">
            <input type="submit" class="btn-act" value="Solicitar Orçamento" />
            <input type="reset" class="btn-act" value="Resetar" />
            <a class='btn-act fcc-btn' href='./'>Início</a>
          </div>

      </form>
    </main>
  </body>
</html>

