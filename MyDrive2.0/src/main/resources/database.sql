drop TABLE user_roles;
drop TABLE files;
drop TABLE users;
drop TABLE roles;

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(12) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE files (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id int not null,
  name TEXT NOT NULL,
  body LONGBLOB NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;


INSERT INTO users VALUES (1, 'pyvovarnazar', '$2a$11$8SRRAngNBzenp9FCTlgsxOa3MKK2Ux13EE.P5ozXtplOwSHVWFTV2','pyvovarnazar@gmail.com','095xxxxxxx');
INSERT INTO files VALUES(1,1,'admin.txt',10);

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);