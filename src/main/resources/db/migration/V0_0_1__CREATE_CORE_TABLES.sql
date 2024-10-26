CREATE TABLE app_user
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
    title          VARCHAR(50) NOT NULL,
    author         VARCHAR(50) NOT NULL,
    isbn           VARCHAR(30) NOT NULL,
    published_date DATE        NOT NULL,
    category       VARCHAR(20) NOT NULL,

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
    CONSTRAINT fk_loan_user FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES book (book_id) ON DELETE CASCADE,
    CONSTRAINT chk_loan_loan_date CHECK ( loan_date <= CURRENT_DATE )
--     CONSTRAINT chk_loan_return_date CHECK (return_date >= loan_date)  -- TODO
);

CREATE FUNCTION fn_check_active_loan()
    RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se já existe um empréstimo ativo para o mesmo `book_id`
    IF
(
SELECT EXISTS (SELECT TRUE FROM loan WHERE book_id = NEW.book_id AND status = TRUE))
    THEN RAISE EXCEPTION 'Um livro só pode ter um empréstimo ativo por vez.';
END IF;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trg_check_active_loan
    BEFORE INSERT OR
UPDATE ON loan
    FOR EACH ROW
    WHEN (NEW.status = TRUE) -- Verifica apenas quando o empréstimo está sendo marcado como ativo
    EXECUTE FUNCTION fn_check_active_loan();
