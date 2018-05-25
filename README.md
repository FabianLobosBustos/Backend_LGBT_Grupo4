Trabajo Backend Lgbt

La base de datos sql es...



CREATE TABLE `stadistic` (
  `id_stadistic` int(11) NOT NULL AUTO_INCREMENT,
  `name_stadistic` varchar(50) NOT NULL,
  `positive_stadistic` int(11) NOT NULL DEFAULT '0',
  `negative_stadistic` int(11) NOT NULL DEFAULT '0',
  `contingency_stadistic` int(11) NOT NULL DEFAULT '0',
  `date_stadistic` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_stadistic`)
) ENGINE=InnoDB AUTO_INCREMENT=2490 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci'


Ir al propierties... poner sus datos de sql... (sus pass y usuario, y el nombre de su bd donde almacenen la tabla)

NECESITAN TENER INSTALADO MONGO Y INICIARLO...
	Eso lo hacen con el comando... 
	sudo systemctl start mongodb


SALUDOS, SE DESPIDE SU SCRUM MASTER Y FIEL DESARROLLADOR BACKEND
					Fabian Lobos Bustos <3
