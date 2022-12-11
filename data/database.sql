CREATE TYPE user_role AS ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS card
(
    id         SERIAL,
    number     VARCHAR(50) UNIQUE NOT NULL,
    balance    DECIMAL DEFAULT NULL,
    created_at DATE    DEFAULT CURRENT_DATE,
    is_deleted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    name       VARCHAR(50)        NOT NULL,
    lastname   VARCHAR(50) DEFAULT NULL,
    phone      VARCHAR(50)        NOT NULL,
    role       user_role   DEFAULT 'USER',
    card_id    INT         DEFAULT NULL,
    is_loyal   BOOLEAN     DEFAULT FALSE,
    created_at DATE        DEFAULT CURRENT_DATE,
    is_deleted BOOLEAN     DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES card (id)
);

INSERT INTO users(email, password, name, lastname, phone, role)
VALUES ('admin@admin.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'admin',
        'admin', '+998901234567', CAST('ADMIN' AS user_role));

CREATE TYPE tour_type AS ENUM ('REST', 'EXCURSION', 'SHOPPING');

CREATE TABLE tours
(
    id          SERIAL,
    name        VARCHAR(50) UNIQUE NOT NULL,
    type        tour_type          NOT NULL,
    description VARCHAR(1000)      NOT NULL,
    discount    INT     DEFAULT NULL,
    date        TIMESTAMP          NOT NULL,
    price       DECIMAL            NOT NULL,
    image_path  VARCHAR(1000)      NOT NULL,
    created_at  DATE    DEFAULT CURRENT_DATE,
    is_deleted  BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id         SERIAL,
    user_id    INT       DEFAULT NULL,
    tour_id    INT       DEFAULT NULL,
    amount     INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN   DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tours (id)
);
