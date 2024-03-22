CREATE TABLE tb_users (
   id UUID PRIMARY KEY NOT NULL,
   username VARCHAR(255) UNIQUE,
   password VARCHAR(255),
   role VARCHAR(255)
);
