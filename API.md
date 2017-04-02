# FFB_DAW
### Friends&amp;Fun&amp;Bet ----- DAW WEB PROJECT --- URJC ETSII

![Nuestro Logo](https://github.com/MarcoCaballero/FFB_DAW/blob/master/ffb_daw_fase3/src/main/resources/static/user/img/LogoFFB.png)


+ A continuacion mostramos la informacion correspondiente a la API REST encargada de la gestion de la informacion de FriendsFun&Bet
#### CODIGOS DE ESTADO DE LA API:
Codigos de estado Http correspondientes a las peticiones REST de la API de FriendsFun&Bet.
+ GET
    - Si se produce con éxito:
        + 200 OK.
    - Si no se encuentra el recurso:
        + 404 Not Found.
+ DELETE
    - Si se produce con éxito:
        + 200 OK.
    - Si no se encuentra el recurso:
        + 404 Not Found.
+ POST
    - Se ha creado el nuevo recurso:
        + 201 Created.
+ PUT
    - Si se produce con éxito:
        + 200 OK.
    - Si no se encuentra el recurso:
        + 404 Not Found.
+ PATCH
    - Si se produce con éxito:
        + 200 OK.
    - Si no se encuentra el recurso:
        + 404 Not Found.
+ GET(Login)
    - Si se produce con éxito:
        + 200 OK.
    - Si no tiene permisos:
        + 401 Unauthorized.
+ PUT(Ingresar dinero)
    - Si se produce con éxito:
        + 200 OK.
    - Si no tiene permisos:
        + 406 Not_Acceptable.
#### INFORMACION GESTIONADA POR LA API: 


+ Partidos
    - Se podrá consultar todos los partidos disponibles o tambien se podran filtrar por el tipo de partido(Sports ó Egames). También podrán ser elegidos los partidos según su id.
    + URL
        - **/api-matches** (sin filtrado), **/api-matches/egames** (partidos de egames), **/api-matches/sports** (partidos de deporte).
        - Ejemplo del formato de salida:
        [
            {
                "id": 36,
                "date": "2017-05-05",
                "time": "22:30:00",
                "type": "LOL",
                "homeTeam": "SKT T1",
                "visitingTeam": "ROX",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaHomeFirstBlood": 10,
                "quotaVisitingFirstBlood": 10,
                "winnerTeam": null,
                "firstBloodTeam": null,
                "winHome": false,
                "firstBloodHome": false,
                "winVisiting": false,
                "firstBloodVisiting": false,
                "finished": false
            }
        ]
            
    - Se podrá modificar un determinado partido partiendo de su id dependiendo del tipo.
    + URL
        - **/api-matches/sports/{id}** (modificacion de un partido de deporte), **/api-matches/egames/{id}** (modificacion de un partido de egames).
        - Ejemplo del formato de entrada:
        [
            {
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        ]
        - Ejemplo del formato de salida:
        [
            {
                "id": 20,
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        ]
    - Se podrá introducir un nuevo partido dependiendo del tipo.
    + URL
        - **/api-matches/sports** (introducción de un partido de deporte), **/api-matches/egames** (introducción de un partido de egames).
        - Ejemplo del formato de entrada:
        [
            {
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        ]
        - Ejemplo del formato de salida:
        [
            {
                "id": 40,
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        ]
    - Se podrá eliminar un partido mediante su id.
    + URL
        - **/api-matches/{id}** (eliminación de un partido).
        - Ejemplo del formato de salida:
        [
            {
                "id": 20,
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        ]
        
    
    
+ Equipos
    - Se podrá consultar todos los equipos existentes (tanto deportes como egames).
    + URL
        - **/api-teams/** (obtiene todos los equipos), **/api-teams/sports** (obtiene todos los equipos de deportes),  **/api-teams/egames** (obtiene todos los equipos de egames).
        - Ejemplo del formato de salida.
        [
            {
                "id": 10,
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
    - Se podrá eliminar un determinado equipo.
    + URL
        - **/api-teams/{id}** (borra el equipo buscado por el id).
        - Ejemplo del formato de salida.
        [
            {
                "id": 10,
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
    - Se podrá modificar un determinado partido según el tipo y el id pasado en la URL.
    + URL
        - **/api-teams/egames/{id}** (modifica el equipo de egames buscado por el id), **/api-teams/sports/{id}** (modifica el equipo de deporte buscado por el id).
        - Ejemplo del formato de entrada.
        [
            {
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
        - Ejemplo del formato de salida.
        [
            {
                "id": 10,
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
    - Se podrá introducir un nuevo equipo según el tipo.
    + URL 
        - **/api-teams/egames/** (introduce el equipo de egames), **/api-teams/sports/** (introduce el equipo de deporte).
        - Ejemplo del formato de entrada:
        [
            {
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
        - Ejemplo del formato de salida:
        [
            {
                "id": 10,
                "name": "SKT T1",
                "type": "LOL",
                "coach": "KOREA",
                "country": "SEÚL",
                "city": "KKOMA",
                "sponsor": "GRIETA"
            }
        ]
    
+ Apuesta
    - Se podrá eliminar un partido de una apuesta ya realizada.
    + URL
        - **/api/bet/matches/{id}** (elimina un partido de la apuesta).
        - Ejemplo del formato de salida:
        [
            {
                "id": 10,
                "betMatchesList":[
                {
                    "id": 20,
                    "date": "2017-03-20",
                    "time": "22:00:00",
                    "type": "Fútbol",
                    "homeTeam": "Atlético de Madrid",
                    "visitingTeam": "F.C. Barcelona",
                    "quotaHomeVictory": 2,
                    "quotaVisitingVictory": 2,
                    "quotaDraw": 10,
                    "homePoints": 0,
                    "visitingPoints": 0,
                    "finished": true
                }
                ], 
                "betEspMatchesList":[],
                "applied_promo": "gtabof4",
                "potentialGain": "104,5",
                "amount": "20",
                "isFinished": "false",
                "isWinned": "false",
                "isUsed": "false",
                "isLosed": "false"
            }
        ]
    - Se podrá añadir un partido a una apuesta ya creada.
    + URL 
        - **/api/bet/match** (añade un partido a la apuesta).
        - Ejemplo del formato de entrada:
            {
                "id": 22,
                "date": "2017-03-20",
                "time": "22:00:00",
                "type": "Fútbol",
                "homeTeam": "Atlético de Madrid",
                "visitingTeam": "F.C. Barcelona",
                "quotaHomeVictory": 2,
                "quotaVisitingVictory": 2,
                "quotaDraw": 10,
                "homePoints": 0,
                "visitingPoints": 0,
                "finished": true
            }
        - Ejemplo del formato de salida:
            [
            {
                "id": 10,
                "betMatchesList":[
                {
                    "id": 22,
                    "date": "2017-03-20",
                    "time": "22:00:00",
                    "type": "Fútbol",
                    "homeTeam": "Atlético de Madrid",
                    "visitingTeam": "F.C. Barcelona",
                    "quotaHomeVictory": 2,
                    "quotaVisitingVictory": 2,
                    "quotaDraw": 10,
                    "homePoints": 0,
                    "visitingPoints": 0,
                    "finished": true
                }
                ], 
                "betEspMatchesList":[],
                "applied_promo": "gtabof4",
                "potentialGain": "104,5",
                "amount": "20",
                "isFinished": "false",
                "isWinned": "false",
                "isUsed": "false",
                "isLosed": "false"
            }
            ]
    - Se podrá ver la validez de una apuesta.
    + URL
        - **/api/bet** (devuelve si es ganadora o no lo es la apuesta).
        - Ejemplo del formato de salida:
            12,80
    - Se podra eliminar una apuesta.
    + URL
        - **/api/bet** (borra la apuesta indicada por el id).
        - Ejemplo del formato de salida:
            [
            {
                "id": 10,
                "betMatchesList":[
                {
                    "id": 22,
                    "date": "2017-03-20",
                    "time": "22:00:00",
                    "type": "Fútbol",
                    "homeTeam": "Atlético de Madrid",
                    "visitingTeam": "F.C. Barcelona",
                    "quotaHomeVictory": 2,
                    "quotaVisitingVictory": 2,
                    "quotaDraw": 10,
                    "homePoints": 0,
                    "visitingPoints": 0,
                    "finished": true
                }
                ], 
                "betEspMatchesList":[],
                "applied_promo": "gtabof4",
                "potentialGain": "104,5",
                "amount": "20",
                "isFinished": "false",
                "isWinned": "false",
                "isUsed": "false",
                "isLosed": "false"
            }
            ]
    - Se podra crear una apuesta.
    + URL
        - **/api/bet** (crea la apuesta indicada).
        - Ejemplo del formato de entrada:
            [
            {
                "id": 10,
                "betMatchesList":[
                {
                    "id": 22,
                    "date": "2017-03-20",
                    "time": "22:00:00",
                    "type": "Fútbol",
                    "homeTeam": "Atlético de Madrid",
                    "visitingTeam": "F.C. Barcelona",
                    "quotaHomeVictory": 2,
                    "quotaVisitingVictory": 2,
                    "quotaDraw": 10,
                    "homePoints": 0,
                    "visitingPoints": 0,
                    "finished": true
                }
                ], 
                "betEspMatchesList":[],
                "applied_promo": "gtabof4",
                "potentialGain": "104,5",
                "amount": "20",
                "isFinished": "false",
                "isWinned": "false",
                "isUsed": "false",
                "isLosed": "false"
            }
            ]
        - Ejemplo del formato de salida:
            [
            {
                "id": 10,
                "betMatchesList":[
                {
                    "id": 22,
                    "date": "2017-03-20",
                    "time": "22:00:00",
                    "type": "Fútbol",
                    "homeTeam": "Atlético de Madrid",
                    "visitingTeam": "F.C. Barcelona",
                    "quotaHomeVictory": 2,
                    "quotaVisitingVictory": 2,
                    "quotaDraw": 10,
                    "homePoints": 0,
                    "visitingPoints": 0,
                    "finished": true
                }
                ], 
                "betEspMatchesList":[],
                "applied_promo": "gtabof4",
                "potentialGain": "104,5",
                "amount": "20",
                "isFinished": "false",
                "isWinned": "false",
                "isUsed": "false",
                "isLosed": "false"
            }
            ]
    
+ Usuario
    - Se podrá borrar un usuario mediante su id.
    + URL
        - **/api/user/{id}** (borra un usuario).
        - Ejemplo del formato de salida:
            [
            {
                "id": 10,
                "name": "Alfonso",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[
                {
                    "id": 10,
                    "betMatchesList":[
                    {
                        "id": 20,
                        "date": "2017-03-20",
                        "time": "22:00:00",
                        "type": "Fútbol",
                        "homeTeam": "Atlético de Madrid",
                        "visitingTeam": "F.C. Barcelona",
                        "quotaHomeVictory": 2,
                        "quotaVisitingVictory": 2,
                        "quotaDraw": 10,
                        "homePoints": 0,
                        "visitingPoints": 0,
                        "finished": true
                    }
                    ], 
                    "betEspMatchesList":[],
                    "applied_promo": "gtabof4",
                    "potentialGain": "104,5",
                    "amount": "20",
                    "isFinished": "false",
                    "isWinned": "false",
                    "isUsed": "false",
                    "isLosed": "false"
                }
                ],
                "promos": "GRIETA",
                "usedPromos": "GRIETA",
                "cards":[
                {
                    "id":189,
                    "type": "MasterCard",
                    "name": "Manuel",
                    "cardNumber": "123312598090990000",
                    "expirationMonth": "12",
                    "expirationYear": "2017",
                    "securityCode": "12313112314",
                    "credit": "45"
                }
                ],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
    - Se podrá añadir saldo a un usuario.
    + URL
        - **/api/user/creditCardPlus/{amount}** (introduce una cantidad de dinero al usuario).
        - Ejemplo del formato de entrada:
            {
                    "id":189,
                    "type": "MasterCard",
                    "name": "Pepe",
                    "cardNumber": "123312598090990000",
                    "expirationMonth": "12",
                    "expirationYear": "2017",
                    "securityCode": "12313112314",
                    "credit": "50"
            }
        - Ejemplo del formato de salida:
            {
                    "id":189,
                    "type": "MasterCard",
                    "name": "Pepe",
                    "cardNumber": "123312598090990000",
                    "expirationMonth": "12",
                    "expirationYear": "2017",
                    "securityCode": "12313112314",
                    "credit": "50"
            }
    - Se podrá quitar saldo a un usuario.
    + URL
        - **/api/user/creditCardLess/{amount}** (elimina una cantidad de dinero al usuario).
        - Ejemplo del formato de entrada:
            {
                    "type": "MasterCard",
                    "name": "Pepe",
                    "cardNumber": "123312598090990000",
                    "expirationMonth": "12",
                    "expirationYear": "2017",
                    "securityCode": "12313112314",
                    "credit": "50"
            }
        - Ejemplo del formato de salida:
            {
                    "id":189,
                    "type": "MasterCard",
                    "name": "Pepe",
                    "cardNumber": "123312598090990000",
                    "expirationMonth": "12",
                    "expirationYear": "2017",
                    "securityCode": "12313112314",
                    "credit": "50"
            }
    - Se podrá crear un nuevo usuario.
    + URL 
        - **/api/user** (crea un nuevo usuario).
        - Ejemplo del formato de entrada:
            [
            {
                "name": "Adrian",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[],
                "promos":[],
                "usedPromos":[],
                "cards":[],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
        - Ejemplo del formato de salida:
            [
            {
                "id": 17,
                "name": "Adrian",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[],
                "promos": "GRIETA",
                "usedPromos": "GRIETA",
                "cards":[],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
    - Se podrá editar un usuario ya creado.
    + URL 
        - **/api/user** (edita un usuario).
        - Ejemplo del formato de entrada:
            [
            {
                "name": "Adrian",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[],
                "promos":[
                {
                    "id": 1,
                    "type": "BONODESCUENTO",
                    "title": "Super promo",
                    "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                    "promotionCode": "	hghd55434gfsg",
                    "promotionImage": "	photo_promo1.jpg",
                    "quantity": "23",
                    "shown": "true",
                }
                ],
                "usedPromos":[],
                "cards":[],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
        - Ejemplo del formato de salida:
            [
            {
                "name": "Adrian",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[],
                "promos":[
                {
                    "id": 1,
                    "type": "BONODESCUENTO",
                    "title": "Super promo",
                    "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                    "promotionCode": "	hghd55434gfsg",
                    "promotionImage": "	photo_promo1.jpg",
                    "quantity": "23",
                    "shown": "true",
                }
                ],
                "usedPromos":[],
                "cards":[],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
    - Se podrá editar la imagen de usuario.
    + URL 
        - **/api/user/uploadImage** (sube una nueva imagen).
        
    
+ Promocion
    - Se podrá ver una lista de todas las promociones del momento.
    + URL
        - **/api/promotions/** (visualiza las promociones guardadas).
        - Ejemplo del formato de salida:
            [
            {
                "id": 1,
                "type": "BONODESCUENTO",
                "title": "Super promo",
                "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                "promotionCode": "	hghd55434gfsg",
                "promotionImage": "	photo_promo1.jpg",
                "quantity": "23",
                "shown": "true",
            }
            ]
    - Se podrá eliminar una promocion pasando como valor su id.
    + URL 
        - **/api/promotion/{id}** (elimina una promocion determinada por el id).
        - Ejemplo del formato de salida:
            [
            {
                "id": 1,
                "type": "BONODESCUENTO",
                "title": "Super promo",
                "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                "promotionCode": "	hghd55434gfsg",
                "promotionImage": "	photo_promo1.jpg",
                "quantity": "23",
                "shown": "true",
            }
            ]
    - Se podrá añadir una nueva promocion.
    + URL
        - **/api/promotion/** (crea una nueva promoción).
        - Ejemplo del formato de entrada:
            [
            {
                "id": 1,
                "type": "BONODESCUENTO",
                "title": "Super promo",
                "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                "promotionCode": "	hghd55434gfsg",
                "promotionImage": "	photo_promo1.jpg",
                "quantity": "23",
                "shown": "true",
            }
            ]
        - Ejemplo del formato de salida:
            [
            {
                "id": 1,
                "type": "BONODESCUENTO",
                "title": "Super promo",
                "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                "promotionCode": "	hghd55434gfsg",
                "promotionImage": "	photo_promo1.jpg",
                "quantity": "23",
                "shown": "true",
            }
            ]
    
    
+ Login
    - Se podrá salir de la sesion actual mediante el LogOut.
    + URL
        - **/api/logOut** (finaliza la sesion del usuario actual).
        - Ejemplo del formato de salida:
            true.
    - Se podrá devolver el usuario logeado actualmente.
    + URL
        - **/api/logIn** (devuelve el usuario actual).
        - Ejemplo del formato de salida:
        [
            {
                "name": "Adrian",
                "surname": "Perez",
                "secondSurname": "García",
                "dni": "45678232K",
                "email": "alfonsito@hotmail.com",
                "telephone": "564245214",
                "password": "holaquetal",
                "country": "España",
                "city": "Madrid",
                "location": "Madrid",
                "bet_tickets":[],
                "promos":[
                {
                    "id": 1,
                    "type": "BONODESCUENTO",
                    "title": "Super promo",
                    "description": "¡Te regalamos 5€ en crédito para apostar en FFBet!",
                    "promotionCode": "	hghd55434gfsg",
                    "promotionImage": "	photo_promo1.jpg",
                    "quantity": "23",
                    "shown": "true",
                }
                ],
                "usedPromos":[],
                "cards":[],
                "roles": "ROL_USER",
                "isMen": "true",
                "isPhotoSelected": "false",
                "credit": "18.90",
                "promotionCredit": "20.0",
            }
            ]
        
    
    

       

Desarrolladores:
@AdrianPedriza
@peloxoo
@MarcoCaballero

