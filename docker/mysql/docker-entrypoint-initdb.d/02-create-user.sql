CREATE USER IF NOT EXISTS 'inquisitor'@'%' IDENTIFIED BY 'albinehare5382';
GRANT ALL ON iss_tracker_db.* TO 'inquisitor'@'%';
CREATE USER IF NOT EXISTS 'site'@'%' IDENTIFIED BY 'marinemerlin8692';
GRANT ALL ON iss_tracker_db.* TO 'site'@'%';
