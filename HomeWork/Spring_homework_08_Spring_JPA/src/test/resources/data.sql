INSERT INTO
    author_table (first_name, second_name)
VALUES('firstName1', 'secondName1'),('firstName2', 'secondName2');

INSERT INTO
    book_table(name, description, author_id)
VALUES('book1', 'description1', 1),('book2', 'description2', 2);

INSERT INTO
    comment_table(comment, book_id)
VALUES('comment11', 1),('comment12', 1),('comment21', 2),('comment22', 2);

INSERT INTO
    genre_table(genre_name)
VALUES('genre1'),('genre2');

INSERT INTO
    book_genre_table(genre_id, book_id)
VALUES(1, 1),(2, 2);