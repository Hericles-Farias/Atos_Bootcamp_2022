Uma empresa de buffets necessita de um sistema para realizar o orÃ§amento de buffets para festas, 
que varia de acordo com o nÃºmero de convidados, com o tipo de prato a ser servido, entre outras informaÃ§Ãµes. 
Para isso, a empresa lhe contratou para desenvolver esse software. 

Os requisitos sÃ£o:

    - O usuÃ¡rio informa no sistema, por meio de um formulÃ¡rio, o nome do cliente, a quantidade de convidados 
    da festa e se serÃ¡ servido sobremesa ou nÃ£o. Assim, o sistema deverÃ¡ retornar o valor total que o 
    cliente deverÃ¡ pagar pelo buffet, obedecendo as seguintes regras:
   
    - O valor total Ã© = valor por convidado + taxa de sobremesa + taxa de garÃ§ons; o valor por convidado Ã© igual a quantidade de convidados * o valor do prato quente, cujo valor Ã© de R$22,90 por prato.
    - Caso haja sobremesa, a taxa de sobremesa Ã© 10% do valor total por convidado;
    - A taxa de garÃ§ons depende da quantidade de garÃ§ons que serÃ£o contratados, que dependerÃ¡ da quantidade de convidados. Ã‰ necessÃ¡rio 1 garÃ§om para cada 15 convidados, e cada garÃ§om recebe R$250,00.
    O sistema deverÃ¡ calcular a quantidade de garÃ§ons de acordo com a quantidade de convidados inserida pelo usuÃ¡rio.
    
    - No momento de exibir o resultado, o sistema deverÃ¡ apresentar as informaÃ§Ãµes em um 
    layout de uma proposta comercial, contendo os dados da empresa (inventar informaÃ§Ãµes fictÃ­cias, 
    juntamente de uma imagem para a empresa), os dados do cliente, bem como os valores da 
    proposta (valor total por convidado, taxa de sobremesa, quantidade de garÃ§ons e taxa de garÃ§ons);
    
    - A proposta comercial deve ser salva no banco de dados.
   
    - DeverÃ¡ ser implementada tambÃ©m uma pÃ¡gina de consulta, no qual o usuÃ¡rio informa o 
    nome do cliente e entÃ£o as informaÃ§Ãµes da proposta comercial a respeito dele devem ser apresentadas na tela.
    
    - DeverÃ¡ ser feita, tambÃ©m, uma pÃ¡gina inicial com links para as pÃ¡ginas de cadastro e consulta.


Utilizar Servlet ou JSF.
Utilizar banco de dados. 