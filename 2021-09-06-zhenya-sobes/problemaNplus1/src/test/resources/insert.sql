-- У одного автора может быть много книг
-- У книг может быть только один автор

-- Начальные данные:
--------------------------
--            /-> Книга 1
-- Aвтор 1 ->
--            \-> Книга 3
--------------------------
-- Aвтор 2 -> Книга 2

insert into author (id, name) values (1, 'автор 1');
insert into author (id, name) values (2, 'автор 2');
insert into book (id, name, author_id) values (1, 'книга 1', 1);
insert into book (id, name, author_id) values (2, 'книга 2', 2);
insert into book (id, name, author_id) values (3, 'книга 3', 1);