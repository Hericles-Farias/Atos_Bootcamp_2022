<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styling/style.css">
    <title>My App</title>
</head>
<body>
    <h2>My app works. index.jsp</h2>

    <a href="./api">Try ./api</a>

    <form action="./api" method="POST">
        
        <label>Your Name Goes Here: </label><input type="text" name="user" autocomplete="off"><br><br>
        <input type="submit" value="Submit" name="USER"><br><br>
    
    </form>

    <div class="buttons">
        <form action="createNewOrder.jsp" method="POST">
            <input type="submit" value="Registrar Nova Ordem">
        </form>
    </div>

    <div class="buttons">
        <form action="seeOrders.jsp" method="POST">
            <input type="submit" value="Consultar Ordens">
        </form>
    </div>

    
</body>
</html>