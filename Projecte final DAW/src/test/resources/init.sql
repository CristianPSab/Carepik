CREATE TABLE magatzems(codi VARCHAR(100) PRIMARY KEY NOT NULL,
                  denominacio VARCHAR(100) NOT NULL,
                  actiu  BOOLEAN NOT NULL,
                  localitat VARCHAR(50) NOT NULL);

INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('010', 'Bodega bananes', TRUE, 'Badalona');
INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('020', 'Bodega pomes', TRUE, 'Badalona');
INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('030', 'Bodega taronjes', TRUE, 'Badalona');
INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('040', 'Acopio bananes', TRUE, 'Sabadell');
INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('050', 'Acopio pomes', TRUE, 'Sabadell');
INSERT INTO magatzems (codi, denominacio, actiu, localitat) VALUES ('060', 'Acopio taronjes', TRUE, 'Sabadell');

