Olá, eu sou o Michael Douglas / JackBravo21.

Este é o desafio para programador backEnd da empresa PicPay:
https://github.com/PicPay/picpay-desafio-backend

Aviso: Até o presente momento esse desafio foi feito em "Java" com server autorizador em NodeJS, será feito em NodeJS e NestJS também. Ainda não foram criados os testes unitários, mas serão.

Requisitos:
- Os usuários devem ter um cadastro completo: nome, email, cpf, etc.
- Realizar transferências de valores/dinheiro entre usuários, logistas e comuns, onde só os comuns podem pagar os logistas.

Deve ocorrer validações antes da transaferência, como:
- Verificar se o usuário tem saldo para pagar/transaferir.
- Verificar se o usuário não é logista.
- Verificar uma api externa como "serviço autorizador", a PicPay disponibiliza um "Mock Externo" com autorização random, como dava muito erro de conexão, criei um server em NodeJS para fazer a validação e mensagens.
- A operação de transaferência deve ser uma transação, caso dê alguma falha, retorna o valor para a carteira do usuário, chamamos isso de RollBack na programação.
- No recebimento do pagamento, o usuário deve receber uma mensagem através de serviços de terceiros, a PicPay disponibilizou um Mock novamente, mas eu preferi fazer com meu server em NodeJs, porque além de evitar incosistências, ainda você vê a mensagem sendo recebida no console log.

Notas, tudo isso que eu criei a mais, não foi solicitado:
- Criei um CRUD completo do usuário, criptografando a senha, e seus EndPoints.
- Criei um server externo com NodeJs para atender as validações externas, basta usar "npm install" e rodar com "nodemon", estará disponivel no "http://localhost:9001";
- Criei um serviço de ATM (caixa eletronico), onde é possivel simular o depósito e saque do usuário na boca do caixa, além de transferir dinheiro entre eles.
- Na pasta "Documentation" do projeto tem o json/insomnia do projeto, com a documentação;

Comandos para rodar o server java:
- mvn spring-boot:run

Comandos para rodar o server NodeJs de checagem:
- npm install
- nodemon
