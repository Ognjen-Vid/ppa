#OPIS#
Aplikacija za rad sa podacima o nabavkama, ugovorima i dobavljacima.
Dugme Pretraga/Unos sluzi kao "toggle" izmedju prikaza (view-ova).
Obezbedjen je referencijalni integritet; ako se obrise Nabavka ili Dobavljac, brisu se svi Ugovori vezani za taj entitet.

Potrebno je da bude kreirana schema **ppa**
- url: jdbc:mysql://localhost:3306/ppa
- username: root
- password: root

Importovati kao maven project.


#TO-DO:# 
1. Dodati proveru postojanja oznake nabavke, internog broja ugovora i maticnog broja dobavljaca, prilikom unosa novog entiteta.
2. Dodati entitetu Nabavka atribut Otvaranje - datum otvaranja.
