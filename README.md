# Front LGBTweet

![](static/img/9.png)

## Integrantes (GRUPO 4)
+ LOBOS BUSTOS FABIAN ANDRES (Scrum Master, master back dev)
+ CIFUENTES ZURITA JAVIER ALEJANDRO (master front dev)
+ VÁSQUEZ FIGUEROA JORGE MANUEL (back dev)
+ SARMIENTO MERANI BÁRBARA FRANCISCA (front dev)
+ PIZARRO RIFFO ALBERTO ANDRÉS (front dev)

## Acerca de LGBTweet:
+ Nuestro proyecto nace en el seno de las demandas provenientes de las minorias sexuales.
+ El Objetivo del mismo es por tanto, visibilizar su causa y dar un estado real de las mismas en el pais.

### Correr el proyecto:

+ Clona el proyecto donde mas te acomode.
+ En la terminal ejecuta (solo linux):
~~~ console
$ build gradle
$ gradle bootrun
~~~
+ Es imperativo que tengas corriendo el Schema SQL, recomiendo expresamente crearlo con Emma (ide sql), si quiere saber el formato de la misma comuniquese a fabian.lobos.b@usach.cl 
+ Tambien debes correr mongodb, asi como neo4j (consulta documentacion al respecto)

+ Todo el core del proyecto, recae en un metodo Scheduler (es un buen inicio para entender como funciona el mismo)
+ Si tiene alguna duda respectoa algun formato JSON de neo4j, sientase abierto de comunicarse al correo antes descrito.

### rutas de backend:
+ A traves de las rutas es posible recabar informacion de las bases de datos:
#### rutas stadistic: entregan los stadistics respecto a un tema especifico
+ http://localhost:8082/lgbTweet/stadistics
+ http://localhost:8082/lgbTweet/stadistics/gay
+ http://localhost:8082/lgbTweet/stadistics/lgbt
+ http://localhost:8082/lgbTweet/stadistics/lesbiana
+ http://localhost:8082/lgbTweet/stadistics/transgenero
+ http://localhost:8082/lgbTweet/stadistics/{id} 

#### rutas region: entregan las stadistics asociados a las 15 regiones de chile
+ http://localhost:8082/lgbTweet/regions
Entrega un array de 15 stadistics donde el 1 stadistic corresponde al de la region 1, el segundo a de la 2, etc...

#### rutas Twitter User
+ http://localhost:8082/lgbTweet/twitterUser
Entrega todos los usuarios que han twitteado o retwitteado respecto al tema LGBT, ordenados por su nivel de influencia (calculada por heuristica)

### rutas de neo4j
+ http://localhost:8082/lgbTweet/node/neo4j
Entrega el grafo de influencia de los 10 usuarios mas influyentes, hasta con un 3 grado de relacion

