USE iss_tracker_db;

CREATE TABLE iss_positions
(
	instant TIMESTAMP NOT NULL PRIMARY KEY,
	latitude FLOAT NOT NULL,
	longitude FLOAT NOT NULL,
  speed     FLOAT
) ENGINE=InnoDB;

CREATE TABLE route_maps
(
  instant TIMESTAMP NOT NULL PRIMARY KEY,
  data MEDIUMBLOB NOT NULL
) ENGINE=InnoDB;
