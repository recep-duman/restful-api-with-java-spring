# Basic Restful Api with Java Spring Boot
This repository is a poc for a basic book store api. 
## Imported libraries
- springframework.boot    
    - web <br>
    - data-jpa<br>
    - validation<br>
    - devtools<br>
- mysql



# Prerequisite
You need to run below command to use mysql database.

docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=book-store-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=book-store --name mysql --publish 3306:3306 mysql:8-oracle