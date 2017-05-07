<img src="https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/EstadoRepositorio/status.png">

***

<img src="https://github.com/MarcoCaballero/FFB_DAW/blob/master/SPRING-APP-TRADITIONAL%2BAPI/src/main/resources/static/user/img/LogoFFB.png" width="500">

# FFBet (:mortar_board: University Project) - URJC ETSII  .

+ FFBET is a web application development project that simulates a sports betting website (and e-games) with simulated money and different levels of security for administrators, users and visitors. The different functionalities of FFBet can be divided into three distinct blocks:

    - ` Visitors `  will be able to:
        * Visualize the results of the finished matches, the next matches (with their respective odds) in order to plan future bets with their corresponding winnings.
        * Visualize a section with the promotions and gifts available. 
        * Sign up section, obtaining at that moment the permissions of the section ` user `.
        * Visualize videos and images available in the web.
                  
    
    - ` Users ` will be able to:
        * To have their own individual section where they can edit their data (including upload pictures), review their betting history and redeem them.
        * A purse with simulated and promotional funds (subject to the specific conditions of the service), in this wallet can be included funds from simulated cards and withdraw them to the previously used.
        * Apostar a los diferentes partidos con sus cuotas disponibles, seleccionar la cantidad de la apuesta y el origen de los fondos y aplicar las posibles promociones.
        * Apply and apply discount promotions / promotional funds.
        * Select a sports team to obtain their data, results and future matches.
        * They inherit all the functions of ` Visitors ` .

    - `Admins` : .
        * Creation, updating, reading and deletion of matches.
        * Creation, updating, reading and deletion of teams.
        * Creation, updating, reading and deletion of users (including user role upgrade/downgrade).
        * Creation, updating, reading and deletion of promotions.
        * Visualize the WebSite Statistics.
        
        > Includes Spring security, cookies managment, CSRF (Cross-site request forgery) prevention.
        


#### Descripción: 

+ Project name: FFBet.com  

+ Content:
    - Traditional interface
    
    > HTML5, CSS3, Bootstrap, jQuery, JavaScript.
    
    - Traditional backend
    
    > Spring MVC, Spring data JPA, Spring Security, Mustache, Maven (Double release MySql & H2 database).
    
    - API REST 
        + Enlace a la documentación de la API
        [API-documentation!](https://github.com/MarcoCaballero/FFB_DAW/blob/develop/API.md)
        
    >SpringBoot Spring MVC, Json, Spring security 
    
    - SPA Client :octocat: We are working on...
    
    >Angular, Bootstrap, TypeScript, RxJS
     
##### TASKS

- [x] Traditional Interface :+1:
- [x] Traditional backend :+1:
- [x] API REST  :+1: [API-documentation!](https://github.com/MarcoCaballero/FFB_DAW/blob/develop/API.md)
- [ ] SPA Interface (Angular) :exclamation:   


#### FBBET USER PAGES:

+ `HOME`: Application main page, contains ads, promotional images, results listings and links to other pages.

+ `SPORTS BET`: It is along with `E-GAMES BET` the heart of the application, the place where the bets are made, in this case the sports. Between its different sections are lists of matches and bets with their corresponding quotas.

+ `E-GAMES BET`: It is along with `SPORTS BET` the heart of the application, the place where the bets are made, in this case the e-games. Between its different sections are lists of matches and bets with their corresponding quotas. In addition it is possible to visualize the last official video of League of legends.

+ `PROMOTIONS`: Contains the promotions and gifts, there are two differentiated sections: promotional codes and promotional funds.

+ `MY ACCOUNT`: his page offers different possibilities: edit the personal data of the user, check your virtual wallet, check the status of your bets and choose a team or learn about data on the team chosen as upcoming matches and the updated ranking.
   - `MY DATA`: section in which the user can conúly the data of his account arranged in a form (name, surname, telephone ...). You have the option to edit them with the "Edit" button.
   
   - `MY HISTORY`: section in which all the bets made by the user, both bets made and completed and bets made and have not yet taken place, as well as the type of bet and the balance entered.
   
   - `PURSE`: a section in which the user can see his available balance to be withdrawn as the current game balance. In this section, the user has the option to enter money with the "Enter €" button and the option to withdraw it with "Withdraw €".
   
   - `MY TEAM`: a section where the user can check current information of his favorite team, such as last matches or current league classification. From here, you can also choose your favorite team with the "Choose Team" button.
   
   - `ENTER FUNDS`: page to deposit balance in a certain account. In it we find a form in which the user will enter different types of data to enter balance in your account (name, card number, payment method ...).
   
   - `WITHDRAW FUNDS`: page to withdraw balance from an account. Composed of a form in which the user enters his name, the amount to be withdrawn and the number of the card where he wants to withdraw the balance.
   
 + `FOOTER AND SIDEBAR`: Shared between the user pages, let us navigate to others FFBet pages or safety bet advices.


#### FBBET ADMIN PAGES:

+ `HOME`: página principal del cPanel donde podemos acceder a cada uno de los apartados tanto desde la barra de menú como en la parte interna de la página. Todas las páginas pueden acceder a la barra de menú, por lo que esta página carece de importancia fundamental.


+ `TEAMS`: página creada con la función de administrar y gestionar (añadir o editar) los equipos. Los equipos pueden ser de deportes originales o de e-Sports, en ambos se ofrecen campos para rellenar con información como nombre, lugar, trofeos, integrantes, redes sociales...


+ `MATCHES`: espacio creado para la adición de cualquier tipo de evento (tanto deportes como e-sports). Por cada partido se solicitará la siguiente información: equipo local y visitante, fecha del encuentro y cuotas.


+ `RESULTS`: esta página se diseñó con la idea de añadir los resultados correspondientes a los partidos subidos en FFBet una vez hayan finalizado. Para realizar esta gestión se pide elegir el partido en cuestión dentro de la lista de partidos disponibles y anotar el resultado final del encuentro.


+ `PROMOTIONS`: dentro de esta sección se pueden modificar las promociones que aparecen dentro de la app web FFBet. Para ello se elige la sección donde se encuentra la promoción que desea editar y el lado concreto (posición) donde aparece en la página. A partir de estos pasos se añade la información necesaria para completar una promoción (imagen promocional, título y descripción).

#### SHARED PAGES:
 
+ `SIGN UP`: FFBet sign up section. It is composed of a registration form in which the user must enter some of his personal data essential to complete the registration. When a user registers he is accepting the internal policy of FFBet, which can consult in a link at the end of the registry.

+ `LOGIN`: Page to login with a user to FFBet. Composed of a form where the user must enter email and password to access his account. If the user does not remember his password, he has the option: Forgot password.

+ `POLICY TERMS`: Includes the policy terms.

##### Website image screenshots:
[Go to ... Website image screenshots!](https://github.com/MarcoCaballero/FFB_DAW/tree/develop/Material%20adicional/Pantallazos).



## BACKEND-DIAGRAMS

#### Navigation diagram:
[Go to ... Navigation diagram!](https://github.com/MarcoCaballero/FFB_DAW/tree/develop/Material%20adicional/Diagrama%20de%20navegaci%C3%B3n).

#### Class diagrams:
[Go to ... Class diagrams!](https://raw.githubusercontent.com/MarcoCaballero/FFB_DAW/develop/Material%20adicional/Diagramas%20de%20clases).

#### API diagrams:
[Go to ... Website image screenshots!](https://github.com/MarcoCaballero/FFB_DAW/tree/master/Material%20adicional/Diagramas%20API).


## DATABASE-DRIAGRAM

#### Database diagram:
[Go to ... Database diagram!](https://github.com/MarcoCaballero/FFB_DAW/tree/develop/Material%20adicional/Diagrama%20de%20base%20de%20datos).


## FRONTEND-DIAGRAMS (ANGULAR)

       

Members:

@AdrianPedriza
@peloxoo
@MarcoCaballero

