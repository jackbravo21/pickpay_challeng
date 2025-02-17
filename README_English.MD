Hello, I am Michael Douglas / JackBravo21.

This is the backend developer challenge from PicPay:
https://github.com/PicPay/picpay-desafio-backend

Notice: As of now, this challenge has been implemented in Java with an authorization server in NodeJS. It will also be implemented in NodeJS and NestJS. Unit tests have not been created yet, but they will be.

Requirements:
- Users must have a complete registration, including name, email, CPF, etc.
- Perform money transfers between users, merchants, and common users, where only common users can pay merchants.

Validation before the transfer:
- Check if the user has sufficient balance to make the payment/transfer.
- Ensure that the user is not a merchant.
- Verify an external API as an "authorization service." PicPay provides an external mock service with random authorization. Since it had many connection errors, I created a NodeJS server to handle validation and messages.
- The transfer operation must be a transaction. If any failure occurs, the amount is returned to the user's wallet—this is called RollBack in programming.
- Upon receiving the payment, the user must receive a message via a third-party service. PicPay provided a mock for this, but I preferred to implement it using my NodeJS server, which not only prevents inconsistencies but also allows you to see the message being received in the console log.

Notes:
All of the following features were additional implementations that were not required:
- I created a complete CRUD for user management, encrypting passwords and providing the necessary endpoints.
- I built an external NodeJS server for handling external validations. Just run "npm install" and start it with "nodemon", and it will be available at "http://localhost:9001".
- I implemented an ATM service, allowing users to deposit, withdraw, and transfer money as if they were using a real cash machine.
- The "Documentation" folder in the project contains the Insomnia JSON file, which includes API documentation.

Commands to run the Java server:
- mvn spring-boot:run

Commands to run the NodeJS validation server:
- npm install
- nodemon
