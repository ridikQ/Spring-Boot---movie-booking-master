CREATE TABLE `user`
(
    id        int         NOT NULL AUTO_INCREMENT primary key,
    name      varchar(50) NOT NULL,
    surname   varchar(50) NOT NULL,
    birthdate DATETIME     NOT NULL,
    role      int          NOT NULL,
    email     varchar(50) NOT NULL,
    password  varchar(50) NOT NULL,
    telephone varchar(50) NOT NULL

);