* Project Code : malicious-liquid-1505
# Online Vegetable Sales Application



# REST API for Online Vegetable Sales Application With FrontEnd

* We have developed this REST API for an Online Vegetable Sales Application. 
* This API performs all the fundamental CRUD operations of any Online Vegetable Sales Application with user validation at every step.
* This project is developed by team of 3 Back-end Developers during project week in Masai School.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger

## Modules

* Login, Logout Module
* User Module
* Admin Module

## Features

* User and Admin authentication & validation with JWT Authentication.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid Authentication token can add/update/delete customer from main database
    * Admin can access the details of different User/Customers .
  
* User Features:
    * A user can register himself or herself on the platform.
    * He/She can check the Buy Vegetable ,Placed order Makes payment and Many more Functionality.
    


## Contributors


* [@Rohan Mahangare](https://github.com/MahangareRohan1203)
* [@Vivek Gupta](https://github.com/VivekGupta96)
* [@Niharika Pandey](https://github.com/niharikapandey94)



<p align="center">
  <img style="width:25%;" src="img" />
</p>



## ER-Diagram
![ER Diagram](https://github.com/MahangareRohan1203/malicious-liquid-1505/blob/main/ER_Table.png)

## Work-Flow
![Work Flow_1]()

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](#) file.
* Update the port number, username and password as per your local database config.

```
    server.port=8088

    spring.datasource.url=jdbc:mysql://localhost:3306/salesApp
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=SQLUsername(i.e=root)
    spring.datasource.password=SQLUserPassword(i.e=root)

```

## API Root Endpoint

`https://localhost:8088/`

`http://localhost:8088/swagger-ui/`


## API Module Endpoints

### Login Module

* `POST //api/adminlogin` : Admin can login with Email and password provided at the time of registation
<!--
### User Module




### Sample API Response for Admin Login

`POST   localhost:8088/adminlogin`

* Request Body

```
    {
        "user": "Admin@gmail.com",
        "password": "admin"
    }
```


---

### Swagger UI

---

<img src="#">

---

### Login Controller

---

<img src="#">

---

### Admin Controller

---

<img src="#">

---

### User Controller

---

<img src="#">

---




