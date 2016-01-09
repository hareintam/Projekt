# Projekt Quiz
Aine "Programmeerimise algkursus Java baasil" projekt
# Kirjeldus
Tegemist on lihtsa küsimustikuga, mis esitab kasutajale küsimuse ja vastusevariandid. 
Küsimused ja vastusevariandid on tekstifailis "Questions.txt". Info on faili kirjutatud kindlal viisil. Ühel real on ühe küsimuse kogu info (eraldatud semikooloniga): esimesel positsioonil on alati küsimus, järgneb õige valikvastuse indeks ja seejärel vastusevariandid. Vastusevariante võib olla erinev arv.
Kasutajaliidese kuvamiseks on kasutatud JavaFX-i. Programmi aknas kuvatakse küsimus, selle vastusevariandid ja "Edasi" nupp. Programm kasutab ühte lava ja stseeni ning kahte erinevat paigutust (VBox ja Stackpane). Stseeni täidab Stackpane, millel paikneb "Edasi" nupp. Stackpane sees on muutuva sisuga Vbox, mille alusel kuvatakse küsimusi, vastusevariante ja kokkuvõte. 
Projektis pole otseselt kasutatud võõrast koodi, ideede saamiseks ja probleemide lahendamiseks kasutasin meie materjale i200-s, õppejõu viidatud Java õpikut ja Google't. 
# Kasutusjuhend
Loe küsimus hoolikalt läbi ja vali õige vastusevariant. Enne vastusevariandi valimist ei saa järgmist küsimust kuvada (kasutaja ei saa küsimustikku lihtsalt läbi klikkida). Küsimuse juures näed (kujul 2/4) mitmes küsimus kuvatakse ja palju küsimusi kokku on. Pärast viimasele küsimusele vastamist kuvatakse kokkuvõte, kus on kirjas skoor ja vajadusel valesti vastatud küsimused. Kokkuvõtte lehel on programmi sulgemise nupp. 
