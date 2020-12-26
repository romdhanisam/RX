
insert into persons(id, first_name, last_name, age, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values (1, 'Samir', 'Romdhani', 26, '2020-12-23', '2020-12-23', null);
insert into persons(id, first_name, last_name, age, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values (2, 'Houssem', 'Romdhani', 28, '2020-12-23', '2020-12-23', null);


insert into authors(id, name, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(10, 'Khaty Sierra', '2020-12-26', '2020-12-26', null) ;
insert into authors(id, name, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(20, 'Bert Bates', '2020-12-26', '2020-12-26', null) ;
insert into authors(id, name, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(30, 'Elisabeth Freeman', '2020-12-26', '2020-12-26', null) ;

insert into books(id, title, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(1000, 'OCA Java SE 8 Programmer I Exam Guide', '2020-12-26', '2020-12-26', null) ;
insert into books(id, title, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(2000, 'Head first, Design patterns', '2020-12-26', '2020-12-26', null) ;
insert into books(id, title, CREATION_DATE, UPDATING_DATE, DELETING_DATE) values(3000, 'Head first, Servlets & JSP', '2020-12-26', '2020-12-26', null) ;

insert into books_authors values(1000, 10)
insert into books_authors values(2000, 10)
insert into books_authors values(3000, 10)
insert into books_authors values(1000, 20)
insert into books_authors values(2000, 30)