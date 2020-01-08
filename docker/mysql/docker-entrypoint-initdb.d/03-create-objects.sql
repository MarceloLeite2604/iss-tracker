USE iss_tracker_db;

CREATE TABLE iss_position
(
	instant TIMESTAMP NOT NULL PRIMARY KEY,
	latitude FLOAT NOT NULL,
	longitude FLOAT NOT NULL,
  speed     FLOAT
) ENGINE=InnoDB;
