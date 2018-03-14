INSERT INTO dobavljac (naziv, maticni_broj) VALUES 
("Merck", "100001"),
("PharmaSwiss", "200002");

INSERT INTO vrsta_postupka (naziv) VALUES
("Otvoreni"),
("JNMV");

INSERT INTO vrsta_predmeta (naziv) VALUES
("Dobra"),
("Usluge"),
("Radovi");

INSERT INTO nabavka (datum_otvaranja, oznaka, procenjena_vrednost, vrsta_postupka_id, vrsta_predmeta_id) VALUES
(20180314, "01-18-O", 1000000 , 1, 1),
(20180314, "02-18-O", 1000000 , 1, 1);

INSERT INTO ugovor (interni_broj, ugovorena_vrednost, datum_zakljucenja, dobavljac_id, nabavka_id) VALUES
("3/11", 500000, 20180201, 1, 1),
("3/12", 500000, 20180210, 1, 1),
("3/13", 500000, 20180220, 1, 1),
("3/14", 500000, 20180301, 1, 2),
("3/15", 500000, 20180315, 1, 2),
("3/16", 500000, 20180401, 1, 2),
("3/21", 500000, 20180501, 2, 1),
("3/22", 500000, 20180601, 2, 1),
("3/23", 500000, 20180701, 2, 1),
("3/24", 500000, 20180801, 2, 2),
("3/25", 500000, 20180901, 2, 2),
("3/26", 500000, 20181001, 2, 2);