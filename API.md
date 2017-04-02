# FFB_DAW
### Friends&amp;Fun&amp;Bet ----- DAW WEB PROJECT --- URJC ETSII

![Nuestro Logo](https://github.com/MarcoCaballero/FFB_DAW/blob/master/ffb_daw_fase3/src/main/resources/static/user/img/LogoFFB.png)


+ A continuacion mostramos la informacion correspondiente a la API REST encargada de la gestion de la informacion de FriendsFun&Bet
#### CODIGOS DE ESTADO DE LA API:

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
    - Se podrá consultar consultar la cantidad de dinero introducido a una apuesta.
    + URL
        - **/api/bet/quota/{id}** (muestra la cantidad apostada).
        - Ejemplo del formato de salida:
            23,43
    - Se podrá eliminar un partido de una apuesta ya realizada.
    + URL
        - **/api/bet/matches/{id}** (elimina un partido de la apuesta).
        - Ejemplo del formato de salida:
        [
            {
                "id": 10,
                "betMatchesList": 
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
    - Disponer un perfil exclusivo con un fondo para depositar dinero y distintas posibilidades de consultar tu historial, ganancias, y otras muchas estadisticas
    
+ Promocion
    - Disponer un perfil exclusivo con un fondo para depositar dinero y distintas posibilidades de consultar tu historial, ganancias, y otras muchas estadisticas
    
+ Login
    - 
    -
    
    

       

Desarrolladores:
@AdrianPedriza
@peloxoo
@MarcoCaballero

