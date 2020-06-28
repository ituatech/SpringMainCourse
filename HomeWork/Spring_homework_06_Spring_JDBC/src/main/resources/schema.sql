drop table if exists book;
drop table if exists author;
drop table if exists genre;

create table if not exists author
(
    id         int not null primary key,
    firstName  varchar(30) null,
    secondName varchar(30) null
);

create table if not exists genre
(
    id        int not null primary key,
    genreName varchar(30) null
);

create table if not exists book
(
    id          int not null primary key,
    name        varchar(30)  null,
    description varchar(255) null,
    author_id   int          null,
    genre_id    int          null,
    constraint author_fk
        foreign key (author_id) references author (id),
    constraint genre_fk
        foreign key (genre_id) references genre (id)
);