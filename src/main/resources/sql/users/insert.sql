INSERT INTO teste_api.tb_users (name, cpf, email)
VALUES
    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),

    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),

    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),


    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),


    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),

    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),

    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com')),

    (CONCAT('Nome', RAND()),
     LPAD(LEFT(FLOOR(RAND() * 1000000000000), 11), 11, '0'),
     CONCAT('email', RAND(), '@example.com'));
