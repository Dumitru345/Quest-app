
### 1. Description

QuestApp is a web application that enables users to post quests and mark specific quests as "done". 
The app has two tables: one for users and one for quests. When a user registers, they start with 0 
experience points (exp), 10 tokens, and the "BRONZE" title. Every time a user marks a quest as "done", 
they receive 7 tokens and 10 exp. On the other hand, every time a user posts a quest, they lose 5 tokens. 
Users can earn new titles as they gain more experience points. Specifically, when a user reaches 20 exp, 
they receive the "SILVER" title, and when they reach 50 exp, they receive the "GOLD" title. 
Finally, when a user reaches 70 exp, they receive the "DIAMOND" title.

QuestApp supports the following features:

- Registering a new user
- Logging in
- Viewing a list of users and quests
- Creating quests
- Marking quests as done


---

### 2. Setup

To set up QuestApp, follow these steps:

- Create a schema in MySQL.
- Update the application.properties file with the MySQL URL, username, and password.
- Start the application.
- Access the application using a web browser at http://localhost:8080.

---

### 3. Technical details

__Technologies__

- web app (started with QuestApp class)

- main code is written in Java (version 17)
- it uses [MySQL](https://www.mysql.com/)
- it uses [SpringBoot](https://spring.io/projects/spring-boot), using this dependencies:
  - Thymeleaf (https://www.thymeleaf.org/)
  - Spring-Boot-Security
  - Spring-Boot-Data-Jpa
- external dependencies:
  - mysql-connector-java 
  - jquery 
  - bootstrap 


__Code structure__

- java code is organized in packages by its role, on layers:
  - entity - including the User class and Quest class
  - controller - Controller app
  - repository for Quest and User class
  - security
  - service
  - templates - with the html
  - questapp with some basics tests

---

### 4. Future plans

Other features which could be added in the future:

- search quest by some text;
- sorting options;
- delete/update quests;

