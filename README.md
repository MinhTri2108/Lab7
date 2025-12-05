# Product Management System

## Student Information
- **Name:** Trương Minh Trí
- **Student ID:** ITCSIU24090
- **Class:** WAD Lab 7

## Technologies Used
- Spring Boot 3.4.2
- Spring Data JPA
- MySQL 8.0
- Thymeleaf
- Maven
- Chart.js (Dashboard charts)

---

## Setup Instructions

### 1. Create the Database
```sql
CREATE DATABASE product_management;
```

### 2. Update your MySQL credentials  
Edit file: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the project
```bash
mvn spring-boot:run
```

### 4. Access the system
- Products: **http://localhost:8080/products**
- Dashboard: **http://localhost:8080/dashboard**

---

## Completed Features

### ✔ Product Module
- Create, Read, Update, Delete
- Quick Search (keyword)
- Advanced Search (name, category, price range)
- Sorting by:
  - Name
  - Category
  - Price
- Pagination
- Validation rules (required fields, price format, length checks)

### ✔ Dashboard Module
- Product statistics
- Low stock warnings
- Category distribution chart
- Price overview chart
---

## Project Structure

```text
src/main/java/com/example/product_management/
├── entity/          → Product.java (Model)
├── repository/      → ProductRepository.java (DAO)
├── service/         → ProductService.java (Business Logic)
└── controller/      
      ├── ProductController.java (CRUD + search)
      └── DashboardController.java (Charts, statistics)

src/main/resources/templates/
├── product-list.html     → Main list page
├── product-form.html     → Add/Edit form
└── dashboard.html        → Dashboard charts
```

---

## Database Schema (products table)

The database script is included in **database.sql** file

---

## Screenshots (Required)

Create a folder:

```
/screenshots
```

Take screenshots of the following pages:

- **Dashboard:** `/dashboard`
- **Product List:** `/products`
- **Add Product Form:** `/products/new`
- **Edit Product:** click "Edit" on any item
- **Advanced Search:** fill filters then Apply

Add them to `/screenshots` and reference here:

```
screenshots/list.png
screenshots/addProduct_form.png
screenshots/edit.png
screenshots/resultEdit.png
screenshots/search.png
```

---


## Notes
This project demonstrates:
- Full CRUD module
- Data validation
- SQL schema design
- MVC architecture
- Chart-based dashboard

