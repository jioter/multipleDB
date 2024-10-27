CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(120),
                                     name VARCHAR(120),
                                     surname VARCHAR(120)
);

INSERT INTO users VALUES ('1', 'TestUser1', 'TestUser1', 'TestUser1');
INSERT INTO users VALUES ('2', 'TestUser2', 'TestUser2', 'TestUser2');
INSERT INTO users VALUES ('3', 'TestUser3', 'TestUser3', 'TestUser3');
