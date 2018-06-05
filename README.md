Trabajo Backend Lgbt

La base de datos sql es...

#PREVIO AL USO

Ir al propierties... poner sus datos de sql... (sus pass y usuario, y el nombre de su bd donde almacenen la tabla)

NECESITAN TENER INSTALADO MONGO Y INICIARLO...
	Eso lo hacen con el comando... 
	sudo systemctl start mongodb
NECESITAN TENER LA BD SQL CORRIENDO Y CON LAS TABLAS TAL CUAL LO INDICA LA IMAGEN "BD UTILIZADA"
 Es importante destacar que yo utilizo emma para crear toda la parafernalia, no es demoroso, creen su bd en emma si quieren


SALUDOS, SE DESPIDE SU SCRUM MASTER Y FIEL DESARROLLADOR BACKEND
					Fabian Lobos Bustos <3

# Referente a las rutas...
### TODAS ESTAN EN LA CARPETA REST DE LA APLICACION.... CUALQUIER DUDA AL INBOX MIS PERONSILLOS AMIGOS

### http://localhost:8082/lgbTweet/stadistics/transgenero
	Entrega todas las stadistics que tengan por name transgenero, las mas actuales son las primeras
### http://localhost:8082/lgbTweet/stadistics/gay
	Entrega todas las stadistics que tengan por name gay, las mas actuales son las primeras
### http://localhost:8082/lgbTweet/stadistics/lesbiana
	Entrega todas las stadistics que tengan por name lesbiana, las mas actuales son las primeras
### http://localhost:8082/lgbTweet/stadistics/lgbt
	Entrega todas las stadistics que tengan por name lgbt, las mas actuales son las primeras
### http://localhost:8082/lgbTweet/stadistics 
	Entrega todas las stadistics que existen en la BD, las mas actuales son las primeras

### http://localhost:8082/lgbTweet/twitterUser
	Entrega todos los usuarios que han emitido al menos 1 tweet referente a la tematica
	LGBT, ordenados por su relevancia, de mayor a menor (los de mayor relevancia al principio...)



