# spring-boot-quest-app
1. Description
   The purpose of this app is to allow a user to post a quest and the other users have the possibility to mark a specific quest as done. The app has 2 tables, 1 with users and 1 with quests.

Supported actions:

- register a new user
- login
- create quests
- mark quests as done
- sort the transactions by amount or date
- 
2. Setup
- Create a schema in MySQL and update in the application.properties the url, username and password accordingly.
- Once started, access it with a web browser at: http://localhost:8080

3. Technical details
   Technologies

- web app (started with QuestApp class)
- ain code is written in Java (version 17)
- it uses MySQL
- it uses SpringBoot
- it uses Thymeleaf

Code structure

java code is organized in packages by its role, on layers:
- entity- including the User class and Quest class
- controller - Controller app
- repository
- security
- service
templates 
- code related to the interface/presentation layer
- 
4. Future plans
   Other features which could be added in the future:

- search quest by some text;
- sorting options;
- delete/update quests;
