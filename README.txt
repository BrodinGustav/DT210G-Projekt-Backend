#POST /auth/login

URL: http://localhost:8080/auth/login
Method: POST
Body (JSON):

{
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

http://localhost:8080/api/reviews
Kräver JWT

 Body (JSON):
{
  "bookId": "zyTCAlFPjgYC",
  "rating": 5,
  "comment": "Fantastisk bok!"
}

Hämta recensioner för en viss bok
GET /api/reviews/book/{bookId}
http://localhost:8080/api/reviews/book/zyTCAlFPjgYC
 Auth: ❌ Nej 

 Radera en recension
DELETE /api/reviews/{id}
http://localhost:8080/api/reviews/1
Kräver JWT

OBS ! Fixa PUT för recensioner ! Och kolla varför reviews nestlas sig !