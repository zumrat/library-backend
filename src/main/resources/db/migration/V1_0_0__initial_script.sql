CREATE TABLE author (
    id              BIGINT          NOT NULL IDENTITY,
    full_name       VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE book (
    id              BIGINT          NOT NULL IDENTITY,
    isbn            VARCHAR(50)     NOT NULL,
    title           VARCHAR(255)    NOT NULL,
    edition         VARCHAR(50)     NULL,
    picture_url     VARCHAR(255)    NULL,
    description     TEXT            NULL,
    book_status     VARCHAR(50)     NOT NULL,
    author_id       BIGINT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_book_author_id FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE reader (
    id              BIGINT          NOT NULL IDENTITY,
    email           VARCHAR(255)    NULL,
    address         VARCHAR(255)    NULL,
    full_name       VARCHAR(255)    NOT NULL,
    phone_number    VARCHAR(50)     NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE check_out (
    id              BIGINT          NOT NULL IDENTITY,
    reserve_date    DATE            NOT NULL,
    due_date        DATE            NOT NULL,
    return_date     DATE            NULL,
    book_id         BIGINT          NOT NULL,
    reader_id       BIGINT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_checkout_book_id FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT fk_checkout_reader_id FOREIGN KEY (reader_id) REFERENCES reader(id)
);

CREATE TABLE genre (
    id              BIGINT          NOT NULL IDENTITY,
    value           VARCHAR(50)     NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE book_genre (
    id              BIGINT          NOT NULL IDENTITY,
    genre_id        BIGINT          NOT NULL,
    book_id         BIGINT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_book_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genre(id),
    CONSTRAINT fk_book_genre_book_id FOREIGN KEY (book_id) REFERENCES book(id)
);
