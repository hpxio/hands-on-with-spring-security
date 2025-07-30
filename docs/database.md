# Setup Database

## Pull the MySQL Docker Image
 
- Open your terminal or command prompt and run the following command to pull the latest MySQL Docker image:
  > docker pull mysql

## Run the MySQL Container with Local Port Forwarding
 
- Start a MySQL container using the pulled image. Set the root password, create a database, and forward the local port to the MySQL container’s port 3306:
  > docker run --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=userdb -d mysql
  
  `Replace employeedb with the name of your desired database.`  

## Configure Your Spring Boot Application

- Update your Spring Boot application’s application.properties or application.yml file to use the MySQL container as the database.
  > spring.datasource.url=jdbc:mysql://localhost:3306/userdb  
  > spring.datasource.username=root  
  > spring.datasource.password=root
