# Currency Converter

This API is available at https://curconverter.herokuapp.com/

## How To Run Application:

* This application has two possible profiles, one for development and testing environment and other for production environment.
* This application runs with an embedded H2 database.

**1** - **Running in development/testing environment:**  
**1.1** - Access the application-dev.properties file and replace the phrase "Your key here" with your exchangeratesapi api key.  
**1.2** - Run the main method of the ConverterApplication.java file, with the following environment variable:  
**1.2.1** - spring.profiles.active=dev;  

**2** - **Running in production environment:**  
**2.1** - Run the main method of the ConverterApplication.java file, with the following environment variables:   
**2.1.1** - spring.profiles.active=prod;  
**2.1.2** - datasource.url="*url you choose to host the H2 database*";  
**2.1.3** - datasource.user="*username to access the H2 database*";  
**2.1.4** - datasource.password="*password to access the H2 databas*e";  
**2.1.5** - jwt.secret="*random security string*";  
**2.1.6** - api.key="*your exchangeratesapi api key*";  
**2.1.7** - PORT="*port you want to run*";  

## How To Use The Application  

**1** - **Create user account:**  
**1.1** - Send a POST request to endpoint "/users" with following body:  
**1.1.1** - email (mandatory, unique).  
**1.1.2** - password (mandatory, have between 8 and 24 characters).  
**1.2** - Example:

![postman com novo usuario](https://github.com/LeonardoCavazzola/currency-converter/blob/main/src/main/resources/images/new%20user2.PNG)

**2** - **Get access token:**  
**2.1** - Send a POST request to endpoint "/auth" with following body:  
**2.1.1** - email (mandatory).  
**3.1.2** - password (mandatory).  
**2.2** - Example:

![obtendo token no postman](https://github.com/LeonardoCavazzola/currency-converter/blob/main/src/main/resources/images/Auth.PNG)

**3** - **Convert currencies:**  
**3.1** - Send a POST request to endpoint "/convertions" with following body and add the your token:  
**3.1.1** - originCurrency (mandatory, 3 characters).  
**3.1.2** - originValue (mandatory, a decimal number).  
**3.1.3** - destinyCurrency (mandatory, 3 characters).  
**3.2** - Example:

![fazendo uma conver????o no postman](https://github.com/LeonardoCavazzola/currency-converter/blob/main/src/main/resources/images/covertion.PNG)

**4** - **Get my past conventions:**  
**4.1** - Send a GET request to endpoint "/convertions" with your token.  
**4.2** - Example:  

![obtendo historico no postman](https://github.com/LeonardoCavazzola/currency-converter/blob/main/src/main/resources/images/allcerrto.PNG)

## How was developing this application (Portuguese)

Nesse textinho pretendo expor o detalhes do desenvolvimento dessa aplica????o.

**O planejameno do projeto:**

Inicialmente eu estava pensando em usar Spring Webflux e fazer a conex??o com o banco de dados usando R2DBC e Spring data R2DBC, mas apareceram alguns empecilhos, como eu optei em programar a classe ???Conversion??? com um atributo da classe ???User??? ao inv??s de usar um atributo que guarde apenas o Id do usu??rio, isso ?? um dos princ??pios b??sicos da orienta????o a objetos, o problema ?? que como ainda n??o existe nenhum ORM que use R2DBC, e como Spring Data R2DBC reduz o SQL, eu fiquei um pouco confuso em como eu iria fazer o mapeamento objeto relacional, lendo a documenta????o do Spring Data eu conclui que teria alguma complexidade desnecess??ria, ent??o decidi n??o usar mais o R2DBC, pensei em usar o MongoDB j?? que eu j?? havia feito um projeto com Spring Webflux e MongoDB, mas MongoDB n??o se encacharia muito bem nesse contesto, dai mudei o projeto para Spring Boot Serverlet e usei Spring Data JPA.

Acho que algumas coisas importantes a ressaltar ?? que eu optei por adicionar o Spring Security, como forma de obter o ID do usuario, pois obter o ID do usuario atrav??s do header ou mesmo do corpo de uma requisi????o n??o ?? uma coisa normal por quest??o de seguran??a, mesmo que seja s?? pra uma aplica????o de demonstra????o acho melhor fazer o mais pr??ximo da realidade, outra coisa importante ?? que a api que consumimos tem um bug, as vezes quando fazemos uma requisi????o com a key de acesso certinha, ela retorna uma resposta dizendo que n??o temos acesso, nesse caso ?? s?? repetir a requisi????o. 

**A camada de modelo:**

Ent??o na camada de modelo n??o tem muito o que falar, s??o 3 classes. A primeira classe ?? a classe ???User??? essa classe como o nome j?? diz representa um usu??rio, ela tem quatro atributos, id email, password e authoritys, este ??ltimo serve para guardar o grau de acesso ao sistema que esse usu??rio tem, nesse contexto n??s n??o o usamos, mas o Spring Security requer esse atributo, at?? daria para contornar isso com uma ???gabiarra???, mas n??o vale a pena porque se no futuro precisar implementar isso, j?? t?? ai certinho.

As outras duas classes s??o a classe ???Authority???, que eu j?? expliquei para que serve e a classe ???Conversion??? que representa uma convers??o de moeda que j?? foi efetuada para ser persistida no banco de dados. Essa classe tem dentre v??rios atributos os atributos originCur e destinyCur que s??o do tipo String, mas poderia ser de um enum, s?? que eu optei em fazer com String para n??o causar uma limita????o que eu vou explicar mais adiante nesse texto.

**A camada de controller:**

Essa aplica????o tem quatro controllers: ???AuthenticationController??? possui o endpoint para fazer a autentica????o do usu??rio e enviar uma resposta com o token. ???UserController??? possui apenas um endpoint para cadastro de usu??rio. ???ConversionController??? tem o endpoint para obter as convers??es anteriores feita pelo usu??rio autenticado pelo token, tamb??m tem o endpoint para fazer a convers??o, esse endpoint ?? um onde a minha intui????o foi contra a teoria, no caso a minha intui????o dizia para usar o m??todo http GET porque o client passaria as informa????es para obter o valor convertido, no caso uma opera????o idempotente que s?? obt??m informa????es, mas a teoria diz para usar POST porque na verdade o que est?? acontecendo aqui ?? exatamente o oposto, ?? uma opera????o que vai gravar informa????es novas no banco dados toda vez que receber uma requisi????o para fazer uma convers??o, tornando assim esse endpoint n??o idempotente, portanto o correto ?? usar o POST. O ??ltimo controller ?? um pouco diferente ele n??o tem endpoints, ele serve para dar respostas alternativas caso haja alguma exce????o na aplica????o.

**A camada de servi??o:**

Nessa camada temos o ???AuthenticationService??? onde possui o m??todo que faz a autentica????o de usu??rio, ???TokenService??? que possui a l??gica de cria????o e descriptografia do token.

Temos a interface ???UserService???, as classes ???DevUserService???, ???ProdUserService???, e a classe abstrata ???AbstractUserService???, temos tantas classes assim para poder controlar o os profiles ???dev??? e ???prod??? da aplica????o, a interface ???UserService??? ?? quem tem todos os m??todos de usu??rio da camada de servi??o, obvio que eles n??o est??o implementados, ?? essa interface que ?? referenciada para a inje????o de depend??ncia, a classe abstrata ???AbstractUserService??? ?? a classe que tem as implementa????es em comum para os profiles do sistema, j?? as classes ???DevUserService??? e ???ProdUserService??? possuem implementa????es para seus respetivos profiles, que no nosso caso ?? s?? o m??todo ???getLoggedUser??? que para o profile de produ????o retorna o usu??rio dono do token usado para fazer a requisi????o, e no profile de desenvolvimento retorna sempre o usu??rio de id=1, j?? que em ambiente de desenvolvimento n??o ?? necess??rio passar o token e se n??o tivesse esse tratamento, daria uma exce????o.

A Aplica????o possui tamb??m a classe ???ConversionService??? com os metodos ???getRates???, ???getMyConvetions??? e ???convert???.

O m??todo ???getRates??? tem unicamente a responsabilidade de obter a cota????o das moedas, ent??o ele pode receber quantas moedas for preciso, no caso ele est?? recebendo como par??metro um Array de String, no formato de 3 pontos (String???) isso possibilita passar como par??metro quantas Strings o programador quiser sem precisar agrup??-las em um Array, o pr??prio Java faz isso para nos, isso deixa o c??digo mais sucinto, esse m??todo podia usar um Set de String tamb??m, mas como eu disse antes de cham??-lo ter??amos que agrupar num set nesse caso, poder??amos criar uma sobrecarga desse m??todo assim. E como eu disse a ??nica responsabilidade desse m??todo ?? obter as cota????es, deixando ele com essa responsabilidade poder??amos reaproveitar ele em outras opera????es com moedas no futuro.

Nessa parte do texto ?? mais prop??cio para explicar porque eu optei em usar as moedas em formato de String, e n??o em formato de enum, basicamente para ter o m??nimo de acoplamento com a exchangeratesapi, por exemplo se a exchangeratesapi adicionar ou remover moedas poss??veis de obter a cota????o n??s ter??amos que alterar esse enum, no caso com String n??o tem essa necessidade, nos entregamos a responsabilidade de validar se essa moeda existe ou se n??o est?? dispon??vel a exchangeratesapi, no caso se a moeda n??o existir a exchangeratesapi apenas ignora essa moeda e retorna a cota????o das que estiverem dispon??veis

Nesse m??todo eu tamb??m uso uma classe incomum, que a classe AtomicReference, como em express??es lambda as vari??veis ???externas??? s??o final, n??s n??o podemos alter??-las, mas podemos contornar isso usando essa classe.

O pr??ximo m??todo ?? o ???getMyConvetions??? esse m??todo chama um m??todo no repository para buscar as convers??es passadas no banco de dados.

O m??todo convert ?? onde acontece a convers??o das moedas, aqui a ??nica coisa a comentar que ?? bem incomum e normalmente n??o ?? recomendado ?? o uso de um try/catch em um ???NullPointerException???, nesse caso eu estou fazendo isso para n??o precisar fazer um if, assim o c??digo ficou mais sucinto, ele recebe essa exce????o e joga uma ???CurrenciesDontExistOrArentAvailableException??? para ser tratada no adviceController que eu citei anteriormente.

**Data transfer object:**

Para complementar a camada de controllers e service na aplica????o h?? oito DTOs, seis deles est??o em records: ???AuthForm??? usado como corpo da requisi????o de autentica????o, ???AuthView??? usado como resposta dessa requisi????o, ???ExpetionView??? usado como resposta do ???AdviceController???, ???UserForm??? usado como corpo da requisi????o de cadastro de usu??rio, ???RatesResponse??? usado como resposta da exchangeratesapi, nesse record h?? um Map com chave String que representa o nome da moeda e o valor com tipo BigDecimal, eu optei pelo Map para que, como eu disse anteriormente, n??o limitar a api que estamos usando.

Os outros dois est??o em classe que s??o ???ConversionView??? ?? usado como corpo da resposta da requisi????o de convers??o, ???UserView??? como corpo da resposta do cadastro de usu??rio, ambos s??o classes porque o construtor recebe apenas uma inst??ncia de seus respectivos modelos. 



