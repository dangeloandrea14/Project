drop database if exists libreriamanoscritti;
create database libreriamanoscritti;
use libreriamanoscritti;

create table Ruolo(
 ID integer auto_increment primary key not null,
 Nome varchar(50) not null
);

create table Permessi(
 ID integer auto_increment primary key not null,
 Nome varchar(50) not null
 );
 
 create table Consente(
 IDRuolo integer not null,
 IDPermessi integer not null,
 constraint RuoloConsente foreign key(IDRuolo) references Ruolo(ID) on update cascade on delete cascade,
 constraint PermessiConsente foreign key(IDPermessi) references Permessi(ID) on update cascade on delete cascade);
 
 create table Utente(
 ID integer auto_increment primary key not null,
 Email varchar(50) unique not null,
 Nome varchar(50) not null,
 Password varchar(20) not null,
 TitoloDiStudio varchar(50),
 Professione varchar(50),
 Ruolo integer not null,
 LivelloTrascrittore integer not null default 0,
 CanDownload boolean not null default false,
 RichiestaTrascrittore boolean not null default false,
constraint UtenteRuolo foreign key(Ruolo) references Ruolo(ID) on update cascade);
 
 create table Manoscritto(
  ID integer auto_increment primary key not null,
  Pubblicato boolean not null default false,
  Anno date,
  Secolo integer not null,
  Titolo varchar(50),
  Genere varchar(50),
  Autore varchar(50));
  
create table Page(
 ID integer auto_increment primary key not null,
 Numero smallint not null,
 Manoscritto integer not null,
 Accettato boolean not null default false,
 Scanpath varchar(100) not null,
 Trascrizione text,
 constraint PageManoscritto foreign key(Manoscritto) references Manoscritto(ID) on update cascade on delete cascade);
 
 create table Assegnazione(
 IDUtente integer not null,
 IDPage integer not null,
 constraint UtenteAssegnazione foreign key(IDUtente) references Utente(ID),
 constraint PageAssegnazione foreign key(IDPage) references Page(ID));
 
 insert into ruolo(nome) values('Utente');
insert into ruolo(nome) values('Uploader');
insert into ruolo(nome) values('RevisoreUpload');
insert into ruolo(nome) values('Trascrittore');
insert into ruolo(nome) values('RevisoreTrascrizioni');
insert into ruolo(nome) values('CapoTrascrittore');
insert into ruolo(nome) values('Amministratore');

insert into permessi(nome) values('Modifica Back-End');
insert into permessi(nome) values('Accetta Trascrizioni');
insert into permessi(nome) values('Accetta Upload');
insert into permessi(nome) values('Visualizza profilo');
insert into permessi(nome) values('Carica Upload');
insert into permessi(nome) values('Carica Trascrizioni');


insert into consente values(1,4);
insert into consente values(2,4);
insert into consente values(3,4);
insert into consente values(4,4);
insert into consente values(5,4);
insert into consente values(6,4);
insert into consente values(7,4);
insert into consente values(7,1);
insert into consente values(7,2);
insert into consente values(7,3);
insert into consente values(7,5);
insert into consente values(7,6);
insert into consente values(2,5);
insert into consente values(4,6);
insert into consente values(3,5);
insert into consente values(3,3);
insert into consente values(5,2);
insert into consente values(5,6);



insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('stef96@gmail.com','utente','pass','nessuno',1);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('frank12@gmail.com','uploader','pass','nessuno',2);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('stas21@gmail.com','revisoreupload','pass','nessuno',3);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('carlo05@gmail.com','trascrittore','pass','nessuno',4);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('andrew21@gmail.com','revisoretrascrizioni','pass','nessuno',5);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('antonio324@gmail.com','capotrascrittore','pass','nessuno',6);
insert into utente(Email,nome,password,Titolodistudio,ruolo) 
values('admin@admin.com','admin','pass','nessuno',7);

insert into Manoscritto(Pubblicato, Anno, Secolo, Titolo, Genere, Autore)
values(2, '1400-7-7', 1500, "Divina Commedia", "Poema", "Dante Alighieri");

insert into Manoscritto(Pubblicato, Anno, Secolo, Titolo, Genere, Autore)
values(2, '567-1-2', 600, "Odissea", "Poema", "Omero");

 