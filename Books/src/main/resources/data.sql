INSERT INTO author (id, first_name, last_name) VALUES (1, 'Brandon', 'Sanderson');
INSERT INTO author (id, first_name, last_name) VALUES (2, 'Joe', 'Abercrombie');

INSERT INTO book (id, title, release_date, category, author_id) VALUES (1, 'Wisdom of Crowds', STR_TO_DATE('17-11-2021', '%d-%m-%Y'), 1, 2);

INSERT INTO user (id, first_name, last_name, email, password, user_role, enabled) VALUES (1, 'Marko', 'Pasalic', 'marko1rose@gmail.com', 'marko123', 'USER', 1);
