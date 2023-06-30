CREATE TABLE `movie`
(
    id           int              NOT NULL AUTO_INCREMENT primary key,
    name         varchar(50)      NOT NULL,
    type         varchar(50)      NOT NULL,
    technology   varchar(50)      NOT NULL,
    rating       DOUBLE PRECISION NOT NULL,
    theater_id   int              NOT NULL,
    timetable_id int              NOT NULL
);