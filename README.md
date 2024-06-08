# SMIDA

Service for the Agency for the Development of the Infrastructure of the Stock Market of Ukraine
## Description

I have been working on a project to develop a RESTful service for managing company data and generating reports for the Agency for the Development of the Infrastructure of the Stock Market of Ukraine. The main steps of development involved creating a database structure, using PostgreSQL to store basic company information and reports, and MongoDB to store detailed report information. I used Liquibase for database migration. After completing the development, I packed the RESTful service along with all dependencies into a docker file and created a docker compose file. Finally, I uploaded this project to the Azure cloud environment.

You can view the documentation for the project at the following link: [Swagger Documentation](https://smida.azurewebsites.net/swagger-ui/index.html)

**Important Note:** GET endpoints are accessible to all users, while POST, PUT, DELETE endpoints are restricted to authenticated users only. To become an authenticated user, enter the **login (admin@gmail.com)** and **password (adminadmin)**.

## Usage

### Option 1: Running Locally with Docker Compose
1. Make sure you have Docker installed on your system.
2. Clone the repository: **git clone https://github.com/oleksapanchuk/smida**
3. Navigate to docker-compose directory: **cd docker-compose**
4. Run the following command to start the app along with all dependencies: **docker compose -f docker-compose.yaml up -d**
5. Once the containers are up and running, you can access the app at `http://localhost:8080`.

#### For testing purposes, you can use the following Postman collection:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://api.postman.com/collections/21898883-14251079-2194-47f1-9b55-59aa781d7ecd?access_key=PMAT-01HZTVSVTJQT8Y690VP7ZKG94C)

### Option 2: Using the Deployed App

You can also access the app via its deployed URL.

1. Visit [Deployed App URL](https://smida.azurewebsites.net).
2. Start exploring the app's features right away!

#### For testing purposes, you can use the folowing Postman collection:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://api.postman.com/collections/21898883-bf6b4d20-412c-4368-a002-9daceda4158f?access_key=PMAT-01HZTY099A2MEQWGFC9T7X7XJ1)
