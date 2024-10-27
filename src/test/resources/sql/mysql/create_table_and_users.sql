CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(120),
                                     name VARCHAR(120),
                                     surname VARCHAR(120)
);

INSERT INTO users (id, username, name, surname) VALUES (4, 'TestUser4', 'TestUser4', 'TestUser4');
INSERT INTO users (id, username, name, surname) VALUES (5, 'TestUser5', 'TestUser5', 'TestUser5');
INSERT INTO users (id, username, name, surname) VALUES (6, 'TestUser6', 'TestUser6', 'TestUser6');
