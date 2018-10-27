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