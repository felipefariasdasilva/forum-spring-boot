INSERT INTO USER(name, email, password) VALUES('Student', 'student@email.com', '123456');

INSERT INTO CURSE(name, category) VALUES('Spring Boot', 'Programming');
INSERT INTO CURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, creation_date, status, author_id, curse_id) VALUES('Duvida', 'Error to create project', '2019-05-05 18:00:00', 'NOT_RESPONSED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, curse_id) VALUES('Duvida2', 'project doesnt compile', '2019-05-05 19:00:00', 'NOT_RESPONSED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, curse_id) VALUES('Duvida3', 'tag html', '2019-05-05 20:00:00', 'NOT_RESPONSED', 1, 2);