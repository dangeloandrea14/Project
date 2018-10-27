use libreriamanoscritti;
insert into Ruolo(Nome) values('Utente');
insert into Ruolo(Nome) values('Uploader');
insert into Ruolo(Nome) values('RevisoreUpload');
insert into Ruolo(Nome) values('Trascrittore');
insert into Ruolo(Nome) values('RevisoreTrascrizioni');
insert into Ruolo(Nome) values('CapoTrascrittore');
insert into Ruolo(Nome) values('Amministratore');

insert into Permessi(Nome) values('Modifica Back-End');
insert into Permessi(Nome) values('Accetta Trascrizioni');
insert into Permessi(Nome) values('Accetta Upload');
insert into Permessi(Nome) values('Visualizza profilo');
insert into Permessi(Nome) values('Carica Upload');
insert into Permessi(Nome) values('Carica Trascrizioni');


insert into Consente values(1,4);
insert into Consente values(2,4);
insert into Consente values(3,4);
insert into Consente values(4,4);
insert into Consente values(5,4);
insert into Consente values(6,4);
insert into Consente values(7,4);
insert into Consente values(7,1);
insert into Consente values(7,2);
insert into Consente values(7,3);
insert into Consente values(2,5);
insert into Consente values(4,6);
insert into Consente values(3,5);
insert into Consente values(3,3);
insert into Consente values(5,2);
insert into Consente values(5,6);

insert into Utente(Email,Nome,Password,TitoloDiStudio,Ruolo) 
values('dangeloandrea14@gmail.com','UtenteNormale','passwordnormale','liceo',1);

insert into Utente(Email,Nome,Password,TitoloDiStudio,Ruolo,CanDownload) 
values('utentone@utente.it','Utentechepuòscaricare','pass','Università',1,1);

insert into Utente(Email,Nome,Password,TitoloDiStudio,Ruolo) 
values('admin@admin.com','Admin','passadmin','nessuno',7);

