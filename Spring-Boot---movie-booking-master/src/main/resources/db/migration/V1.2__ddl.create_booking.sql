CREATE TABLE `booking`
(
    id          int      NOT NULL AUTO_INCREMENT primary key,
    seat_amount int      NOT NULL,
    date        DATETIME NOT NULL,
    user_id     int      NOT NULL,
    movie_id    int      NOT NULL
);