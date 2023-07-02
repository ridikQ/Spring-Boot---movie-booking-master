CREATE TABLE `timetable`
(
    id         int  NOT NULL AUTO_INCREMENT primary key,
    start_date date NOT NULL,
    end_date   date NOT NULL,
    start_time time NOT NULL,
    end_time   time NOT NULL

);