# tourAgency
Tour agency website for purchasing tours. It was developed using Java Servlets, JSP, JDBC and Postgresql technologies

The website supports languages: English and Russian.

## Database schema

Postgresql database is used to store data.

</p>
<p align="center">
  <kbd> <img alt="Database" src="https://user-images.githubusercontent.com/96039201/206891479-8308f6d5-fd78-4c63-a5f4-ad50f94d43eb.jpg" width="100%" style="border-radius:10px"\></kbd> 
</p>
<p align="center">Database schema</p>

## Installation

1. Clone the project.
2. Create a new Postgresql database using database.sql from the data folder.
3. Change the database.properties file, located in the resources/properties/ folder, based on your database configurations.
4. Fill up any valid email service's user and password in the mail.properties file, located in the resources/properties/ folder, for password change functionality.
5. Build the project using maven.
6. Add new Tomcat 10.0.27 configuration to the project.
7. Run Tomcat and open http://localhost:8080/ on the browser.
8. Log in as admin, sign up as a new user. Admin default account is admin@admin.com, password - 1234.

## Usage as user
When signing up, you enter your email, password which must be 4 characters at least, name, surname, phone number(full number e.g. +998901234567).
After you sign in, you can choose and purchase tours. They are divided into three categories: excursion, rest, shopping. If you make three or more purchases, you can buy tours with discount. You can also view your purchased tours. When buying, you enter your card number which should be 12 numbers.

## Usage as admin
After you sign in, you can view users, orders and tours, and you can also delete them. Moreover, you can add tours by providing its name, type(3 types described above), description, discount(0 if no discount), date, price and image path.
