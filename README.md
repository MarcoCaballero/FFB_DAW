
![Nuestro Logo](https://github.com/MarcoCaballero/FFB_DAW/blob/master/ffb_daw_fase3/src/main/resources/static/user/img/LogoFFB.png)

# FFBet (:mortar_board: University Project)
#### URJC ETSII  .

##### TASKS



+ FFBet is a web application development project that simulates a sports betting website (and e-games) with simulated money and different levels of security for administrators, users and visitors. The different functionalities of FFBet can be divided into three distinct blocks:

    - ~~ Visitors ~~ will be able to:
        * Visualize the results of the finished matches, the next matches (with their respective odds) in order to plan future bets with their corresponding winnings.
        * Visualize a section with the promotions and gifts available. 
        * Sign up section, obtaining at that moment the permissions of the section ~~ user ~~.
        * Visualize videos and images available in the web.
    
    - ~~ Users ~~ will be able to:
        * To have their own individual section where they can edit their data (including upload pictures), review their betting history and redeem them.
        * A purse with simulated and promotional funds (subject to the specific conditions of the service), in this wallet can be included funds from simulated cards and withdraw them to the previously used.
        * Apostar a los diferentes partidos con sus cuotas disponibles, seleccionar la cantidad de la apuesta y el origen de los fondos y aplicar las posibles promociones.
        * Apply and apply discount promotions / promotional funds.
        * They inherit all the functions of ~~ Visitors ~~ .

    - ~~Admins~~ : .
        * Creation, updating, reading and deletion of matches.
        * Creation, updating, reading and deletion of teams.
        * Creation, updating, reading and deletion of users (including user role upgrade/downgrade).
        * Creation, updating, reading and deletion of promotions.
        * Visualize the WebSite Statistics.
        
        > Includes Spring security, cookies managment, CSRF (Cross-site request forgery) prevention.
        
    
    
- [x] Traditional Interface :+1:
- [x] Traditional backend :+1:
- [x] API REST  :+1:
- [ ] SPA Interface (Angular) :exclamation:


#### Descripción: 

+ Project name: FFBet.com ![Nuestro Logo](https://github.com/MarcoCaballero/FFB_DAW/blob/develop/ffb_daw_fase3/src/main/resources/static/user/img/LogoFFBalpha_sin_com.png)

+ Content:
    - Traditional interface
    > HTML5, CSS3, Bootstrap, jQuery, JavaScript.
    
    - Traditional backend
    > Spring MVC, Spring data JPA, Spring Security, Mustache, Maven (Double release MySql & H2 database).
    
    -API REST
    >SpringBoot Spring MVC, Json, Spring security 
    
    -SPA Client :octocat: We are working on...
    >Angular, Bootstrap, TypeScript, RxJS
     
    
#### FBBET PAGES:

+ HOME: 




+ INDEX: página principal de nuestra aplicación, contiene anuncios, barra de menú, footer y enlaces a promociones que ofrece la casa de apuestas. Si está registrado un usuario puede acceder desde esta y cualquiera de las páginas a su cuenta en la zona superior derecha donde a simple vista encontrará su imagen de perfil, su nombre de usuario y su saldo actual. Así como un botón para el ingreso de dinero para sus apuestas.


+ APUESTAS DEPORTIVAS: dentro de esta página contamos con un espacio publicitario, barra de menú, footer, apuestas disponibles de los diferentes eventos deportivos (no e-games), boleto de apuestas y porcentajes y estadísticas sobre las apuestas realizadas por los usuarios de la aplicación.


+ APUESTAS E-SPORTS: contamos con el footer y la barra de menú como en el resto de páginas, en esta página encontramos la novedad de un video en la zona superior (header) y apuestas únicamente sobre e-Sports. Estas son las diferencias con el apartado de apuestas  deportivas, ya que el resto de elementos son similares (boleto de apuestas, porcentajes...).

+ OFERTAS Y PROMOCIONES: página divida en tres secciones en las que podemos encontrar promociones de la casa de apuestas FFBet. Las secciones son bonos gratuitos, promociones de fútbol y otros deportes. Las promociones se definen como una imagen promocional, descripción de la promoción y código promocional que se generará para que el usuario pueda aplicarlo a su boleto.


+ MI CUENTA: esta página ofrece diferentes posibilidades: editar los datos personales del usuario, consultar su monedero virtual, comprobar el estado de sus apuestas y elegir un equipo o informarse acerca de datos sobre el equipo elegido como próximos partidos y la clasificación actualizada.
    - MIS DATOS: apartado en el que el usuario puede conultar los datos de su cuenta dispuestos en un formulario(nombre, apellidos, telefono...). Tiene la opción de editarlos con el botón "Editar".
    - MI HISTORIAL: apartado en el que se muetran todas las apuestas realizadas por el usuario, tanto las apuestas realizadas y ya finalizadas como las apuestas realizadas y que no han tenido lugar todavía, así como el tipo de apuesta y el saldo introducido.
    - MONEDERO: apartado en el que el usuario puede ver su saldo disponible para ser retirado como el saldo en juego actual. En este apartado, el usuario tiene la opción de introducir dinero con el botón "Introducir €" y la opción de retirarlo con "Retirar €"
    - MI EQUIPO: apartado en el que el usuario puede conultar información actual de su equipo favorito, como últimos partidos o clasificación actual de su liga. Desde aquí, tambien podrá elegir equipo favorito con el botón "Elegir Equipo".

 
+ REGISTRATE: apartado de registro de FFBet. Se compone de un formulario de registro en el que el usuario deberá introducir algunos de sus datos personales imprescindibles para completar el registro. Cuando un usuario se registra está aceptando la política interna de FFBet, la cuál puede consultar en un enlace al final del registro.

+ LOGIN/RECUPERACIÓN DE CONTRASEÑA: página para acceder con un usuario a FFBet. Compuesta por un formulario donde el usuario debe introducir correo electrónico y contraseña para acceder a su cuenta. Si el usuario no recuerda su contraseña, posee la opción: Olvidaste contraseña

+ DEPÓSITAR SALDO: página para depositar saldo en una determinada cuenta. En ella nos encontramos un formulario en el que el usuario introducirá distintos tipos de datos para introducir saldo en su cuenta(nombre, nº tarjeta, forma de pago...).

+ RETIRAR SALDO: página para retirar saldo de una cuenta. Compuesta por un formulario en el que el usuario introduce su nombre, el importe a retirar y el número de la tarjeta donde quiere retirarse el saldo.




#### Estructura del Admin:


+ HOME: página principal del cPanel donde podemos acceder a cada uno de los apartados tanto desde la barra de menú como en la parte interna de la página. Todas las páginas pueden acceder a la barra de menú, por lo que esta página carece de importancia fundamental.


+ EQUIPOS: página creada con la función de administrar y gestionar (añadir o editar) los equipos. Los equipos pueden ser de deportes originales o de e-Sports, en ambos se ofrecen campos para rellenar con información como nombre, lugar, trofeos, integrantes, redes sociales...


+ PARTIDOS: espacio creado para la adición de cualquier tipo de evento (tanto deportes como e-sports). Por cada partido se solicitará la siguiente información: equipo local y visitante, fecha del encuentro y cuotas.


+ RESULTADOS: esta página se diseñó con la idea de añadir los resultados correspondientes a los partidos subidos en FFBet una vez hayan finalizado. Para realizar esta gestión se pide elegir el partido en cuestión dentro de la lista de partidos disponibles y anotar el resultado final del encuentro.


+ PROMOCIONES: dentro de esta sección se pueden modificar las promociones que aparecen dentro de la app web FFBet. Para ello se elige la sección donde se encuentra la promoción que desea editar y el lado concreto (posición) donde aparece en la página. A partir de estos pasos se añade la información necesaria para completar una promoción (imagen promocional, título y descripción).

+ LOGIN: página para acceder con un usuario a FFBetAdmin. Compuesta por un formulario donde el usuario debe introducir correo electrónico y contraseña para acceder a su cuenta. Si el usuario no recuerda su contraseña, posee la opción: Olvidaste contraseña.

#### Enlace a la documentación de la API
![API-documentation](https://github.com/MarcoCaballero/FFB_DAW/blob/develop/API.md)

### Diagramas de la aplicación:
#### Diagrama de navegación:
![Diagrama de navegacion](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagrama%20de%20navegaci%C3%B3n/Diagrama%20de%20Navegacion.png).
#### Diagramas de clases:

+ Domain:
![Dominio](https://raw.githubusercontent.com/MarcoCaballero/FFB_DAW/develop/Material%20adicional/Diagramas%20de%20clases/DomainDiagram.png).
+ Ajax Controller:
![AjaxControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/AjaxControllerDiagram.png).
+ Bets Controllers:
![BetsControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/BetsControllerDiagram.png).
+Homes Controllers:
![HomeControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/HomeControllerDiagram.png).
+Login, Logout, and main functions:
![LoginControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/LoginControllerDiagram.jpg).
+Match Controller:
![MatchControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/MatchControllerDiagram.jpg).
+Promotions Controller:
![PromotionControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/PromotionControllerDiagram.jpg).
+Teams Controllers:
![TeamControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/TeamControllerDiagram.jpg).
+Users Account Controller:
![UserAccountControllerDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/UserAccountControllerDiagram.png).
+Security :
![SecurityDiagram](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagramas%20de%20clases/SecurityDiagram.png).

#### Diagrama de Base de Datos:
![modelDB](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Diagrama%20de%20base%20de%20datos/modelDB.png).

#### Pantallazos de la página web:
![LOGIN](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/LOGIN.png).

#### Parte del administrador:
![ADMIN-HOME](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/ADMIN%20-%20HOME.jpg).
![ADMIN-MATCHES](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/ADMIN%20-%20MATCHES.jpg).
![ADMIN-PROMOTIONS](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/ADMIN%20-%20PROMOTIONS.jpg).
![ADMIN-SCORES](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/ADMIN%20-%20SCORES.jpg).
![ADMIN-TEAMS](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/ADMIN%20-%20TEAMS.jpg).

#### Parte pública de la web:
![HOME-PUBLIC](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/HOME%20-%20PUBLIC.png).
![SPORTSBET-PUBLIC](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/SPORTS%20BET%20-%20PUBLIC.png).
![EGAMESBET-PUBLIC](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/EGAMES%20BET-%20PUBLIC.png).
![REG](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/REG.png).

#### Parte privada (sólo para usuarios registrados):
![HOME-PRIVATE](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/HOME-%20PRIVATE.png).
![SPORTSBET-PRIVATE](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/SPORTS%20BET%20-%20PRIVATE.png).
![EGAMESBET-PRIVATE](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/EGAMES%20BET%20-%20PRIVATE.png).
![PROMOTIONS](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/PROMOTIONS.png).
![USERACCOUNT-DATA](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/USER%20ACCOUNT%20-%20DATA.png).
![USERACCOUNT-MONEY](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/USER%20ACCOUNT%20-%20MONEY.png).
![USERACCOUNT-BETS](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/USER%20ACCOUNT%20-%20BETS.png).
![USERACCOUNT-TEAMS](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/USER%20ACCOUNT%20-%20MY%20TEAMS.png).
![PAYIN](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/PAY%20IN.png).
![WITHDRAWAL](https://github.com/MarcoCaballero/FFB_DAW/blob/master/Material%20adicional/Pantallazos/WITHDRAWAL.png).

    
#### Tecnologias usadas para el desarrollo de la aplicación:
    
>Html5

>Bootstrap.

>Css3.

>JavaScript.

>SpringBoot y mySQL.

>TypeScript.

>Implemetación API REST.

>Implementación SPA con Angular2 y empaquetado Docker.
       

Desarrolladores:
@AdrianPedriza
@peloxoo
@MarcoCaballero

