# Currency Converter

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

## How To Use The Application  

**1** - **Create user account:**  
**1.1** - Send a POST request to endpoint "/users" with following body:  
**1.1.1** - email (mandatory, unique).  
**1.1.2** - password (mandatory, have between 8 and 24 characters).  
**1.2** - Example:

**2** - **Get access token:**  
**2.1** - Send a POST request to endpoint "/auth" with following body:  
**2.1.1** - email (mandatory).  
**3.1.2** - password (mandatory).  
**2.2** - Example:

**3** - **Convert currencies:**  
**3.1** - Send a POST request to endpoint "/convertions" with following body and add the your token:  
**3.1.1** - originCurrency (mandatory, 3 characters).  
**3.1.2** - originValue (mandatory, a decimal number).  
**3.1.3** - destinyCurrency (mandatory, 3 characters).  
**3.2** - Example:

**4** - **Get my past conventions:**  
**4.1** - Send a GET request to endpoint "/convertions" with your token.  
**4.2** - Example:  

## How was developing this application (Portuguese) (Não concluido)

Nesse textinho pretendo expor o detalhes do desenvolvimento dessa aplicação.

**O planejameno do projeto:**

Inicialmente eu estava pensando em usar Spring Webflux e fazer a conexão com o banco de dados usando R2DBC e Spring data R2DBC, mas apareceram alguns empecilhos, como eu optei em programar a classe “Conversion” com um atributo da classe “User” ao invés de usar um atributo que guarde apenas o Id do usuário, isso é um dos princípios básicos da orientação a objetos, o problema é que como ainda não existe nenhum ORM que use R2DBC, e como Spring Data R2DBC reduz o SQL, eu fiquei um pouco confuso em como eu iria fazer o mapeamento objeto relacional, lendo a documentação do Spring Data eu conclui que teria alguma complexidade desnecessária, então decidi não usar mais o R2DBC, pensei em usar o MongoDB já que eu já havia feito um projeto com Spring Webflux e MongoDB, mas MongoDB não se encacharia muito bem nesse contesto, dai mudei o projeto para Spring Boot Serverlet e usei Spring Data JPA.

**A camada de modelo:**

Então na camada de modelo não tem muito o que falar, são 3 classes. A primeira classe é a classe “User” essa classe como o nome já diz representa um usuário, ela tem quatro atributos, id email, password e authoritys, este último serve para guardar o grau de acesso ao sistema que esse usuário tem, nesse contexto nós não o usamos, mas o Spring Security requer esse atributo, até daria para contornar isso com uma “gabiarra”, mas não vale a pena porque se no futuro precisar implementar isso, já tá ai certinho.

As outras duas classes são a classe “Authority”, que eu já expliquei para que serve e a classe “Conversion” que representa uma conversão de moeda que já foi efetuada para ser persistida no banco de dados. Essa classe tem dentre vários atributos os atributos originCur e destinyCur que são do tipo String, mas poderia ser de um enum, só que eu optei em fazer com String para não causar uma limitação que eu vou explicar mais adiante nesse texto.

**A camada de controller:**

Essa aplicação tem quatro controllers: “AuthenticationController” possui o endpoint para fazer a autenticação do usuário e enviar uma resposta com o token. “UserController” possui apenas um endpoint para cadastro de usuário. “ConversionController” tem o endpoint para obter as conversões anteriores feita pelo usuário autenticado pelo token, também tem o endpoint para fazer a conversão, esse endpoint é um onde a minha intuição foi contra a teoria, no caso a minha intuição diz para usar o método http GET porque o client passaria as informações para obter o valor convertido, no caso uma operação idempotente que só obtêm informações, mas a teoria diz para usar POST porque na verdade o que está acontecendo aqui é exatamente o oposto, é uma operação que vai gravar informações novas no banco dados toda vez que receber uma requisição para fazer uma conversão, tornando assim esse endpoint não idempotente, portanto o correto é usar o POST. O último controller é um pouco diferente ele não tem endpoints, ele serve para dar respostas alternativas caso de alguma exceção na aplicação.

**A camada de serviço:**

Nessa camada temos o “AuthenticationService” onde possui o método que faz a autenticação de usuário, “TokenService” que possui a lógica de criação e descriptografia do token.

Temos a interface “UserService”, as classes “DevUserService”, “ProdUserService”, e a classe abstrata “AbstractUserService”, temos tantas classes assim para poder controlar o os profiles “dev” e “prod” da aplicação, a interface “UserService” é quem tem todos os métodos de usuário da camada de serviço, obvio que eles não estão implementados, é essa interface que é referenciada para a injeção de dependência, a classe abstrata “AbstractUserService” é a classe que tem as implementações em comum para os profiles do sistema, já as classes “DevUserService” e “ProdUserService” possuem implementações para seus respetivos profiles, que no nosso caso é só o método “getLoggedUser” que para o profile de produção retorna o usuário dono do token usado para fazer a requisição, e no profile de desenvolvimento retorna sempre o usuário de id=1, já que em ambiente de desenvolvimento não é necessário passar o token e se não tivesse esse tratamento, daria uma exceção.

A Aplicação possui também a classe “ConversionService” com os metodos “getRates”, “getMyConvetions” e “convert”.

O método “getRates” tem unicamente a responsabilidade de obter a cotação das moedas, então ele pode receber quantas moedas for preciso, no caso ele está recebendo como parâmetro um Array de String, no formato de 3 pontos (String…) isso possibilita passar como parâmetro quantos String o programador quiser sem precisar agrupá-lo em um Array, o próprio Java faz isso para nos, isso deixa o código mais sucinto, esse método podia usar um Set de String também, mas como eu disse antes de chamá-lo teríamos que agrupar num set nesse caso, poderíamos criar uma sobrecarga desse método assim. E como eu disse a única responsabilidade desse método é obter as cotações, deixando ele com essa responsabilidade poderíamos reaproveitar ele em outras operações com moedas no futuro.

Nessa parte do texto é mais propício para explicar porque eu optei em usar as moedas em formato de String, e não em formato de enum, basicamente para ter o mínimo de acoplamento com a exchangeratesapi, por exemplo se a exchangeratesapi adicionar ou remover moedas possíveis de obter a cotação nós teríamos que alterar esse enum, no caso com String não tem essa necessidade, nos entregamos a responsabilidade de validar se essa moeda existe ou se não está disponível a exchangeratesapi, no caso se a moeda não existir a exchangeratesapi apenas ignora essa moeda e retorna a cotação das que estiverem disponíveis

Nesse método eu também uso uma classe incomum, que a classe AtomicReference, como em expressões lambda as variáveis “externas” são final, nós não podemos alterá-las, mas podemos contornar isso usando essa classe.

O próximo método é o “getMyConvetions” esse método chama um método no repository para buscar as conversões passadas no banco de dados.

O método convert é onde acontece a conversão das moedas, aqui a única coisa a comentar que é bem incomum e normalmente não é recomendado é o uso de um try/catch em um “NullPointerException”, nesse caso eu estou fazendo isso para não precisar fazer um if, assim o código ficou mais sucinto, ele recebe essa exceção e joga uma “CurrenciesDontExistOrArentAvailableException” para ser tratada no adviceController que eu citei anteriormente.

**Data transfer object:**

Para complementar a camada de controllers e service na aplicação há oito DTOs, seis deles estão em records: “AuthForm” usado como corpo da requisição de autenticação, “AuthView” usado como resposta dessa requisição, “ExpetionView” usado como resposta do “AdviceController”, “UserForm” usado como corpo da requisição de cadastro de usuário, “RatesResponse” usado como resposta da exchangeratesapi, nesse record há um Map com chave String que representa o nome da moeda e o valor com tipo BigDecimal, eu optei pelo Map para que, como eu disse anteriormente, não limitar a api que estamos usando.

Os outros dois estão em classe que são “ConversionView” é usado como corpo da resposta da requisição de conversão, “UserView” como corpo da resposta do cadastro de usuário, ambos são classes porque o construtor recebe apenas uma instância de seus respectivos modelos. 



