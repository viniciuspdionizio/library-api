INSERT INTO app_user (name, email, phone_number)
VALUES ('Vinicius Dionizio', 'vinicius@example.com', '1234567890'),
       ('Maria Silva', 'maria@example.com', '0987654321'),
       ('João Santos', 'joao@example.com', '1122334455'),
       ('Ana Oliveira', 'ana@example.com', '2233445566'),
       ('Pedro Costa', 'pedro@example.com', '3344556677'),
       ('Lucas Almeida', 'lucas@example.com', '4455667788'),
       ('Fernanda Pereira', 'fernanda@example.com', '5566778899'),
       ('Camila Souza', 'camila@example.com', '6677889900'),
       ('Diego Lima', 'diego@example.com', '7788990011'),
       ('Juliana Rocha', 'juliana@example.com', '8899001122');



INSERT INTO book (title, author, isbn, published_date, category)
VALUES ('O Alquimista', 'Paulo Coelho', '9788577023202', '1988-05-01', 'FICTION'),
       ('Sapiens: Uma Breve História da Humanidade', 'Yuval Noah Harari', '9788535925587', '2014-08-01', 'NON_FICTION'),
       ('A Teoria de Tudo', 'Stephen Hawking', '9788573023719', '2002-01-01', 'SCIENCE'),
       ('O Senhor dos Anéis', 'J.R.R. Tolkien', '9788532522892', '1954-07-29', 'FANTASY'),
       ('Steve Jobs', 'Walter Isaacson', '9788551000320', '2011-10-24', 'BIOGRAPHY'),
       ('O Silêncio dos Inocentes', 'Thomas Harris', '9788501087094', '1988-01-01', 'MYSTERY'),
       ('A História do Mundo', 'H.G. Wells', '9788535933254', '1922-01-01', 'HISTORY'),
       ('Dom Casmurro', 'Machado de Assis', '9788525050963', '1899-01-01', 'ROMANCE'),
       ('A Garota no Trem', 'Paula Hawkins', '9788542211120', '2015-01-13', 'THRILLER'),
       ('A Turma da Mônica', 'Mauricio de Sousa', '9786555063009', '2004-01-01', 'CHILDREN');



INSERT INTO book (title, author, isbn, published_date, category)
VALUES ('1984', 'George Orwell', '9780451524935', '1949-06-08', 'FICTION'),
       ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', '9788579900701', '1943-04-06', 'FICTION'),
       ('O Andar do Bêbado', 'Leonard Mlodinow', '9788535938266', '2009-01-01', 'NON_FICTION'),
       ('Freakonomics', 'Steven D. Levitt', '9780060731335', '2005-04-12', 'NON_FICTION'),
       ('Uma Breve História do Tempo', 'Stephen Hawking', '9788535938259', '1988-01-01', 'SCIENCE'),
       ('Cosmos', 'Carl Sagan', '9788535922180', '1980-01-01', 'SCIENCE'),
       ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', '9788545060140', '1997-06-26', 'FANTASY'),
       ('O Nome do Vento', 'Patrick Rothfuss', '9788580577347', '2007-03-27', 'FANTASY'),
       ('A Autobiografia de Malcolm X', 'Malcolm X', '9788535922203', '1965-01-01', 'BIOGRAPHY'),
       ('A Menina que Roubava Livros', 'Markus Zusak', '9788573021975', '2005-03-01', 'FICTION'),
       ('O Código Da Vinci', 'Dan Brown', '9788595081820', '2003-03-18', 'MYSTERY'),
       ('O Casamento', 'Nelson Motta', '9788580576791', '2009-01-01', 'ROMANCE'),
       ('O Lobo de Wall Street', 'Jordan Belfort', '9788542212578', '2008-11-01', 'NON_FICTION'),
       ('O Livro dos Espíritos', 'Allan Kardec', '9788576740832', '1857-01-01', 'NON_FICTION'),
       ('Sherlock Holmes: Um Estudo em Vermelho', 'Arthur Conan Doyle', '9788575030773', '1887-01-01', 'MYSTERY'),
       ('A Revolução dos Bichos', 'George Orwell', '9788535938259', '1945-08-17', 'FICTION'),
       ('O Senhor das Moscas', 'William Golding', '9788573021883', '1954-09-17', 'FICTION'),
       ('A História da Arte', 'Ernst Gombrich', '9788535933315', '1950-01-01', 'NON_FICTION'),
       ('A Vida do Livreiro A.J. Fikry', 'Gabrielle Zevin', '9788595088928', '2014-01-01', 'FICTION'),
       ('O Príncipe', 'Nicolau Maquiavel', '9788535911154', '1532-01-01', 'NON_FICTION'),
       ('A Cor Púrpura', 'Alice Walker', '9788535922265', '1982-06-01', 'FICTION');


-- Inserindo 30 empréstimos na tabela loan
INSERT INTO loan (user_id, book_id, loan_date, return_date, status)
VALUES (1, 1, '2024-09-15', '2024-09-30', FALSE), -- Usuário 1 devolveu o Livro 1
       (2, 2, '2024-09-20', '2024-10-05', FALSE), -- Usuário 2 devolveu o Livro 2
       (1, 1, '2024-10-01', '2024-10-15', TRUE),  -- Usuário 1, Livro 1
       (2, 2, '2024-10-05', '2024-10-20', TRUE),  -- Usuário 2, Livro 2
       (3, 3, '2024-10-07', '2024-10-21', TRUE),  -- Usuário 3, Livro 3
       (1, 4, '2024-10-10', '2024-10-24', TRUE),  -- Usuário 1, Livro 4
       (2, 5, '2024-10-12', '2024-10-26', TRUE),  -- Usuário 2, Livro 5
       (3, 6, '2024-10-15', '2024-10-29', TRUE),  -- Usuário 3, Livro 6
       (1, 7, '2024-10-17', '2024-10-31', TRUE),  -- Usuário 1, Livro 7
       (2, 8, '2024-10-20', '2024-10-25', TRUE),  -- Usuário 2, Livro 8
       (3, 9, '2024-10-22', '2024-10-24', TRUE),  -- Usuário 3, Livro 9
       (1, 10, '2024-10-24', '2024-10-25', TRUE), -- Usuário 1, Livro 10
       (2, 11, '2024-10-26', '2024-10-30', TRUE), -- Usuário 2, Livro 11
       (3, 12, '2024-10-25', '2024-10-30', TRUE), -- Usuário 3, Livro 12
       (1, 13, '2024-10-15', '2024-10-20', TRUE), -- Usuário 1, Livro 13
       (2, 14, '2024-10-26', '2024-10-28', TRUE), -- Usuário 2, Livro 14
       (3, 15, '2024-10-24', '2024-10-29', TRUE), -- Usuário 3, Livro 15
       (1, 16, '2024-10-20', '2024-10-25', TRUE), -- Usuário 1, Livro 16
       (2, 17, '2024-10-15', '2024-10-20', TRUE), -- Usuário 2, Livro 17
       (3, 18, '2024-10-22', '2024-10-25', TRUE), -- Usuário 3, Livro 18
       (1, 19, '2024-10-01', '2024-10-10', TRUE), -- Usuário 1, Livro 19
       (2, 20, '2024-10-05', '2024-10-15', TRUE), -- Usuário 2, Livro 20
       (3, 21, '2024-10-12', '2024-10-18', TRUE), -- Usuário 3, Livro 21
       (1, 22, '2024-10-20', '2024-10-25', TRUE), -- Usuário 1, Livro 22
       (2, 23, '2024-10-15', '2024-10-20', TRUE), -- Usuário 2, Livro 23
       (3, 24, '2024-10-12', '2024-10-18', TRUE), -- Usuário 3, Livro 24
       (1, 25, '2024-10-15', '2024-10-20', TRUE), -- Usuário 1, Livro 25
       (2, 26, '2024-10-20', '2024-10-25', TRUE), -- Usuário 2, Livro 26
       (3, 27, '2024-10-25', '2024-10-30', TRUE), -- Usuário 3, Livro 27
       (1, 28, '2024-10-20', '2024-10-30', TRUE), -- Usuário 1, Livro 28
       (2, 29, '2024-10-22', '2024-10-29', TRUE), -- Usuário 2, Livro 29
       (3, 30, '2024-10-26', '2024-10-30', TRUE);
-- Usuário 3, Livro 30

-- Inserindo 20 empréstimos na tabela loan com status FALSE
INSERT INTO loan (user_id, book_id, loan_date, return_date, status)
VALUES (1, 1, '2024-09-15', '2024-09-30', FALSE),  -- Usuário 1 devolveu o Livro 1
       (2, 2, '2024-09-20', '2024-10-05', FALSE),  -- Usuário 2 devolveu o Livro 2
       (1, 3, '2024-09-25', '2024-10-10', FALSE),  -- Usuário 1 devolveu o Livro 3
       (2, 4, '2024-10-01', '2024-10-15', FALSE),  -- Usuário 2 devolveu o Livro 4
       (3, 5, '2024-10-02', '2024-10-16', FALSE),  -- Usuário 3 devolveu o Livro 5
       (1, 6, '2024-10-05', '2024-10-20', FALSE),  -- Usuário 1 devolveu o Livro 6
       (2, 7, '2024-10-10', '2024-10-24', FALSE),  -- Usuário 2 devolveu o Livro 7
       (3, 8, '2024-10-12', '2024-10-26', FALSE),  -- Usuário 3 devolveu o Livro 8
       (1, 9, '2024-10-15', '2024-10-30', FALSE),  -- Usuário 1 devolveu o Livro 9
       (2, 10, '2024-10-17', '2024-11-01', FALSE), -- Usuário 2 devolveu o Livro 10
       (3, 11, '2024-10-20', '2024-11-04', FALSE), -- Usuário 3 devolveu o Livro 11
       (1, 12, '2024-10-22', '2024-11-06', FALSE), -- Usuário 1 devolveu o Livro 12
       (2, 13, '2024-10-24', '2024-11-08', FALSE), -- Usuário 2 devolveu o Livro 13
       (3, 14, '2024-10-25', '2024-11-09', FALSE), -- Usuário 3 devolveu o Livro 14
       (1, 15, '2024-10-26', '2024-11-10', FALSE), -- Usuário 1 devolveu o Livro 15
       (2, 16, '2024-10-25', '2024-11-09', FALSE), -- Usuário 2 devolveu o Livro 16
       (3, 17, '2024-10-22', '2024-11-06', FALSE), -- Usuário 3 devolveu o Livro 17
       (1, 18, '2024-10-19', '2024-11-03', FALSE), -- Usuário 1 devolveu o Livro 18
       (2, 19, '2024-10-15', '2024-10-30', FALSE), -- Usuário 2 devolveu o Livro 19
       (3, 20, '2024-10-16', '2024-10-31', FALSE), -- Usuário 3 devolveu o Livro 20
       -- Usuário 4
       (4, 6, '2024-10-16', '2024-10-31', FALSE), -- Usuário 3 devolveu o Livro 20
       (4, 21, '2024-10-20', '2024-10-31', FALSE), -- Usuário 3 devolveu o Livro 20
       (4, 25, '2024-10-25', '2024-10-31', FALSE); -- Usuário 3 devolveu o Livro 20
