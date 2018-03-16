# OPIS
Aplikacija za rad sa podacima o nabavkama, ugovorima i dobavljacima.
Dugme Pretraga/Unos sluzi kao "toggle" izmedju prikaza (view-ova).
Obezbedjen je referencijalni integritet; ako se obrise Nabavka ili Dobavljac, brisu se svi Ugovori vezani za taj entitet.

# KREIRANJE DATABASE
Potrebno je da bude kreirana schema **ppa**
- url: jdbc:mysql://localhost:3306/ppa
- username: root
- password: root

```SQL
DROP DATABASE IF EXISTS ppa;
CREATE DATABASE ppa DEFAULT CHARACTER SET utf8;

USE ppa;

GRANT ALL ON ppa.* TO 'ppa'@'%' IDENTIFIED BY 'ppa';

FLUSH PRIVILEGES;
```

Importovati kao maven project.


# TO-DO: 
1. Napraviti custom servis koji ce enkapsulirati CRUD preko REST servisa.
