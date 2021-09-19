# move-money

This project contains a basic demo bank server.

Two interfaces are exposed:

* `/account/{id}` to get the account balance and a list of transactions

* `/transaction/new?sender={sender_id}&recipient={recipient_id}&amount={amount}` to move money from one account to the other

THe server initializes 2 test account on start-up with id 0 and 1.


### How to run it

1. Clone this repo
2. Create a mysql database on port 3306
3. Create a new user for the database granting all privileges
4. Open the file `src/main/resources/application.properties`
5. Change property `spring.datasource.url`, replace `bank_demo`with the name of the database created
6. Change property `spring.datasource.username` to the username of the user you created
7. Change property `spring.datasource.password` to the password of the user you created
8. Run `src/main/java/bank/demo/DemoApplication.java` to start the server

