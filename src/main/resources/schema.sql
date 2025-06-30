CREATE TABLE IF NOT EXISTS books (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   title VARCHAR(255),
   author VARCHAR(255),
   price DOUBLE
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_book (
    user_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);