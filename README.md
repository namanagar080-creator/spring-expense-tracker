# Spring Expense Tracker

Small Spring Boot learning project for tracking expenses with a simple REST API and an in-memory H2 database.

## Project Layout

- `demo/` - Spring Boot application
- `test.http` - sample request file for local API testing

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- H2 in-memory database
- Maven Wrapper

## Run Locally

From the repository root:

```powershell
cd demo
.\mvnw.cmd spring-boot:run
```

The application starts on `http://localhost:8080`.

## API Endpoints

### Get all expenses

```http
GET /expenses
```

### Create an expense

```http
POST /expenses
Content-Type: application/json
```

Example body:

```json
{
  "title": "Books",
  "amount": 799.0
}
```

### Update an expense

```http
PUT /expenses/{id}
Content-Type: application/json
```

Example body:

```json
{
  "title": "Groceries",
  "amount": 950.0
}
```

The API returns the updated expense as JSON.

### Delete an expense

```http
DELETE /expenses/{id}
```

If the expense does not exist, the API returns `404 Not Found`.

## Sample Data

On startup, the app inserts two sample expenses when the database is empty:

- `Food` - `500`
- `Travel` - `1200`

## H2 Console

The H2 console is enabled for local debugging:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:expensedb`
- Username: `sa`
- Password: empty

## Test

```powershell
cd demo
.\mvnw.cmd test
```
