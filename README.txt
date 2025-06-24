## Inlogg 


{
  "username": "user",
  "password": "password"
}

#Token används vid åtkomst till skyddade endpoints

#GET
http://localhost:8080/api/books/search?query=Harry Potter (Du kan byta ut värdet på query till vilken sökterm du vill)


Böcker
Hämta böcker via sökning:
GET /api/books/search?query=valfriSökterm
Exempel: /api/books/search?query=Harry+Potter

Hämta en specifik bok:
GET /api/books/{bookId}
Exempel: /api/books/zyTCAlFPjgYC (Google Books ID)

Recensioner
Hämta recensioner för en specifik bok:
http://localhost:8080/api/reviews/book/{bookId}

Skapa en ny recension (autentisering krävs):
POST /api/reviews (med recension i body + userId i header)

Uppdatera en recension (autentisering krävs):
PUT /api/reviews/{reviewId}

Ta bort en recension (autentisering krävs):
DELETE /api/reviews/{reviewId}



GET /api/books/zyTCAlFPjgYC/reviews hämtar både bokinfo och recensioner

GET /api/books/zyTCAlFPjgYC hämtar bara bokinfo (utan recensioner)

GET /api/books/search?query=... söker böcker

