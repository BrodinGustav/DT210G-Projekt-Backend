#POST /auth/login

URL: http://localhost:8080/auth/login
Method: POST
Body (JSON):

{
  "email": "test@example.com",
  "username": "user",
  "password": "password"
}

Respons:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}

----------------------------

#BookController – Böcker

GET /api/books/search?query=java
URL: http://localhost:8080/api/books/search?query=java
Method: GET
Auth: ❌ Ingen token krävs

GET /api/books/{id}
Exempel:
http://localhost:8080/api/books/zyTCAlFPjgYC
Method: GET
Auth: ❌ Ingen token krävs

 GET /api/books/{bookId}/reviews
Exempel:
http://localhost:8080/api/books/zyTCAlFPjgYC/reviews
Method: GET
Auth: ❌ Ingen token krävs

----------------------------------

#/api/reviews
Alla endpoints börjar med http://localhost:8080/api/reviews.

POST /api/reviews

Skapa recension
http://localhost:8080/api/reviews
Kräver JWT

 Body (JSON):
{
  "bookId": "zyTCAlFPjgYC",
  "rating": 5,
  "comment": "Fantastisk bok!"
}

 Radera en recension
DELETE /api/reviews/{id}
http://localhost:8080/api/reviews/1
Kräver JWT

Uppdatera en recension
PUT /api/reviews/{id}
http://localhost:8080/api/reviews/1
Kräver JWT

 Body (JSON):
{
  "bookId": "zyTCAlFPjgYC",
  "rating": 1,
  "comment": "Hemsk bok!"
}

Hämta in alla recensioner för inloggad användare

GET /api/reviews/user
http://localhost:8080/api/reviews/user
Kräver JWT

[
  {
    "id": 102,
    "bookId": null,
    "rating": 5,
    "comment": "hjhjj",
    "userId": 1,
    "userEmail": "test@example.com"
  },
  {
    "id": 103,
    "bookId": null,
    "rating": 5,
    "comment": "asd",
    "userId": 1,
    "userEmail": "test@example.com"
  },
]