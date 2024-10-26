CREATE TABLE user
(
    user_id      SERIAL,
    name         VARCHAR(50)  NOT NULL,
    email        VARCHAR(150) NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    phone_number VARCHAR(20)  NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE book
(
    book_id        SERIAL,
    title          VARCHAR(30) NOT NULL,
    author         VARCHAR(50) NOT NULL,
    isbn           VARCHAR(30) NOT NULL,
    published_date DATE        NOT NULL,

    CONSTRAINT pk_book PRIMARY KEY (book_id)
);

CREATE TABLE loan
(
    loan_id     SERIAL,
    user_id     INTEGER NOT NULL,
    book_id     INTEGER NOT NULL,
    loan_date   DATE    NOT NULL,
    return_date DATE    NOT NULL,
    status      BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_loan PRIMARY KEY (loan_id),
    CONSTRAINT fk_loan_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES book (book_id) ON DELETE CASCADE,
    CONSTRAINT chk_loan_loan_date CHECK ( loan_date <= CURRENT_DATE )
);